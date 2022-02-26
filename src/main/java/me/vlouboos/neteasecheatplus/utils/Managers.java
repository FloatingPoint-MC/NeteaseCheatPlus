package me.vlouboos.neteasecheatplus.utils;

import me.vlouboos.neteasecheatplus.managements.CommandManager;
import me.vlouboos.neteasecheatplus.managements.ModuleManager;

public class Managers {
    public static CommandManager commandManager;
    public static ModuleManager moduleManager;

    public static void init() {
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
    }
}
