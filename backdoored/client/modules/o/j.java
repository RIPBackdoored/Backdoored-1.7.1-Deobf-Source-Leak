package r.k.d.o;

import java.util.Iterator;
import r.k.d.m.g;
import java.util.ArrayList;

public class j
{
    static ArrayList<m> sgi;
    public static final boolean sgp;
    public static final int sgr;
    public static final boolean sgf;
    
    public j() {
        super();
    }
    
    public static ArrayList<m> xi() {
        return j.sgi;
    }
    
    public static ArrayList<m> s(final g g) {
        final ArrayList<m> list = new ArrayList<m>();
        final Iterator<m> iterator = j.sgi.iterator();
        if (iterator.hasNext()) {
            final m m = iterator.next();
            if (m.yk() == g) {
                list.add(m);
            }
        }
        return (ArrayList<m>)list;
    }
    
    static {
        j.sgi = new ArrayList<m>();
    }
}
