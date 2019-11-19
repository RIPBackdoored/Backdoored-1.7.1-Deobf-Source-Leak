package r.k.d.m.y;

enum z$s
{
    sak("Offhand"), 
    saq("Sneak"), 
    sav("VClip"), 
    saj("Window Click"), 
    sae("Item Switch"), 
    sao("Use Item"), 
    sat("Hand Spam"), 
    san("Entity Movement");
    
    private final String sai;
    private static final /* synthetic */ z$s[] $VALUES;
    public static final int sap;
    public static final boolean sar;
    
    public static z$s[] values() {
        return z$s.$VALUES.clone();
    }
    
    public static z$s valueOf(final String s) {
        return Enum.<z$s>valueOf(z$s.class, s);
    }
    
    private z$s(final String sai) {
        this.sai = sai;
    }
    
    @Override
    public String toString() {
        return this.sai;
    }
    
    static {
        $VALUES = new z$s[] { z$s.sak, z$s.saq, z$s.sav, z$s.saj, z$s.sae, z$s.sao, z$s.sat, z$s.san };
    }
}
