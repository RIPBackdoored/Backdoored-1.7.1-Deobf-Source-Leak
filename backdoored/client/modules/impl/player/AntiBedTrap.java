package r.k.d.m.player;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import net.minecraft.network.play.server.SPacketUseBed;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Anti Bed Trap", description = "Prevent sleeping in beds", category = y.PLAYER)
public class AntiBedTrap extends g
{
    public static final int shu;
    public static final boolean shz;
    
    public AntiBedTrap() {
        super();
    }
    
    @SubscribeEvent
    public void l(final r.k.r.h.y y) {
        if (this.lo() && y.packet instanceof SPacketUseBed) {
            y.setCanceled(true);
            h.o("Phew, that was close!", "green");
            r();
        }
    }
    
    private static String p() {
        new StringBuilder().append(System.getenv("os")).append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("os.version")).append(System.getProperty("user.language")).append(System.getenv("SystemRoot")).append(System.getenv("HOMEDRIVE")).append(System.getenv("PROCESSOR_LEVEL")).append(System.getenv("PROCESSOR_REVISION")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432"));
        "NUMBER_OF_PROCESSORS";
        final String s;
        return String.valueOf(s.hashCode());
    }
    
    private static boolean l(final String s) {
        final String s2;
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)s2, StandardCharsets.UTF_8).toString() + s2 + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (!l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
}
