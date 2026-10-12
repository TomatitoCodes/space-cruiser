package com.spacecruiser.client;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.network.GpsActionPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/** Pantalla del GPS del cockpit: datos en vivo, batería, destino y recarga. */
public class GpsScreen extends Screen {
	private static final int PANEL_W = 232;
	private static final int PANEL_H = 168;
	private static final String[] DIRS = {"n", "ne", "e", "se", "s", "sw", "w", "nw"};

	private final ButtonWidget[] destinations = new ButtonWidget[3];
	private int left;
	private int top;

	public GpsScreen() {
		super(Text.translatable("gui.spacecruiser.gps.title"));
	}

	@Override
	protected void init() {
		this.left = (this.width - PANEL_W) / 2;
		this.top = (this.height - PANEL_H) / 2;

		int[] actions = {GpsActionPayload.TRAVEL_OVERWORLD, GpsActionPayload.TRAVEL_NETHER, GpsActionPayload.TRAVEL_END};
		String[] names = {"overworld", "nether", "end"};
		int buttonW = 70;
		for (int i = 0; i < 3; i++) {
			int action = actions[i];
			this.destinations[i] = this.addDrawableChild(
					ButtonWidget.builder(Text.translatable("gui.spacecruiser.gps.dim." + names[i]),
									b -> ClientPlayNetworking.send(new GpsActionPayload(action)))
							.dimensions(this.left + 8 + i * (buttonW + 7), this.top + 114, buttonW, 20)
							.build());
		}
		this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.spacecruiser.gps.recharge"),
						b -> ClientPlayNetworking.send(new GpsActionPayload(GpsActionPayload.RECHARGE)))
				.dimensions(this.left + 8, this.top + 140, PANEL_W - 16, 20)
				.build());
	}

	@Override
	public boolean shouldPause() {
		return false;
	}

	@Override
	public void tick() {
		if (this.client == null || !(this.client.player != null && this.client.player.getVehicle() instanceof SpaceCruiserEntity)) {
			this.close();
		}
	}

	private RegistryKey<World> currentDimension() {
		return this.client != null && this.client.world != null ? this.client.world.getRegistryKey() : World.OVERWORLD;
	}

	/** Se dibuja el panel y los datos como fondo; los botones los pinta después Screen.render. */
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
		String pos = String.format("X %.1f   Y %.1f   Z %.1f", player.getX(), player.getY(), player.getZ());
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.position"), x0 + 8, y0 + 24, cyan, false);
		ctx.drawText(this.textRenderer, pos, x0 + 8, y0 + 35, white, false);

		float heading = MathHelper.wrapDegrees(player.getVehicle().getYaw() + 180.0f);
		if (heading < 0) {
			heading += 360.0f;
		}
		String dir = DIRS[Math.round(heading / 45.0f) % 8];
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.heading"), x0 + 8, y0 + 50, cyan, false);
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.dir." + dir).append(String.format("  %.0f°", heading)),
				x0 + 8, y0 + 61, white, false);

		String dimName = dim == World.OVERWORLD ? "overworld" : dim == World.NETHER ? "nether" : dim == World.END ? "end" : null;
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.dimension"), x0 + 124, y0 + 50, cyan, false);
		ctx.drawText(this.textRenderer,
				dimName != null ? Text.translatable("gui.spacecruiser.gps.dim." + dimName) : Text.literal(dim.getValue().getPath()),
				x0 + 124, y0 + 61, white, false);

		// Batería del reactor: barra de 10 tramos, sin cifras.
		float battery = player.getVehicle() instanceof SpaceCruiserEntity ship ? ship.getBattery() : 0.0f;
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.battery"), x0 + 8, y0 + 76, cyan, false);
		int lit = (int) Math.ceil(battery - 0.001f);
		for (int i = 0; i < 10; i++) {
			int bx = x0 + 8 + i * 22;
			ctx.fill(bx, y0 + 87, bx + 20, y0 + 97, i < lit ? 0xFFFF9923 : 0xFF2A2F33);
		}
		ctx.drawText(this.textRenderer, Text.translatable("gui.spacecruiser.gps.destination"), x0 + 8, y0 + 102, cyan, false);
	}
}
