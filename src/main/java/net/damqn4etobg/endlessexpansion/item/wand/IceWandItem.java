package net.damqn4etobg.endlessexpansion.item.wand;

import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;

import java.util.Properties;

public class IceWandItem extends WandItem {
    public IceWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public WandEvolution getBaseEvolution() {
        return WandEvolutions.ICEBALL.get();
    }
}
