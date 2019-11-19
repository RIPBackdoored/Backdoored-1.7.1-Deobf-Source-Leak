package r.k.d.o.h;

import r.k.d.o.y;
import r.k.d.m.g;
import \u0000r.\u0000k.\u0000d.\u0000o.\u0000m;

public class u<T extends Enum> extends \u0000m<T>
{
    private Enum[] sep;
    public static final int ser;
    public static final boolean sef;
    
    public u(final String s, final g g, final T t) {
        super(s, g, y.vd, t);
        this.sep = (Enum[])t.getClass().getEnumConstants();
    }
    
    public Class<?> sy() {
        return String.class;
    }
    
    public Enum[] yt() {
        return this.sep;
    }
}
