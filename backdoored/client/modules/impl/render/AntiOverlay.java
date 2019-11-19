package r.k.d.m.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Anti Overlay", description = "Prevents Overlay", category = y.RENDER)
public class AntiOverlay extends g
{
    private m<Boolean> stw;
    private m<Boolean> stg;
    private m<Boolean> stu;
    public static final boolean stz;
    public static final int stc;
    public static final boolean sta;
    
    public AntiOverlay() {
        this.stw = (m<Boolean>)new p("Fire", this, true);
        this.stg = (m<Boolean>)new p("Blocks", this, true);
        this.stu = (m<Boolean>)new p("Water", this, true);
    }
    
    @SubscribeEvent
    public void d(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if (!this.lo()) {
            return;
        }
        boolean canceled = false;
        Label_0094: {
            switch (l$s.rp[renderBlockOverlayEvent.getOverlayType().ordinal()]) {
                case 1:
                    if (this.stw.yv()) {
                        canceled = true;
                        break Label_0094;
                    }
                    break;
                case 2:
                    if (this.stg.yv()) {
                        canceled = true;
                        break Label_0094;
                    }
                    break;
                case 3:
                    if (this.stu.yv()) {
                        canceled = true;
                        break;
                    }
                    break;
            }
        }
        renderBlockOverlayEvent.setCanceled(canceled);
    }
}
