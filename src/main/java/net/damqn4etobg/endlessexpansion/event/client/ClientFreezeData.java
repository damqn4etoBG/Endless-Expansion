package net.damqn4etobg.endlessexpansion.event.client;

public class ClientFreezeData {
    private static int playerFreeze;

    public static void set(int freeze) {
        ClientFreezeData.playerFreeze = freeze;
    }

    public static int getPlayerFreeze() {
        return playerFreeze;
    }
}
