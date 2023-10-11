package net.damqn4etobg.endlessexpansion.util;

public class TickCounter {
    private int ticks;

    public TickCounter() {
        this.ticks = 0;
    }

    public void reset() {
        this.ticks = 0;
    }

    public void increment() {
        this.ticks++;
    }

    public int getTicks() {
        return this.ticks;
    }
}
