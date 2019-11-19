package r.k.d.m.player;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.IntFunction;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000p.\u0000f;
import java.util.Random;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraftforge.client.event.GuiOpenEvent;
import r.k.d.o.h.p;
import r.k.d.o.m;
import net.minecraft.util.text.TextComponentString;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "AutoSign", description = "Automatically place signs with text", category = y.PLAYER)
public class AutoSign extends g
{
    private static final TextComponentString[] textComponentString;
    private String[] pa;
    private m<Boolean> pm;
    public static final boolean ph;
    public static final int rd;
    public static final boolean rs;
    
    public AutoSign() {
        super();
        this.pa = new String[] { "Backdoored Client", "Is the best", "2b2t client", "discord/pdMhDwN" };
        this.pm = (m<Boolean>)new p("Try Chunk Ban", this, false);
    }
    
    @SubscribeEvent
    public void s(final GuiOpenEvent guiOpenEvent) {
        if (this.lo() && guiOpenEvent.getGui() instanceof GuiEditSign) {
            final TileEntitySign tileEntitySign = (TileEntitySign)ObfuscationReflectionHelper.getPrivateValue((Class)GuiScreen.class, (Object)guiOpenEvent.getGui(), new String[] { "tileSign", "field_146848_f" });
            if (tileEntitySign != null) {
                AutoSign.mc.player.connection.sendPacket((Packet)new CPacketUpdateSign(tileEntitySign.getPos(), (ITextComponent[])AutoSign.textComponentString));
                guiOpenEvent.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void l(final r.k.r.h.y y) {
        if (this.lo() && y.packet instanceof CPacketUpdateSign) {
            final CPacketUpdateSign cPacketUpdateSign = (CPacketUpdateSign)y.packet;
            final String[] lines = cPacketUpdateSign.getLines();
            if (this.pm.yv()) {
                final String sv = sv();
                int n = 0;
                if (n < 4) {
                    lines[n] = sv.substring(n * 384, (n + 1) * 384);
                    ++n;
                }
            }
            ObfuscationReflectionHelper.setPrivateValue((Class)CPacketUpdateSign.class, (Object)cPacketUpdateSign, (Object)this.pa, new String[] { "lines", "field_149590_d" });
        }
    }
    
    private static String sv() {
        return new Random().ints(128, 1112063).map(\u0000f::x).limit(1536L).<Object>mapToObj((IntFunction<?>)\u0000f::y).<String, ?>collect((Collector<? super Object, ?, String>)Collectors.joining());
    }
    
    private static /* synthetic */ String y(final int n) {
        return String.valueOf((char)n);
    }
    
    private static /* synthetic */ int x(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            7
        //     4: ldc             1273204038
        //     6: ireturn        
        //     7: nop            
        //     8: iload_0        
        //     9: ldc             55296
        //    11: if_icmpge       16
        //    14: iload_0        
        //    15: nop            
        //    16: iload_0        
        //    17: sipush          2048
        //    20: iadd           
        //    21: ireturn        
        //    StackMapTable: 00 04 FE 00 04 00 00 01 02 FF 00 08 00 04 01 01 00 01 00 00 44 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        textComponentString = new TextComponentString[] { new TextComponentString(""), new TextComponentString(""), new TextComponentString(""), new TextComponentString("") };
    }
}
