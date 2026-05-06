package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.item.IArmorSetBonus;
import net.damqn4etobg.endlessexpansion.screen.InfusingStationScreen;
import net.damqn4etobg.endlessexpansion.screen.menu.EndlessMenus;
import net.damqn4etobg.endlessexpansion.util.EndlessKeyBinds;
import net.damqn4etobg.endlessexpansion.util.EndlessRenderTypes;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterNamedRenderTypesEvent;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.HashMap;
import java.util.Map;

import static net.damqn4etobg.endlessexpansion.item.EndlessItems.*;
import static net.damqn4etobg.endlessexpansion.util.EndlessStyles.cacheTranslatable;
import static net.damqn4etobg.endlessexpansion.util.EndlessStyles.getAnnotated;

@EventBusSubscriber(value = Dist.CLIENT, modid = EndlessExpansion.MODID)
public class EndlessClientEvents {
    private static final Map<Item, String> TOOLTIP_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        addTooltipsToMap();
        cacheTranslatable();

        event.enqueueWork(() -> {

        });
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        TOOLTIP_MAP.forEach((item, loc) -> {
            if (stack.is(item)) event.getToolTip().add(getAnnotated(Component.translatable(loc)));
        });

        if (stack.getItem() instanceof IArmorSetBonus bonus) bonus.setBonusTooltip().forEach(c -> event.getToolTip().add(getAnnotated(c)));
    }

    private static void addTooltipsToMap() {
        TOOLTIP_MAP.put(FLAMMATINE_INGOT.get(), "item.endlessexpansion.flammatine_ingot.desc");
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(EndlessKeyBinds.KEY_DASH);
    }

    @SubscribeEvent
    public static void onClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
                return IClientItemExtensions.super.getHumanoidArmorModel(itemStack, layerType, original);
            }
        }, SHADOWSTEEL_HOOD.get(), SHADOWSTEEL_CLOAK.get(), SHADOWSTEEL_PANTS.get(), SHADOWSTEEL_BOOTS.get());
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(EndlessMenus.INFUSING_STATION_MENU.get(), InfusingStationScreen::new);
    }

    @SubscribeEvent
    public static void registerPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(EndlessRenderTypes.TEST_PIPELINE);
    }

    @SubscribeEvent
    public static void registerRenderTypes(RegisterNamedRenderTypesEvent event) {
        event.register(Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "test"), ChunkSectionLayer.SOLID, RenderTypes::entitySolid);
    }
}
