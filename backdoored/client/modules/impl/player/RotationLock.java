package r.k.d.m.player;

import r.k.d.o.h.u;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Rotation Lock", description = "Lock your rotation", category = y.PLAYER)
public class RotationLock extends g
{
    private final m<j$s> lyj;
    public static final boolean lye;
    public static final int lyo;
    public static final boolean lyt;
    
    public RotationLock() {
        super();
        this.lyj = (m<j$s>)new u("Facing", this, j$s.gt);
    }
    
    public void v() {
        RotationLock.mc.player.setRotationYawHead((float)this.kw());
    }
    
    public void j() {
        if (this.lo()) {
            RotationLock.mc.player.rotationYaw = (float)this.kw();
        }
    }
    
    private int kw() {
        switch (j$x.sqe[this.lyj.yv().ordinal()]) {
            default:
                return -90;
            case 2:
            case 3:
            case 4:
                return -90;
        }
    }
}
