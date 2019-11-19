package r.k.d.m.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import r.k.d.o.h.i.s;
import r.k.d.o.h.u;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "ElytraFlight", description = "Rockets aren't needed", category = y.MOVEMENT)
public class ElytraFlight extends g
{
    private m<u$s> sjr;
    private m<Double> sjf;
    private m<Double> sjb;
    private m<Double> sjw;
    public static final boolean sjg;
    public static final int sju;
    public static final boolean sjz;
    
    public ElytraFlight() {
        super();
        this.sjr = (m<u$s>)new u("Mode", this, u$s.soh);
        this.sjf = (m<Double>)new s("Boost-Speed", this, 0.05, 0.01, 0.2);
        this.sjb = (m<Double>)new s("Flight-Speed", this, 0.05, 0.01, 0.2);
        this.sjw = (m<Double>)new s("Control-Speed", this, 0.9, 0.01, 4.0);
    }
    
    public void j() {
        if (!ElytraFlight.mc.player.isElytraFlying() || !this.lo()) {
            return;
        }
        if (this.sjr.yv() == u$s.soh) {
            if (ElytraFlight.mc.player.capabilities.isFlying) {
                ElytraFlight.mc.player.capabilities.isFlying = false;
            }
            if (ElytraFlight.mc.player.isInWater()) {
                ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
            }
            final float n = (float)Math.toRadians(ElytraFlight.mc.player.rotationYaw);
            if (ElytraFlight.mc.gameSettings.keyBindForward.isKeyDown()) {
                final EntityPlayerSP player = ElytraFlight.mc.player;
                player.motionX -= MathHelper.sin(n) * this.sjf.yv();
                final EntityPlayerSP player2 = ElytraFlight.mc.player;
                player2.motionZ += MathHelper.cos(n) * this.sjf.yv();
            }
            if (ElytraFlight.mc.gameSettings.keyBindBack.isKeyDown()) {
                final EntityPlayerSP player3 = ElytraFlight.mc.player;
                player3.motionX += MathHelper.sin(n) * this.sjf.yv();
                final EntityPlayerSP player4 = ElytraFlight.mc.player;
                player4.motionZ -= MathHelper.cos(n) * this.sjf.yv();
            }
        }
        if (this.sjr.yv() == u$s.sts) {
            ElytraFlight.mc.player.capabilities.isFlying = true;
            ElytraFlight.mc.player.jumpMovementFactor = this.sjb.yv().floatValue();
            if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                final EntityPlayerSP player5 = ElytraFlight.mc.player;
                player5.motionY += this.sjb.yv();
            }
            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final EntityPlayerSP player6 = ElytraFlight.mc.player;
                player6.motionY -= this.sjb.yv();
            }
        }
        if (this.sjr.yv() == u$s.std) {
            ElytraFlight.mc.player.motionY = 0.0;
            ElytraFlight.mc.player.motionX = 0.0;
            ElytraFlight.mc.player.motionZ = 0.0;
            final float n2 = (float)Math.toRadians(ElytraFlight.mc.player.rotationYaw);
            final float n3 = (float)Math.toRadians(ElytraFlight.mc.player.rotationPitch);
            if (ElytraFlight.mc.gameSettings.keyBindForward.isKeyDown()) {
                ElytraFlight.mc.player.motionX = -(MathHelper.sin(n2) * MathHelper.cos(n3) * this.sjw.yv());
                ElytraFlight.mc.player.motionZ = MathHelper.cos(n2) * MathHelper.cos(n3) * this.sjw.yv();
                ElytraFlight.mc.player.motionY = -(MathHelper.sin(n3) * this.sjw.yv());
            }
            if (ElytraFlight.mc.gameSettings.keyBindBack.isKeyDown()) {
                ElytraFlight.mc.player.motionX = MathHelper.sin(n2) * MathHelper.cos(n3) * this.sjw.yv();
                ElytraFlight.mc.player.motionZ = -(MathHelper.cos(n2) * MathHelper.cos(n3) * this.sjw.yv());
                ElytraFlight.mc.player.motionY = MathHelper.sin(n3) * this.sjw.yv();
            }
            if (ElytraFlight.mc.gameSettings.keyBindLeft.isKeyDown()) {
                ElytraFlight.mc.player.motionZ = MathHelper.sin(n2) * MathHelper.cos(n3) * this.sjw.yv();
                ElytraFlight.mc.player.motionX = MathHelper.cos(n2) * MathHelper.cos(n3) * this.sjw.yv();
            }
            if (ElytraFlight.mc.gameSettings.keyBindRight.isKeyDown()) {
                ElytraFlight.mc.player.motionZ = -(MathHelper.sin(n2) * this.sjw.yv());
                ElytraFlight.mc.player.motionX = -(MathHelper.cos(n2) * this.sjw.yv());
            }
            if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                ElytraFlight.mc.player.motionY = this.sjw.yv();
            }
            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                ElytraFlight.mc.player.motionY = -this.sjw.yv();
            }
        }
    }
    
    public void v() {
        if (this.sjr.yv() != u$s.sts) {
            return;
        }
        ElytraFlight.mc.player.capabilities.setFlySpeed(this.sjb.yv().floatValue());
        ElytraFlight.mc.addScheduledTask(this::xl);
    }
    
    public void t() {
        if (this.sjr.yv() == u$s.sts) {
            ElytraFlight.mc.player.capabilities.isFlying = false;
            ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        }
    }
    
    private /* synthetic */ void xl() {
        if (ElytraFlight.mc.player != null && ElytraFlight.mc.player.isElytraFlying() && this.lo() && this.sjr.yv() == u$s.sts) {
            ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        }
    }
}
