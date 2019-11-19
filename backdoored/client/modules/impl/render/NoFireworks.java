package r.k.d.m.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.cu;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "No Fireworks", description = "Stop people from lagging you out", category = y.RENDER)
public class NoFireworks extends g
{
    public static final int fq;
    public static final boolean fv;
    
    public NoFireworks() {
        super();
    }
    
    @SubscribeEvent
    public void d(final cu cu) {
        if (this.lo()) {
            cu.setCanceled(true);
        }
    }
}
