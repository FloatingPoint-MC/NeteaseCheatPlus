package me.vlouboos.neteasecheatplus;

import me.vlouboos.neteasecheatplus.exceptions.InitializeException;

public class Client {
    public static Client instance;

    public Client() throws InitializeException {
        if (instance != null) {
            throw new InitializeException("Initialized twice!");
        }
        instance = this;
    }

    public void startGame() {

    }
}
