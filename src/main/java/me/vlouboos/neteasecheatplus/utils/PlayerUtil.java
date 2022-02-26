package me.vlouboos.neteasecheatplus.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PlayerUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void tellPlayer(String message) {
        mc.thePlayer.addChatComponentMessage(new ChatComponentText("\2476[\247fNCP\2476]\247r " + message));
    }
}
