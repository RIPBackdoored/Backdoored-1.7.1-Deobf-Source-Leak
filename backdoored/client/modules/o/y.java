package r.k.d.o;

import java.util.ArrayList;

public enum y
{
    qh, 
    vd, 
    vs, 
    vl, 
    vy, 
    vx, 
    vk;
    
    protected ArrayList<m> vq;
    private static final /* synthetic */ y[] $VALUES;
    public static final int vv;
    public static final boolean vj;
    
    public static y[] values() {
        return y.$VALUES.clone();
    }
    
    public static y valueOf(final String s) {
        return Enum.<y>valueOf(y.class, s);
    }
    
    private y() {
        this.vq = new ArrayList<m>();
    }
    
    public ArrayList<m> h() {
        return this.vq;
    }
    
    static {
        $VALUES = new y[] { y.qh, y.vd, y.vs, y.vl, y.vy, y.vx, y.vk };
    }
}
