package r.k.d.m.chatbot;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketChat;
import r.k.r.h.p;
import r.k.b.c.h;
import r.k.d.m.q.i;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chat Bot", description = "Scriptable chat bot", category = y.CHATBOT)
public class ChatBot extends g
{
    private i snc;
    private long sna;
    public static final boolean snm;
    public static final int snh;
    public static final boolean sid;
    
    public ChatBot() {
        super();
        this.sna = 0L;
    }
    
    public void v() {
        try {
            this.snc = new i();
        }
        catch (Exception ex) {
            this.s(false);
            h.o("Failed to initialise chatbot script: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void y(final p p) {
        if (this.lo() && p.packet instanceof SPacketChat && System.currentTimeMillis() - this.sna > 5000L) {
            final SPacketChat sPacketChat = (SPacketChat)p.packet;
            this.e(sPacketChat.getChatComponent().getUnformattedText(), sPacketChat.getType().name());
            this.sna = System.currentTimeMillis();
        }
    }
    
    private void e(final String s, final String s2) {
        if (ChatBot.mc.player == null || s.startsWith("<" + ChatBot.mc.player.getName()) || s.startsWith("<" + ChatBot.mc.player.getDisplayNameString())) {
            return;
        }
        try {
            if (this.snc == null) {
                this.snc = new i();
            }
            final String k = this.snc.k(s, s2);
            if (k != null) {
                ChatBot.mc.player.sendChatMessage(k);
            }
        }
        catch (Exception ex) {
            this.s(false);
            h.o("Failure while invoking chatbot script: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }
}
