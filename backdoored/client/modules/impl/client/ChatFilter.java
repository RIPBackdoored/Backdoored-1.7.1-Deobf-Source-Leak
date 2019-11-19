package r.k.d.m.client;

import java.util.Objects;
import r.k.b.c.h;
import org.apache.logging.log4j.LogManager;
import r.k.d.m.q.i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.text.ChatType;
import java.util.regex.Pattern;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import r.k.d.o.h.p;
import r.k.d.m.q.u;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chat Filter", description = "Filter your chat", category = y.CLIENT)
public class ChatFilter extends g
{
    private m<Boolean> sit;
    private m<Boolean> sin;
    private m<Boolean> sii;
    private u sip;
    public static final boolean sir;
    public static final int sif;
    public static final boolean sib;
    
    public ChatFilter() {
        super();
        this.sit = (m<Boolean>)new p("Allow Whispers", this, true);
        this.sin = (m<Boolean>)new p("Allow Mentions", this, true);
        this.sii = (m<Boolean>)new p("Allow Game Info", this, true);
    }
    
    @SubscribeEvent
    public void l(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (this.lo()) {
            clientChatReceivedEvent.setCanceled(true);
            final String lowerCase = clientChatReceivedEvent.getMessage().getUnformattedText().toLowerCase();
            if (this.sit.yv()) {
                final String[] split = lowerCase.split(Pattern.quote(" "));
                if (split.length >= 3 && split[1].equals("whispers:")) {
                    clientChatReceivedEvent.setCanceled(false);
                }
            }
            if (this.sin.yv() && lowerCase.contains(ChatFilter.mc.player.getName().toLowerCase())) {
                clientChatReceivedEvent.setCanceled(false);
            }
            if (this.sii.yv() && clientChatReceivedEvent.getType() == ChatType.GAME_INFO) {
                clientChatReceivedEvent.setCanceled(false);
            }
            if (!clientChatReceivedEvent.isCanceled()) {
                clientChatReceivedEvent.setCanceled(this.sk(clientChatReceivedEvent.getMessage().getUnformattedText()));
            }
        }
    }
    
    public boolean sk(final String s) {
        if (this.sip == null) {
            try {
                this.sip = new u().sy(i.g("Backdoored/chatfilter.js")).d(LogManager.getLogger("BackdooredChatFilter"));
            }
            catch (Exception ex) {
                this.s(false);
                h.o("Failed to initialise Chat Filter script: " + ex.getMessage(), "red");
                ex.printStackTrace();
                return false;
            }
        }
        try {
            return Objects.<Boolean>requireNonNull(this.sip.s("isExcluded", s));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        }
    }
}
