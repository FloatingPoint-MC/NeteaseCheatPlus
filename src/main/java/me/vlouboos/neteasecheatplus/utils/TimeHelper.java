package me.vlouboos.neteasecheatplus.utils;

public class TimeHelper {
    private long lastMS;

    public boolean isDelayComplete(double delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }
}
