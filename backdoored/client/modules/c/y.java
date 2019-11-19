package r.k.d.c;

public enum y
{
    xv("Movement"), 
    xj("Render"), 
    xe("Player"), 
    xo("Combat"), 
    xt("Misc"), 
    xn("Exploits"), 
    xi("Client"), 
    xp("UIs"), 
    xr("ChatBot");
    
    public h xf;
    private static final /* synthetic */ y[] $VALUES;
    public static final int xb;
    public static final boolean xw;
    
    public static y[] values() {
        return y.$VALUES.clone();
    }
    
    public static y valueOf(final String s) {
        return Enum.<y>valueOf(y.class, s);
    }
    
    private y(final String s2) {
        this.xf = new h(s2);
    }
    
    static {
        $VALUES = new y[] { y.xv, y.xj, y.xe, y.xo, y.xt, y.xn, y.xi, y.xp, y.xr };
    }
}
