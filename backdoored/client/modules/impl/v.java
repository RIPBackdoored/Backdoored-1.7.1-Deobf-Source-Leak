package r.k.d.m;

import r.k.i.o;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000g;
import java.util.Comparator;

class v implements Comparator<\u0000g>
{
    public static final int sfj;
    public static final boolean sfe;
    
    v() {
        super();
    }
    
    public int d(final g g, final g g2) {
        return Integer.compare(o.fontRenderer.getStringWidth(g2.cm), o.fontRenderer.getStringWidth(g.cm));
    }
    
    @Override
    public /* bridge */ int compare(final Object o, final Object o2) {
        return this.d((g)o, (g)o2);
    }
}
