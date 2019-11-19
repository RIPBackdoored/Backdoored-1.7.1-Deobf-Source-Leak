package r.k.d.m.y;

enum i$s
{
    d, 
    s, 
    l;
    
    private static final /* synthetic */ i$s[] $VALUES;
    public static final int y;
    public static final boolean x;
    
    public static i$s[] values() {
        return i$s.$VALUES.clone();
    }
    
    public static i$s valueOf(final String s) {
        return Enum.<i$s>valueOf(i$s.class, s);
    }
    
    static {
        $VALUES = new i$s[] { i$s.d, i$s.s, i$s.l };
    }
}
