package net.damqn4etobg.endlessexpansion.screen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuideScreen extends Screen {
    int imageWidth = 200;
    int imageHeight = 200;
    int page = 0;

    public GuideScreen() {
        super(Component.literal("Guide"));
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        Map<Integer, Section> map = makeSections();
        Font font = Minecraft.getInstance().font;
        Section section = map.get(page);

        if(section != null) {
            pGuiGraphics.fill(x - 100, y - 25, x + 300, y + 200, 0xFFE8C280);
            pGuiGraphics.drawString(font, section.name, x, y - 25, -1);
            pGuiGraphics.drawString(font, FormattedCharSequence.composite(section.text), x, y, -1);
        }
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    private Map<Integer, Section> makeSections() {
        Map<Integer, Section> sections = new HashMap<>();
        Font font = Minecraft.getInstance().font;
        int page = 0;

        try(Reader reader = Minecraft.getInstance().getResourceManager().openAsReader(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "texts/test_guide.json"))) {
            for(JsonElement elem : GsonHelper.parseArray(reader)) {
                JsonObject sectionElem = elem.getAsJsonObject().getAsJsonObject("section");
                String name = sectionElem.get("name").getAsString();
                String rawText = sectionElem.get("text").getAsString();
                boolean underlined = sectionElem.get("underlined").getAsBoolean();
                List<FormattedText> allTextParts = new ArrayList<>();

                for (String word : rawText.split(" ")) {
                    if (word.startsWith("&col")) {
                        int color = Integer.parseInt(word.substring(5, 11), 16);
                        allTextParts.add(FormattedText.of(" " + word.substring(11) + " ", Style.EMPTY.withColor(color)));
                    } else if (word.startsWith("&r")) {
                        allTextParts.add(FormattedText.of(word.substring(2) + " "));
                    } else {
                        allTextParts.add(FormattedText.of(word + " "));
                    }
                }

                FormattedText formatted = FormattedText.composite(allTextParts);
                List<FormattedCharSequence> formattedText = font.split(formatted, 200);
                Section section = new Section(name, formattedText, null, underlined);

                sections.put(page, section);
                page++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sections;
    }

    private void renderSectionContents(GuiGraphics graphics, int mouseX, int mouseY, int page) {
        Map<Integer, Section> map = makeSections();
        List<SectionContent> contents = map.get(page).getContents();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(contents == null) return;

        for(SectionContent content : contents) {
            if(content.type.equals(SectionContent.Type.ITEM_DISPLAY)) {
                for (Item item : content.items) {
                    ItemStack stack = new ItemStack(item);
                    graphics.renderItem(stack, x, y);
                    graphics.renderTooltip(this.font, stack, x, y);
                }
            }
        }
    }

    public static class Section {
        final String name;
        final List<FormattedCharSequence> text;
        final @Nullable List<SectionContent> content;
        final boolean underlined;

        public Section(String name, List<FormattedCharSequence> text, @Nullable List<SectionContent> content, boolean underlined) {
            this.name = name;
            this.text = text;
            this.content = content;
            this.underlined = underlined;
        }

        public List<SectionContent> getContents() {
            return this.content;
        }
    }

    public static class SectionContent {
        final @Nullable List<Item> items;
        final Type type;

        public SectionContent(@Nullable List<Item> items, Type type) {
            this.items = items;
            this.type = type;
        }

        public static SectionContent itemDisplay(List<Item> items) {
            return new SectionContent(items, Type.ITEM_DISPLAY);
        }

        public enum Type {
            @SerializedName("item_display") ITEM_DISPLAY
        }
    }
}
