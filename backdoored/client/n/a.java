package r.k.n;

import r.k.b.c.h;
import r.k.w.k;
import java.io.File;

public class a extends c
{
    public static final int shc;
    public static final boolean sha;
    
    public a() {
        super("load");
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length <= 0) {
            return false;
        }
        k.s(new File("Backdoored/config-" + array[0].toLowerCase() + ".json"));
        h.o("Loaded " + array[0].toLowerCase() + " config", "red");
        return true;
    }
    
    @Override
    public String k() {
        return "-load <config name>";
    }
}
