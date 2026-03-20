package net.damqn4etobg.endlessexpansion.sound;

import net.damqn4etobg.endlessexpansion.config.EndlessExpansionClientConfig;

public class ModSoundOptions {
    private static ModSoundOption getCurrentOption() {
        return ModSoundOption.fromString(EndlessExpansionClientConfig.MOD_SOUNDS.get());
    }

    public static boolean ON() {
        return getCurrentOption().isOn();
    }

    public static boolean Partial() {
        return getCurrentOption().isPartial();
    }

    public static boolean OFF() {
        return getCurrentOption().isOff();
    }
}