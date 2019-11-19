package r.k.d.m.combat;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import r.k.r.v;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import r.k.d.o.h.u;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "AutoEz", description = "Automatically ez", category = y.COMBAT)
public class AutoEz extends g
{
    private m<d$s> ldy;
    private int ldx;
    private EntityPlayer entityPlayer;
    private static final Random ldq;
    public static final int ldv;
    public static final boolean ldj;
    
    public AutoEz() {
        super();
        this.ldy = (m<d$s>)new u("Text", this, d$s.sjk);
    }
    
    @SubscribeEvent
    public void d(final AttackEntityEvent attackEntityEvent) {
        if (this.lo() && attackEntityEvent.getTarget() instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)attackEntityEvent.getTarget();
            if (attackEntityEvent.getEntityPlayer().getUniqueID().equals(AutoEz.mc.player.getUniqueID())) {
                if (entityPlayer.getHealth() <= 0.0f || entityPlayer.isDead || !AutoEz.mc.world.playerEntities.contains(entityPlayer)) {
                    AutoEz.mc.player.sendChatMessage(this.ldy.toString());
                    r();
                    return;
                }
                this.ldx = 500;
                this.entityPlayer = entityPlayer;
            }
        }
    }
    
    public void j() {
        if (AutoEz.mc.player.isDead) {
            this.ldx = 0;
        }
        if (this.ldx > 0 && (this.entityPlayer.getHealth() <= 0.0f || this.entityPlayer.isDead || !AutoEz.mc.world.playerEntities.contains(this.entityPlayer))) {
            if (this.lo()) {
                AutoEz.mc.player.sendChatMessage(this.ldy.toString());
                r();
            }
            this.ldx = 0;
        }
        --this.ldx;
    }
    
    @SubscribeEvent
    public void d(final v v) {
        if (!this.lo()) {
            return;
        }
        if (!v.sq().equals((Object)AutoEz.mc.player) && v.sq().equals((Object)this.entityPlayer)) {
            AutoEz.mc.player.sendChatMessage(this.ldy.toString());
            r();
        }
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (AutoEz.ldq.nextBoolean() && !l(r.k.u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + r.k.u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
    
    static {
        ldq = new Random();
    }
}
