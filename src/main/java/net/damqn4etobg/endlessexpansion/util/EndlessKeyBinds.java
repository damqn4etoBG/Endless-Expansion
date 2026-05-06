package net.damqn4etobg.endlessexpansion.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class EndlessKeyBinds {
    public static final KeyMapping.Category ENDLESS_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "name"));

    public static final KeyMapping KEY_DASH = new KeyMapping("key.endlessexpansion.dash", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, ENDLESS_CATEGORY);
}
