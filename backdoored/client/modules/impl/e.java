package r.k.d.m;

import \u0000r.\u0000k.\u0000d.\u0000m.\u0000g;
import java.util.Comparator;

class e implements Comparator<\u0000g>
{
    public static final boolean sqb;
    
    e() {
        super();
    }
    
    public int d(final g g, final g g2) {
        return g.cm.compareTo(g2.cm);
    }
    
    @Override
    public /* bridge */ int compare(final Object o, final Object o2) {
        return this.d((g)o, (g)o2);
    }
}
