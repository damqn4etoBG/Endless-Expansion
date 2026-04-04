package net.damqn4etobg.endlessexpansion;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EndlessConfig {
    public static class Client {
        public static final Client CONFIG;
        public static final ModConfigSpec CONFIG_SPEC;

        public final ModConfigSpec.ConfigValue<BossbarStyle> bossbarStyle;

        public Client(ModConfigSpec.Builder builder) {
            bossbarStyle = builder.defineEnum("bossbar_style", BossbarStyle.EXACT_VALUE);
        }

        static {
            Pair<Client, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Client::new);
            CONFIG = pair.getLeft();
            CONFIG_SPEC = pair.getRight();
        }

        public enum BossbarStyle {
            PERCENT,
            EXACT_VALUE
        }
    }

    public static class Server {
        public static final Server CONFIG;
        public static final ModConfigSpec CONFIG_SPEC;

        public final ModConfigSpec.ConfigValue<Integer> serverInt;

        public Server(ModConfigSpec.Builder builder) {
            serverInt = builder.define("server_int",  72);
        }

        static {
            Pair<Server, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Server::new);
            CONFIG = pair.getLeft();
            CONFIG_SPEC = pair.getRight();
        }
    }
}
