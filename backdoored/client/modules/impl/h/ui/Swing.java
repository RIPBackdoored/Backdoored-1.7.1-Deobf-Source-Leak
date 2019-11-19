package r.k.d.m.h.ui;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import r.k.d.m.h.p.s;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Swing", description = "Popup console", category = y.UI)
public class Swing extends g
{
    public static m<Boolean> tb;
    private boolean tw;
    private s tg;
    public static final int tu;
    public static final boolean tz;
    
    public Swing() {
        super();
        this.tw = false;
    }
    
    public void v() {
        if (this.tg == null || this.tw) {
            this.tg = new s();
        }
        this.tw = false;
        r();
    }
    
    public void t() {
        this.tg.b();
        this.tw = true;
        r();
    }
    
    public void j() {
        if (this.tg != null) {
            this.tg.f();
        }
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
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
