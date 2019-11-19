package r.k.d.m.render;

import r.k.r.d;
import r.k.r.co;
import r.k.r.l;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.o;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Full Bright", description = "Big Brightness bois", category = y.RENDER)
public class FullBright extends g
{
    public static final int slb;
    public static final boolean slw;
    
    public FullBright() {
        super();
    }
    
    @SubscribeEvent
    public void d(final o o) {
        if (this.lo()) {
            o.ll = 1.0f;
        }
    }
    
    @SubscribeEvent
    public void d(final l l) {
        if (this.lo()) {
            l.mz = 1.0f;
        }
    }
    
    @SubscribeEvent
    public void d(final co co) {
        if (this.lo()) {
            co.spf = 1000;
        }
    }
    
    @SubscribeEvent
    public void d(final d d) {
        if (this.lo()) {
            d.sdo = false;
        }
    }
}
