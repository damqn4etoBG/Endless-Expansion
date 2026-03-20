package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import com.google.gson.JsonObject;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class WandEvolution {
    private final ResourceLocation id;
    private Component name = Component.empty();
    private Component description = Component.empty();
    @Nullable
    private ItemStack icon;
    @Nullable
    private ResourceLocation icon_texture;
    private int xpCost;
    private int cooldown;

    private WandEvolution(ResourceLocation id) {
        this.id = id;
    }

    public WandEvolution(String id) {
        this.id = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "evolution_table/evolutions/" + id);
    }

    public void use(Level level, Player player, ItemStack stack) {
    }

    public static WandEvolution empty() {
        return new WandEvolution("empty");
    }

    public static WandEvolution fromJson(ResourceLocation id, JsonObject json) {
        Component name = Component.Serializer.fromJson(json.get("name"));
        Component description = Component.Serializer.fromJson(json.get("description"));

        ItemStack icon = null;
        ResourceLocation texture = null;
        int xpCost = 0;
        int cooldown = 0;

        if (name != null && description != null) {
            if (json.has("icon")) {
                JsonObject iconObj = json.getAsJsonObject("icon");

                if (iconObj.has("item")) {
                    Item item = GsonHelper.getAsItem(iconObj, "item");
                    icon = new ItemStack(item);
                } else if (iconObj.has("texture")) {
                    texture = ResourceLocation.parse(GsonHelper.getAsString(iconObj, "texture"));
                }
            }
            if(json.has("cost")) {
                JsonObject costObj = json.getAsJsonObject("cost");
                xpCost = GsonHelper.getAsInt(costObj, "xp");
            }
            if(json.has("cooldown")) {
                cooldown = GsonHelper.getAsInt(json, "cooldown");
            }
        }

        WandEvolution evolution = new WandEvolution(id);
        evolution.name = name;
        evolution.description = description;
        evolution.icon = icon;
        evolution.icon_texture = texture;
        evolution.xpCost = xpCost;
        evolution.cooldown = cooldown;

        return evolution;
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.id);
        buf.writeComponent(this.name);
        buf.writeComponent(this.description);
        // write icon presence
        buf.writeBoolean(this.icon != null);
        if (this.icon != null) {
            buf.writeItem(this.icon);
        }

        // write texture presence
        buf.writeBoolean(this.icon_texture != null);
        if (this.icon_texture != null) {
            buf.writeResourceLocation(this.icon_texture);
        }
        buf.writeInt(this.xpCost);
        buf.writeInt(this.cooldown);
    }

    public static WandEvolution fromNetwork(FriendlyByteBuf buf) {
        ResourceLocation id = buf.readResourceLocation();
        Component name = buf.readComponent();
        Component description = buf.readComponent();
        // read icon if present
        ItemStack icon = ItemStack.EMPTY;
        if (buf.readBoolean()) {
            icon = buf.readItem();
        }

        // read texture if present
        ResourceLocation icon_texture = null;
        if (buf.readBoolean()) {
            icon_texture = buf.readResourceLocation();
        }
        int xpCost = buf.readInt();
        int cooldown = buf.readInt();

        WandEvolution evolution = new WandEvolution(id);

        evolution.name = name;
        evolution.description = description;
        evolution.icon = icon;
        evolution.icon_texture = icon_texture;
        evolution.xpCost = xpCost;
        evolution.cooldown = cooldown;

        return evolution;
    }

    public ResourceLocation getId() { return id; }
    public ResourceLocation getBaseId() {
        String[] pathParts = id.getPath().split("/");
        String baseId = pathParts[pathParts.length - 1].replace(".json", "");
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), baseId);
    }
    public Component getName() { return name; }
    public Component getDescription() { return description; }
    public @Nullable ItemStack getIcon() { return icon; }
    public @Nullable ResourceLocation getIconTexture() { return icon_texture; }
    public void setName(Component name) { this.name = name; }
    public void setDescription(Component description) { this.description = description; }
    public void setIcon(@Nullable ItemStack icon) { this.icon = icon; }
    public void setIconTexture(@Nullable ResourceLocation texture) { this.icon_texture = texture; }
    public int getXpCost() { return this.xpCost; }
    public void setXpCost(int xpCost) { this.xpCost = xpCost; }
    public int getCooldown() { return this.cooldown; }
    public void setCooldown(int cooldown) { this.cooldown = cooldown; }
}
