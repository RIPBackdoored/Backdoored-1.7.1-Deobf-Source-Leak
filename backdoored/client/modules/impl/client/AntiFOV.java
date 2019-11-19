package r.k.d.m.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Anti FOV", description = "Cap your FOV", category = y.CLIENT)
public class AntiFOV extends g
{
    private m<Integer> cs;
    public static final int cl;
    public static final boolean cy;
    
    public AntiFOV() {
        super();
        this.cs = (m<Integer>)new x("Max FOV", this, 125, 0, 360);
    }
    
    @SubscribeEvent
    public void d(final EntityViewRenderEvent.FOVModifier fovModifier) {
        if (this.lo()) {
            fovModifier.setFOV(Math.min(fovModifier.getFOV(), this.cs.yv()));
        }
    }
}
