package r.k.d.m.b;

enum c$s
{
    snb("12"), 
    snw("24");
    
    private String sng;
    private static final /* synthetic */ c$s[] $VALUES;
    public static final int snu;
    public static final boolean snz;
    
    public static c$s[] values() {
        return c$s.$VALUES.clone();
    }
    
    public static c$s valueOf(final String s) {
        return Enum.<c$s>valueOf(c$s.class, s);
    }
    
    private c$s(final String sng) {
        this.sng = sng;
    }
    
    @Override
    public String toString() {
        return this.sng;
    }
    
    static {
        $VALUES = new c$s[] { c$s.snb, c$s.snw };
    }
}
