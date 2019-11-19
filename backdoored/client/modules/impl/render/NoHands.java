package r.k.d.m.render;

import org.lwjgl.opengl.GL11;
import r.k.r.j;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import r.k.d.o.h.i.s;
import r.k.d.o.h.u;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "No Hands", description = "Dont render your hands", category = y.RENDER)
public class NoHands extends g
{
    private m<f$s> fr;
    private m<Double> ff;
    private m<Double> fb;
    private m<Double> fw;
    public static final int fg;
    public static final boolean fu;
    
    public NoHands() {
        super();
        this.fr = (m<f$s>)new u("Blacklist", this, f$s.sxq);
        this.ff = (m<Double>)new s("Mainhand Offset", this, 1.0, 0.0, 2.0);
        this.fb = (m<Double>)new s("Offhand Offset", this, 1.0, 0.0, 2.0);
        this.fw = (m<Double>)new s("Transparency", this, 1.0, 0.0, 1.0);
    }
    
    @SubscribeEvent
    public void d(final RenderSpecificHandEvent renderSpecificHandEvent) {
        if (this.lo()) {
            if (this.fr.yv() == f$s.sxq) {
                renderSpecificHandEvent.setCanceled(true);
            }
            if (this.fr.yv() == f$s.sxv && renderSpecificHandEvent.getHand() == EnumHand.OFF_HAND) {
                renderSpecificHandEvent.setCanceled(true);
            }
            if (this.fr.yv() == f$s.sxj && renderSpecificHandEvent.getHand() == EnumHand.MAIN_HAND) {
                renderSpecificHandEvent.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void d(final j j) {
        if (this.lo()) {
            if (j.enumHand == EnumHand.MAIN_HAND) {
                j.ac = this.ff.yv().floatValue() - 1.0f;
            }
            if (j.enumHand == EnumHand.OFF_HAND) {
                j.ac = this.fb.yv().floatValue() - 1.0f;
            }
            GL11.glColor4f(0.0f, 0.0f, 0.0f, this.fw.yv().floatValue());
        }
    }
}
