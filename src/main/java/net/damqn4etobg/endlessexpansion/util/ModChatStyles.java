package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.network.chat.Style;

public class ModChatStyles {
    public static final Style ORANGE = registerStyle(0xFFA500);
    public static final Style LIGHT_GRAY = registerStyle(0xCCCCCC);
    public static final Style GREEN_OFF = registerStyle(0x33CC33);
    public static final Style RED_OFF = registerStyle(0xCC3333);

    private static Style registerStyle(int color) {
        return Style.EMPTY.withColor(color);
    }
}
