package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExampleEvolution extends WandEvolution {
    public ExampleEvolution(String id) {
        super(id);
    }

    @Override
    public void use(Level level, Player player, ItemStack stack) {
        System.out.println("testinisnasd");
    }
}
