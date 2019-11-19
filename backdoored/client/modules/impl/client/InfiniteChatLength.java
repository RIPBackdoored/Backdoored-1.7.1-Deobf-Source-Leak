package r.k.d.m.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import r.k.r.e;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "InfiniteChatLength", description = "Have infinite scrolling chat", category = y.CLIENT)
public class InfiniteChatLength extends g
{
    public static final boolean llw;
    public static final int llg;
    public static final boolean llu;
    
    public InfiniteChatLength() {
        super();
    }
    
    @SubscribeEvent
    public void d(final e e) {
        if (this.lo()) {
            e.setResult(Event.Result.ALLOW);
        }
        e.setResult(Event.Result.DENY);
    }
}
