package r.k.n;

import java.util.Iterator;
import r.k.d.m.g;
import r.k.d.m.o;
import r.k.b.c.h;

public class t extends c
{
    public static final boolean sne;
    public static final int sno;
    public static final boolean snt;
    
    public t() {
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length < 1) {
            h.sj("Invalid args!");
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        final int length = array.length;
        int n = 0;
        if (n < length) {
            sb.append(array[n]);
            ++n;
        }
        final String string = sb.toString();
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (string.equalsIgnoreCase(g.cm.replace(" ", ""))) {
                g.al.d("NONE");
                h.sj("Bound " + string + " to " + g.al.yv());
            }
        }
        h.sj("Could not find hack '" + string + "'");
        return false;
    }
    
    @Override
    public String k() {
        return "-unbind Twerk";
    }
}
