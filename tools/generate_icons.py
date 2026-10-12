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
    # Grises metálicos (de oscuro a claro); K hace de contorno suave, como en el vanilla
    "K": (40, 42, 48, 255),
    "g": (70, 72, 80, 255),
    "G": (100, 102, 110, 255),
    "M": (130, 132, 140, 255),
    "L": (165, 167, 175, 255),
    "H": (205, 207, 214, 255),
    "W": (240, 248, 255, 255),
    # Cristal / pantalla
    "d": (60, 110, 165, 255),
    "b": (95, 155, 205, 255),
    "B": (150, 205, 238, 255),
    "c": (205, 232, 248, 255),
    # Energía
    "o": (222, 100, 20, 255),
    "O": (255, 150, 32, 255),
    "Y": (255, 214, 64, 255),
    "y": (255, 242, 170, 255),
    "u": (200, 160, 40, 255),   # amarillo oscuro (botón)
    # Shulker
    "P": (150, 100, 170, 255),
    "p": (108, 68, 128, 255),
    "q": (196, 154, 210, 255),
    # Tierra del mapa / botón verde
    "E": (74, 170, 96, 255),
    "e": (40, 120, 70, 255),
}

ICONS = {
    # Reactor: cubo de basura con la tapa cerrada y un chorro de energía por el fondo (tobera de caza)
    "reactor": [
        "......KKKK......",
        "....KKHLLGKK....",
        "..KHLLLLMMMGGK..",
        "..KKKKKKKKKKKK..",
        "...KHLGLGLGgK...",
        "...KHLGLGLGgK...",
        "..KHMMMMMMMMgK..",
        "...KHLGLGLGgK...",
        "...KHLGLGLGgK...",
        "..KHMMMMMMMMgK..",
        "...KHLGLGLGgK...",
        "...KgOYYYYOgK...",
        "..O.oOYyyYOo.Y..",
        ".....oOYYOo.....",
        "......oYYo......",
        ".......yy.......",
    ],
    # Casco: placa octogonal con panel de shulker, remaches y un trozo de cinta americana
    "hull": [
        ".....KKKKKK.....",
        "...KKHHHHLLKK...",
        "..KHLLLLLLHHGK..",
        ".KHLLLLLLLLHWGK.",
        "KHLLKKKKKKKKHHGK",
        "KHLLKqqPPPPKMGGK",
        "KHLLKqPPPPpKMGGK",
        "KHLLKPPPPppKMGGK",
        "KHLLKPPppppKMGGK",
        "KHLLKPpppppKMGGK",
        "KHLLKKKKKKKKMGGK",
        ".KGLLLLLLMMMGGK.",
        "..KGMMMMMMMGGK..",
        "...KKGGGGGGKK...",
        ".....KKKKKK.....",
        "................",
    ],
    # Cabina: cúpula de cristal con dados colgando, asiento y base con luces
    "cockpit": [
        "................",
        ".....KKKKKK.....",
        "....KcWBBBBK....",
        "...KcBBBBBBbK...",
        "..KcBBBBBBBBbK..",
        "..KBBBBBBBBBbK..",
        "..KBBBBBBBBBbK..",
        ".KBBBBBBBBBBbdK.",
        ".KBBBGGGGGGBbdK.",
        ".KbbbGMMMMGbbdK.",
        ".KKKKKKKKKKKKKK.",
        "KHHLLLLLLLLLMGGK",
        "KHLMMMMMMMMMMGGK",
        "KgGOGGGGGGGOGGgK",
        ".KKKKKKKKKKKKKK.",
        "................",
    ],
    # Consola de navegación: pantalla con mapa (cuadrícula, tierra, posición) y tres botones
    "navigation_console": [
        "................",
        ".KKKKKKKKKKKKKK.",
        ".KHHHHHHHHHHLLK.",
        ".KHKKKKKKKKKKLK.",
        ".KHKcbbdbbbbKLK.",
        ".KHKbEEdbbbbKLK.",
        ".KHKbbbdObbbKLK.",
        ".KHKbbbdbEEbKLK.",
        ".KHKbbbdbbbbKLK.",
        ".KHKKKKKKKKKKLK.",
        ".KHLMMMMMMMMGGK.",
        ".KHLOOMYYMEEGGK.",
        ".KHLooMuuMeeGGK.",
        ".KgGGGGGGGGGGgK.",
        ".KKKKKKKKKKKKKK.",
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
