package r.k.c.c;

import r.k.d.c.h;
import java.util.ArrayList;

public class m extends y
{
    private static ArrayList<m> sms;
    private ArrayList<j> sml;
    public static final int smy;
    public static final int smx;
    private static final int smk;
    private static int smq;
    private h smv;
    int smj;
    public boolean sme;
    public int smo;
    public int smt;
    public static final boolean smn;
    public static final int smi;
    public static final boolean smp;
    
    public m(final h smv) {
        super(25, m.smq, 100, 20, smv.o(), true, new float[] { 1.0f, 0.0f, 0.0f, 1.0f });
        this.sml = new ArrayList<j>();
        this.smj = 1;
        this.sme = false;
        this.smt = 0;
        this.smv = smv;
        m.smq += 21;
        m.sms.add(this);
    }
    
    public h xm() {
        return this.smv;
    }
    
    public ArrayList<j> xh() {
        return this.sml;
    }
    
    public static ArrayList<m> h() {
        return m.sms;
    }
    
    public static ArrayList<m> kd() {
        final ArrayList<m> list = new ArrayList<m>();
        int n = h().size() - 1;
        if (n >= 0) {
            list.add(h().get(n));
            --n;
        }
        return list;
    }
    
    static {
        smk = 25;
        smx = 20;
        smy = 100;
        m.smq = 25;
    }
}
