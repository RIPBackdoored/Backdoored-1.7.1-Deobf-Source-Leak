package r.k.d.m.client;

import r.k.r.u;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import r.k.r.s;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "MsgOnToggle", description = "Sends message to chat on module toggle", category = y.CLIENT, defaultOn = true)
public class MsgOnToggle extends g
{
    public static final int ssq;
    public static final boolean ssv;
    
    public MsgOnToggle() {
        super();
    }
    
    @SubscribeEvent
    public void d(final s s) {
        if (this.lo() && !s.fz.cm.equalsIgnoreCase("clickgui")) {
            h.o(s.fz.cm + " was enabled", "green");
        }
    }
    
    @SubscribeEvent
    public void d(final u u) {
        if (this.lo() && !u.te.cm.equalsIgnoreCase("clickgui")) {
            h.o(u.te.cm + " was disabled", "red");
        }
    }
}
