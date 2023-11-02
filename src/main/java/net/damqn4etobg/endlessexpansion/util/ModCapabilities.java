package net.damqn4etobg.endlessexpansion.util;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities {
    public static final Capability<ITemperature> TEMPERATURE = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<ILuminiteEssence> LUMINITE_ESSENCE = CapabilityManager.get(new CapabilityToken<>(){});
}
