package r.k.n;

import r.k.b.c.h;

public class j extends c
{
    public static final int shh;
    public static final boolean ldd;
    
    public j() {
        super("prefix");
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length > 0) {
            h.o("Set cmd prefix to " + i.re, "red");
            return true;
        }
        return false;
    }
    
    @Override
    public String k() {
        return "Usage: .prefix <new prefix character>";
    }
}
