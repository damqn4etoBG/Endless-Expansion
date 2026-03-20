package net.damqn4etobg.endlessexpansion.sound;

public enum ModSoundOption {
    ON,
    PARTIAL,
    OFF;

    public boolean isOn() {
        return this == ON;
    }

    public boolean isPartial() {
        return this == PARTIAL;
    }

    public boolean isOff() {
        return this == OFF;
    }

    public static ModSoundOption fromString(String value) {
        return switch (value.toUpperCase()) {
            case "ON" -> ON;
            case "PARTIAL" -> PARTIAL;
            case "OFF" -> OFF;
            default -> OFF; // fallback
        };
    }
}
