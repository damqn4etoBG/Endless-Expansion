package net.damqn4etobg.endlessexpansion.screen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, EndlessExpansion.MODID);

    public static final RegistryObject<MenuType<RadioactiveGeneratorMenu>> RADIOACTIVE_GENERATOR_MENU =
            registerMenuType(RadioactiveGeneratorMenu::new, "radioactive_generator_menu");

    public static final RegistryObject<MenuType<InfuserMenu>> INFUSER_MENU =
            registerMenuType(InfuserMenu::new, "infuser_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}