package r.k.d.m.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketChat;
import r.k.r.h.p;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Reply", description = "Tell those scrubs whos boss", category = y.MISC)
public class AutoReply extends g
{
    private final String sgg = "Ebic/autoreplies.txt";
    private String[] sgu;
    public static final int sgz;
    public static final boolean sgc;
    
    public AutoReply() {
        this.sgu = new String[0];
    }
    
    @SubscribeEvent
    public void k(final p p) {
        if (this.lo() && p.packet instanceof SPacketChat && ((SPacketChat)p.packet).getChatComponent().getUnformattedText().contains(" whispers: ")) {
            AutoReply.mc.player.sendChatMessage("/r Shut up Scrub");
        }
    }
}
