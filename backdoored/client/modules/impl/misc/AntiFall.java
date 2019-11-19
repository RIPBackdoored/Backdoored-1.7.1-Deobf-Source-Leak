package r.k.d.m.misc;

import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Anti Fall", description = "Tries to prevent you falling a certain distance by lagging you back", category = y.MISC)
public class AntiFall extends g
{
    private final m<Integer> sih;
    private boolean spd;
    public static final boolean sps;
    public static final int spl;
    public static final boolean spy;
    
    public AntiFall() {
        super();
        this.sih = (m<Integer>)new x("Max Fall Distance", this, 10, 3, 40);
        this.spd = false;
    }
    
    public void j() {
        if (this.lo()) {
            if (AntiFall.mc.player.fallDistance >= this.sih.yv()) {
                AntiFall.mc.player.capabilities.isFlying = true;
                this.spd = true;
            }
            if (this.spd) {
                AntiFall.mc.player.capabilities.isFlying = false;
                this.spd = false;
            }
        }
    }
}
