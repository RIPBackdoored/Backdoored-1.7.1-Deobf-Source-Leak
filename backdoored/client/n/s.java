package r.k.n;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.ChatType;
import r.k.b.c.h;

public class s extends c
{
    public static final boolean sij;
    public static final int sie;
    public static final boolean sio;
    
    public s() {
        super(new String[] { "fakemsg", "msg", "impersonate" });
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length < 3) {
            return false;
        }
        final String s = array[0];
        int n = -1;
        Label_0144: {
            switch (s.hashCode()) {
                case 3052376:
                    if (s.equals("chat")) {
                        n = 0;
                        break Label_0144;
                    }
                    break;
                case 1316693890:
                    if (s.equals("whisper")) {
                        n = 2;
                        break Label_0144;
                    }
                    break;
                case -905826493:
                    if (s.equals("server")) {
                        n = 3;
                        break Label_0144;
                    }
                    break;
                case -1861625298:
                    if (s.equals("suicide")) {
                        n = 4;
                        break Label_0144;
                    }
                    break;
                case 3291998:
                    if (s.equals("kill")) {
                        n = 5;
                        break Label_0144;
                    }
                    break;
                case -1458148582:
                    if (s.equals("killWeapon")) {
                        n = 6;
                        break;
                    }
                    break;
            }
        }
        switch (n) {
            default: {
                final StringBuilder sb = new StringBuilder();
                sb.append("<").append(array[1]).append("> ");
                int n2 = 2;
                if (n2 < array.length) {
                    sb.append(array[n2]).append(" ");
                    ++n2;
                }
                h.s(sb.toString(), false);
                return true;
            }
            case 2: {
                final String s2 = array[1];
                final StringBuilder sb2 = new StringBuilder();
                int n3 = 2;
                if (n3 < array.length) {
                    sb2.append(array[n3]).append(" ");
                    ++n3;
                }
                this.mc.ingameGUI.addChatMessage(ChatType.CHAT, (ITextComponent)new TextComponentString("§d" + s2 + " whispers: " + sb2.toString()));
                return true;
            }
            case 3: {
                final StringBuilder sb3 = new StringBuilder("§e[SERVER] ");
                int n4 = 1;
                if (n4 < array.length) {
                    sb3.append(array[n4]).append(" ");
                    ++n4;
                }
                h.s(sb3.toString(), false);
                return true;
            }
            case 4: {
                final String s3 = array[1];
                final StringBuilder sb4 = new StringBuilder("§4");
                int n5 = 2;
                if (n5 < array.length) {
                    sb4.append(array[n5]).append(" ");
                    ++n5;
                }
                h.s(sb4.toString().replace(" player ", " §3" + s3 + " §4"), false);
                return true;
            }
            case 5: {
                final String s4 = array[1];
                final String s5 = array[2];
                final StringBuilder sb5 = new StringBuilder("§4");
                int n6 = 3;
                if (n6 < array.length) {
                    sb5.append(array[n6]).append(" ");
                    ++n6;
                }
                h.s(sb5.toString().replace(" player1 ", " §3" + s4 + " §4").replace(" player2 ", " §3" + s5 + " §4"), false);
                return true;
            }
            case 6: {
                final String s6 = array[1];
                final String s7 = array[2];
                final String s8 = array[3];
                final StringBuilder sb6 = new StringBuilder("§4");
                int n7 = 4;
                if (n7 < array.length) {
                    sb6.append(array[n7]).append(" ");
                    ++n7;
                }
                h.s(sb6.toString().replace(" player1 ", " §3" + s6 + " §4").replace(" player2 ", " §3" + s7 + " §4").replace(" weapon ", " §6" + s8 + " §4"), false);
                return true;
            }
        }
    }
    
    @Override
    public String k() {
        return "-fakemsg chat 4yl im kinda ez ngl\n-fakemsg whisper John200410 Backdoored client on top\n-fakemsg server buy prio pls";
    }
}
