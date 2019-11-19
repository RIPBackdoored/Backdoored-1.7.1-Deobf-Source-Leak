package r.k.n;

import java.io.File;
import r.k.b.c.h;
import r.k.w.k;

public class u extends c
{
    public static final int io;
    public static final boolean it;
    
    public u() {
        super("save");
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length <= 0) {
            k.sc();
            h.o("Saved config", "red");
            return true;
        }
        k.d(new File("Backdoored/config-" + array[0].toLowerCase() + ".json"));
        h.o("Saved new config under name: " + array[0].toLowerCase(), "red");
        return true;
    }
    
    @Override
    public String k() {
        return "-save <save name>";
    }
}
