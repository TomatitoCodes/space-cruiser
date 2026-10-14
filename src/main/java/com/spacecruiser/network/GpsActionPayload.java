package com.spacecruiser.network;

import com.spacecruiser.SpaceCruiserMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/** Orden que el GPS (cliente) manda al servidor: recargar la batería o viajar a una dimensión. */
public record GpsActionPayload(int action) implements CustomPacketPayload {
	public static final int RECHARGE = 0;
	public static final int TRAVEL_OVERWORLD = 1;
	public static final int TRAVEL_NETHER = 2;
	public static final int TRAVEL_END = 3;

	public static final CustomPacketPayload.Type<GpsActionPayload> TYPE =
			new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "gps_action"));
	public static final StreamCodec<ByteBuf, GpsActionPayload> CODEC =
			ByteBufCodecs.VAR_INT.map(GpsActionPayload::new, GpsActionPayload::action);

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
