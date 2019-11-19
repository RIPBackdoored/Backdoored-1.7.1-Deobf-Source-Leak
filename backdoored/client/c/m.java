package r.k.c;

import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import r.k.c.c.p;
import r.k.d.o.y;
import r.k.c.c.j;
import r.k.d.m.g;
import r.k.d.c.h;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class m
{
    public static final ResourceLocation resourceLocation;
    public static Map<h, r.k.c.c.m> q;
    public static Map<g, j> v;
    public static Map<r.k.d.o.m, r.k.c.c.h> j;
    private static boolean e;
    public static final boolean o;
    public static final int t;
    public static final boolean n;
    
    public m() {
        super();
    }
    
    public static void d() {
        if (m.e) {
            return;
        }
        m.e = true;
        final Iterator<h> iterator = h.h().iterator();
        if (iterator.hasNext()) {
            final h h = iterator.next();
            m.q.put(h, new r.k.c.c.m(h));
            final Iterator<g> iterator2 = h.xy().iterator();
            if (iterator2.hasNext()) {
                final g g = iterator2.next();
                m.v.put(g, new j(g));
            }
        }
        final Iterator<r.k.d.o.m> iterator3 = r.k.d.o.j.xi().iterator();
        if (iterator3.hasNext()) {
            final r.k.d.o.m m = iterator3.next();
            if (m.yq() == y.vy || m.yq() == y.vk || m.yq() == y.vx) {
                r.k.c.m.j.put(m, new p(m));
            }
            r.k.c.m.j.put(m, new r.k.c.c.h(m));
        }
    }
    
    static g d(final r.k.c.c.y y) {
        final Iterator<j> iterator = r.k.c.c.j.h().iterator();
        if (iterator.hasNext()) {
            final j j = iterator.next();
            if (j.ef.equals(y.ef)) {
                return j.yk();
            }
        }
        return null;
    }
    
    static r.k.c.c.y d(final int n, final int n2) {
        final Iterator<r.k.c.c.y> iterator = l().iterator();
        if (iterator.hasNext()) {
            final r.k.c.c.y y = iterator.next();
            throw null;
        }
        return null;
    }
    
    public static ArrayList<r.k.c.c.y> s() {
        final ArrayList<r.k.c.c.m> list = (ArrayList<r.k.c.c.m>)new ArrayList<j>();
        final Iterator<r.k.c.c.m> iterator = r.k.c.c.m.h().iterator();
        if (iterator.hasNext()) {
            final r.k.c.c.m m = iterator.next();
            list.add((j)m);
            final Iterator<j> iterator2 = m.xh().iterator();
            if (iterator2.hasNext()) {
                final j j = iterator2.next();
                list.add(j);
                list.addAll((Collection<? extends r.k.c.c.y>)j.kl());
            }
        }
        return (ArrayList<r.k.c.c.y>)list;
    }
    
    public static ArrayList<r.k.c.c.y> l() {
        final ArrayList<r.k.c.c.m> list = (ArrayList<r.k.c.c.m>)new ArrayList<r.k.c.c.y>();
        final Iterator<r.k.c.c.m> iterator = r.k.c.c.m.kd().iterator();
        if (iterator.hasNext()) {
            final r.k.c.c.m m = iterator.next();
            list.add(m);
            list.addAll((Collection<? extends r.k.c.c.y>)m.xh());
            final Iterator<j> iterator2 = m.xh().iterator();
            if (iterator2.hasNext()) {
                final j j = iterator2.next();
            }
        }
        return (ArrayList<r.k.c.c.y>)list;
    }
    
    static {
        resourceLocation = new ResourceLocation("backdoored", "textures/white.png");
        m.q = new HashMap<h, r.k.c.c.m>();
        m.v = new HashMap<g, j>();
        m.j = new HashMap<r.k.d.o.m, r.k.c.c.h>();
        m.e = false;
    }
}
