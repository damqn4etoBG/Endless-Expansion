package net.damqn4etobg.endlessexpansion.command.argument;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArgumentTypes {
    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, EndlessExpansion.MODID);

    public static final RegistryObject<ArgumentTypeInfo<?, ?>> HEX_COLOR_ARGUMENT = ARGUMENT_TYPES.register("hex_color",
            () -> ArgumentTypeInfos.registerByClass(HexArgumentType.class, SingletonArgumentInfo.contextFree(HexArgumentType::hexColor)));

    public static void register(IEventBus eventBus) {
        ARGUMENT_TYPES.register(eventBus);
    }
}
