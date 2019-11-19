package r.k.b.c;

import net.minecraftforge.fml.common.eventhandler.Event;
import r.k.r.n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import java.util.Objects;
import net.minecraft.util.text.ITextComponent;

public class h implements r.k.h
{
    public static final boolean lsw;
    public static final int lsg;
    public static final boolean lsu;
    
    public h() {
        super();
    }
    
    public static void sj(final String s) {
        s(s, true);
    }
    
    public static void s(final String s, final boolean b) {
        d(s, "white", b);
    }
    
    public static void o(final String s, final String s2) {
        d(s, s2, true);
    }
    
    public static void d(final String s, final String s2, final boolean b) {
        try {
            d(Objects.<ITextComponent>requireNonNull(ITextComponent.Serializer.jsonToComponent("{\"text\":\"" + s + "\",\"color\":\"" + s2 + "\"}")), b);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void d(final ITextComponent textComponent) {
    }
    
    public static void d(final ITextComponent textComponent, final boolean b) {
        try {
            if (b) {
                new TextComponentString("§a[§cBD§a]§r ").appendSibling(textComponent);
            }
            h.mc.ingameGUI.addChatMessage(ChatType.SYSTEM, textComponent);
            MinecraftForge.EVENT_BUS.post((Event)new n(textComponent));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
