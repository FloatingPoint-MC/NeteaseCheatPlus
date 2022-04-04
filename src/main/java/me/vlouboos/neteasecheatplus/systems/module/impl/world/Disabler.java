package me.vlouboos.neteasecheatplus.systems.module.impl.world;

import com.darkmagician6.eventapi.EventTarget;
import me.vlouboos.neteasecheatplus.events.EventMotionPre;
import me.vlouboos.neteasecheatplus.systems.module.Category;
import me.vlouboos.neteasecheatplus.systems.module.Module;

public class Disabler extends Module {
    public Disabler() {
        super("Disabler", Category.World);
    }

    @EventTarget
    public void onPacketSend(EventMotionPre e) {

    }
}
