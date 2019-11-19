package r.k.d.m.t;

enum g$x
{
    ldo, 
    ldt, 
    ldn, 
    ldi;
    
    private static final /* synthetic */ g$x[] $VALUES;
    public static final int ldp;
    public static final boolean ldr;
    
    public static g$x[] values() {
        return g$x.$VALUES.clone();
    }
    
    public static g$x valueOf(final String s) {
        return Enum.<g$x>valueOf(g$x.class, s);
    }
    
    static {
        $VALUES = new g$x[] { g$x.ldo, g$x.ldt, g$x.ldn, g$x.ldi };
    }
}
