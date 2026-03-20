package net.damqn4etobg.endlessexpansion.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class EndlessExpansionClientConfig {
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_CONFIG;

    public static final ForgeConfigSpec.BooleanValue CUSTOM_MAIN_MENU;
    public static final ForgeConfigSpec.ConfigValue<String> BACKGROUND_NAME;
    public static final ForgeConfigSpec.ConfigValue<String> MOD_SOUNDS;
    public static final ForgeConfigSpec.ConfigValue<String> BOSSBAR_STYLE;

    static {
        CUSTOM_MAIN_MENU = CLIENT_BUILDER.comment("Enable custom main menu?").define("customMainMenu", false);
        BACKGROUND_NAME = CLIENT_BUILDER.comment("Custom main menu background").define("backgroundName", "Titanic Forest");
        MOD_SOUNDS = CLIENT_BUILDER.comment("Mod sounds").define("modSounds", "ON");
        BOSSBAR_STYLE = CLIENT_BUILDER.comment("Modded bossbar style").define("bossbarStle", "Percent");

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void load(Path configPath) {
        CommentedFileConfig configData = CommentedFileConfig.builder(configPath).sync().autosave().build();
        configData.load();  // Load the config file
        CLIENT_CONFIG.setConfig(configData);  // Merge the loaded data into the config spec
    }
}
