package r.k.n;

import java.util.Iterator;
import r.k.b.c.h;

public class o extends c
{
    public static final boolean lj;
    public static final int le;
    public static final boolean lo;
    
    public o() {
        super(new String[] { "help", "commands" });
    }
    
    @Override
    public boolean d(final String[] array) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<c> iterator = c.sc.iterator();
        if (iterator.hasNext()) {
            sb.append(iterator.next().sa.get(0)).append(", ");
        }
        h.sj("Commands:\n" + sb.toString());
        return true;
    }
    
    @Override
    public String k() {
        return "-help";
    }
}
