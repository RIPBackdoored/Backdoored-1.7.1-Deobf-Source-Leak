package b.k;

import r.k.b.m.y;
import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

public class d
{
    public d() {
        super();
    }
    
    private static void r() {
        final String string = Hashing.sha512().hashString((CharSequence)(System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")), StandardCharsets.UTF_8).toString();
        final String string2 = Hashing.sha1().hashString((CharSequence)string, StandardCharsets.UTF_8).toString();
        if (!Hashing.sha512().hashString((CharSequence)("john" + string2 + string + string2), StandardCharsets.UTF_8).toString().equalsIgnoreCase(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + string);
            a.llp = true;
            throw new y("Invalid License");
        }
    }
}
