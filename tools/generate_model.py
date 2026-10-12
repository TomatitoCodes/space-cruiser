#!/usr/bin/env python3
"""
Genera SpaceCruiserModel.java y la textura space_cruiser.png a partir de la geometría
de la nave modelada en Blockbench (proyecto "space_cruiser_nuevo", formato Modded Entity).

Uso (desde la raíz del repositorio):
    python3 tools/generate_model.py

Convenciones:
  - Coordenadas de Blockbench: Y hacia arriba, el morro de la nave apunta a -Z.
  - Modelo del mod: Y hacia abajo y morro en +Z (ver SpaceCruiserRenderer), así que se
    pasa de (x, y, z) a (x, -y, -z). El giro en X se mantiene y el giro en Y se invierte.
  - Cuatro grupos de render: body (opaco), glow (luces a brillo máximo),
    frame (marco opaco) y dome (cristal translúcido).
  - Textura: atlas con un rectángulo plano por cubo (UV de caja de Minecraft).
"""
import math
import os
import random
import sys

from PIL import Image

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
JAVA_OUT = os.path.join(ROOT, "src/main/java/com/spacecruiser/client/SpaceCruiserModel.java")
TEX_OUT = os.path.join(ROOT, "src/main/resources/assets/spacecruiser/textures/entity/space_cruiser.png")

TEX_W = 512
D = 180 / math.pi
T = math.tan(math.pi / 8)

COLORS = {
    "hull": (122, 124, 130),
    "dark": (61, 63, 69),
    "pod": (36, 36, 42),
    "yellow": (255, 220, 70),
    "orange": (255, 153, 35),
    "frame": (153, 203, 235),
    "seat": (112, 78, 52),
    "glass": (160, 207, 235),
}
NOISE = {"hull": 6, "dark": 5, "pod": 4, "seat": 6}

cubes = []  # cada cubo: dict


def add(name, group, frm, to, slot, lado=None, origin=(0.0, 0.0, 0.0), rot=(0.0, 0.0, 0.0)):
    cubes.append(dict(name=name, group=group, frm=list(frm), to=list(to), slot=slot,
                      lado=lado, origin=list(origin), rot=list(rot)))


def octagon(prefix, r, y0, y1, group, slot):
    """Octógono regular con 4 barras que se cruzan a 0/45/90/135 grados."""
    for i, a in enumerate((0, 45, 90, 135)):
        add("%s_%d" % (prefix, i), group, (-r, y0 + 0.02 * i, -r * T), (r, y1 - 0.02 * i, r * T),
            slot, origin=(0, y0, 0), rot=(0, a, 0))


def build():
    R, yb, yt = 36.0, 12.0, 17.0

    # --- Casco
    octagon("nucleo_inf", 18, 8, yb, "body", "dark")
    octagon("borde", R, yb, yt, "body", "hull")
    octagon("nucleo_sup", 25, yt, 19.5, "body", "hull")

    # --- Medidas de la cúpula (apotema = distancia del centro a una cara del octógono)
    DOME = 19.0           # pared de cristal (antes 20: la cúpula es 2 px más pequeña de diámetro)
    SILL_OUT = 20.0       # borde opaco bajo el cristal
    SILL_IN = 18.5
    FLOOR = 19.5          # suelo de la cabina (parte alta del casco)
    SILL_TOP = 23.5       # el borde opaco es bajo para que el piloto se vea entero
    GLASS_TOP = 41.0

    # --- Cúpula: tapa y postes (el borde opaco va por lados, más abajo)
    octagon("tapa", 9.8, 48.5, 50, "frame", "frame")
    rv = DOME / math.cos(math.pi / 8)
    for k in range(8):
        th = (22.5 + 45 * k) / D
        x, z = rv * math.sin(th), -rv * math.cos(th)
        add("poste_%d" % k, "frame", (x - 0.8, SILL_TOP, z - 0.8), (x + 0.8, GLASS_TOP + 0.5, z + 0.8), "frame")

    # --- Pendiente superior: 6 tramos por lado. Cada tramo es un panel con la anchura del
    #     extremo interior (sin solaparse con los vecinos) y detrás hay placas octogonales
    #     del mismo color que tapan los huecos de las esquinas.
    THICK = 2.0
    r_in_top, y_in_top = SILL_OUT, 22.0
    n_top = 6
    dr_t = (R - r_in_top) / n_top
    dy_t = (y_in_top - yt) / n_top
    ang_t = math.atan2(dy_t, dr_t) * D
    len_t = math.hypot(dr_t, dy_t)
    for i in range(n_top):
        if yt + dy_t * i - THICK / math.cos(ang_t / D) - 0.05 > yt + 0.2:
            octagon("relleno_sup_%d" % i, R - dr_t * i, yt, yt + dy_t * i - THICK / math.cos(ang_t / D) - 0.05,
                    "body", "hull")

    # --- Pendiente inferior: 6 tramos por lado que bajan hacia el centro
    r_in_bot, y_in_bot = 18.0, 8.0
    n_bot = 6
    dr_b = (R - r_in_bot) / n_bot
    dy_b = (yb - y_in_bot) / n_bot
    ang_b = math.atan2(dy_b, dr_b) * D
    len_b = math.hypot(dr_b, dy_b)

    # --- Cristal: techo en 3 tramos que se estrechan y no se solapan
    aR = math.atan2(8, DOME - 10) * D
    seg_len = math.hypot(DOME - 10, 8) / 3

    for k in range(8):
        L = k
        for i in range(n_top):
            r_a = R - dr_t * i
            y_a = yt + dy_t * i
            w = (r_a - dr_t) * T - 0.05
            add("pend_sup_%d_%d" % (i, k), "body", (-w, y_a - THICK, -r_a), (w, y_a, -r_a + len_t), "hull", L,
                origin=(0, y_a, -r_a), rot=(-ang_t, 0, 0))
        for i in range(n_bot):
            r_a = R - dr_b * i
            y_a = yb - dy_b * i
            w = (r_a - dr_b) * T - 0.05
            add("pend_inf_%d_%d" % (i, k), "body", (-w, y_a, -r_a), (w, y_a + THICK, -r_a + len_b), "dark", L,
                origin=(0, y_a, -r_a), rot=(ang_b, 0, 0))
        add("franja_borde_%d" % k, "glow", (-3.5, yb + 1, -R - 0.6), (3.5, yt - 1, -R), "yellow", L)
        add("franja_pendiente_%d" % k, "glow", (-2.5, yt, -R), (2.5, yt + 0.6, -R + len_t * n_top), "yellow", L,
            origin=(0, yt, -R), rot=(-ang_t, 0, 0))
        # borde opaco bajo el cristal (hueco por dentro: no hay bloque macizo)
        w_sill = (SILL_OUT + SILL_IN) / 2 * T
        add("borde_cupula_%d" % k, "body", (-w_sill, FLOOR, -SILL_OUT), (w_sill, SILL_TOP, -SILL_IN), "dark", L)
        w_g = DOME * T - 0.2
        add("cristal_pared_%d" % k, "dome", (-w_g, SILL_TOP, -DOME - 0.5), (w_g, GLASS_TOP, -DOME + 0.5), "glass", L)
        for i in range(3):
            r = DOME - (DOME - 10) / 3 * i
            y = GLASS_TOP + 8 / 3 * i
            wr = (r - (DOME - 10) / 3) * T - 0.25
            add("cristal_techo_%d_%d" % (i, k), "dome", (-wr, y - 1.5, -r), (wr, y, -r + seg_len), "glass", L,
                origin=(0, y, -r), rot=(-aR, 0, 0))

    # --- Cabina hueca: asiento bajo, respaldo y consola delante, fuera del alcance de las piernas
    add("asiento_base", "body", (-5, FLOOR, -1), (5, 23, 5), "seat")
    add("asiento_respaldo", "body", (-5, 23, 3), (5, 36, 5), "seat")
    add("consola", "body", (-9, FLOOR, -18), (9, 26, -12), "dark")
    add("columna_volante", "body", (-1.5, 26, -17), (1.5, 28, -15), "dark")
    add("volante", "body", (-5, 28, -18), (5, 29.5, -13), "dark")

    # --- Propulsores: cubos de basura tumbados, cerrados delante y abiertos detrás (fuego)
    for n, s in (("izq", -1), ("der", 1)):
        def fx(a, b, s=s):
            return (-b, -a) if s < 0 else (a, b)

        def box(name, xs, y0, y1, z0, z1, group, slot, n=n):
            add("%s_%s" % (name, n), group, (xs[0], y0, z0), (xs[1], y1, z1), slot)

        box("tubo_arriba", fx(24, 36), 35.5, 37, -9, 9, "body", "pod")
        box("tubo_abajo", fx(24, 36), 25, 26.5, -9, 9, "body", "pod")
        box("tubo_interior", fx(24, 25.5), 26.5, 35.5, -9, 9, "body", "pod")
        box("tubo_exterior", fx(34.5, 36), 26.5, 35.5, -9, 9, "body", "pod")
        box("tapa_delantera", fx(23, 37), 24, 38, -11, -9, "body", "hull")
        box("asa_delantera", fx(28, 32), 30, 32, -12, -11, "body", "dark")
        box("labio_arriba", fx(23, 37), 36.5, 38, 9, 10.5, "body", "hull")
        box("labio_abajo", fx(23, 37), 24, 25.5, 9, 10.5, "body", "hull")
        box("labio_interior", fx(23, 24.5), 25.5, 36.5, 9, 10.5, "body", "hull")
        box("labio_exterior", fx(35.5, 37), 25.5, 36.5, 9, 10.5, "body", "hull")
        box("fondo_brasa", fx(25.5, 34.5), 26.5, 35.5, 6, 7.5, "glow", "orange")
        box("fuego_1", fx(27, 29.5), 28, 34, 7.5, 12, "glow", "orange")
        box("fuego_2", fx(30.5, 33), 28, 34, 7.5, 12, "glow", "orange")
        box("soporte", fx(20, 24), 28, 34, -3, 3, "body", "dark")

    # --- Patas
    for i, (sx, sz) in enumerate(((-1, -1), (1, -1), (-1, 1), (1, 1))):
        x0, x1 = (-17, -11) if sx < 0 else (11, 17)
        z0, z1 = (-17, -11) if sz < 0 else (11, 17)
        add("pata_%d" % i, "body", (x0, 2, z0), (x1, 10, z1), "dark")


def checksum():
    """Suma de todas las coordenadas, para compararla con la de Blockbench."""
    total = 0.0
    for c in cubes:
        total += sum(c["frm"]) + sum(c["to"]) + sum(c["origin"]) + sum(c["rot"])
    total += sum(45 * k for k in range(8))  # rotación de los 8 grupos "lado"
    return total


def pack():
    """Coloca el rectángulo UV de cada cubo en el atlas (empaquetado por filas)."""
    items = []
    for i, c in enumerate(cubes):
        sx, sy, sz = (c["to"][a] - c["frm"][a] for a in range(3))
        c["size"] = (sx, sy, sz)
        w = math.ceil(2 * (sx + sz)) + 2
        hgt = math.ceil(sz + sy) + 2
        items.append((hgt, w, i))
    items.sort(reverse=True)
    x = y = row_h = 0
    for hgt, w, i in items:
        if x + w > TEX_W:
            x, y, row_h = 0, y + row_h, 0
        cubes[i]["uv"] = (x + 1, y + 1)
        cubes[i]["rect"] = (x + 1, y + 1, w - 2, hgt - 2)
        x += w
        row_h = max(row_h, hgt)
    total_h = y + row_h
    tex_h = 1
    while tex_h < total_h:
        tex_h *= 2
    return tex_h


def paint(tex_h):
    random.seed(7)
    img = Image.new("RGBA", (TEX_W, tex_h), (0, 0, 0, 0))
    px = img.load()
    for c in cubes:
        if c["slot"] == "glass":
            continue
        base = COLORS[c["slot"]]
        amp = NOISE.get(c["slot"], 0)
        x0, y0, w, h = c["rect"]
        for yy in range(y0 - 1, y0 + h + 1):
            for xx in range(x0 - 1, x0 + w + 1):
                d = random.randint(-amp, amp) if amp else 0
                px[xx, yy] = tuple(max(0, min(255, v + d)) for v in base) + (255,)
    img.save(TEX_OUT)


def fmt(v):
    s = ("%.4f" % v).rstrip("0").rstrip(".")
    if s in ("-0", ""):
        s = "0"
    if "." not in s:
        s += ".0"
    return s + "f"


def java_for(c):
    ox, oy, oz = c["origin"]
    x0, y0, z0 = c["frm"]
    x1, y1, z1 = c["to"]
    sx, sy, sz = c["size"]
    # Origen -> pivote del modelo (Y hacia abajo, morro en +Z).
    pivot = (ox, -oy, -oz)
    off = (x0 - ox, -(y1 - oy), -(z1 - oz))
    pitch = c["rot"][0]
    yaw = -c["rot"][1]
    return pivot, off, (sx, sy, sz), pitch, yaw


def write_java():
    groups = ["body", "glow", "frame", "dome"]
    lines = []
    for g in groups:
        lines.append("")
        lines.append("\t\t// --- %s" % g)
        members = [c for c in cubes if c["group"] == g]
        for c in [m for m in members if m["lado"] is None]:
            lines.append(call(g, c))
        for k in range(8):
            side = [m for m in members if m["lado"] == k]
            if not side:
                continue
            var = "%s_lado%d" % (g, k)
            lines.append("\t\tModelPartData %s = %s.addChild(\"lado_%d\", ModelPartBuilder.create(), "
                         "ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(%d), 0));" % (var, g, k, -45 * k))
            for c in side:
                lines.append(call(var, c))

    src = '''package com.spacecruiser.client;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;

/**
 * GENERADO por tools/generate_model.py a partir del modelo de Blockbench. No editar a mano:
 * cambia el script (o el modelo) y vuelve a generarlo.
 *
 * Unidades en píxeles (16 = 1 bloque). Origen en el suelo, centro de la nave en X/Z, morro en +Z.
 *
 * Grupos (los usa SpaceCruiserRenderer):
 *  - body:  casco, cabina, propulsores (opaco)
 *  - glow:  franjas luminosas y fuego de los propulsores (brillo máximo)
 *  - frame: postes y tapa de la cúpula (opaco, se oculta en primera persona)
 *  - dome:  cristal de la cúpula (translúcido, se oculta en primera persona)
 *
 * Textura "space_cruiser.png": atlas de %dx%d con un rectángulo plano de color por cubo.
 */
public final class SpaceCruiserModel {
	public static final int TEXTURE_WIDTH = %d;
	public static final int TEXTURE_HEIGHT = %d;

	private SpaceCruiserModel() {
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData glow = root.addChild("glow", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData frame = root.addChild("frame", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData dome = root.addChild("dome", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
%s

		return TexturedModelData.of(data, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	/** Cubo con pivote propio: posición del cubo relativa al pivote, giros en grados (X = pitch, Y = yaw). */
	private static void c(ModelPartData parent, String name, int u, int v,
						  float x, float y, float z, float sx, float sy, float sz,
						  float px, float py, float pz, float pitchDeg, float yawDeg) {
		parent.addChild(
				name,
				ModelPartBuilder.create().uv(u, v).cuboid(x, y, z, sx, sy, sz),
				ModelTransform.of(px, py, pz, (float) Math.toRadians(pitchDeg), (float) Math.toRadians(yawDeg), 0.0f)
		);
	}
}
''' % (TEX_W, TEX_H, TEX_W, TEX_H, "\n".join(lines))
    with open(JAVA_OUT, "w", encoding="utf-8") as f:
        f.write(src)


def call(parent, c):
    pivot, off, size, pitch, yaw = java_for(c)
    return '\t\tc(%s, "%s", %d, %d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);' % (
        parent, c["name"], c["uv"][0], c["uv"][1],
        fmt(off[0]), fmt(off[1]), fmt(off[2]), fmt(size[0]), fmt(size[1]), fmt(size[2]),
        fmt(pivot[0]), fmt(pivot[1]), fmt(pivot[2]), fmt(pitch), fmt(yaw))


if __name__ == "__main__":
    build()
    TEX_H = pack()
    paint(TEX_H)
    write_java()
    print("cubos:", len(cubes), "textura: %dx%d" % (TEX_W, TEX_H), "checksum: %.6f" % checksum())
