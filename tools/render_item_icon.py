#!/usr/bin/env python3
"""Renderiza el modelo 3D real de la nave como icono del item (textures/item/space_cruiser.png).

Usa los mismos cubos que tools/generate_model.py (con sus rotaciones y grupos), así que el
icono siempre coincide con el modelo. Vista isométrica 3/4 con el morro hacia la cámara.

Uso (desde la raíz del repositorio):
    python3 tools/render_item_icon.py [tamaño_icono=64] [directorio_preview]
"""
import math
import os
import sys

import numpy as np
from PIL import Image

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)
import generate_model as gm  # noqa: E402

ROOT = os.path.dirname(HERE)
OUT = os.path.join(ROOT, "src/main/resources/assets/spacecruiser/textures/item/space_cruiser.png")

AZIMUTH = -38.0     # grados: negativo = el morro apunta hacia la izquierda de la imagen
ELEVATION = 30.0    # grados de inclinación de la cámara
SUPER = 1024        # lado del render antes de reducir
OUTLINE = (28, 29, 34)
GLASS_ALPHA = 0.26


def rx(p, a):
    x, y, z = p
    c, s = math.cos(a), math.sin(a)
    return (x, y * c - z * s, y * s + z * c)


def ry(p, a):
    x, y, z = p
    c, s = math.cos(a), math.sin(a)
    return (x * c + z * s, y, -x * s + z * c)


def cube_faces(c):
    """Devuelve las 6 caras (4 esquinas en px, normal) del cubo en coordenadas del mundo (Y arriba)."""
    ox, oy, oz = c["origin"]
    x0, y0, z0 = c["frm"]
    x1, y1, z1 = c["to"]
    sx, sy, sz = x1 - x0, y1 - y0, z1 - z0
    # Misma conversión que java_for() del generador (marco del modelo: Y abajo, morro en +Z).
    pivot = (ox, -oy, -oz)
    off = (x0 - ox, -(y1 - oy), -(z1 - oz))
    pitch = math.radians(c["rot"][0])
    yaw = math.radians(-c["rot"][1])
    k = c["lado"]
    yaw_g = 0.0
    if k is not None:
        yaw_g = math.radians(-45 * k if k < 8 else -45 * (k - 8) - 22.5)

    def to_world(corner):
        p = (off[0] + corner[0] * sx, off[1] + corner[1] * sy, off[2] + corner[2] * sz)
        p = ry(rx(p, pitch), yaw)
        p = (p[0] + pivot[0], p[1] + pivot[1], p[2] + pivot[2])
        if k is not None:
            p = ry(p, yaw_g)
        # Marco de modelo -> mundo: X invertida, Y hacia arriba (el renderer escala por (-1,-1,1)).
        return (-p[0], -p[1], p[2])

    cs = {(i, j, l): to_world((i, j, l)) for i in (0, 1) for j in (0, 1) for l in (0, 1)}
    quads = [
        [(0, 0, 0), (1, 0, 0), (1, 1, 0), (0, 1, 0)],
        [(0, 0, 1), (1, 0, 1), (1, 1, 1), (0, 1, 1)],
        [(0, 0, 0), (0, 0, 1), (0, 1, 1), (0, 1, 0)],
        [(1, 0, 0), (1, 0, 1), (1, 1, 1), (1, 1, 0)],
        [(0, 0, 0), (1, 0, 0), (1, 0, 1), (0, 0, 1)],
        [(0, 1, 0), (1, 1, 0), (1, 1, 1), (0, 1, 1)],
    ]
    faces = []
    for q in quads:
        pts = [np.array(cs[i]) for i in q]
        n = np.cross(pts[1] - pts[0], pts[3] - pts[0])
        ln = np.linalg.norm(n)
        if ln < 1e-9:
            continue
        faces.append((pts, n / ln))
    # Orientar las normales hacia fuera del cubo.
    center = sum(cs.values(), np.zeros(3)) / 8.0 if False else np.mean([np.array(v) for v in cs.values()], axis=0)
    out = []
    for pts, n in faces:
        mid = sum(pts) / 4.0
        if np.dot(n, mid - center) < 0:
            n = -n
        out.append((pts, n))
    return out


def main():
    size = int(sys.argv[1]) if len(sys.argv) > 1 else 64
    prev = sys.argv[2] if len(sys.argv) > 2 else None

    gm.build()
    tris = []   # (pts4, normal, color, emissive, glass)
    for c in gm.cubes:
        slot = c["slot"]
        if slot == "glass":
            col = gm.COLORS["glass"]
        else:
            col = gm.COLORS[slot]
        emissive = c["group"] in ("glow", "bateria")
        glass = c["group"] == "dome" or slot == "glass"
        for pts, n in cube_faces(c):
            tris.append((pts, n, col, emissive, glass))

    az, el = math.radians(AZIMUTH), math.radians(ELEVATION)
    ce, se = math.cos(el), math.sin(el)
    cam = np.array([0.0, se, ce])            # hacia la cámara, en el marco girado por el azimut
    light = np.array([-0.45, 0.85, 0.55])
    light /= np.linalg.norm(light)

    def view(p):
        x1, y1, z1 = ry(tuple(p), az)
        sy = y1 * ce - z1 * se
        close = y1 * se + z1 * ce
        return np.array([x1, sy]), close, np.array([x1, y1, z1])

    proj = []
    for pts, n, col, emis, glass in tris:
        vs = [view(p) for p in pts]
        n1 = np.array(ry(tuple(n), az))
        proj.append(([v[0] for v in vs], [v[1] for v in vs], n1, col, emis, glass))

    allp = np.array([p for item in proj for p in item[0]])
    mn, mx = allp.min(axis=0), allp.max(axis=0)
    span = max(mx[0] - mn[0], mx[1] - mn[1])
    margin = 0.06
    scale = SUPER * (1 - 2 * margin) / span
    ctr = (mn + mx) / 2.0

    def to_px(p):
        return ((p[0] - ctr[0]) * scale + SUPER / 2.0, SUPER / 2.0 - (p[1] - ctr[1]) * scale)

    img = np.zeros((SUPER, SUPER, 4), dtype=np.float32)
    zbuf = np.full((SUPER, SUPER), -1e9, dtype=np.float32)

    def raster(p2, close, color, alpha, write_z, ztest_eps=0.0):
        xs = [to_px(p)[0] for p in p2]
        ys = [to_px(p)[1] for p in p2]
        for tri in ((0, 1, 2), (0, 2, 3)):
            ax, ay = xs[tri[0]], ys[tri[0]]
            bx, by = xs[tri[1]], ys[tri[1]]
            cx_, cy_ = xs[tri[2]], ys[tri[2]]
            minx = max(int(math.floor(min(ax, bx, cx_))), 0)
            maxx = min(int(math.ceil(max(ax, bx, cx_))), SUPER - 1)
            miny = max(int(math.floor(min(ay, by, cy_))), 0)
            maxy = min(int(math.ceil(max(ay, by, cy_))), SUPER - 1)
            if maxx < minx or maxy < miny:
                continue
            den = (by - cy_) * (ax - cx_) + (cx_ - bx) * (ay - cy_)
            if abs(den) < 1e-9:
                continue
            gx, gy = np.meshgrid(np.arange(minx, maxx + 1) + 0.5, np.arange(miny, maxy + 1) + 0.5)
            w0 = ((by - cy_) * (gx - cx_) + (cx_ - bx) * (gy - cy_)) / den
            w1 = ((cy_ - ay) * (gx - cx_) + (ax - cx_) * (gy - cy_)) / den
            w2 = 1 - w0 - w1
            inside = (w0 >= -1e-4) & (w1 >= -1e-4) & (w2 >= -1e-4)
            if not inside.any():
                continue
            z = w0 * close[tri[0]] + w1 * close[tri[1]] + w2 * close[tri[2]]
            sub_z = zbuf[miny:maxy + 1, minx:maxx + 1]
            sub = img[miny:maxy + 1, minx:maxx + 1]
            if alpha >= 1.0:
                m = inside & (z > sub_z)
                sub[m, 0], sub[m, 1], sub[m, 2], sub[m, 3] = color[0], color[1], color[2], 1.0
                if write_z:
                    sub_z[m] = z[m]
            else:
                m = inside & (z >= sub_z - ztest_eps) & (sub[:, :, 3] > 0)
                for k in range(3):
                    sub[m, k] = sub[m, k] * (1 - alpha) + color[k] * alpha

    # 1) opacos
    for p2, close, n1, col, emis, glass in proj:
        if glass:
            continue
        if emis:
            shade = 1.0
        else:
            shade = 0.50 + 0.50 * max(0.0, float(np.dot(n1, light)))
        color = [min(255.0, col[k] * shade) for k in range(3)]
        raster(p2, close, color, 1.0, True)

    # 2) cristal translúcido, de atrás hacia delante, solo caras que miran a la cámara
    glass_faces = [it for it in proj if it[5] and float(np.dot(it[2], cam)) > 0]
    glass_faces.sort(key=lambda it: sum(it[1]) / 4.0)
    for p2, close, n1, col, emis, glass in glass_faces:
        shade = 0.85 + 0.15 * max(0.0, float(np.dot(n1, light)))
        color = [min(255.0, col[k] * shade) for k in range(3)]
        raster(p2, close, color, GLASS_ALPHA, False, ztest_eps=0.5)

    img[:, :, 3] *= 255.0   # el alfa se acumuló como 0..1
    big = Image.fromarray(np.clip(img, 0, 255).astype(np.uint8), "RGBA")

    # Reducir a tamaño de icono (promedio por bloque) y recortar a píxel limpio.
    small = big.resize((size, size), Image.BOX)
    arr = np.array(small).astype(np.float32)
    a = arr[:, :, 3] / 255.0
    rgb = np.where(a[:, :, None] > 0, arr[:, :, :3] / np.maximum(a[:, :, None], 1e-6), 0)
    solid = a >= 0.5
    out = np.zeros((size, size, 4), dtype=np.uint8)
    out[solid, :3] = np.clip(rgb[solid], 0, 255).astype(np.uint8)
    out[solid, 3] = 255
    # Contorno oscuro de 1 px alrededor de la silueta.
    pad = np.pad(solid, 1)
    neigh = pad[:-2, 1:-1] | pad[2:, 1:-1] | pad[1:-1, :-2] | pad[1:-1, 2:]
    edge = neigh & ~solid
    out[edge] = (*OUTLINE, 255)
    icon = Image.fromarray(out, "RGBA")
    icon.save(OUT)
    print("Icono escrito:", OUT, icon.size)

    if prev:
        os.makedirs(prev, exist_ok=True)
        big.save(os.path.join(prev, "nave_render_grande.png"))
        bg = Image.new("RGBA", (size * 12, size * 12), (139, 139, 139, 255))
        bg.alpha_composite(icon.resize((size * 12, size * 12), Image.NEAREST))
        bg.convert("RGB").save(os.path.join(prev, "nave_icono_vista.png"))


if __name__ == "__main__":
    main()
