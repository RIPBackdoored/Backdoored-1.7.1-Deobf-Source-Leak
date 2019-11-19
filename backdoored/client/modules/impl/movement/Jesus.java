package r.k.d.m.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.cc;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Jesus", description = "Walk on water", category = y.MOVEMENT)
public class Jesus extends g
{
    public static final int mk;
    public static final boolean mq;
    
    public Jesus() {
        super();
    }
    
    @SubscribeEvent
    public void d(final cc cc) {
        if (this.lo()) {
            cc.spx = 0.4;
        }
    }
}
