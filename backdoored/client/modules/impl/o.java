package r.k.d.m;

import java.util.Comparator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import r.k.d.c.h;
import java.util.HashMap;
import java.util.Iterator;
import r.k.g.c;
import java.util.Map;
import java.util.List;

public class o
{
    private static List<g> hc;
    static Map<String, g> ha;
    private static Boolean hm;
    public static v hh;
    public static e sdd;
    public static final boolean sds;
    public static final int sdl;
    public static final boolean sdy;
    private static List<g> hc;
    static Map<String, g> ha;
    private static Boolean hm;
    public static v hh;
    public static e sdd;
    public static final boolean sds;
    public static final int sdl;
    public static final boolean sdy;
    
    public o() {
        super();
    }
    
    public static List<g> lc() {
        return o.hc;
    }
    
    public static g m(final String s) {
        final Iterator<g> iterator = o.hc.iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (g.cm.equalsIgnoreCase(s)) {
                return g;
            }
        }
        throw new RuntimeException(c.v(10) + s + c.v(11));
    }
    
    public static g d(final Class<? extends g> clazz) {
        final Iterator<g> iterator = o.hc.iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (g.getClass() == clazz) {
                return g;
            }
        }
        throw new RuntimeException(c.v(12) + clazz.getName() + c.v(11));
    }
    
    public static HashMap<h, List<g>> la() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: new             Ljava/util/HashMap;
        //    11: dup            
        //    12: invokespecial   java/util/HashMap.<init>:()V
        //    15: astore_0       
        //    Signature:
        //  ()Ljava/util/HashMap<Lr/k/d/c/h;Ljava/util/List<Lr/k/d/m/g;>;>;
        //    StackMapTable: 00 03 FF 00 04 00 06 00 00 00 00 00 01 00 00 01 FF 00 09 00 06 07 00 6A 07 00 2F 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public o() {
        super();
    }
    
    public static List<g> lc() {
        return o.hc;
    }
    
    public static g m(final String s) {
        final Iterator<g> iterator = o.hc.iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (g.cm.equalsIgnoreCase(s)) {
                return g;
            }
        }
        throw new RuntimeException(c.v(10) + s + c.v(11));
    }
    
    public static g d(final Class<? extends g> clazz) {
        final Iterator<g> iterator = o.hc.iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (g.getClass() == clazz) {
                return g;
            }
        }
        throw new RuntimeException(c.v(12) + clazz.getName() + c.v(11));
    }
    
    public static HashMap<h, List<g>> la() {
        final HashMap<h, ArrayList<g>> hashMap = (HashMap<h, ArrayList<g>>)new HashMap<h, List<g>>();
        final Iterator<g> iterator;
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (hashMap.containsKey(g.ch)) {
                hashMap.get(g.ch).add(g);
            }
            hashMap.put(g.ch, new ArrayList<g>(Arrays.<g>asList(g)));
        }
        return (HashMap<h, List<g>>)hashMap;
    }
    
    public static void l(final boolean b) {
        if (b) {
            if (o.hm != null && o.hm) {
                return;
            }
            o.hc.sort((Comparator<? super g>)o.sdd);
            o.hm = true;
        }
        if (o.hm == null || o.hm) {
            o.hc.sort((Comparator<? super g>)o.hh);
            o.hm = false;
        }
    }
    
    static {
        o.ha = new HashMap<String, g>();
        o.hm = null;
        o.hh = new v();
        o.sdd = new e();
    }
}
