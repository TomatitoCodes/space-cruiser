package com.spacecruiser.network;

import com.spacecruiser.SpaceCruiserMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/** Orden que el GPS (cliente) manda al servidor: recargar la batería o viajar a una dimensión. */
public record GpsActionPayload(int action) implements CustomPayload {
	public static final int RECHARGE = 0;
	public static final int TRAVEL_OVERWORLD = 1;
	public static final int TRAVEL_NETHER = 2;
	public static final int TRAVEL_END = 3;

	public static final CustomPayload.Id<GpsActionPayload> ID =
			new CustomPayload.Id<>(Identifier.of(SpaceCruiserMod.MOD_ID, "gps_action"));
	public static final PacketCodec<ByteBuf, GpsActionPayload> CODEC =
			PacketCodecs.VAR_INT.xmap(GpsActionPayload::new, GpsActionPayload::action);

	@Override
	public CustomPayload.Id<? extends CustomPayload> getId() {
		return ID;
	}
}
