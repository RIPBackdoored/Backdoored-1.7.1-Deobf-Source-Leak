package r.k.d.m.combat;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import java.util.Iterator;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import r.k.d.o.h.i.x;
import r.k.d.o.h.p;
import r.k.d.o.h.i.s;
import java.util.Random;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Kill Aura", description = "Attack players near you", category = y.COMBAT)
public class KillAura extends g
{
    private m<Double> yi;
    private m<Boolean> yp;
    private m<Boolean> yr;
    private m<Integer> yf;
    private int yb;
    private static final Random yw;
    public static final boolean yg;
    public static final int yu;
    public static final boolean yz;
    
    public KillAura() {
        super();
        this.yi = (m<Double>)new s("Range", this, 5.0, 1.0, 15.0);
        this.yp = (m<Boolean>)new p("32k Only", this, false);
        this.yf = (m<Integer>)new x("Delay in ticks", this, 0, 0, 50);
        this.yb = 0;
    }
    
    public void j() {
        if (!this.lo() || KillAura.mc.player.isDead || KillAura.mc.world == null) {
            return;
        }
        if (this.yb < this.yf.yv()) {
            ++this.yb;
            return;
        }
        this.yb = 0;
        for (final Entity entity : KillAura.mc.world.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                if (entity == KillAura.mc.player) {}
                if (KillAura.mc.player.getDistance(entity) <= this.yi.yv() && ((EntityLivingBase)entity).getHealth() > 0.0f && (entity instanceof EntityPlayer || !this.yr.yv()) && (!(entity instanceof EntityPlayer) || !r.k.b.i.p.l((EntityPlayer)entity)) && !this.yp.yv()) {
                    KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.player, entity);
                    KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                break;
            }
        }
    }
    
    public void t() {
        r();
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (KillAura.yw.nextBoolean() && !l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
    
    static {
        yw = new Random();
    }
}
