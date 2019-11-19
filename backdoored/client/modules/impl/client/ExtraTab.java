package r.k.d.m.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.p;
import r.k.d.m.b.z;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Extra Tab", description = "Display full tab menu", category = y.CLIENT)
public class ExtraTab extends g
{
    public static z sje;
    public static final int sjo;
    public static final boolean sjt;
    
    public ExtraTab() {
        super();
        ExtraTab.sje = (z)this;
    }
    
    @SubscribeEvent
    public void d(final p p) {
        p.sse = p.ssj.size();
    }
}
