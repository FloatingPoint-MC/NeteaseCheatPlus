package me.vlouboos.neteasecheatplus.systems.command.impl;

import me.vlouboos.neteasecheatplus.systems.command.Command;
import me.vlouboos.neteasecheatplus.systems.module.Module;
import me.vlouboos.neteasecheatplus.utils.Managers;
import me.vlouboos.neteasecheatplus.utils.PlayerUtil;
import org.lwjgl.input.Keyboard;

public class Bind extends Command {
    public Bind() {
        super("Bind", "Bind keys to modules.");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String moduleName = args[0];
            String keyName = args[1];
            Module module = null;
            for (Module moduleInList : Managers.moduleManager.getModules()) {
                if (moduleInList.getName().equalsIgnoreCase(moduleName)) {
                    module = moduleInList;
                    break;
                }
            }
            if (module == null) {
                PlayerUtil.tellPlayer("\247cUnknown module!");
            } else {
                int key = Keyboard.getKeyIndex(keyName.toUpperCase());
                module.setKey(key);
                PlayerUtil.tellPlayer(String.format("\247bBound \247a%s\247b to \247a%s\247b.", module.getName(), Keyboard.getKeyName(key)));
            }
        } else {
            PlayerUtil.tellPlayer("\247cUsage: .bind <Module> <Key>");
        }
    }
}
