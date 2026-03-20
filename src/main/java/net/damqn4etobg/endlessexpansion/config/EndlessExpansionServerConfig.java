package net.damqn4etobg.endlessexpansion.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class EndlessExpansionServerConfig {
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SERVER_CONFIG;

    public static final ForgeConfigSpec.BooleanValue SEND_EFFECT_PACKETS;

    static {
        SEND_EFFECT_PACKETS = SERVER_BUILDER.comment("Send effect packets?").define("sendEffectPackets", true);

        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static void load(Path configPath) {
        CommentedFileConfig configData = CommentedFileConfig.builder(configPath).sync().autosave().build();
        configData.load();  // Load the config file
        SERVER_CONFIG.setConfig(configData);  // Merge the loaded data into the config spec
    }
}
