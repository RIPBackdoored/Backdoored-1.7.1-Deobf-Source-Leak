package r.k.d.m.client;

import java.util.Date;
import java.text.SimpleDateFormat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import r.k.d.o.h.u;
import r.k.d.o.h.p;
import r.k.b.k$h;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chat Timestamps", description = "Timestamps on chat messages", category = y.CLIENT)
public class ChatTimestamps extends g
{
    private m<Boolean> fa;
    private m<Boolean> fm;
    private m<c$s> fh;
    private m<k$h> bd;
    private m<Boolean> bs;
    public static final boolean bl;
    public static final int by;
    public static final boolean bx;
    
    public ChatTimestamps() {
        super();
        this.fa = (m<Boolean>)new p("Seconds", this, false);
        this.fh = (m<c$s>)new u("Hour format", this, c$s.snb);
        this.bd = (m<k$h>)new u("Colour", this, k$h.srp);
        this.bs = (m<Boolean>)new p("Brackets", this, true);
    }
    
    @SubscribeEvent
    public void s(final ClientChatReceivedEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: aload_0        
        //     6: invokevirtual   r/k/d/m/client/ChatTimestamps.lo:()Z
        //     9: ifeq            135
        //    12: aload_0        
        //    13: getfield        r/k/d/m/client/ChatTimestamps.bd:Lr/k/d/o/m;
        //    16: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    19: checkcast       Lr/k/b/k$h;
        //    22: invokevirtual   r/k/b/k$h.toString:()Ljava/lang/String;
        //    25: invokestatic    r/k/b/k.a:(Ljava/lang/String;)Ljava/lang/String;
        //    28: astore_2       
        //    29: aload_0        
        //    30: getfield        r/k/d/m/client/ChatTimestamps.bs:Lr/k/d/o/m;
        //    33: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    36: checkcast       Ljava/lang/Boolean;
        //    39: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    42: ifeq            60
        //    45: iconst_2       
        //    46: anewarray       Ljava/lang/String;
        //    49: dup            
        //    50: iconst_0       
        //    51: ldc             "<"
        //    53: aastore        
        //    54: dup            
        //    55: iconst_1       
        //    56: ldc             ">"
        //    58: aastore        
        //    59: nop            
        //    60: iconst_2       
        //    61: anewarray       Ljava/lang/String;
        //    64: dup            
        //    65: iconst_0       
        //    66: ldc             ""
        //    68: aastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: ldc             ""
        //    73: aastore        
        //    74: astore_3       
        //    75: new             Lnet/minecraft/util/text/TextComponentString;
        //    78: dup            
        //    79: new             Ljava/lang/StringBuilder;
        //    82: dup            
        //    83: invokespecial   java/lang/StringBuilder.<init>:()V
        //    86: aload_2        
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: aload_3        
        //    91: iconst_0       
        //    92: aaload         
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: aload_0        
        //    97: invokespecial   r/k/d/m/client/ChatTimestamps.sr:()Ljava/lang/String;
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload_3        
        //   104: iconst_1       
        //   105: aaload         
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: ldc             "§r "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: invokespecial   net/minecraft/util/text/TextComponentString.<init>:(Ljava/lang/String;)V
        //   120: astore          4
        //   122: aload_1        
        //   123: aload           4
        //   125: aload_1        
        //   126: invokevirtual   net/minecraftforge/client/event/ClientChatReceivedEvent.getMessage:()Lnet/minecraft/util/text/ITextComponent;
        //   129: invokevirtual   net/minecraft/util/text/TextComponentString.appendSibling:(Lnet/minecraft/util/text/ITextComponent;)Lnet/minecraft/util/text/ITextComponent;
        //   132: invokevirtual   net/minecraftforge/client/event/ClientChatReceivedEvent.setMessage:(Lnet/minecraft/util/text/ITextComponent;)V
        //   135: return         
        //    StackMapTable: 00 05 FF 00 04 00 08 07 00 11 07 00 5B 00 00 00 00 00 01 00 00 FF 00 00 00 08 07 00 11 07 00 5B 00 00 00 01 01 01 00 00 FF 00 36 00 08 07 00 11 07 00 5B 07 00 76 00 00 01 01 01 00 00 4D 07 00 7D FF 00 3C 00 08 07 00 11 07 00 5B 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private String sr() {
        final StringBuilder sb = new StringBuilder();
        if (this.fh.yv() == c$s.snb) {
            sb.append("hh");
        }
        sb.append("HH");
        sb.append(":mm");
        if (this.fa.yv()) {
            sb.append(":ss");
        }
        if (this.fm.yv()) {
            sb.append(".SSS");
        }
        if (this.fm.yv()) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sb.toString());
        }
        return new SimpleDateFormat(sb.toString()).format(new Date());
    }
}
