package com.mojang.blaze3d.platform;

import com.mojang.logging.LogUtils;
import java.nio.IntBuffer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.sdl.SDLError;
import org.lwjgl.sdl.SDLMessageBox;
import org.lwjgl.sdl.SDL_MessageBoxButtonData;
import org.lwjgl.sdl.SDL_MessageBoxData;
import org.lwjgl.sdl.SDL_MessageBoxButtonData.Buffer;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;

@Environment(EnvType.CLIENT)
public class MessageBox {
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final String DEFAULT_TITLE = "Minecraft";
	private static final int BUTTON_ID_YES = 1;
	private static final String BUTTON_TITLE_YES = "Yes";
	private static final int BUTTON_ID_NO = 2;
	private static final String BUTTON_TITLE_NO = "No";

	public static void error(final String message) {
		if (!SDLMessageBox.SDL_ShowSimpleMessageBox(16, "Minecraft", message, 0L)) {
			String error = SDLError.SDL_GetError();
			LOGGER.error("Failed to show error message '{}': {}", message, error);
		}
	}

	public static boolean errorWithContinue(final String message) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer buttonResult = stack.callocInt(1);
			Buffer buttonData = SDL_MessageBoxButtonData.calloc(2, stack);
			buttonData.get(0).buttonID(1).text(stack.UTF8("Yes"));
			buttonData.get(1).buttonID(2).text(stack.UTF8("No"));
			SDL_MessageBoxData data = SDL_MessageBoxData.calloc(stack)
				.flags(16)
				.title(stack.UTF8("Minecraft", true))
				.message(stack.UTF8(message, true))
				.buttons(buttonData);
			if (SDLMessageBox.SDL_ShowMessageBox(data, buttonResult)) {
				return buttonResult.get(0) == 1;
			}

			String error = SDLError.SDL_GetError();
			LOGGER.error("Failed to show error message '{}': {}", message, error);
			return false;
		}
	}
}
