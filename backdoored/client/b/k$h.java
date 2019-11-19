package r.k.b;

public enum k$h
{
    public static final k$h srx;
    public static final k$h srk;
    public static final k$h srq;
    public static final k$h srv;
    public static final k$h srj;
    public static final k$h sre;
    public static final k$h sro;
    public static final k$h srt;
    public static final k$h srn;
    public static final k$h sri;
    public static final k$h srp;
    public static final k$h srr;
    public static final k$h srf;
    public static final k$h srb;
    public static final k$h srw;
    public static final k$h srg;
    private String sru;
    private static final /* synthetic */ k$h[] $VALUES;
    public static final int srz;
    public static final boolean src;
    
    public static k$h[] values() {
        return k$h.$VALUES.clone();
    }
    
    public static k$h valueOf(final String s) {
        return Enum.<k$h>valueOf(k$h.class, s);
    }
    
    private k$h(final String sru) {
        this.sru = sru;
    }
    
    @Override
    public String toString() {
        return this.sru;
    }
    
    static {
        srx = new k$h("Dark Red");
        srk = new k$h("Red");
        srq = new k$h("Gold");
        srv = new k$h("Yellow");
        sre = new k$h("Green");
        sro = new k$h("Aqua");
        srt = new k$h("Dark Aqua");
        srn = new k$h("Dark Blue");
        sri = new k$h("Blue");
        srp = new k$h("Light Purple");
        srr = new k$h("Dark Purple");
        srf = new k$h("White");
        srb = new k$h("Gray");
        srw = new k$h("Dark Gray");
        srg = new k$h("Black");
        $VALUES = new k$h[] { k$h.srx, k$h.srk, k$h.srq, k$h.srv, k$h.srj, k$h.sre, k$h.sro, k$h.srt, k$h.srn, k$h.sri, k$h.srp, k$h.srr, k$h.srf, k$h.srb, k$h.srw, k$h.srg };
    }
}
