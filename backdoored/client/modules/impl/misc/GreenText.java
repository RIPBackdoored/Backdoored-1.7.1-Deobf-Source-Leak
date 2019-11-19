package r.k.d.m.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "GreenText", description = "Prepend text with >", category = y.MISC)
public class GreenText extends g
{
    public static final int lys;
    public static final boolean lyl;
    
    public GreenText() {
        super();
    }
    
    @SubscribeEvent
    public void d(final ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().charAt(0) == '/' || clientChatEvent.getMessage().charAt(0) == '!') {
            return;
        }
        clientChatEvent.setMessage(">" + clientChatEvent.getMessage());
    }
}
