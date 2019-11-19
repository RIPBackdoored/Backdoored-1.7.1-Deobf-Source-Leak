package r.k.g;

import r.k.q;
import net.minecraftforge.fml.common.FMLLog;
import java.util.HashMap;

public class c
{
    private static final HashMap<Integer, String> sch;
    public static final boolean sad;
    public static final int sas;
    public static final boolean sal;
    
    public c() {
        super();
    }
    
    public static String v(final int n) {
        return c.sch.get(n);
    }
    
    public static void xa() {
        FMLLog.log.info("Encrypting things");
        final long currentTimeMillis = System.currentTimeMillis();
        int n = 0;
        final String[] array = { "Error initialising class ", "Backdoored tried to load ", " hack, out of which ", " failed", "Failed hack: ", "x", "y", "open", "prefix", "Backdoored startup finished", "Hack with name ", " not found", "Hack of class ", "Logging into an online account with email: ", "session", "field_71449_j", "Logged in successfully:", "Session ID: ", "Username: ", "textures/cape_backdoored.png", "textures/cape_backdoored_dev.png", "http://pastebin.com/raw/ZMZcF3nJ", "Gave capes to: ", "Could not fetch capes", "http://pastebin.com/raw/g4wjzg5U", "Could not fetch dev capes", "dark_red", "red", "gold", "yellow", "dark_green", "green", "aqua", "dark_aqua", "dark_blue", "blue", "light_purple", "dark_purple", "white", "gray", "dark_gray", "black", "Backdoored/friends.txt" };
        final int length = array.length;
        int n2 = 0;
        if (n2 < length) {
            final String s = array[n2];
            System.out.print("\nput(" + n + ",\"" + q.c(s) + "\");      // " + s);
            ++n;
            ++n2;
        }
        System.out.print("\nTook " + (System.currentTimeMillis() - currentTimeMillis) / 1000.0 + "s");
        System.exit(-1);
    }
    
    static {
        sch = new c$y();
    }
}
