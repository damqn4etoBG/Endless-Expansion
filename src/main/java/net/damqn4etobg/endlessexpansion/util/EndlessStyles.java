package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndlessStyles {
    private static final Map<String, StyleWithLength> STYLE_TO_ANNOTATION_LENGTH = new HashMap<>();

    public static final Style FLAMMATINE = registerStyle(Style.EMPTY.withColor(0xFFF2661F), "flt");
    public static final Style ITEM_DESCRIPTION = registerStyle(Style.EMPTY.withColor(0xff7f7f7f).withItalic(true), "itemdesc");
    public static final Style SHADOWSTEEL = registerStyle(Style.EMPTY.withColor(0xFF7d408f), "sdw");

    private static final Style EMPTY = registerStyle(Style.EMPTY, "r");

    public static MutableComponent parseCustomAnnotations(Component annotatedComponent) {
        MutableComponent component = Component.empty();
        String fullString = annotatedComponent.getString();
        List<String> separated = Arrays.stream(fullString.split(" ")).toList();
        Style currentStyle = Style.EMPTY;

        for (String s : separated) {
            if (s.startsWith("$")) {
                String noPrefix = s.substring(1);

                for (var entry : STYLE_TO_ANNOTATION_LENGTH.entrySet()) {
                    String annotation = entry.getKey();
                    StyleWithLength sl = entry.getValue();

                    if (noPrefix.startsWith(annotation)) {
                        String actual = noPrefix.substring(sl.length());
                        currentStyle = sl.style;
                        component.append(Component.literal(actual).withStyle(currentStyle));
                    }
                }
            } else {
                component.append(Component.literal(s).withStyle(currentStyle));
            }
            component.append(Component.literal(" "));
        }
        return component;
    }

    public static Style registerStyle(Style style, String annotation) {
        STYLE_TO_ANNOTATION_LENGTH.put(annotation, new StyleWithLength(style, annotation.length()));
        return style;
    }

    public record StyleWithLength(Style style, int length) {
    }
}
