package r.k.c.c;

import r.k.d.o.m;
import java.util.ArrayList;

public class h extends y
{
    private static ArrayList<h> wb;
    private j ww;
    private m wg;
    public boolean wu;
    public static final int wz;
    public static final boolean wc;
    
    public h(final m wg) {
        super(0, 0, 100, 12, wg.o() + ": " + wg.yv(), false, new float[] { 1.0f, 0.4f, 0.0f, 1.0f });
        this.wu = false;
        this.wg = wg;
        this.ww.kl().add(this);
        h.wb.add(this);
    }
    
    @Override
    public void y(final int n, final int n2) {
    }
    
    public j sb() {
        return this.ww;
    }
    
    public m sw() {
        return this.wg;
    }
    
    public static ArrayList<h> h() {
        return h.wb;
    }
    
    static {
        h.wb = new ArrayList<h>();
    }
}
