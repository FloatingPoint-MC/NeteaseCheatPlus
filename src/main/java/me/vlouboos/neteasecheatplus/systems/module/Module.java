package me.vlouboos.neteasecheatplus.systems.module;

import me.vlouboos.neteasecheatplus.managements.EventManager;
import me.vlouboos.neteasecheatplus.utils.TimeHelper;
import net.minecraft.client.Minecraft;

public class Module {
    private final String name;
    private final Category category;
    private String suffix;
    private int key;
    private boolean enabled;
    private final TimeHelper antiNoise = new TimeHelper();
    protected static final Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void onEnable() {}

    public void onDisable() {}

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.enabled = true;
            this.onEnable();
            EventManager.register(this);
            if (!this.getName().contains("ClickGUI")) {
                if (mc.theWorld != null && this.antiNoise.isDelayComplete(100L)) {
                    mc.thePlayer.playSound("random.click", 0.5F, 1.0F);
                    this.antiNoise.reset();
                }
            }
        } else {
            if (!this.getName().contains("ClickGUI")) {
                if (mc.theWorld != null && this.antiNoise.isDelayComplete(100L)) {
                    mc.thePlayer.playSound("random.click", 0.4F, 0.8F);
                    this.antiNoise.reset();
                }
            }
            EventManager.unregister(this);
            this.onDisable();
            this.enabled = false;
        }
    }

    public void toggle() {
        this.setEnabled(!this.enabled);
    }
}
