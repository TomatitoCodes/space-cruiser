# Space Cruiser — Roadmap

Mod de Minecraft para **Fabric 1.21.1**. Nave pilotable inspirada en la Space Cruiser.

## Estado actual

- [x] Nave pilotable: se puede volar con ella
- [x] Repositorio en GitHub (`space-cruiser`) y compilación del jar con GitHub Actions
- [x] Modelo propio (casco octogonal, cúpula de cristal, propulsores tipo cubo de basura) a escala 12/16
- [x] Al bajarse, el jugador aparece junto a la nave
- [x] GPS del cockpit y nuevo interior (v1.7)
- [x] Cámara de cabina y clic derecho sobre la pantalla del GPS (v1.8)
- [x] Mirada libre de 180° en la cabina, A/D para girar solo en primera persona y GPS con mapa del terreno (v1.9)
- [x] Una sola nave a escala 12/16, asiento 2 px más atrás, volante de mando en el centro, GPS-mapa en el lado izquierdo, propulsores cuadrados y pintura inferior mejorada (v2.1)

## Ambientación (guiños)

- [x] Botella de cristal con líquido verde en el suelo del cockpit (modelo propio, cristal translúcido, decorativa) (v2.5)
- [x] Partículas verdes en espiral al cambiar de dimensión, a modo de portal (v2.5)
- [ ] Sonidos propios para el viaje (ahora usa el sonido de teletransporte del Enderman como provisional)
- Todo el arte y los sonidos serán originales, sin copiar los de la serie

## Visión

La Space Cruiser es un **item de endgame**: no se consigue al principio, hay que haber progresado por el Nether, el End y el Deep Dark para poder fabricarla.

## Próximas funcionalidades

### 1. Receta de endgame (v2.4, borrador)

La nave se fabrica por partes, todas en mesa de crafteo, que luego se ensamblan:

| Pieza | Cant. | Ingredientes |
|---|---|---|
| Reactor | 2 | 4 bloques de magma, 2 lingotes de netherita, 2 blast furnaces, heavy core |
| Casco | 1 | 4 caparazones de shulker, 2 diamantes, 3 cristales de prismarina |
| Cabina | 1 | 5 cristal tintado, elytra, 2 barras del End, caparazón de shulker |
| Consola de navegación | 1 | Recovery compass, 4 ojos de ender, 4 bloques de redstone |
| Ensamblaje final | 1 | 2 reactores, casco, cabina, consola |

Patrones (M magma, N netherita, B blast furnace, C heavy core / S shulker, D diamante, P cristal de prismarina / T cristal tintado, E elytra, R barra del End / R bloque de redstone, O ojo de ender, C recovery compass):

```
Reactor     Casco     Cabina    Consola   Ensamblaje
M N M       S D S     T T T     R O R     - H -
B C B       P P P     T E T     O C O     R K R
M N M       S D S     R S R     R O R     - N -
```

H = casco, R = reactor, K = cabina, N = consola

Zonas que hay que haber pasado: Nether (netherita, magma), End (elytra, shulker, barras), Océano/monumento (prismarina), Deep Dark (recovery compass), Trial Chambers (heavy core), geodas de amatista (cristal tintado).

- [x] Recetas y objetos de las piezas implementados (iconos provisionales; los definitivos se sustituyen por los mismos nombres de archivo)
- [ ] Ajustar cantidades y balance al probar en juego

### 2. GPS de navegación dentro del cockpit

Un "navegador GPS" con pantalla dentro de la nave. En primera persona, al interactuar con la pantalla se abre su interfaz.

**Fase 1: GPS básico** (v1.7)
- [x] Pantalla en el cockpit, interactuable en primera persona: clic derecho mirándola (con aviso junto a la mirilla), o tecla `G`
- [x] Mapa del terreno bajo la nave (norte arriba, con la posición y el rumbo)
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
- [x] Cámara exterior: la de tercera persona de siempre (F5); sin ajustes propios todavía
- [x] La mirada del piloto es libre 90° a cada lado de la proa (180° en total) sin girar la nave
- [x] Controles de vuelo: W/S avanzar y frenar, A/D girar la nave (solo en primera persona; en tercera, como antes: la nave sigue la mirada y A/D desplazan de lado), Espacio sube y Ctrl baja
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
