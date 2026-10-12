#!/usr/bin/env python3
"""Genera los iconos 16x16 de los items de la receta de la Space Cruiser.

Uso: python3 tools/generate_icons.py [directorio_preview]
Escribe los PNG en src/main/resources/assets/spacecruiser/textures/item/ y,
si se pasa un directorio, una hoja de previsualización ampliada.
"""
import os
import sys
from PIL import Image

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "src/main/resources/assets/spacecruiser/textures/item")

# Paleta tomada del icono actual de la nave (space_cruiser.png)
PALETTE = {
    ".": (0, 0, 0, 0),
    "K": (52, 54, 60, 255),     # contorno / sombra
    "G": (96, 98, 104, 255),    # gris medio
    "L": (150, 152, 158, 255),  # gris claro
    "B": (160, 210, 238, 255),  # cristal claro
    "b": (110, 165, 205, 255),  # cristal oscuro
    "W": (232, 246, 255, 255),  # brillo
    "O": (255, 150, 32, 255),   # naranja (energía)
    "Y": (255, 214, 64, 255),   # amarillo (núcleo)
}

ICONS = {
    # Reactor: cilindro con el núcleo de energía a la vista
    "reactor": [
        "................",
        ".....KKKKKK.....",
        "....KLLLLLLK....",
        "...KKGGGGGGKK...",
        "...KGKKKKKKGK...",
        "...KGKOOOOKGK...",
        "...KGKOYYOKGK...",
        "...KGKOYYOKGK...",
        "...KGKOYYOKGK...",
        "...KGKOYYOKGK...",
        "...KGKOOOOKGK...",
        "...KGKKKKKKGK...",
        "...KKGGGGGGKK...",
        "....KLLLLLLK....",
        ".....KKKKKK.....",
        "................",
    ],
    # Casco: placa octogonal con remaches y escotilla
    "hull": [
        "................",
        "....KKKKKKKK....",
        "...KLLLLLLLLK...",
        "..KLGGGGGGGGLK..",
        ".KLGGKGGGGKGGLK.",
        ".KLGGGGGGGGGGLK.",
        ".KLGGGKKKKGGGLK.",
        ".KLGGGKbbKGGGLK.",
        ".KLGGGKbbKGGGLK.",
        ".KLGGGKKKKGGGLK.",
        ".KLGGGGGGGGGGLK.",
        ".KLGGKGGGGKGGLK.",
        "..KLGGGGGGGGLK..",
        "...KLLLLLLLLK...",
        "....KKKKKKKK....",
        "................",
    ],
    # Cabina: cúpula de cristal sobre su base
    "cockpit": [
        "................",
        ".....KKKKKK.....",
        "....KBBBBBBK....",
        "...KBWBBBBBbK...",
        "..KBWBBBBBBBbK..",
        "..KBBBBBBBBBbK..",
        "..KBBBBBBBBbbK..",
        "..KBBBBBBBbbbK..",
        "..KbbbbbbbbbbK..",
        "..KKKKKKKKKKKK..",
        ".KLLLLLLLLLLLLK.",
        ".KGGGGGGGGGGGGK.",
        ".KGKGGGGGGGGKGK.",
        ".KGGGGGGGGGGGGK.",
        "..KKKKKKKKKKKK..",
        "................",
    ],
    # Consola de navegación: pantalla del GPS con marcador y botones
    "navigation_console": [
        "................",
        "................",
        "................",
        "..KKKKKKKKKKKK..",
        ".KLLLLLLLLLLLLK.",
        ".KLKKKKKKKKKKLK.",
        ".KLKbbbbbbbbKLK.",
        ".KLKbBBBBBBbKLK.",
        ".KLKbBBOBBBbKLK.",
        ".KLKbBBBBBBbKLK.",
        ".KLKbbbbbbbbKLK.",
        ".KLKKKKKKKKKKLK.",
        ".KGGOGGGGGGYGGK.",
        ".KGGGGGGGGGGGGK.",
        ".KKKKKKKKKKKKKK.",
        "................",
    ],
}


def build(grid):
    assert len(grid) == 16, "debe tener 16 filas"
    im = Image.new("RGBA", (16, 16))
    for y, row in enumerate(grid):
        assert len(row) == 16, f"fila {y} mide {len(row)}"
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
            # tamaño real (x1) y x2 bajo cada casilla
            sheet.alpha_composite(t, (x + 12, gap + slot + 8))
            sheet.alpha_composite(t.resize((32, 32), Image.NEAREST), (x + 40, gap + slot + 4))
        sheet.convert("RGB").save(os.path.join(prev, "preview_iconos.png"))
        print("Preview:", os.path.join(prev, "preview_iconos.png"))


if __name__ == "__main__":
    main()
