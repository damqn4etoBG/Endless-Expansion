package net.damqn4etobg.endlessexpansion.util;

import com.google.gson.JsonObject;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.GsonHelper;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndlessStyles {
    private static final Map<String, StyleWithLength> STYLE_TO_ANNOTATION_LENGTH = new HashMap<>();
    private static final Pattern ANNOTATION_PATTERN = Pattern.compile("<(\\w+)!([^>]+)>");
    private static final Map<String, MutableComponent> CACHED = new HashMap<>();

    public static final Style FLAMMATINE = registerStyle(Style.EMPTY.withColor(0xFFF2661F), "flt");
    public static final Style ITEM_DESCRIPTION = registerStyle(Style.EMPTY.withColor(0xff7f7f7f).withItalic(true), "itemdesc");
    public static final Style SHADOWSTEEL = registerStyle(Style.EMPTY.withColor(0xFF7d408f), "sdw");
    public static final Style SHADOWSTEEL_ITALIC = registerStyle(SHADOWSTEEL.withItalic(true), "sdwitalic");
    public static final Style ARMOR_SET_BONUS = registerStyle(Style.EMPTY.withColor(0xFFfcc03d).withBold(true), "armorbonus");
    public static final Style HIGHLIGHT1 = registerStyle(Style.EMPTY.withColor(0xFFf7d53b), "hl1");

    private static final Style EMPTY = registerStyle(Style.EMPTY, "r");

    public static MutableComponent parse(Component annotated) {
        String text = annotated.getString();
        MutableComponent result = Component.empty();
        Matcher matcher = ANNOTATION_PATTERN.matcher(text);
        int lastEnd = 0;

        while (matcher.find()) {
            // append normal text before match
            if (matcher.start() > lastEnd) {
                String normalText = text.substring(lastEnd, matcher.start());
                result.append(Component.literal(normalText));
            }

            String annotation = matcher.group(1);
            String content = matcher.group(2);

            StyleWithLength style = STYLE_TO_ANNOTATION_LENGTH.get(annotation);
            if (style != null) result.append(Component.literal(content).withStyle(style.style()));
            else result.append(Component.literal(matcher.group())); // fallback

            lastEnd = matcher.end();
        }

        // append remaining normal text
        if (lastEnd < text.length()) result.append(Component.literal(text.substring(lastEnd)));

        return result;
    }

    // todo: add multiple language support?
    public static void cacheTranslatable() {
        try (Reader reader = Minecraft.getInstance().getResourceManager().openAsReader(
                Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "lang/en_us.json"))) {

            JsonObject root = GsonHelper.parse(reader);
            for (var entry : root.entrySet()) {
                CACHED.put(entry.getKey(), parse(Component.literal(entry.getValue().getAsString())));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MutableComponent getAnnotated(Component component) {
        if (component.getContents() instanceof TranslatableContents t) {
            if (t.getArgs().length > 0) {
                return parse(component);
            } else {
                return CACHED.getOrDefault(t.getKey(), component.copy());
            }
        } else {
            return component.copy();
        }
    }

    public static Style registerStyle(Style style, String annotation) {
        STYLE_TO_ANNOTATION_LENGTH.put(annotation, new StyleWithLength(style, annotation.length()));
        return style;
    }

    public record StyleWithLength(Style style, int length) {
    }
}
