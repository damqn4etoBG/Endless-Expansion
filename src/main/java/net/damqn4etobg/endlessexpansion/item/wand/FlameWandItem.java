package net.damqn4etobg.endlessexpansion.item.wand;

import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;

import java.util.Properties;

public class FlameWandItem extends WandItem {
    public FlameWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public WandEvolution getBaseEvolution() {
        return WandEvolutions.FIREBALL.get();
    }
}