package me.vlouboos.neteasecheatplus.systems.module.impl.combat;

import com.darkmagician6.eventapi.EventTarget;
import me.vlouboos.neteasecheatplus.events.EventMotionPre;
import me.vlouboos.neteasecheatplus.systems.module.Category;
import me.vlouboos.neteasecheatplus.systems.module.Module;
import me.vlouboos.neteasecheatplus.systems.module.value.BooleanValue;
import me.vlouboos.neteasecheatplus.systems.module.value.NumberValue;
import me.vlouboos.neteasecheatplus.utils.TimeHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Killaura extends Module {
    private final NumberValue.DoubleValue range = new NumberValue.DoubleValue("Range", 3.0, 6.0, 4.0);
    private final NumberValue.DoubleValue minAps = new NumberValue.DoubleValue("MinAPS", 0.1, 20.0, 4.0);
    private final NumberValue.DoubleValue maxAps = new NumberValue.DoubleValue("MaxAPS", 0.1, 20.0, 4.0);
    private final BooleanValue autoBlock = new BooleanValue("AutoBlock", false);
    private boolean rotated;
    private final TimeHelper timeHelper = new TimeHelper();

    public Killaura() {
        super("Killaura", Category.Combat);
        this.registerValues(this.range, this.minAps, this.maxAps);
    }

    @EventTarget
    public void onPre(EventMotionPre e) {
        List<Entity> targets = mc.theWorld.getLoadedEntityList().stream().filter(entity -> entity != mc.thePlayer && entity instanceof EntityLivingBase && mc.thePlayer.getDistanceToEntity(entity) <= this.range.getValue()).collect(Collectors.toList());
        if (!targets.isEmpty()) {
            targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase) entity).getHealth()));
            Entity target = targets.get(0);
            if (this.rotated && this.timeHelper.isDelayComplete(MathHelper.getRandomDoubleInRange(new Random(), this.minAps.getValue(), this.maxAps.getValue()))) {
                if (mc.thePlayer.isBlocking()) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                }
                mc.thePlayer.swingItem();
                mc.playerController.attackEntity(mc.thePlayer, target);
                if (mc.thePlayer.isBlocking()) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
                } else if (this.autoBlock.getValue()) {
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                }
            }
            double diffX = target.posX - mc.thePlayer.posX;
            double diffY = target.posY - mc.thePlayer.posZ;
            double diffZ = target.posZ - mc.thePlayer.posZ;
            double distanceXZ = Math.hypot(diffX, diffZ);
            float readyYaw = (float) ((Math.atan2(diffZ, diffX) * 180.0f / Math.PI) - 90.0f);
            float readyPitch = (float) (-Math.atan2(diffY - (target instanceof EntityPlayer ? 0.25f : 0.0f), distanceXZ) * 180.0f / Math.PI);
            e.setYaw(readyYaw);
            e.setPitch(readyPitch);
            this.rotated = true;
        } else {
            this.rotated = false;
            if (this.autoBlock.getValue()) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
            }
        }
    }
}
