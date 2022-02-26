package me.vlouboos.neteasecheatplus.managements;

import com.darkmagician6.eventapi.EventTarget;
import me.vlouboos.neteasecheatplus.events.EventKey;
import me.vlouboos.neteasecheatplus.systems.module.Module;
import me.vlouboos.neteasecheatplus.systems.module.impl.combat.Killaura;

import java.util.ArrayList;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        this.modules.add(new Killaura());
        EventManager.register(this);
    }

    @EventTarget
    public void onKey(EventKey e) {
        for (Module module : this.modules) {
            if (e.getKey() == module.getKey()) {
                module.toggle();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
