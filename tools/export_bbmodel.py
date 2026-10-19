#!/usr/bin/env python3
"""
Exporta la nave a un proyecto de Blockbench (.bbmodel, formato libre con UV de caja) a partir de la misma
geometría que usa generate_model.py. Solo lee: no toca el modelo Java ni la textura del mod.

Uso (desde la raíz del repo):  python3 tools/export_bbmodel.py [salida.bbmodel]
"""
import base64
import io
import json
import os
import sys
import uuid

from PIL import Image

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import generate_model as g  # noqa: E402

OUT = sys.argv[1] if len(sys.argv) > 1 else os.path.join(g.ROOT, "space_cruiser.bbmodel")

GROUPS = ["body", "glow", "frame", "dome", "bateria", "botella"]


def uid():
    return str(uuid.uuid4())


def main():
    g.build()
    tex_h = g.pack()
    g.paint(tex_h)
    # El cristal no se pinta en el atlas del mod (usa otra textura): aquí se rellena para verlo en Blockbench.
    img = Image.open(g.TEX_OUT).convert("RGBA")
    px = img.load()
    for c in g.cubes:
        if c["slot"] == "glass":
            x0, y0, w, h = c["rect"]
            for yy in range(y0 - 1, y0 + h + 1):
                for xx in range(x0 - 1, x0 + w + 1):
                    px[xx, yy] = (160, 207, 235, 110)
    buf = io.BytesIO()
    img.save(buf, "PNG")
    data_uri = "data:image/png;base64," + base64.b64encode(buf.getvalue()).decode()

    elements = []
    ids = {}
    for i, c in enumerate(g.cubes):
        u = uid()
        ids[i] = u
        elements.append({
            "name": c["name"], "type": "cube", "uuid": u, "box_uv": True, "rescale": False, "locked": False,
            "from": [round(v, 4) for v in c["frm"]], "to": [round(v, 4) for v in c["to"]],
            "origin": [round(v, 4) for v in c["origin"]], "rotation": [round(v, 4) for v in c["rot"]],
            "uv_offset": [c["uv"][0], c["uv"][1]], "autouv": 0, "color": 0, "export": True,
        })

    outliner = []
    for grp in GROUPS:
        children = []
        for i, c in enumerate(g.cubes):
            if c["group"] == grp and c["lado"] is None:
                children.append(ids[i])
        for k in range(16):
            side = [ids[i] for i, c in enumerate(g.cubes) if c["group"] == grp and c["lado"] == k]
            if not side:
                continue
            yaw = 45 * k if k < 8 else 45 * (k - 8) + 22.5
            children.append({"name": "lado_%d" % k, "origin": [0, 0, 0], "rotation": [0, yaw, 0], "color": 0,
                             "uuid": uid(), "export": True, "isOpen": False, "locked": False, "visibility": True,
                             "autouv": 0, "children": side})
        outliner.append({"name": grp, "origin": [0, 0, 0], "rotation": [0, 0, 0], "color": 0, "uuid": uid(),
                         "export": True, "isOpen": False, "locked": False, "visibility": True, "autouv": 0,
                         "children": children})

    project = {
        "meta": {"format_version": "4.10", "model_format": "free", "box_uv": True},
        "name": "space_cruiser",
        "model_identifier": "space_cruiser",
        "visible_box": [4, 3, 0],
        "variable_placeholders": "",
        "resolution": {"width": g.TEX_W, "height": tex_h},
        "elements": elements,
        "outliner": outliner,
        "textures": [{"path": "", "name": "space_cruiser.png", "folder": "", "namespace": "", "id": "0",
                      "particle": False, "render_mode": "default", "render_sides": "auto", "frame_time": 1,
                      "frame_order_type": "loop", "frame_order": "", "frame_interpolate": False, "visible": True,
                      "internal": True, "saved": False, "uuid": uid(), "source": data_uri,
                      "width": g.TEX_W, "height": tex_h, "uv_width": g.TEX_W, "uv_height": tex_h}],
    }
    with open(OUT, "w") as f:
        json.dump(project, f, separators=(",", ":"))
    print("cubos:", len(elements), "->", OUT)


if __name__ == "__main__":
    main()
