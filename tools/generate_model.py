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
    "cyan": (110, 230, 255),
    "red": (255, 90, 70),
    "bin": (92, 98, 128),
    "bin_dark": (64, 68, 94),
    "bin_lid": (122, 128, 155),
    "beige": (226, 206, 176),
    "moss": (92, 112, 72),
}
NOISE = {"hull": 6, "dark": 5, "pod": 4, "seat": 6, "bin": 6, "bin_dark": 5, "bin_lid": 6, "beige": 4, "moss": 5}

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
            w = (r_a - dr_t / 2) * T          # anchura media del tramo: huecos y solapes de solo ~0,3 px en la arista
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

    # Asiento del piloto: base, respaldo, reposacabezas, refuerzos laterales y reposabrazos
    add("asiento_base", "body", (-5, FLOOR, -2), (5, FLOOR + 2, 4), "seat")
    add("asiento_refuerzo_izq", "body", (-6.5, FLOOR, -1.5), (-5, FLOOR + 5, 4), "seat")
    add("asiento_refuerzo_der", "body", (5, FLOOR, -1.5), (6.5, FLOOR + 5, 4), "seat")
    add("respaldo", "body", (-5, FLOOR + 2, 3.5), (5, FLOOR + 13, 5.5), "seat")
    add("respaldo_ala_izq", "body", (-6.5, FLOOR + 2, 2.5), (-5, FLOOR + 11, 5.5), "seat")
    add("respaldo_ala_der", "body", (5, FLOOR + 2, 2.5), (6.5, FLOOR + 11, 5.5), "seat")
    add("reposacabezas", "body", (-3.5, FLOOR + 13, 3.5), (3.5, FLOOR + 17, 5.5), "seat")
    add("respaldo_luz", "glow", (-1.5, FLOOR + 6, 3.2), (1.5, FLOOR + 11, 3.5), "cyan")
    add("pedestal_asiento", "body", (-3, FLOOR + 0.3, 0), (3, FLOOR + 1, 6), "dark")
    for n, s_ in (("izq", -1), ("der", 1)):
        x0, x1 = (-11.0, -9.5) if s_ < 0 else (9.5, 11.0)
        add("reposabrazos_%s" % n, "body", (x0, FLOOR, -4), (x1, FLOOR + 4.5, 4), "dark")
        add("reposabrazos_tapa_%s" % n, "body", (x0 - 0.5, FLOOR + 4.5, -4), (x1 + 0.5, FLOOR + 5.5, 4), "pod")
        add("boton_%s" % n, "glow", (x0 + 0.2, FLOOR + 5.5, -3), (x1 - 0.2, FLOOR + 6, -1.5), "red" if s_ < 0 else "cyan")
        add("boton2_%s" % n, "glow", (x0 + 0.2, FLOOR + 5.5, 0), (x1 - 0.2, FLOOR + 6, 1.5), "yellow")

    # Pilares de pared en cada arista (marcan los módulos y sujetan el cristal)
    rp = (SILL_IN - 0.9) / math.cos(math.pi / 8)
    for k in range(8):
        th = (22.5 + 45 * k) / D
        x, z = rp * math.sin(th), -rp * math.cos(th)
        add("pilar_%d" % k, "body", (x - 0.7, FLOOR, z - 0.7), (x + 0.7, SILL_TOP, z + 0.7), "dark")

    # --- Consola principal (pared del morro, lado 0): panel inclinado con la pantalla del GPS
    PAN = 25.0
    for k, w in ((0, 7.0), (1, 5.0), (7, 5.0)):
        e = 0.01 * k
        lb("consola_base_a_%d" % k, "body", k, -w, FLOOR, -16.0, w, FLOOR + 4.3 + e, -14.5, "dark")
        lb("consola_base_b_%d" % k, "body", k, -w, FLOOR, -17.2, w, FLOOR + 4.9 + e, -16.0, "dark")
        lb("consola_base_c_%d" % k, "body", k, -w, FLOOR, WALL - 0.1, w, FLOOR + 5.4 + e, -17.2, "dark")
        lb("consola_panel_%d" % k, "body", k, -w, FLOOR + 4, -18.4, w, FLOOR + 5.5, -14.5, "pod",
           origin=(0, FLOOR + 4, -14.5), rot=(PAN, 0, 0))
        lb("consola_labio_%d" % k, "body", k, -w - 0.3, FLOOR + 4.3, -14.8, w + 0.3, FLOOR + 5.0, -14.3, "dark")
    # pantalla grande del GPS con retícula y punto de posición
    sc = dict(origin=(0, FLOOR + 4, -14.5), rot=(PAN, 0, 0))
    lb("gps_pantalla", "glow", 0, -5.5, FLOOR + 5.5, -17.4, 5.5, FLOOR + 5.9, -15.6, "cyan", **sc)
    lb("gps_linea_h", "glow", 0, -5.0, FLOOR + 5.9, -16.6, 5.0, FLOOR + 6.1, -16.4, "dark", **sc)
    lb("gps_linea_v", "glow", 0, -0.1, FLOOR + 5.9, -17.2, 0.1, FLOOR + 6.1, -15.8, "dark", **sc)
    lb("gps_punto", "glow", 0, -0.6, FLOOR + 5.9, -16.9, 0.6, FLOOR + 6.3, -16.1, "orange", **sc)
    for i, (xa, col) in enumerate(((-6.2, "red"), (-4.8, "orange"), (3.8, "cyan"), (5.2, "yellow"))):
        lb("gps_boton_%d" % i, "glow", 0, xa, FLOOR + 5.5, -15.4, xa + 0.9, FLOOR + 5.9, -14.9, col, **sc)
    # pantallas laterales: radar (lado 1) y estado de sistemas (lado 7)
    lb("radar_pantalla", "glow", 1, -3.8, FLOOR + 5.5, -17.4, 3.8, FLOOR + 5.9, -15.6, "cyan", **sc)
    lb("radar_anillo", "glow", 1, -1.6, FLOOR + 5.9, -17.0, 1.6, FLOOR + 6.1, -16.0, "dark", **sc)
    lb("radar_punto", "glow", 1, -0.4, FLOOR + 5.9, -16.7, 0.4, FLOOR + 6.3, -16.3, "yellow", **sc)
    for i, (xa, hgt, col) in enumerate(((-3.6, 0.6, "cyan"), (-1.6, 0.9, "orange"), (0.4, 0.5, "red"), (2.4, 0.8, "cyan"))):
        lb("estado_barra_%d" % i, "glow", 7, xa, FLOOR + 5.5, -17.4, xa + 1.2, FLOOR + 5.5 + hgt, -15.6, col, **sc)
    # batería: marco oscuro y 10 pilotos a lo ancho de la cara frontal de la consola (se encienden según la carga)
    add("bateria_marco", "body", (-6.3, FLOOR + 0.8, -14.5), (6.3, FLOOR + 3.2, -14.1), "pod")
    for i in range(10):
        xa = -5.5 + i * 1.1
        add("bat_%d" % i, "bateria", (xa, FLOOR + 1.5, -14.1), (xa + 0.9, FLOOR + 2.5, -13.8), "orange")

    # --- Reactor central (pared trasera, lado 4): núcleo brillante entre aletas
    lb("reactor_base", "body", 4, -6, FLOOR, -18.4, 6, FLOOR + 3, -13.5, "dark")
    lb("reactor_peana", "body", 4, -4.5, FLOOR + 3, -17.6, 4.5, FLOOR + 4, -14.4, "pod")
    lb("reactor_nucleo", "glow", 4, -2, FLOOR + 4, -16.6, 2, FLOOR + 12, -14.6, "orange")
    lb("reactor_corazon", "glow", 4, -1, FLOOR + 5, -16.1, 1, FLOOR + 11, -15.1, "yellow")
    lb("reactor_tapa", "body", 4, -4, FLOOR + 12, -17.2, 4, FLOOR + 13, -14.2, "pod")
    for sgn in (-1, 1):
        xa, xb = (-5.5, -4.5) if sgn < 0 else (4.5, 5.5)
        lb("reactor_aleta_%d" % sgn, "body", 4, xa, FLOOR + 3, -17.8, xb, FLOOR + 11, -15.0, "dark")
        lb("reactor_luz_%d" % sgn, "glow", 4, xa, FLOOR + 5, -15.0, xb, FLOOR + 9, -14.6, "cyan")

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

    # --- Propulsores: cubos de basura tumbados (tapa con asa delante, boca abierta con fuego detrás),
    #     sujetos al casco con dos correas y sendas cunas que se hunden en la pendiente.
    for n, s_ in (("izq", -1), ("der", 1)):
        def fx(a, b, s_=s_):
            return (-b, -a) if s_ < 0 else (a, b)

        def box(name, xs, y0, y1, z0, z1, group, slot, n=n):
            add("%s_%s" % (name, n), group, (xs[0], y0, z0), (xs[1], y1, z1), slot)

        # cuerpo hueco (paredes de 1,5 px), eje a lo largo de Z
        box("tubo_arriba", fx(24, 36), 34.5, 36, -9, 9, "body", "bin")
        box("tubo_abajo", fx(24, 36), 22, 23.5, -9, 9, "body", "bin")
        box("tubo_interior", fx(24, 25.5), 23.5, 34.5, -9, 9, "body", "bin")
        box("tubo_exterior", fx(34.5, 36), 23.5, 34.5, -9, 9, "body", "bin")
        # tapa delantera más ancha, con panel hundido y asa
        box("tapa_delantera", fx(23, 37), 21, 37, -11, -9, "body", "bin_lid")
        box("tapa_panel", fx(25, 35), 23, 35, -11.5, -11, "body", "bin")
        box("aro_tapa", fx(23.5, 36.5), 21.5, 36.5, -9, -7.5, "body", "bin_dark")
        box("asa_pata_1", fx(26.5, 27.5), 28.2, 30.8, -12.6, -11.5, "body", "pod")
        box("asa_pata_2", fx(32.5, 33.5), 28.2, 30.8, -12.6, -11.5, "body", "pod")
        box("asa_barra", fx(26.5, 33.5), 28.2, 29.6, -13.6, -12.4, "body", "pod")
        # borde trasero (la boca por donde sale el fuego)
        box("labio_arriba", fx(23, 37), 34.5, 37, 9, 10.8, "body", "bin_lid")
        box("labio_abajo", fx(23, 37), 21, 23.5, 9, 10.8, "body", "bin_lid")
        box("labio_interior", fx(23, 25.5), 23.5, 34.5, 9, 10.8, "body", "bin_lid")
        box("labio_exterior", fx(34.5, 37), 23.5, 34.5, 9, 10.8, "body", "bin_lid")
        # costillas a lo largo del cuerpo (arriba, abajo y dos en la cara exterior)
        for j, xo in enumerate((26.5, 29.5, 32.5)):
            box("costilla_arriba_%d" % j, fx(xo, xo + 1), 36, 36.4, -7.5, 9, "body", "bin_dark")
            box("costilla_abajo_%d" % j, fx(xo, xo + 1), 21.6, 22, -7.5, 9, "body", "bin_dark")
        box("costilla_ext_1", fx(36, 36.4), 24, 25, -7.5, 9, "body", "bin_dark")
        box("costilla_ext_2", fx(36, 36.4), 33, 34, -7.5, 9, "body", "bin_dark")
        # correas alrededor del cuerpo y cunas hundidas en el casco
        for z0 in (-5.8, 4.2):
            z1 = z0 + 1.6
            tag = "%d" % round(z0)
            box("correa_arriba_" + tag, fx(23.2, 36.8), 36, 36.8, z0, z1, "body", "pod")
            box("correa_abajo_" + tag, fx(23.2, 36.8), 21.2, 22, z0, z1, "body", "pod")
            box("correa_interior_" + tag, fx(23.2, 24), 22, 36, z0, z1, "body", "pod")
            box("correa_exterior_" + tag, fx(36, 36.8), 22, 36, z0, z1, "body", "pod")
            box("cuna_" + tag, fx(26, 34), 17.2, 21.2, z0, z1, "body", "dark")
        # interior: brasa y llama (a brillo máximo)
        box("fondo_brasa", fx(25.5, 34.5), 23.5, 34.5, 4.5, 6, "glow", "orange")
        box("fuego_1", fx(26.5, 29.5), 25.5, 32.5, 6, 13.5, "glow", "orange")
        box("fuego_2", fx(30.5, 33.5), 25.5, 32.5, 6, 13.5, "glow", "orange")
        box("fuego_nucleo", fx(28.5, 31.5), 27, 31, 6, 11, "glow", "yellow")

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
    groups = ["body", "glow", "frame", "dome", "bateria"]
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
            lines.append("\t\tModelPartData %s = %s.addChild(\"lado_%d\", ModelPartBuilder.create(), "
                         "ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(%s), 0));" % (var, g, k, ("%g" % yaw_g)))
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
 *  - bateria: los 10 pilotos de la batería (brillo máximo; el renderer enciende solo los que tocan)
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
		ModelPartData bateria = root.addChild("bateria", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
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
