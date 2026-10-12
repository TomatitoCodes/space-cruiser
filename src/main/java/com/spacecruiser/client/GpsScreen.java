package com.spacecruiser.client;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.network.GpsActionPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;

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

	private final ButtonWidget[] destinations = new ButtonWidget[3];
	private final int[] colors = new int[CELLS * CELLS];
	private int left;
	private int top;
	private int ticks;

	public GpsScreen() {
		super(Text.translatable("gui.spacecruiser.gps.title"));
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
			this.destinations[i] = this.addDrawableChild(
					ButtonWidget.builder(Text.translatable("gui.spacecruiser.gps.dim." + names[i]),
									b -> ClientPlayNetworking.send(new GpsActionPayload(action)))
							.dimensions(rx + i * (buttonW + 4), this.top + 138, buttonW, 20)
							.build());
		}
		this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.spacecruiser.gps.recharge"),
						b -> ClientPlayNetworking.send(new GpsActionPayload(GpsActionPayload.RECHARGE)))
				.dimensions(rx, this.top + 164, RIGHT_W, 20)
				.build());
		this.updateMap();
	}

	@Override
	public boolean shouldPause() {
		return false;
	}

	@Override
	public void tick() {
		if (this.client == null || this.client.player == null || !(this.client.player.getVehicle() instanceof SpaceCruiserEntity)) {
			this.close();
			return;
		}
		if (++this.ticks % 10 == 0) {
			this.updateMap();
		}
	}

	private RegistryKey<World> currentDimension() {
		return this.client != null && this.client.world != null ? this.client.world.getRegistryKey() : World.OVERWORLD;
	}

	/** Recalcula los colores del mapa a partir de los chunks cargados alrededor de la nave. */
	private void updateMap() {
		if (this.client == null || this.client.world == null || this.client.player == null) {
			return;
		}
		ClientWorld world = this.client.world;
		BlockPos center = this.client.player.getBlockPos();
		boolean ceiling = world.getDimension().hasCeiling();
		int half = CELLS / 2;
		int[] heights = new int[CELLS * CELLS];
		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int cz = 0; cz < CELLS; cz++) {
			for (int cx = 0; cx < CELLS; cx++) {
				int wx = center.getX() + (cx - half) * BLOCKS_PER_CELL;
				int wz = center.getZ() + (cz - half) * BLOCKS_PER_CELL;
				int index = cz * CELLS + cx;
				if (world.getChunkManager().getChunk(wx >> 4, wz >> 4, ChunkStatus.FULL, false) == null) {
					this.colors[index] = UNKNOWN;
					heights[index] = Integer.MIN_VALUE;
					continue;
				}

				int y;
				if (ceiling) {
					y = Math.min(center.getY() + 2, world.getTopY() - 1);
				} else {
					y = world.getTopY(Heightmap.Type.WORLD_SURFACE, wx, wz) - 1;
				}
				int rgb = -1;
				for (; y >= world.getBottomY(); y--) {
					pos.set(wx, y, wz);
					BlockState state = world.getBlockState(pos);
					MapColor mapColor = state.getMapColor(world, pos);
					if (mapColor != MapColor.CLEAR) {
						rgb = mapColor.color;
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
	public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
		super.renderBackground(ctx, mouseX, mouseY, delta);
		if (this.client == null || this.client.player == null) {
			return;
		}
		RegistryKey<World> dim = currentDimension();
		this.destinations[0].active = dim != World.OVERWORLD;
		this.destinations[1].active = dim != World.NETHER;
		this.destinations[2].active = dim != World.END;

		int x0 = this.left;
		int y0 = this.top;
		ctx.fill(x0 - 2, y0 - 2, x0 + PANEL_W + 2, y0 + PANEL_H + 2, 0xFF1B3A44);
		ctx.fill(x0, y0, x0 + PANEL_W, y0 + PANEL_H, 0xFF0A1418);
		ctx.fill(x0, y0, x0 + PANEL_W, y0 + 16, 0xFF123038);

		int cyan = 0xFF6EE6FF;
		int white = 0xFFE6F7FA;
		ctx.drawText(this.textRenderer, this.title, x0 + 8, y0 + 4, cyan, false);

		var player = this.client.player;
		float yaw = player.getVehicle() != null ? player.getVehicle().getYaw() : player.getYaw();

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
		ctx.drawText(this.textRenderer, "N", mx + MAP_PX / 2 - 2, my + 2, 0xFFFFFFFF, true);

		// Datos en vivo
		int rx = mx + MAP_PX + 10;
		String pos = String.format("X %.0f   Y %.0f   Z %.0f", player.getX(), player.getY(), player.getZ());
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.position"), rx, y0 + 22, cyan, false);
		ctx.drawText(this.textRenderer, pos, rx, y0 + 33, white, false);

		float heading = MathHelper.wrapDegrees(yaw + 180.0f);
		if (heading < 0) {
			heading += 360.0f;
		}
		String dir = DIRS[Math.round(heading / 45.0f) % 8];
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.heading"), rx, y0 + 48, cyan, false);
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.dir." + dir).append(String.format("  %.0f°", heading)),
				rx, y0 + 59, white, false);

		String dimName = dim == World.OVERWORLD ? "overworld" : dim == World.NETHER ? "nether" : dim == World.END ? "end" : null;
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.dimension"), rx + 90, y0 + 48, cyan, false);
		ctx.drawText(this.textRenderer,
				dimName != null ? Text.translatable("gui.spacecruiser.gps.dim." + dimName) : Text.literal(dim.getValue().getPath()),
				rx + 90, y0 + 59, white, false);

		// Batería del reactor: barra de 10 tramos, sin cifras.
		float battery = player.getVehicle() instanceof SpaceCruiserEntity ship ? ship.getBattery() : 0.0f;
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.battery"), rx, y0 + 76, cyan, false);
		int lit = (int) Math.ceil(battery - 0.001f);
		for (int i = 0; i < 10; i++) {
			int bx = rx + i * 17;
			ctx.fill(bx, y0 + 88, bx + 15, y0 + 98, i < lit ? 0xFFFF9923 : 0xFF2A2F33);
		}
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.destination"), rx, y0 + 124, cyan, false);
	}
}
