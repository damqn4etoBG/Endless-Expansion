package net.damqn4etobg.endlessexpansion.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ENDEXP = "key.category.endlessexpansion.name";
    public static final String KEY_DASH = "key.endlessexpansion.dash";
    public static final String KEY_SHOW_CONFIG = "key.endlessexpansion.show_config";

    public static final KeyMapping DASHING_KEY = new KeyMapping(KEY_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY_ENDEXP);
    public static final KeyMapping SHOW_CONFIG_KEY = new KeyMapping(KEY_SHOW_CONFIG, KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_BRACKET, KEY_CATEGORY_ENDEXP);
}
