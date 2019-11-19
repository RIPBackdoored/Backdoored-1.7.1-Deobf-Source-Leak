package r.k.d.m.s;

enum u$s
{
    soh, 
    std, 
    sts;
    
    private static final /* synthetic */ u$s[] $VALUES;
    public static final int stl;
    public static final boolean sty;
    
    public static u$s[] values() {
        return u$s.$VALUES.clone();
    }
    
    public static u$s valueOf(final String s) {
        return Enum.<u$s>valueOf(u$s.class, s);
    }
    
    static {
        $VALUES = new u$s[] { u$s.soh, u$s.std, u$s.sts };
    }
}
