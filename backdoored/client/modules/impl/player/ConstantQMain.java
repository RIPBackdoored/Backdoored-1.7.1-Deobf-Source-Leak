package r.k.d.m.player;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import r.k.b.c.h;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "ConstantQMain", description = "Does \"/queue main\" once a minute to help you get through the 2b2t queue", category = y.PLAYER)
public class ConstantQMain extends g
{
    private static long pw;
    private m<Boolean> pg;
    public static final int pu;
    public static final boolean pz;
    
    public ConstantQMain() {
        super();
        this.pg = (m<Boolean>)new p("Only in end", this, true);
    }
    
    public void j() {
        if (System.currentTimeMillis() >= ConstantQMain.pw + 30000L && this.lo()) {
            if (ConstantQMain.mc.player == null) {
                return;
            }
            if (!this.pg.yv() || (ConstantQMain.mc.player.dimension != -1 && ConstantQMain.mc.player.dimension != 0)) {
                ConstantQMain.pw = System.currentTimeMillis();
                ConstantQMain.mc.player.sendChatMessage("/queue main");
                h.sj("/queue main");
                r();
            }
        }
    }
    
    public void t() {
        ConstantQMain.pw = 0L;
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
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
    
    static {
        ConstantQMain.pw = 0L;
    }
}
