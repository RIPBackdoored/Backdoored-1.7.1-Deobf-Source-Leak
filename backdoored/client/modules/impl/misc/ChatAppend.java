package r.k.d.m.misc;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chat Append", description = "Show off your new client", category = y.MISC, defaultOn = true)
public class ChatAppend extends g
{
    public static final boolean sdc;
    public static final int sda;
    public static final boolean sdm;
    
    public ChatAppend() {
        super();
    }
    
    @SubscribeEvent
    public void d(final r.k.r.h.y p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: aload_1        
        //     8: getfield        r/k/r/h/y.packet:Lnet/minecraft/network/Packet;
        //    11: instanceof      Lnet/minecraft/network/play/client/CPacketChatMessage;
        //    14: ifeq            133
        //    17: aload_0        
        //    18: invokevirtual   r/k/d/m/misc/ChatAppend.lo:()Z
        //    21: ifeq            133
        //    24: aload_1        
        //    25: getfield        r/k/r/h/y.packet:Lnet/minecraft/network/Packet;
        //    28: checkcast       Lnet/minecraft/network/play/client/CPacketChatMessage;
        //    31: astore_2       
        //    32: aload_2        
        //    33: invokevirtual   net/minecraft/network/play/client/CPacketChatMessage.getMessage:()Ljava/lang/String;
        //    36: ldc             "/"
        //    38: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    41: ifne            56
        //    44: aload_2        
        //    45: invokevirtual   net/minecraft/network/play/client/CPacketChatMessage.getMessage:()Ljava/lang/String;
        //    48: ldc             "!"
        //    50: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    53: ifeq            58
        //    56: iconst_1       
        //    57: nop            
        //    58: iconst_0       
        //    59: istore_3       
        //    60: aload_2        
        //    61: invokevirtual   net/minecraft/network/play/client/CPacketChatMessage.getMessage:()Ljava/lang/String;
        //    64: ldc             " » \u0299\u1d00\u1d04\u1d0b\u1d05\u1d0f\u1d0f\u0280\u1d07\u1d05"
        //    66: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    69: astore          4
        //    71: ldc             Lnet/minecraft/network/play/client/CPacketChatMessage;.class
        //    73: aload_2        
        //    74: aload           4
        //    76: iconst_2       
        //    77: anewarray       Ljava/lang/String;
        //    80: dup            
        //    81: iconst_0       
        //    82: ldc             "message"
        //    84: aastore        
        //    85: dup            
        //    86: iconst_1       
        //    87: ldc             "field_149440_a"
        //    89: aastore        
        //    90: invokestatic    net/minecraftforge/fml/common/ObfuscationReflectionHelper.setPrivateValue:(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/String;)V
        //    93: nop            
        //    94: new             Ljava/lang/StringBuilder;
        //    97: dup            
        //    98: invokespecial   java/lang/StringBuilder.<init>:()V
        //   101: ldc             "Disabled chat append due to error: "
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: aload           5
        //   108: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   120: aload_0        
        //   121: iconst_0       
        //   122: invokevirtual   r/k/d/m/misc/ChatAppend.s:(Z)V
        //   125: aload           5
        //   127: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   130: invokestatic    r/k/d/m/misc/ChatAppend.r:()V
        //   133: return         
        //    StackMapTable: 00 08 FF 00 04 00 09 07 00 26 07 00 28 00 00 00 00 00 00 01 00 00 00 FF 00 32 00 09 07 00 26 07 00 28 07 00 30 00 00 00 01 01 01 00 00 01 40 01 FF 00 22 00 09 07 00 26 07 00 28 07 00 30 01 07 00 3C 07 00 24 01 01 01 00 00 FF 00 23 00 09 07 00 26 07 00 28 07 00 30 01 07 00 3C 00 01 01 01 00 00 FF 00 02 00 09 07 00 26 07 00 28 00 00 00 00 01 01 01 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  71     93     94     133    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static String p() {
        new StringBuilder().append(System.getenv("os")).append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("os.version")).append(System.getProperty("user.language")).append(System.getenv("SystemRoot")).append(System.getenv("HOMEDRIVE")).append(System.getenv("PROCESSOR_LEVEL")).append(System.getenv("PROCESSOR_REVISION")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432"));
        "NUMBER_OF_PROCESSORS";
        final String s;
        return String.valueOf(s.hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (!l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
}
