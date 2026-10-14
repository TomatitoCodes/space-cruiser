package com.spacecruiser.client;

import net.minecraft.client.renderer.entity.state.EntityRenderState;

/** Datos de la nave que necesita el renderer (en 26.x se copian de la entidad antes de dibujar). */
public class SpaceCruiserRenderState extends EntityRenderState {
	/** Rumbo de la nave, en grados. */
	public float yRot;
	public float leanPitch;
	public float leanRoll;
	/** Escala con la que se dibuja la nave. */
	public float modelScale = 1.0f;
	/** Pilotos de la batería que están encendidos (0 a 10). */
	public int batteryLit;
	/** En primera persona a bordo se oculta la cúpula para no taparte la vista. */
	public boolean hideDome;
}
