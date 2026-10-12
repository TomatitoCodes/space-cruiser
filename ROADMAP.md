# Space Cruiser — Roadmap

Mod de Minecraft para **Fabric 1.21.1**. Nave pilotable inspirada en la Space Cruiser.

## Estado actual

- [x] Nave pilotable: se puede volar con ella
- [x] Repositorio en GitHub (`space-cruiser`) y compilación del jar con GitHub Actions
- [x] Modelo propio (casco octogonal, cúpula de cristal, propulsores tipo cubo de basura) a escala 14/16
- [x] Al bajarse, el jugador aparece junto a la nave
- [x] GPS del cockpit y nuevo interior (v1.7)
- [x] Cámara de cabina y clic derecho sobre la pantalla del GPS (v1.8)

## Visión

La Space Cruiser es un **item de endgame**: no se consigue al principio, hay que haber progresado por el Nether, el End y el Deep Dark para poder fabricarla.

## Próximas funcionalidades

### 1. Receta de endgame (borrador)

- [ ] Craftear la nave requiere materiales del Nether y del End (referencia de nivel: las elytras)
- [ ] La nave se fabrica por partes, que luego se ensamblan:

| Pieza | Cant. | Dónde | Ingredientes (propuesta) |
|---|---|---|---|
| Reactor | 2 | Mesa de crafteo | Lingote de netherita, lágrima de ghast, vara de blaze, bloque de redstone |
| Casco | 1 | Mesa de herrería | Plantilla de mejora de netherita, caparazones de shulker, varas del End |
| Cabina | 1 | Mesa de crafteo | 5 cristal, elytra, 2 diamantes, caparazón de shulker (patrón abajo) |
| Consola de navegación | 1 | Mesa de crafteo | Recovery compass, ojo de ender |
| Ensamblaje final | 1 | Mesa de crafteo | 2 reactores, casco, cabina, consola |

Patrón de la cabina:

```
C  C  C
C  E  C
D  S  D
```

C = cristal, E = elytra, D = diamante, S = caparazón de shulker

- [ ] Ajustar cantidades y balance al probar en juego

### 2. GPS de navegación dentro del cockpit

Un "navegador GPS" con pantalla dentro de la nave. En primera persona, al interactuar con la pantalla se abre su interfaz.

**Fase 1: GPS básico** (v1.7)
- [x] Pantalla en el cockpit, interactuable en primera persona: clic derecho mirándola (con aviso junto a la mirilla), o tecla `G`
- [x] Datos en vivo: coordenadas, dimensión, rumbo y altura
- [x] Selector de dimensión de destino (Overworld / Nether / End)
- [x] Llegada en las mismas coordenadas (con conversión 1:8 del Nether), buscando un punto seguro libre
- [x] El reactor guarda una batería con capacidad para 10 fire charges, mostrada como barra (sin cifras): 10 LEDs en la consola
- [x] Cada viaje consume 3,5 fire charges (el 35 % de la barra): una carga completa da para 2 viajes
- [x] El coste es el mismo para todas las dimensiones
- [x] Recarga desde el propio GPS, gastando fire charges del inventario del jugador
- [x] Barra de batería visible en la pantalla del GPS
- [ ] Transición del viaje (fundido, efectos) y sonido propio

**Fase 2: Última muerte**
- [ ] Botón para ir al punto de la última muerte (enlaza con el recovery compass de la consola)

**Fase 3: Waypoints**
- [ ] Guardar y elegir puntos propios (casa, base...)

### 3. Modo vuelo (se mantiene)

- [x] Conservar el vuelo libre tal como funciona ahora
- [x] El selector de dimensión y el vuelo conviven en la misma nave

### 4. Manejo y cámara

- [ ] Cámara en tercera persona al pilotar (distancia, ángulo, zoom)
- [x] Cámara en primera persona dentro del cockpit, necesaria para usar el GPS: se activa sola al subir y se recupera la anterior al bajar
- [x] Cambio entre ambas cámaras con la tecla `V`
- [ ] Controles de vuelo: aceleración, frenado, subir y bajar
- [ ] Velocidad máxima, inercia y giro
- [ ] Mejorar la experiencia de manejo en general (se concretará probando la nave)

## Decisiones tomadas

- Llegada: mismas coordenadas (con conversión del Nether) en un punto seguro
- Coste del viaje: 3,5 fire charges por viaje, de una batería en el reactor con capacidad de 10 y mostrada como barra
- Mismo coste de viaje a cualquier dimensión
- La batería se recarga desde el GPS
- Acceso al End: lo cubre la receta, ya que la elytra obliga a llegar a una ciudad del End
- Interfaz: pantalla tipo GPS dentro del cockpit

## Preguntas abiertas

- Manejo y cámara: concretar qué se quiere mejorar exactamente
- La batería no se conserva al recoger la nave como objeto (se pierde); decidir si debe guardarse en el ítem

## Ideas para más adelante

_(vacío por ahora)_
