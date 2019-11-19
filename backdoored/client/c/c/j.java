package r.k.c.c;

import r.k.d.m.g;
import java.util.ArrayList;

public class j extends y
{
    private static ArrayList<j> shs;
    private ArrayList<h> shl;
    private m shy;
    private g shx;
    public static final int shk;
    public static final boolean shq;
    
    public j(final g shx) {
        super(r.k.c.m.q.get(shx.ch).en, r.k.c.m.q.get(shx.ch).ei + 20 * r.k.c.m.q.get(shx.ch).smj, 100, 20, shx.cm, true, new float[] { 0.2f, 0.0f, 0.9f, 1.0f });
        this.shl = new ArrayList<h>();
        final m m = r.k.c.m.q.get(shx.ch);
        ++m.smj;
        this.shy = r.k.c.m.q.get(shx.ch);
        this.shx = shx;
        j.shs.add(this);
        this.shy.xh().add(this);
    }
    
    public g yk() {
        return this.shx;
    }
    
    public m ks() {
        return this.shy;
    }
    
    public ArrayList<h> kl() {
        return this.shl;
    }
    
    public static ArrayList<j> h() {
        return j.shs;
    }
    
    public String toString() {
        return this.ef;
    }
    
    static {
        j.shs = new ArrayList<j>();
    }
}
