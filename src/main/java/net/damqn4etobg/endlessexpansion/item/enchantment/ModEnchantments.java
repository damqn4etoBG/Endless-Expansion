package net.damqn4etobg.endlessexpansion.item.enchantment;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EndlessExpansion.MODID);

    public static final RegistryObject<Enchantment> IMPACT_EXPLODE = ENCHANTMENTS.register("impact_explode", () -> new ImpactExplodeEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
