package net.damqn4etobg.endlessexpansion.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.Collections;

public class HexArgumentType implements ArgumentType<Integer> {
    private static final SimpleCommandExceptionType ERROR_INVALID_HEX = new SimpleCommandExceptionType(Component.translatable("commands.endlessexpansion.argument.hex.invalid"));

    public static HexArgumentType hexColor() {
        return new HexArgumentType();
    }

    public static int getHexColor(CommandContext<CommandSourceStack> context, String name) {
        return context.getArgument(name, Integer.class);
    }

    @Override
    public Integer parse(StringReader reader) throws CommandSyntaxException {
        String hexString = reader.readUnquotedString().toLowerCase();

        if (hexString.startsWith("0x")) {
            hexString = hexString.substring(2);
        }

        try {
            return (int) Long.parseLong(hexString, 16);
        } catch (NumberFormatException e) {
            throw ERROR_INVALID_HEX.create();
        }
    }

    @Override
    public Collection<String> getExamples() {
        return Collections.singletonList("0xFF0000");
    }
}
