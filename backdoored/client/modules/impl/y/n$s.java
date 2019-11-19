package r.k.d.m.y;

enum n$s
{
    public static final n$s spo;
    public static final n$s spt;
    public static final n$s spn;
    private final String spi;
    private static final /* synthetic */ n$s[] $VALUES;
    public static final int spp;
    public static final boolean spr;
    
    public static n$s[] values() {
        return n$s.$VALUES.clone();
    }
    
    public static n$s valueOf(final String s) {
        return Enum.<n$s>valueOf(n$s.class, s);
    }
    
    private n$s(final String spi) {
        this.spi = spi;
    }
    
    @Override
    public String toString() {
        return this.spi;
    }
    
    static {
        spo = new n$s("Portal God Mode");
        spt = new n$s("Spoof Death");
        $VALUES = new n$s[] { n$s.spo, n$s.spt, n$s.spn };
    }
}
