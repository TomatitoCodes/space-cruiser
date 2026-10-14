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
    "chrome": (168, 176, 192),
    "yellow": (255, 220, 70),
    "orange": (255, 153, 35),
    "frame": (153, 203, 235),
    "seat": (112, 78, 52),
    "glass": (160, 207, 235),
    "lamp": (255, 243, 200),
    "amber": (225, 100, 20),
    "hot": (255, 240, 170),
    "lamp_body": (96, 100, 52),
    "lamp_dark": (68, 72, 38),
    "tape": (136, 140, 146),
    "potion": (70, 225, 95),
    "potion2": (45, 190, 80),
    "cork": (150, 108, 68),
    "cyan": (110, 230, 255),
    "red": (255, 90, 70),
    "bin": (92, 98, 128),
    "bin_dark": (64, 68, 94),
    "bin_lid": (122, 128, 155),
    "beige": (226, 206, 176),
    "moss": (92, 112, 72),
    "map_water": (46, 118, 205),
    "map_water2": (30, 92, 175),
    "map_grass": (78, 176, 86),
    "map_grass2": (56, 146, 66),
    "map_sand": (224, 204, 128),
    "map_rock": (128, 128, 134),
}
NOISE = {"lamp_body": 4, "lamp_dark": 4, "tape": 4, "hull": 6, "dark": 5, "pod": 4, "seat": 6, "bin": 6, "bin_dark": 5, "bin_lid": 6, "beige": 4, "moss": 5}

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
    # Platillo hueco: solo hay cáscara (pendientes, borde y fondo). El suelo de la cabina es
    # la parte alta del fondo, a 14 px; el jugador va a 16 px (1 bloque), 2 px por encima.
    octagon("fondo", 19.5, 8.5, 14, "body", "dark")

    # --- Medidas de la cúpula (apotema = distancia del centro a una cara del octógono)
    DOME = 19.0           # pared de cristal (antes 20: la cúpula es 2 px más pequeña de diámetro)
    SILL_OUT = 20.0       # borde opaco bajo el cristal
    SILL_IN = 18.5
    FLOOR = 14.0          # suelo de la cabina (parte alta del fondo del platillo)
    SILL_TOP = 23.5       # el borde opaco es bajo para que el piloto se vea entero
    GLASS_TOP = 39.0      # el cristal empieza a curvarse 1 px antes que en la v1.5 (40)
    # Perfil de la cúpula (apotema, altura): pared vertical y tres tramos cada vez más suaves
    PROFILE = [(19.0, 39.0), (16.6, 41.8), (13.0, 44.0), (9.8, 45.5)]

    # --- Cúpula: tapa y postes (el borde opaco va por lados, más abajo)
    # techo sólido integrado: su cara superior queda a ras del cristal, no un bloque por encima
    octagon("tapa", PROFILE[-1][0], PROFILE[-1][1] - 1.5, PROFILE[-1][1], "frame", "frame")
    rv = (DOME - 0.2) / math.cos(math.pi / 8)
    for k in range(8):
        th = (22.5 + 45 * k) / D
        x, z = rv * math.sin(th), -rv * math.cos(th)
        add("poste_%d" % k, "frame", (x - 0.5, SILL_TOP, z - 0.5), (x + 0.5, GLASS_TOP + 0.3, z + 0.5), "frame")

    # --- Pendiente superior: 6 tramos por lado. Cada tramo es un panel con la anchura del
    #     extremo interior (sin solaparse con los vecinos) y detrás hay placas octogonales
    #     del mismo color que tapan los huecos de las esquinas.
    THICK = 2.0
    r_in_top, y_in_top = SILL_OUT + 2.0, 22.0   # la pendiente acaba 2 px antes: ahí empieza el anillo plano
    R0 = R - 0.4          # las pendientes arrancan dentro del muro del borde: así no comparten cara con él
    n_top = 8
    dr_t = (R0 - r_in_top) / n_top
    dy_t = (y_in_top - yt) / n_top
    ang_t = math.atan2(dy_t, dr_t) * D
    len_t = math.hypot(dr_t, dy_t)

    # --- Pendiente inferior: 6 tramos por lado que bajan hacia el centro
    r_in_bot, y_in_bot = 18.0, 8.0
    n_bot = 8
    dr_b = (R0 - r_in_bot) / n_bot
    dy_b = (yb - y_in_bot) / n_bot
    ang_b = math.atan2(dy_b, dr_b) * D
    len_b = math.hypot(dr_b, dy_b)

    # --- Cristal: techo en 3 tramos que se estrechan y no se solapan

    for k in range(8):
        L = k
        for i in range(n_top):
            r_a = R0 - dr_t * i
            y_a = yt + dy_t * i
            w = (r_a - dr_t / 2) * T + 0.25   # anchura media del tramo: huecos y solapes de solo ~0,3 px en la arista
            add("pend_sup_%d_%d" % (i, k), "body", (-w, y_a - THICK, -r_a), (w, y_a, -r_a + len_t), "hull", L,
                origin=(0, y_a, -r_a), rot=(-ang_t, 0, 0))
        for i in range(n_bot):
            r_a = R0 - dr_b * i
            y_a = yb - dy_b * i
            w = (r_a - dr_b / 2) * T
            add("pend_inf_%d_%d" % (i, k), "body", (-w, y_a, -r_a), (w, y_a + THICK, -r_a + len_b), "dark", L,
                origin=(0, y_a, -r_a), rot=(ang_b, 0, 0))
        # Placas planas de relleno justo bajo la superficie: lo que se ve por las juntas es casi del mismo tono y altura
        for i in range(n_top):
            r_a = R0 - dr_t * i
            y_a = yt + dy_t * i
            add("soporte_sup_%d_%d" % (i, k), "body", (-r_a * T, y_a - 1.05, -r_a), (r_a * T, y_a - 0.05, -(r_a - dr_t)), "hull", L)
        for i in range(n_bot):
            r_a = R0 - dr_b * i
            y_a = yb - dy_b * i
            add("soporte_inf_%d_%d" % (i, k), "body", (-r_a * T, y_a + 0.05, -r_a), (r_a * T, y_a + 1.05, -(r_a - dr_b)), "dark", L)
        # Muro del borde: anchura completa hasta la arista (sin muescas en las esquinas) y alturas desfasadas
        # 0,01 px entre lados alternos para que las tapas solapadas no parpadeen.
        off = 0.01 * (k % 2)
        add("borde_%d" % k, "body", (-R * T, yb + off, -R), (R * T, yt - off, -(R - 2.0)), "hull", L)
        add("franja_borde_%d" % k, "glow", (-3.5, yb + 1, -R - 0.6), (3.5, yt - 1, -R), "yellow", L)
        add("franja_pendiente_%d" % k, "glow", (-2.5, yt - 0.05, -R0), (2.5, yt + 0.6, -R0 + len_t * n_top), "yellow", L,
            origin=(0, yt - 0.05, -R0), rot=(-ang_t, 0, 0))
        # anillo plano de 2 px (dos tiras de 1 px) alrededor de la cúpula, a la altura donde acaba la pendiente
        for j in range(2):
            r_out = SILL_OUT + 2.0 - j
            w_flat = (r_out - 0.5) * T
            add("anillo_plano_%d_%d" % (j, k), "body", (-w_flat, y_in_top - 2.0, -r_out), (w_flat, y_in_top, -(r_out - 1.0)), "hull", L)
        # borde opaco bajo el cristal (hueco por dentro: no hay bloque macizo)
        w_sill = (SILL_OUT + SILL_IN) / 2 * T
        add("borde_cupula_%d" % k, "body", (-w_sill, yb, -SILL_OUT), (w_sill, SILL_TOP, -SILL_IN), "dark", L)
        # tira de luz en la pared interior de la cabina
        add("luz_interior_%d" % k, "glow", (-5.5, 19.5, -SILL_IN), (5.5, 20.5, -SILL_IN + 0.4), "cyan", L)
        w_g = DOME * T - 0.2
        add("cristal_pared_%d" % k, "dome", (-w_g, SILL_TOP, -DOME - 0.5), (w_g, GLASS_TOP, -DOME + 0.5), "glass", L)
        for i in range(3):
            r_a, y_a = PROFILE[i]
            r_b, y_b = PROFILE[i + 1]
            ang = math.atan2(y_b - y_a, r_a - r_b) * D
            ln = math.hypot(r_a - r_b, y_b - y_a)
            wr = (r_a - 0.6 * (r_a - r_b)) * T
            add("cristal_techo_%d_%d" % (i, k), "dome", (-wr, y_a - 1.5, -r_a), (wr, y_a, -r_a + ln), "glass", L,
                origin=(0, y_a, -r_a), rot=(-ang, 0, 0))

    # --- Nervios del marco sobre las aristas de la cúpula (siguen el perfil curvo hasta la tapa)
    for k in range(8):
        for i in range(3):
            r_a, y_a = PROFILE[i]
            r_b, y_b = PROFILE[i + 1]
            rv_a, rv_b = r_a / math.cos(math.pi / 8), r_b / math.cos(math.pi / 8)
            ang = math.atan2(y_b - y_a, rv_a - rv_b) * D
            ln = math.hypot(rv_a - rv_b, y_b - y_a)
            add("nervio_%d_%d" % (i, k), "frame", (-0.5, y_a - 0.9, -rv_a), (0.5, y_a + 0.25, -rv_a + ln), "frame", 8 + k,
                origin=(0, y_a, -rv_a), rot=(-ang, 0, 0))
    # piloto de luz embutido en el techo
    add("baliza", "glow", (-2, PROFILE[-1][1], -2), (2, PROFILE[-1][1] + 0.3, 2), "yellow")

    # --- Cabina. Nariz de la nave = -Z. Todo es bajo o va pegado a las paredes: el piloto (cadera a 16 px)
    #     queda libre y a la vista. Los módulos de pared se definen en el marco de cada "lado" (pared en z=-18,5).
    def lb(name, group, k, x0, y0, z0, x1, y1, z1, slot, origin=None, rot=None):
        o = origin if origin else (0.0, 0.0, 0.0)
        r = rot if rot else (0.0, 0.0, 0.0)
        add(name, group, (x0, y0, z0), (x1, y1, z1), slot, lado=k, origin=o, rot=r)

    WALL = -SILL_IN + 0.1   # cara interior de la pared (un pelo dentro para no solapar caras)

    # Suelo: placa octogonal oscura sobre el fondo y tiras de luz
    octagon("suelo_placa", 15.0, FLOOR, FLOOR + 0.3, "body", "pod")
    for n, s_ in (("izq", -1), ("der", 1)):
        add("suelo_luz_%s" % n, "glow", ((-13.5 if s_ < 0 else 12.5), FLOOR + 0.3, -9), ((-12.5 if s_ < 0 else 13.5), FLOOR + 0.7, 9), "cyan")

    # Asiento del piloto: base, respaldo, reposacabezas, refuerzos laterales y reposabrazos.
    # Todo va ZB px más atrás (el piloto también, ver ModEntities): gana sitio delante y la cabina parece más ancha.
    ZB = 2.0
    add("asiento_base", "body", (-5, FLOOR, -2 + ZB), (5, FLOOR + 2, 4 + ZB), "seat")
    add("asiento_refuerzo_izq", "body", (-6.5, FLOOR, -1.5 + ZB), (-5, FLOOR + 5, 4 + ZB), "seat")
    add("asiento_refuerzo_der", "body", (5, FLOOR, -1.5 + ZB), (6.5, FLOOR + 5, 4 + ZB), "seat")
    add("respaldo", "body", (-5, FLOOR + 2, 3.5 + ZB), (5, FLOOR + 13, 5.5 + ZB), "seat")
    add("respaldo_ala_izq", "body", (-6.5, FLOOR + 2, 2.5 + ZB), (-5, FLOOR + 11, 5.5 + ZB), "seat")
    add("respaldo_ala_der", "body", (5, FLOOR + 2, 2.5 + ZB), (6.5, FLOOR + 11, 5.5 + ZB), "seat")
    add("reposacabezas", "body", (-3.5, FLOOR + 13, 3.5 + ZB), (3.5, FLOOR + 17, 5.5 + ZB), "seat")
    add("respaldo_luz", "glow", (-1.5, FLOOR + 6, 3.2 + ZB), (1.5, FLOOR + 11, 3.5 + ZB), "cyan")
    add("pedestal_asiento", "body", (-3, FLOOR + 0.3, 0 + ZB), (3, FLOOR + 1, 6 + ZB), "dark")
    for n, s_ in (("izq", -1), ("der", 1)):
        x0, x1 = (-11.0, -9.5) if s_ < 0 else (9.5, 11.0)
        add("reposabrazos_%s" % n, "body", (x0, FLOOR, -4 + ZB), (x1, FLOOR + 4.5, 4 + ZB), "dark")
        add("reposabrazos_tapa_%s" % n, "body", (x0 - 0.5, FLOOR + 4.5, -4 + ZB), (x1 + 0.5, FLOOR + 5.5, 4 + ZB), "pod")
        add("boton_%s" % n, "glow", (x0 + 0.2, FLOOR + 5.5, -3 + ZB), (x1 - 0.2, FLOOR + 6, -1.5 + ZB), "red" if s_ < 0 else "cyan")
        add("boton2_%s" % n, "glow", (x0 + 0.2, FLOOR + 5.5, 0 + ZB), (x1 - 0.2, FLOOR + 6, 1.5 + ZB), "yellow")

    # Pilares de pared en cada arista (marcan los módulos y sujetan el cristal)
    rp = (SILL_IN - 0.9) / math.cos(math.pi / 8)
    for k in range(8):
        th = (22.5 + 45 * k) / D
        x, z = rp * math.sin(th), -rp * math.cos(th)
        add("pilar_%d" % k, "body", (x - 0.7, FLOOR, z - 0.7), (x + 0.7, SILL_TOP, z + 0.7), "dark")

    # --- Consola central (lado 0, morro): instrumentos, batería y el volante
    PAN = 25.0
    for k, w in ((0, 7.0), (7, 5.0)):
        e = 0.01 * k
        lb("consola_base_a_%d" % k, "body", k, -w, FLOOR, -16.0, w, FLOOR + 4.3 + e, -14.5, "dark")
        lb("consola_base_b_%d" % k, "body", k, -w, FLOOR, -17.2, w, FLOOR + 4.9 + e, -16.0, "dark")
        lb("consola_base_c_%d" % k, "body", k, -w, FLOOR, WALL - 0.1, w, FLOOR + 5.4 + e, -17.2, "dark")
        lb("consola_panel_%d" % k, "body", k, -w, FLOOR + 4, -18.4, w, FLOOR + 5.5, -14.5, "pod",
           origin=(0, FLOOR + 4, -14.5), rot=(PAN, 0, 0))
        lb("consola_labio_%d" % k, "body", k, -w - 0.3, FLOOR + 4.3, -14.8, w + 0.3, FLOOR + 5.0, -14.3, "dark")
    sc = dict(origin=(0, FLOOR + 4, -14.5), rot=(PAN, 0, 0))
    # pantalla de instrumentos (horizonte) entre los dos mandos
    lb("panel_pantalla", "glow", 0, -5.5, FLOOR + 5.5, -17.4, 5.5, FLOOR + 5.9, -15.6, "cyan", **sc)
    lb("panel_linea_h", "glow", 0, -5.0, FLOOR + 5.9, -16.6, 5.0, FLOOR + 6.1, -16.4, "dark", **sc)
    lb("panel_punto", "glow", 0, -0.6, FLOOR + 5.9, -16.9, 0.6, FLOOR + 6.3, -16.1, "orange", **sc)
    for i, (xa, col) in enumerate(((-6.2, "red"), (-4.8, "orange"), (3.8, "cyan"), (5.2, "yellow"))):
        lb("panel_boton_%d" % i, "glow", 0, xa, FLOOR + 5.5, -15.4, xa + 0.9, FLOOR + 5.9, -14.9, col, **sc)
    # volante / mando de vuelo en el centro, inclinado hacia el piloto (pivote en la base)
    yk = dict(origin=(0, FLOOR + 5.5, -15.5), rot=(22.0, 0, 0))
    lb("volante_base", "body", 0, -2.2, FLOOR + 5.0, -16.8, 2.2, FLOOR + 6.4, -14.2, "dark")
    lb("volante_columna", "body", 0, -0.9, FLOOR + 5.5, -16.2, 0.9, FLOOR + 11.5, -14.8, "pod", **yk)
    lb("volante_barra", "body", 0, -4.8, FLOOR + 10.5, -16.2, 4.8, FLOOR + 11.7, -14.8, "pod", **yk)
    for n, s_ in (("izq", -1), ("der", 1)):
        xa, xb = (-5.0, -3.8) if s_ < 0 else (3.8, 5.0)
        lb("volante_mango_%s" % n, "body", 0, xa, FLOOR + 8.0, -16.3, xb, FLOOR + 12.6, -14.7, "dark", **yk)
        lb("volante_boton_%s" % n, "glow", 0, xa + 0.2, FLOOR + 12.6, -15.9, xb - 0.2, FLOOR + 13.1, -15.1,
           "red" if s_ < 0 else "cyan", **yk)
    # lado 7 (delante-derecha): radar y barras de estado
    lb("radar_pantalla", "glow", 7, -4.4, FLOOR + 5.5, -17.4, -0.5, FLOOR + 5.9, -15.6, "cyan", **sc)
    lb("radar_anillo", "glow", 7, -3.4, FLOOR + 5.9, -17.0, -1.5, FLOOR + 6.1, -16.0, "dark", **sc)
    lb("radar_punto", "glow", 7, -2.7, FLOOR + 5.9, -16.7, -2.1, FLOOR + 6.3, -16.3, "yellow", **sc)
    for i, (xa, hgt, col) in enumerate(((0.5, 0.6, "cyan"), (1.9, 0.9, "orange"), (3.3, 0.5, "red"))):
        lb("estado_barra_%d" % i, "glow", 7, xa, FLOOR + 5.5, -17.4, xa + 1.1, FLOOR + 5.5 + hgt, -15.6, col, **sc)
    # batería: marco oscuro y 10 pilotos a lo ancho de la cara frontal de la consola (se encienden según la carga)
    add("bateria_marco", "body", (-6.3, FLOOR + 0.8, -14.5), (6.3, FLOOR + 3.2, -14.1), "pod")
    for i in range(10):
        xa = -5.5 + i * 1.1
        add("bat_%d" % i, "bat_%d" % i, (xa, FLOOR + 1.5, -14.1), (xa + 0.9, FLOOR + 2.5, -13.8), "orange")

    # --- Guiño: botella de cristal con líquido verde en el suelo, junto al reposabrazos derecho (decorativa)
    # Tumbada, pequeña, con el alcohol desparramado por el suelo. Cuello hacia el centro de la cabina.
    F0 = FLOOR + 0.3
    add("botella_liquido", "glow", (8.2, F0 + 0.2, -8.7), (11.5, F0 + 1.2, -6.9), "potion")
    add("botella_cuerpo", "botella", (7.3, F0, -9.0), (11.8, F0 + 2.4, -6.6), "glass")
    add("botella_cuello", "botella", (4.9, F0 + 0.6, -8.4), (7.3, F0 + 1.8, -7.2), "glass")
    add("botella_corcho", "body", (4.1, F0 + 0.6, -8.4), (4.9, F0 + 1.8, -7.2), "cork")
    # charco irregular (brillo máximo, dos tonos)
    for n, (x0, x1, z0, z1, sl) in enumerate((
            (2.6, 4.1, -9.6, -6.0, "potion"), (1.4, 2.6, -8.8, -6.6, "potion2"),
            (3.4, 5.4, -6.0, -4.9, "potion2"), (4.1, 6.4, -10.1, -8.4, "potion2"),
            (0.5, 1.4, -8.0, -7.0, "potion"), (5.4, 7.0, -7.2, -6.0, "potion"))):
        add("charco_%d" % n, "glow", (x0, F0, z0), (x1, F0 + 0.12 + 0.01 * n, z1), sl)

    # --- GPS: consola a la izquierda del piloto (lado 1, delante-izquierda), mismo perfil que las otras consolas
    k = 1
    wk = 5.6
    for nm, y1, z0, z1 in (("a", 4.3, -16.0, -14.5), ("b", 4.9, -17.2, -16.0), ("c", 5.4, WALL - 0.1, -17.2)):
        lb("gps_base_%s" % nm, "body", k, -wk, FLOOR, z0, wk, FLOOR + y1, z1, "dark")
    mp = dict(origin=(0, FLOOR + 4, -14.5), rot=(PAN, 0, 0))
    lb("gps_panel", "body", k, -wk, FLOOR + 4, -18.4, wk, FLOOR + 5.5, -14.5, "pod", **mp)
    lb("gps_labio", "body", k, -wk - 0.3, FLOOR + 4.3, -14.8, wk + 0.3, FLOOR + 5.0, -14.3, "dark")
    # marco de la pantalla
    lb("gps_marco_arriba", "body", k, -4.9, FLOOR + 5.5, -17.9, 4.9, FLOOR + 5.9, -17.5, "dark", **mp)
    lb("gps_marco_abajo", "body", k, -4.9, FLOOR + 5.5, -15.5, 4.9, FLOOR + 5.9, -15.1, "dark", **mp)
    lb("gps_marco_izq", "body", k, -4.9, FLOOR + 5.5, -17.5, -4.5, FLOOR + 5.9, -15.5, "dark", **mp)
    lb("gps_marco_der", "body", k, 4.5, FLOOR + 5.5, -17.5, 4.9, FLOOR + 5.9, -15.5, "dark", **mp)
    # el mapa: cuadrícula de teselas de terreno (agua, hierba, arena, roca)
    cols_n, rows_n = 8, 3
    tw = 9.0 / cols_n
    th = 2.0 / rows_n
    pal = ["map_water", "map_water2", "map_grass", "map_grass2", "map_sand", "map_rock"]
    for j in range(rows_n):
        for i in range(cols_n):
            v = math.sin(i * 0.95 + 0.6) + math.cos(j * 1.9 + i * 0.35) + 0.4 * math.sin(i * 2.3 + j)
            idx = 0 if v < -1.0 else 1 if v < -0.45 else 4 if v < -0.1 else 3 if v < 0.55 else 2 if v < 1.3 else 5
            xa = -4.5 + i * tw
            za = -17.5 + j * th
            lb("mapa_tesela_%d_%d" % (j, i), "glow", k, xa, FLOOR + 5.5, za, xa + tw - 0.1, FLOOR + 5.8, za + th - 0.1, pal[idx], **mp)
    # ruta (cian), posición de la nave (naranja) y botones junto al borde
    lb("mapa_ruta_1", "glow", k, -3.4, FLOOR + 5.8, -16.4, 0.2, FLOOR + 5.95, -16.2, "cyan", **mp)
    lb("mapa_ruta_2", "glow", k, 0.0, FLOOR + 5.8, -17.0, 0.2, FLOOR + 5.95, -16.2, "cyan", **mp)
    lb("mapa_ruta_3", "glow", k, 0.0, FLOOR + 5.8, -17.0, 2.6, FLOOR + 5.95, -16.8, "cyan", **mp)
    lb("mapa_nave", "glow", k, 2.4, FLOOR + 5.8, -17.2, 3.4, FLOOR + 6.2, -16.4, "orange", **mp)
    for i, (xa, col) in enumerate(((-4.4, "red"), (-3.2, "orange"), (2.6, "cyan"), (3.8, "yellow"))):
        lb("mapa_boton_%d" % i, "glow", k, xa, FLOOR + 5.5, -14.9, xa + 0.8, FLOOR + 5.9, -14.5, col, **mp)

    # --- Reactor central (pared trasera, lado 4): 1 px más pequeño; núcleo en cápsula con anillos y brillo en degradado
    lb("reactor_base", "body", 4, -5, FLOOR, -18.4, 5, FLOOR + 2.5, -14.0, "dark")
    lb("reactor_peana", "body", 4, -3.8, FLOOR + 2.5, -17.6, 3.8, FLOOR + 3.4, -14.9, "pod")
    lb("reactor_aro_inf", "body", 4, -2.7, FLOOR + 3.4, -17.0, 2.7, FLOOR + 4.0, -15.2, "chrome")
    # cápsula: cuatro tramos de brillo creciente hacia el centro, separados por anillos oscuros
    segs = ((4.0, 5.6, "amber"), (5.9, 7.7, "orange"), (8.0, 9.6, "yellow"), (9.9, 10.5, "orange"))
    for j, (y0, y1, sl) in enumerate(segs):
        lb("reactor_nucleo_%d" % j, "glow", 4, -1.9, FLOOR + y0, -16.6, 1.9, FLOOR + y1, -14.8, sl)
    lb("reactor_corazon", "glow", 4, -0.8, FLOOR + 5.0, -16.9, 0.8, FLOOR + 9.6, -14.6, "hot")
    for j, y in enumerate((5.6, 7.7, 9.6)):
        lb("reactor_anillo_%d" % j, "body", 4, -2.3, FLOOR + y, -16.9, 2.3, FLOOR + y + 0.35, -14.6, "pod")
    lb("reactor_tapa", "body", 4, -3.0, FLOOR + 10.5, -17.2, 3.0, FLOOR + 11.5, -14.6, "pod")
    lb("reactor_tapa_luz", "glow", 4, -1.0, FLOOR + 11.5, -16.4, 1.0, FLOOR + 11.8, -15.4, "red")
    for sgn in (-1, 1):
        xa, xb = (-4.8, -3.8) if sgn < 0 else (3.8, 4.8)
        lb("reactor_aleta_%d" % sgn, "body", 4, xa, FLOOR + 2.5, -17.8, xb, FLOOR + 10.0, -15.0, "dark")
        lb("reactor_luz_%d" % sgn, "glow", 4, xa, FLOOR + 4.5, -15.0, xb, FLOOR + 8.5, -14.6, "cyan")
        # tubos de refrigeración hacia la pared y válvula amarilla
        lb("reactor_tubo_%d" % sgn, "body", 4, xa - 0.2 * sgn, FLOOR + 10.0, -18.2, xb + 0.2 * sgn, FLOOR + 10.8, -16.2, "chrome")
        lb("reactor_valvula_%d" % sgn, "glow", 4, (xa + xb) / 2 - 0.4, FLOOR + 10.8, -17.4, (xa + xb) / 2 + 0.4, FLOOR + 11.2, -16.6, "yellow")
        lb("reactor_piloto_%d" % sgn, "glow", 4, (xa + xb) / 2 - 0.5, FLOOR + 1.0, -14.4, (xa + xb) / 2 + 0.5, FLOOR + 1.8, -14.0, "orange")

    # --- Módulos laterales (lados 2 y 6): terminales bajos con tira de luz y botones
    for k in (2, 6):
        lb("terminal_base_%d" % k, "body", k, -6, FLOOR, WALL - 0.1, 6, FLOOR + 6, -16.0, "dark")
        lb("terminal_tapa_%d" % k, "body", k, -6.4, FLOOR + 6, WALL - 0.2, 6.4, FLOOR + 7, -15.6, "pod")
        lb("terminal_luz_%d" % k, "glow", k, -5, FLOOR + 3, -16.0, 5, FLOOR + 3.6, -15.7, "cyan")
        for i, col in enumerate(("red", "orange", "cyan")):
            lb("terminal_boton_%d_%d" % (k, i), "glow", k, -3.5 + i * 2.6, FLOOR + 7, -17.4, -2.5 + i * 2.6, FLOOR + 7.4, -16.6, col)

    # --- Taquillas (lados 3 y 5): puertas con tirador
    for k in (3, 5):
        lb("taquilla_%d" % k, "body", k, -5.5, FLOOR, WALL - 0.1, 5.5, FLOOR + 8, -15.5, "dark")
        lb("taquilla_puerta_%d" % k, "body", k, -4.5, FLOOR + 1, -15.5, 4.5, FLOOR + 7, -15.2, "pod")
        lb("taquilla_tirador_%d" % k, "glow", k, -0.4, FLOOR + 3, -15.2, 0.4, FLOOR + 5, -14.9, "yellow")

    # --- Propulsores: cubos de basura tumbados de sección cuadrada (14x14, tapa de 16x16), con asa delante,
    #     boca abierta con fuego detrás y sujetos al casco con dos correas y sendas cunas hundidas en la pendiente.
    for n, s_ in (("izq", -1), ("der", 1)):
        def fx(a, b, s_=s_):
            return (-b, -a) if s_ < 0 else (a, b)

        def box(name, xs, y0, y1, z0, z1, group, slot, n=n):
            add("%s_%s" % (name, n), group, (xs[0], y0, z0), (xs[1], y1, z1), slot)

        # cuerpo hueco (paredes de 1,5 px): x 23..37, y 22..36
        box("tubo_arriba", fx(23, 37), 34.5, 36, -9, 9, "body", "bin")
        box("tubo_abajo", fx(23, 37), 22, 23.5, -9, 9, "body", "bin")
        box("tubo_interior", fx(23, 24.5), 23.5, 34.5, -9, 9, "body", "bin")
        box("tubo_exterior", fx(35.5, 37), 23.5, 34.5, -9, 9, "body", "bin")
        # tapa delantera más ancha (16x16), con panel hundido y asa
        box("tapa_delantera", fx(22, 38), 21, 37, -11, -9, "body", "bin_lid")
        box("tapa_panel", fx(24, 36), 23, 35, -11.5, -11, "body", "bin")
        box("aro_tapa", fx(22.5, 37.5), 21.5, 36.5, -9, -7.5, "body", "bin_dark")
        box("asa_pata_1", fx(26.5, 27.5), 28.2, 30.8, -12.6, -11.5, "body", "pod")
        box("asa_pata_2", fx(32.5, 33.5), 28.2, 30.8, -12.6, -11.5, "body", "pod")
        box("asa_barra", fx(26.5, 33.5), 28.2, 29.6, -13.6, -12.4, "body", "pod")
        # borde trasero (la boca por donde sale el fuego)
        box("labio_arriba", fx(22, 38), 34.5, 37, 9, 10.8, "body", "bin_lid")
        box("labio_abajo", fx(22, 38), 21, 23.5, 9, 10.8, "body", "bin_lid")
        box("labio_interior", fx(22, 24.5), 23.5, 34.5, 9, 10.8, "body", "bin_lid")
        box("labio_exterior", fx(35.5, 38), 23.5, 34.5, 9, 10.8, "body", "bin_lid")
        # costillas a lo largo del cuerpo (arriba, abajo y dos en la cara exterior)
        for j, xo in enumerate((25.5, 29.5, 33.5)):
            box("costilla_arriba_%d" % j, fx(xo, xo + 1), 36, 36.4, -7.5, 9, "body", "bin_dark")
            box("costilla_abajo_%d" % j, fx(xo, xo + 1), 21.6, 22, -7.5, 9, "body", "bin_dark")
        box("costilla_ext_1", fx(37, 37.4), 24.5, 25.5, -7.5, 9, "body", "bin_dark")
        box("costilla_ext_2", fx(37, 37.4), 32.5, 33.5, -7.5, 9, "body", "bin_dark")
        # correas alrededor del cuerpo y cunas hundidas en el casco
        for z0 in (-5.8, 4.2):
            z1 = z0 + 1.6
            tag = "%d" % round(z0)
            box("correa_arriba_" + tag, fx(22.2, 37.8), 36, 36.8, z0, z1, "body", "pod")
            box("correa_abajo_" + tag, fx(22.2, 37.8), 21.2, 22, z0, z1, "body", "pod")
            box("correa_interior_" + tag, fx(22.2, 23), 22, 36, z0, z1, "body", "pod")
            box("correa_exterior_" + tag, fx(37, 37.8), 22, 36, z0, z1, "body", "pod")
            box("cuna_" + tag, fx(25, 35), 17.2, 21.2, z0, z1, "body", "dark")
        # interior: brasa y llama (a brillo máximo)
        box("fondo_brasa", fx(24.5, 35.5), 23.5, 34.5, 4.5, 6, "glow", "orange")
        box("fuego_1", fx(25.5, 28.5), 25.5, 32.5, 6, 13.5, "glow", "orange")
        box("fuego_2", fx(31.5, 34.5), 25.5, 32.5, 6, 13.5, "glow", "orange")
        box("fuego_nucleo", fx(28.5, 31.5), 27, 31, 6, 11, "glow", "yellow")

    # --- Dos linternas pegadas con cinta americana, juntas en el centro del borde de la placa delantera (lado 0),
    # a cada lado de la franja amarilla y apuntando hacia delante (-Z)
    for n, sx in (("izq", -1), ("der", 1)):
        cx = sx * 11.0

        def lin(name, hx, y0, y1, z0, z1, slot, group="body", dx=0.0, n=n, cx=cx):
            add("linterna_%s_%s" % (n, name), group, (cx + dx - hx, y0, -R0 + z0), (cx + dx + hx, y1, -R0 + z1), slot, 0)

        lin("cuerpo", 1.4, 16.9, 19.5, 0.6, 5.4, "lamp_body")
        lin("cabeza", 1.9, 16.7, 20.1, -0.6, 1.4, "lamp_dark")
        lin("aro", 1.6, 17.0, 19.8, -0.9, -0.6, "chrome")
        lin("lente", 1.25, 17.4, 19.4, -1.15, -0.85, "lamp", "glow")
        lin("mango", 0.55, 19.5, 20.5, 1.8, 4.8, "dark")
        lin("boton", 0.4, 20.5, 20.9, 2.6, 3.4, "red")
        lin("cinta_1", 1.55, 16.8, 19.7, 2.7, 3.7, "tape")
        lin("cinta_2", 1.55, 16.8, 19.7, 4.4, 5.1, "tape")
        lin("cinta_ala_a", 1.0, 17.1, 17.7, 2.7, 3.7, "tape", dx=-2.55)
        lin("cinta_ala_b", 1.0, 17.1, 17.7, 2.7, 3.7, "tape", dx=2.55)

    # --- Patas: tren de aterrizaje de nave espacial (anclaje al casco, manguito, pistón, aletas y plato de apoyo)
    for i, (sx, sz) in enumerate(((-1, -1), (1, -1), (-1, 1), (1, 1))):
        cx, cz = sx * 14.5, sz * 14.5

        def leg(name, hx, y0, y1, slot, group="body", hz=None, dx=0.0, dz=0.0, i=i, cx=cx, cz=cz):
            hz = hx if hz is None else hz
            add("pata_%d_%s" % (i, name), group, (cx + dx - hx, y0, cz + dz - hz), (cx + dx + hx, y1, cz + dz + hz), slot)

        leg("anclaje", 3.4, 8.6, 10.0, "chrome")
        leg("manguito", 2.6, 7.0, 8.6, "dark")
        leg("piston", 1.8, 1.2, 7.0, "chrome")
        leg("pie_base", 4.2, 0.0, 1.2, "dark")
        # luces del plato (a brillo máximo)
        leg("pie_luz_x", 1.6, 1.2, 1.5, "cyan", "glow", hz=0.3, dz=sz * 3.8, dx=0.0)
        leg("pie_luz_z", 0.3, 1.2, 1.5, "cyan", "glow", hz=1.6, dx=sx * 3.8, dz=0.0)
        leg("pie_marca", 0.8, 1.2, 1.5, "yellow", "glow", hz=0.8, dx=sx * 1.4, dz=sz * 1.4)


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


def paint_underside(px, c, rng):
    """Pintura del bajo del casco: paneles de dos tonos, costuras en las aristas y remaches.

    Se pinta la cara inferior del cubo (en la caja UV de Minecraft es la segunda del bloque superior)."""
    sx, sy, sz = c["size"]
    u, v = c["uv"]
    rx = int(round(u + sz + sx))
    ry = int(round(v))
    rw = max(1, int(round(sx)))
    rh = max(1, int(round(sz)))
    parts = c["name"].split("_")
    try:
        panel = int(parts[2]) if c["name"].startswith(("pend_inf", "soporte_inf")) else 0
    except ValueError:
        panel = 0
    k = c["lado"] or 0
    tone = (6 if panel % 2 else -4) + (3 if k % 2 else 0)
    base = (58 + tone, 63 + tone, 76 + tone)   # gris azulado, algo más frío que el resto
    for yy in range(ry, ry + rh):
        for xx in range(rx, rx + rw):
            grad = 0.92 + 0.14 * (xx - rx) / max(1, rw - 1)
            n = rng.randint(-4, 4)
            col = [max(0, min(255, int(ch * grad) + n)) for ch in base]
            edge_x = xx in (rx, rx + rw - 1)
            edge_y = rh >= 6 and yy in (ry, ry + rh - 1)
            if edge_x or edge_y:
                col = [max(0, ch - 22) for ch in col]           # costura
            elif rh >= 6 and (xx - rx) % 8 == 3 and yy in (ry + 2, ry + rh - 3):
                col = [min(255, ch + 28) for ch in col]          # remache
            elif rh < 6 and (xx - rx) % 7 == 3 and yy == ry:
                col = [min(255, ch + 24) for ch in col]          # remache en los tramos estrechos
            px[xx, yy] = tuple(col) + (255,)
    if rh >= 6:  # costura central en las placas grandes (el fondo del platillo)
        for xx in range(rx, rx + rw):
            px[xx, ry + rh // 2] = tuple(max(0, ch - 14) for ch in px[xx, ry + rh // 2][:3]) + (255,)


def paint(tex_h):
    random.seed(7)
    rng = random.Random(11)
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
        if c["name"].startswith(("pend_inf", "soporte_inf", "fondo_")):
            paint_underside(px, c, rng)
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
    groups = ["body", "glow", "frame", "dome"] + ["bat_%d" % i for i in range(10)] + ["botella"]
    lines = []
    for g in groups:
        lines.append("")
        lines.append("\t\t// --- %s" % g)
        members = [c for c in cubes if c["group"] == g]
        for c in [m for m in members if m["lado"] is None]:
            lines.append(call(g, c))
        for k in range(16):
            side = [m for m in members if m["lado"] == k]
            if not side:
                continue
            var = "%s_lado%d" % (g, k)
            yaw_g = -45 * k if k < 8 else -45 * (k - 8) - 22.5
            lines.append("\t\tPartDefinition %s = %s.addOrReplaceChild(\"lado_%d\", CubeListBuilder.create(), "
                         "PartPose.offsetAndRotation(0, 0, 0, 0, (float) Math.toRadians(%s), 0));" % (var, g, k, ("%g" % yaw_g)))
            for c in side:
                lines.append(call(var, c))

    src = '''package com.spacecruiser.client;

import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;

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
 *  - bat_0..bat_9: los 10 pilotos de la batería (brillo máximo; el renderer dibuja solo los que tocan)
 *
 * Textura "space_cruiser.png": atlas de %dx%d con un rectángulo plano de color por cubo.
 */
public final class SpaceCruiserModel {
	public static final int TEXTURE_WIDTH = %d;
	public static final int TEXTURE_HEIGHT = %d;

	private SpaceCruiserModel() {
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
		PartDefinition glow = root.addOrReplaceChild("glow", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
		PartDefinition frame = root.addOrReplaceChild("frame", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
		PartDefinition dome = root.addOrReplaceChild("dome", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
@@BAT@@		PartDefinition botella = root.addOrReplaceChild("botella", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
%s

		return LayerDefinition.create(mesh, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	/** Cubo con pivote propio: posición del cubo relativa al pivote, giros en grados (X = pitch, Y = yaw). */
	private static void c(PartDefinition parent, String name, int u, int v,
						  float x, float y, float z, float sx, float sy, float sz,
						  float px, float py, float pz, float pitchDeg, float yawDeg) {
		parent.addOrReplaceChild(
				name,
				CubeListBuilder.create().texOffs(u, v).addBox(x, y, z, sx, sy, sz),
				PartPose.offsetAndRotation(px, py, pz, (float) Math.toRadians(pitchDeg), (float) Math.toRadians(yawDeg), 0.0f)
		);
	}
}
''' % (TEX_W, TEX_H, TEX_W, TEX_H, "\n".join(lines))
    bat = "".join('\t\tPartDefinition bat_%d = root.addOrReplaceChild("bat_%d", CubeListBuilder.create(), PartPose.offset(0, 24, 0));\n' % (i, i) for i in range(10))
    src = src.replace("@@BAT@@", bat)
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
