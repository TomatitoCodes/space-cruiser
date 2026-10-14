package com.spacecruiser.client;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.network.GpsActionPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.MapColor;

/** Pantalla del GPS del cockpit: mapa del terreno bajo la nave, datos en vivo, batería, destino y recarga. */
public class GpsScreen extends Screen {
	/** El mapa son CELLS x CELLS celdas; cada celda abarca BLOCKS_PER_CELL bloques y se dibuja de CELL_PX píxeles. */
	private static final int CELLS = 80;
	private static final int BLOCKS_PER_CELL = 2;
	private static final int CELL_PX = 2;
	private static final int MAP_PX = CELLS * CELL_PX;

	private static final int RIGHT_W = 176;
	private static final int PANEL_W = 8 + MAP_PX + 10 + RIGHT_W + 8;
	private static final int PANEL_H = 194;
	private static final String[] DIRS = {"n", "ne", "e", "se", "s", "sw", "w", "nw"};

	private static final int UNKNOWN = 0xFF1C2226;

	private final Button[] destinations = new Button[3];
	private final int[] colors = new int[CELLS * CELLS];
	private int left;
	private int top;
	private int ticks;

	public GpsScreen() {
		super(Component.translatable("gui.spacecruiser.gps.title"));
	}

	@Override
	protected void init() {
		this.left = (this.width - PANEL_W) / 2;
		this.top = (this.height - PANEL_H) / 2;
		int rx = this.left + 8 + MAP_PX + 10;

		int[] actions = {GpsActionPayload.TRAVEL_OVERWORLD, GpsActionPayload.TRAVEL_NETHER, GpsActionPayload.TRAVEL_END};
		String[] names = {"overworld", "nether", "end"};
		int buttonW = 56;
		for (int i = 0; i < 3; i++) {
			int action = actions[i];
			this.destinations[i] = this.addRenderableWidget(
					Button.builder(Component.translatable("gui.spacecruiser.gps.dim." + names[i]),
									b -> ClientPlayNetworking.send(new GpsActionPayload(action)))
							.bounds(rx + i * (buttonW + 4), this.top + 138, buttonW, 20)
							.build());
		}
		this.addRenderableWidget(Button.builder(Component.translatable("gui.spacecruiser.gps.recharge"),
						b -> ClientPlayNetworking.send(new GpsActionPayload(GpsActionPayload.RECHARGE)))
				.bounds(rx, this.top + 164, RIGHT_W, 20)
				.build());
		this.updateMap();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void tick() {
		if (this.minecraft == null || this.minecraft.player == null || !(this.minecraft.player.getVehicle() instanceof SpaceCruiserEntity)) {
			this.onClose();
			return;
		}
		if (++this.ticks % 10 == 0) {
			this.updateMap();
		}
	}

	private ResourceKey<Level> currentDimension() {
		return this.minecraft != null && this.minecraft.level != null ? this.minecraft.level.dimension() : Level.OVERWORLD;
	}

	/** Recalcula los colores del mapa a partir de los chunks cargados alrededor de la nave. */
	private void updateMap() {
		if (this.minecraft == null || this.minecraft.level == null || this.minecraft.player == null) {
			return;
		}
		ClientLevel world = this.minecraft.level;
		BlockPos center = this.minecraft.player.blockPosition();
		boolean ceiling = world.dimensionType().hasCeiling();
		int half = CELLS / 2;
		int[] heights = new int[CELLS * CELLS];
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int cz = 0; cz < CELLS; cz++) {
			for (int cx = 0; cx < CELLS; cx++) {
				int wx = center.getX() + (cx - half) * BLOCKS_PER_CELL;
				int wz = center.getZ() + (cz - half) * BLOCKS_PER_CELL;
				int index = cz * CELLS + cx;
				if (world.getChunkSource().getChunk(wx >> 4, wz >> 4, ChunkStatus.FULL, false) == null) {
					this.colors[index] = UNKNOWN;
					heights[index] = Integer.MIN_VALUE;
					continue;
				}

				int y;
				if (ceiling) {
					y = Math.min(center.getY() + 2, world.getMaxY());
				} else {
					y = world.getHeight(Heightmap.Types.WORLD_SURFACE, wx, wz) - 1;
				}
				int rgb = -1;
				for (; y >= world.getMinY(); y--) {
					pos.set(wx, y, wz);
					BlockState state = world.getBlockState(pos);
					MapColor mapColor = state.getMapColor(world, pos);
					if (mapColor != MapColor.NONE) {
						rgb = mapColor.col;
						break;
					}
				}
				if (rgb < 0) {
					this.colors[index] = UNKNOWN;
					heights[index] = Integer.MIN_VALUE;
					continue;
				}
				heights[index] = y;

				// Sombreado como el de los mapas: más claro si el terreno sube hacia el sur, más oscuro si baja.
				double shade = 1.0;
				if (cz > 0 && heights[index - CELLS] != Integer.MIN_VALUE) {
					int diff = y - heights[index - CELLS];
					shade = diff > 0 ? 1.18 : diff < 0 ? 0.82 : 1.0;
				}
				int r = Math.min(255, (int) (((rgb >> 16) & 0xFF) * shade));
				int g = Math.min(255, (int) (((rgb >> 8) & 0xFF) * shade));
				int b = Math.min(255, (int) ((rgb & 0xFF) * shade));
				this.colors[index] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}

	/** Se dibujan el panel, el mapa y los datos como fondo; los botones los pinta después Screen.render. */
	@Override
	public void extractBackground(GuiGraphicsExtractor ctx, int mouseX, int mouseY, float delta) {
		super.extractBackground(ctx, mouseX, mouseY, delta);
		if (this.minecraft == null || this.minecraft.player == null) {
			return;
		}
		ResourceKey<Level> dim = currentDimension();
		this.destinations[0].active = dim != Level.OVERWORLD;
		this.destinations[1].active = dim != Level.NETHER;
		this.destinations[2].active = dim != Level.END;

		int x0 = this.left;
		int y0 = this.top;
		ctx.fill(x0 - 2, y0 - 2, x0 + PANEL_W + 2, y0 + PANEL_H + 2, 0xFF1B3A44);
		ctx.fill(x0, y0, x0 + PANEL_W, y0 + PANEL_H, 0xFF0A1418);
		ctx.fill(x0, y0, x0 + PANEL_W, y0 + 16, 0xFF123038);

		int cyan = 0xFF6EE6FF;
		int white = 0xFFE6F7FA;
		ctx.text(this.font, this.title, x0 + 8, y0 + 4, cyan, false);

		var player = this.minecraft.player;
		float yaw = player.getVehicle() != null ? player.getVehicle().getYRot() : player.getYRot();

		// Mapa (norte arriba). Cada fila se pinta por tramos del mismo color para no saturar de rectángulos.
		int mx = x0 + 8;
		int my = y0 + 22;
		ctx.fill(mx - 1, my - 1, mx + MAP_PX + 1, my + MAP_PX + 1, 0xFF3C7C8C);
		for (int cz = 0; cz < CELLS; cz++) {
			int runStart = 0;
			for (int cx = 1; cx <= CELLS; cx++) {
				if (cx == CELLS || this.colors[cz * CELLS + cx] != this.colors[cz * CELLS + runStart]) {
					ctx.fill(mx + runStart * CELL_PX, my + cz * CELL_PX, mx + cx * CELL_PX, my + (cz + 1) * CELL_PX,
							this.colors[cz * CELLS + runStart]);
					runStart = cx;
				}
			}
		}
		// Cuadrícula tenue cada 16 celdas y marca de la nave con su rumbo.
		for (int i = 16; i < CELLS; i += 16) {
			ctx.fill(mx + i * CELL_PX, my, mx + i * CELL_PX + 1, my + MAP_PX, 0x30FFFFFF);
			ctx.fill(mx, my + i * CELL_PX, mx + MAP_PX, my + i * CELL_PX + 1, 0x30FFFFFF);
		}
		int cx = mx + MAP_PX / 2;
		int cy = my + MAP_PX / 2;
		double yawRad = Math.toRadians(yaw);
		double fx = -Math.sin(yawRad);
		double fz = Math.cos(yawRad);
		for (int t = 2; t <= 9; t++) {
			int px = cx + (int) Math.round(fx * t * 2.2);
			int pz = cy + (int) Math.round(fz * t * 2.2);
			ctx.fill(px - 1, pz - 1, px + 1, pz + 1, 0xFFFF4040);
		}
		ctx.fill(cx - 3, cy - 3, cx + 3, cy + 3, 0xFF000000);
		ctx.fill(cx - 2, cy - 2, cx + 2, cy + 2, 0xFFFFFFFF);
		ctx.text(this.font, "N", mx + MAP_PX / 2 - 2, my + 2, 0xFFFFFFFF, true);

		// Datos en vivo
		int rx = mx + MAP_PX + 10;
		String pos = String.format("X %.0f   Y %.0f   Z %.0f", player.getX(), player.getY(), player.getZ());
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.position"), rx, y0 + 22, cyan, false);
		ctx.text(this.font, pos, rx, y0 + 33, white, false);

		float heading = Mth.wrapDegrees(yaw + 180.0f);
		if (heading < 0) {
			heading += 360.0f;
		}
		String dir = DIRS[Math.round(heading / 45.0f) % 8];
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.heading"), rx, y0 + 48, cyan, false);
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.dir." + dir).append(String.format("  %.0f°", heading)),
				rx, y0 + 59, white, false);

		String dimName = dim == Level.OVERWORLD ? "overworld" : dim == Level.NETHER ? "nether" : dim == Level.END ? "end" : null;
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.dimension"), rx + 90, y0 + 48, cyan, false);
		ctx.drawText(this.font,
				dimName != null ? Component.translatable("gui.spacecruiser.gps.dim." + dimName) : Component.literal(dim.identifier().getPath()),
				rx + 90, y0 + 59, white, false);

		// Batería del reactor: barra de 10 tramos, sin cifras.
		float battery = player.getVehicle() instanceof SpaceCruiserEntity ship ? ship.getBattery() : 0.0f;
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.battery"), rx, y0 + 76, cyan, false);
		int lit = (int) Math.ceil(battery - 0.001f);
		for (int i = 0; i < 10; i++) {
			int bx = rx + i * 17;
			ctx.fill(bx, y0 + 88, bx + 15, y0 + 98, i < lit ? 0xFFFF9923 : 0xFF2A2F33);
		}
		ctx.text(this.font, Component.translatable("gui.spacecruiser.gps.destination"), rx, y0 + 124, cyan, false);
	}
}
