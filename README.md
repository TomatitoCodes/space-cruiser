# Space Cruiser (Fabric 1.21.1)

Nave espacial pilotable: casco octogonal gris, franjas luminosas amarillas, propulsores laterales con luces ámbar y cúpula de cristal.

## Compilar y probar

Requiere JDK 21.

```
./gradlew runClient     # abre Minecraft 1.21.1 con el mod
./gradlew build         # genera build/libs/spacecruiser-0.1.0.jar
```

Si Gradle no resuelve alguna versión, mira las actuales para 1.21.1 en https://fabricmc.net/develop/ y cámbialas en `gradle.properties` (`loader_version`, `fabric_version`, `yarn_mappings`) y en `build.gradle` (versión de Loom).

## Cómo se usa

- **Receta** (mesa de crafteo, patrón de tu imagen):

  | | | |
  |---|---|---|
  | Losa de piedra | Cristal | Losa de piedra |
  | Lingote de hierro | Horno alto | Lingote de hierro |
  | Lingote de oro | Losa de piedra | Lingote de oro |

  Está en `data/spacecruiser/recipe/space_cruiser.json`; cambia ahí cualquier ingrediente.
- Click derecho sobre un bloque para colocar la nave; click derecho sobre la nave para subir.
- **W/A/S/D**: moverse. La nave gira hacia donde miras.
- **Espacio**: subir. **Ctrl izquierdo**: bajar (se puede cambiar en Controles).
- **Shift**: bajarse. Golpear la nave la recoge (en creativo no suelta el objeto).
- Sin piloto, la nave desciende despacio y se posa. No hay daño por caída a bordo.
- En primera persona la cúpula se oculta para no taparte la vista.

## Estructura

- `entity/SpaceCruiserEntity` — física y controles de vuelo (constantes de velocidad al principio).
- `client/SpaceCruiserModel` — modelo construido por código (medidas en píxeles, 16 = 1 bloque).
- `client/SpaceCruiserRenderer` — render: inclinación, luces a brillo máximo y cristal translúcido.
- `resources/assets/spacecruiser/textures/entity/` — `space_cruiser.png` (8 franjas de color de 32 px) y `space_cruiser_glass.png`.

## Ideas para siguientes pasos

- Sonido de motor y sonido al "viajo dimensional" (portal) con una tecla.
- Combustible, sistema de vida o segundo asiento.
- Modelo en Blockbench + texturas pintadas a mano en lugar del modelo por código.
