package net.damqn4etobg.endlessexpansion.screen.menu;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EndlessMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, EndlessExpansion.MODID);

    public static final Supplier<MenuType<InfusingStationMenu>> INFUSING_STATION_MENU = MENUS.register("infusing_station", () -> IMenuTypeExtension.create(InfusingStationMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
