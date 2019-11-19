package r.k.d.m.combat;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import java.util.Iterator;
import r.k.b.c.h;
import net.minecraft.init.MobEffects;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Set;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Strength Pot Detect", description = "Detect when enemies strength pot", category = y.COMBAT)
public class StrengthPotDetect extends g
{
    private Set<EntityPlayer> sbk;
    public static final boolean sbq;
    public static final int sbv;
    public static final boolean sbj;
    
    public StrengthPotDetect() {
        super();
        this.sbk = Collections.<EntityPlayer>newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final Iterator<EntityPlayer> iterator = StrengthPotDetect.mc.world.playerEntities.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer = iterator.next();
            if (entityPlayer.equals((Object)StrengthPotDetect.mc.player)) {}
            if (entityPlayer.isPotionActive(MobEffects.STRENGTH) && !this.sbk.contains(entityPlayer)) {
                h.o("PlayerPreviewElement '" + entityPlayer.getDisplayNameString() + "' has strenth potted", "yellow");
                this.sbk.add(entityPlayer);
                r();
            }
            h.o("PlayerPreviewElement '" + entityPlayer.getDisplayNameString() + "' no longer has strength", "green");
            this.sbk.remove(entityPlayer);
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
        if (!l(u.lsn)) {
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
}
