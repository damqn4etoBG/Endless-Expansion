package net.damqn4etobg.endlessexpansion.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum ShroomieVariant {
    RED(0),
    BROWN(1),
    DEV(2);

    private static final ShroomieVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(ShroomieVariant::getId)).toArray(ShroomieVariant[]::new);
    private final int id;

    ShroomieVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ShroomieVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
