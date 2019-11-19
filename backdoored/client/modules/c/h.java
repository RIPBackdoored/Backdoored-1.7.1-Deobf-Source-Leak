package r.k.d.c;

import r.k.d.m.g;
import java.util.ArrayList;

public class h
{
    private static ArrayList<h> sow;
    private ArrayList<g> sog;
    private String sou;
    public static final int soz;
    public static final boolean soc;
    
    h(final String s) {
        super();
        this.sog = new ArrayList<g>();
        h.sow.add(this);
    }
    
    public String o() {
        return this.sou;
    }
    
    public ArrayList<g> xy() {
        return this.sog;
    }
    
    @Override
    public String toString() {
        return this.o();
    }
    
    public static ArrayList<h> h() {
        System.out.println("Categories: " + h.sow.toString());
        return h.sow;
    }
    
    static {
        h.sow = new ArrayList<h>();
    }
}
