package r.k.n;

import java.util.Iterator;
import java.util.Arrays;
import r.k.d.m.g;
import r.k.d.m.o;

public class h extends c
{
    public static final boolean sci;
    public static final int scp;
    public static final boolean scr;
    
    public h() {
        super(new String[] { "bind", "keybind" });
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length <= 0) {
            r.k.b.c.h.sj("Please specify a hack!");
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        final int length = array.length;
        int n = 0;
        if (n < length) {
            sb.append(array[n]);
            ++n;
        }
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (sb.toString().equalsIgnoreCase(g.cm.replace(" ", ""))) {
                r.k.b.c.h.sj(g.cm + ": " + g.al.yv());
                return true;
            }
        }
        final StringBuilder sb2 = new StringBuilder();
        final String[] array2 = Arrays.<String>copyOf(array, array.length - 1);
        final int length2 = array2.length;
        int n2 = 0;
        if (n2 < length2) {
            sb2.append(array2[n2]);
            ++n2;
        }
        final Iterator<g> iterator2 = o.lc().iterator();
        if (iterator2.hasNext()) {
            final g g2 = iterator2.next();
            if (sb2.toString().equalsIgnoreCase(g2.cm.replace(" ", ""))) {
                g2.al.d(array[array.length - 1].toUpperCase());
                r.k.b.c.h.sj("Set keybind of hack '" + g2.cm + "' to '" + g2.al.yv() + "'");
                return true;
            }
        }
        r.k.b.c.h.o(sb2.toString() + " not found!", "red");
        return false;
    }
    
    @Override
    public String k() {
        return "-t <hackname>";
    }
}
