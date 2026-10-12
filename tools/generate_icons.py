#!/usr/bin/env python3
"""Genera los iconos 16x16 de los items de la receta de la Space Cruiser.

Uso: python3 tools/generate_icons.py [directorio_preview]
Escribe los PNG en src/main/resources/assets/spacecruiser/textures/item/ y,
si se pasa un directorio, una hoja de previsualización ampliada.

Convención de luz: foco arriba a la izquierda (H/c claros a la izquierda y
arriba, g/K/d oscuros a la derecha y abajo).
"""
import os
import sys
from PIL import Image

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "src/main/resources/assets/spacecruiser/textures/item")

PALETTE = {
    ".": (0, 0, 0, 0),
    # Grises metálicos (de oscuro a claro)
    "1": (28, 29, 34, 255),     # contorno
    "K": (52, 54, 60, 255),
    "g": (74, 76, 82, 255),
    "G": (96, 98, 104, 255),
    "M": (122, 124, 130, 255),
    "L": (150, 152, 158, 255),
    "H": (190, 192, 198, 255),
    "W": (240, 248, 255, 255),  # brillo puntual
    # Cristal
    "d": (70, 120, 170, 255),
    "b": (110, 165, 205, 255),
    "B": (160, 210, 238, 255),
    "c": (205, 232, 248, 255),
    # Energía (blaze / fire charge)
    "o": (230, 110, 20, 255),
    "O": (255, 150, 32, 255),
    "Y": (255, 214, 64, 255),
    "y": (255, 242, 160, 255),
    # Redstone
    "R": (214, 42, 42, 255),
    # Shulker
    "P": (150, 100, 170, 255),
    "p": (108, 68, 128, 255),
    "q": (196, 154, 210, 255),
    # Tierra del mapa
    "E": (74, 170, 96, 255),
}

ICONS = {
    # Reactor: cilindro con ventanilla al núcleo, collares con luces de redstone
    "reactor": [
        "................",
        "................",
        "....11111111....",
        "...1HHLLLLMG1...",
        "..1HLRLMMRGGK1..",
        "..1HL111111gK1..",
        "..1HL1oOOo1gK1..",
        "..1HL1OYYO1gK1..",
        "..1HL1OyyO1gK1..",
        "..1HL1OYYO1gK1..",
        "..1HL1oOOo1gK1..",
        "..1HL111111gK1..",
        "..1HLRLMMRGGK1..",
        "...1LMMGGGKK1...",
        "....11111111....",
        "................",
    ],
    # Casco: placa octogonal biselada con panel de caparazón de shulker y remaches
    "hull": [
        "................",
        "....11111111....",
        "...1HHHHLLMG1...",
        "..1HLMMMMMGGK1..",
        ".1HLMMMMMMMMGK1.",
        ".1HM11111111GK1.",
        ".1HM1qqPPPP1GK1.",
        ".1HM1qPPPPp1GK1.",
        ".1HM1PPPPpp1GK1.",
        ".1HM1PPpppp1GK1.",
        ".1HM11111111GK1.",
        ".1HLKMMMMMMKGK1.",
        "..1LMMMMMMMGGK..",
        "...1GMMMMGGG1...",
        "....11111111....",
        "................",
    ],
    # Cabina: cúpula de cristal con asiento visible y base con luces
    "cockpit": [
        "................",
        "................",
        "....11111111....",
        "...1cWBBBBBb1...",
        "..1cWBBBBBBbd1..",
        ".1cBBBBBBBBBbd1.",
        ".1BBBBBBBBBBbd1.",
        ".1BBBBKKKBBBbd1.",
        ".1BBBGMGGBBbbd1.",
        ".1bbbGGGGGbbbd1.",
        ".11111111111111.",
        "1HHHLLLLLLLLGGK1",
        "1HMMMMMMMMMMGGK1",
        "1gGOGGGGGGGOGGK1",
        ".1KKKKKKKKKKKK1.",
        "................",
    ],
    # Consola de navegación: pantalla con mapa (cuadrícula, tierra, posición) y botones
    "navigation_console": [
        "................",
        "................",
        "..111111111111..",
        ".1HHHHHHHHHHLL1.",
        ".1H1111111111L1.",
        ".1H1BBbdbbbb1L1.",
        ".1H1bEEdbbbb1L1.",
        ".1H1bbbdObbb1L1.",
        ".1H1bbbdbEEb1L1.",
        ".1H1bbbdbbbb1L1.",
        ".1H1111111111L1.",
        ".1HLMMMMMMMMGK1.",
        ".1HLOMMYMMEMGK1.",
        ".1gGGGGGGGGGGK1.",
        "..111111111111..",
        "................",
    ],
}

# La 'E' de los botones de la consola es verde (ojo de ender); ya está en la paleta.


def build(grid):
    assert len(grid) == 16, "debe tener 16 filas"
    im = Image.new("RGBA", (16, 16))
    for y, row in enumerate(grid):
        assert len(row) == 16, f"fila {y} mide {len(row)}: {row!r}"
        for x, ch in enumerate(row):
            im.putpixel((x, y), PALETTE[ch])
    return im


def main():
    os.makedirs(OUT, exist_ok=True)
    icons = {}
    for name, grid in ICONS.items():
        im = build(grid)
        im.save(os.path.join(OUT, f"{name}.png"))
        icons[name] = im
    print("Iconos escritos:", ", ".join(f"{n}.png" for n in icons))

    if len(sys.argv) > 1:
        prev = sys.argv[1]
        os.makedirs(prev, exist_ok=True)
        order = ["reactor", "hull", "cockpit", "navigation_console"]
        ship = Image.open(os.path.join(OUT, "space_cruiser.png")).convert("RGBA")
        tiles = [icons[n] for n in order] + [ship]
        scale, slot, gap = 12, 16 * 12 + 24, 12
        w = gap + len(tiles) * (slot + gap)
        sheet = Image.new("RGBA", (w, slot + 2 * gap + 40), (198, 198, 198, 255))
        for i, t in enumerate(tiles):
            x = gap + i * (slot + gap)
            bg = Image.new("RGBA", (slot, slot), (139, 139, 139, 255))
            big = t.resize((16 * scale, 16 * scale), Image.NEAREST)
            bg.alpha_composite(big, (12, 12))
            sheet.alpha_composite(bg, (x, gap))
            sheet.alpha_composite(t, (x + 12, gap + slot + 8))
            sheet.alpha_composite(t.resize((32, 32), Image.NEAREST), (x + 40, gap + slot + 4))
        sheet.convert("RGB").save(os.path.join(prev, "preview_iconos.png"))
        print("Preview:", os.path.join(prev, "preview_iconos.png"))


if __name__ == "__main__":
    main()
