package r.k.d.m.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.y.l;
import r.k.b.c.y.u;
import r.k.b.c.y.p;
import r.k.b.c.y.f;
import r.k.b.c.y.c;
import r.k.b.c.y.i;
import r.k.d.o.m;
import r.k.b.c.y.j;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chat Modifier", description = "Modify your chat messages", category = y.PLAYER)
public class ChatModifier extends g
{
    private j[] gb;
    private m<Boolean> gw;
    private m<Boolean> gg;
    private m<Boolean> gu;
    private m<Boolean> gz;
    private m<Boolean> gc;
    private m<Boolean> ga;
    private m<Boolean> gm;
    private m<Boolean> gh;
    public static final boolean ud;
    public static final int us;
    public static final boolean ul;
    
    public ChatModifier() {
        super();
        this.gb = new j[] { new r.k.b.c.y.g(), new r.k.b.c.y.y(), new i(), new c(), new f(), new p(), new u(), new l() };
        this.gw = (m<Boolean>)new r.k.d.o.h.p("Emphasize", this, false);
        this.gg = (m<Boolean>)new r.k.d.o.h.p("Reverse", this, false);
        this.gu = (m<Boolean>)new r.k.d.o.h.p("Chav", this, false);
        this.gz = (m<Boolean>)new r.k.d.o.h.p("JustLearntEngrish", this, false);
        this.gc = (m<Boolean>)new r.k.d.o.h.p("L33t", this, false);
        this.ga = (m<Boolean>)new r.k.d.o.h.p("Disabled", this, false);
        this.gm = (m<Boolean>)new r.k.d.o.h.p("Fancy", this, false);
        this.gh = (m<Boolean>)new r.k.d.o.h.p("Soviet", this, false);
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
        //    14: ifeq            559
        //    17: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    20: ldc             "Was packet"
        //    22: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: invokevirtual   r/k/d/m/player/ChatModifier.lo:()Z
        //    29: ifeq            559
        //    32: aload_1        
        //    33: getfield        r/k/r/h/y.packet:Lnet/minecraft/network/Packet;
        //    36: checkcast       Lnet/minecraft/network/play/client/CPacketChatMessage;
        //    39: astore_2       
        //    40: aload_2        
        //    41: invokevirtual   net/minecraft/network/play/client/CPacketChatMessage.getMessage:()Ljava/lang/String;
        //    44: astore_3       
        //    45: aload_3        
        //    46: ldc             "/"
        //    48: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    51: ifne            63
        //    54: aload_3        
        //    55: ldc             "!"
        //    57: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    60: ifeq            65
        //    63: iconst_1       
        //    64: nop            
        //    65: iconst_0       
        //    66: istore          4
        //    68: aload_0        
        //    69: getfield        r/k/d/m/player/ChatModifier.gb:[Lr/k/b/c/y/j;
        //    72: astore          5
        //    74: aload           5
        //    76: arraylength    
        //    77: istore          6
        //    79: iconst_0       
        //    80: istore          7
        //    82: iload           7
        //    84: iload           6
        //    86: if_icmpge       499
        //    89: aload           5
        //    91: iload           7
        //    93: aaload         
        //    94: astore          8
        //    96: iconst_0       
        //    97: istore          9
        //    99: aload           8
        //   101: invokevirtual   r/k/b/c/y/j.o:()Ljava/lang/String;
        //   104: astore          10
        //   106: iconst_m1      
        //   107: istore          11
        //   109: aload           10
        //   111: invokevirtual   java/lang/String.hashCode:()I
        //   114: lookupswitch {
        //          -1812617442: 287
        //          -1530467646: 202
        //          -180296146: 230
        //          2099066: 216
        //          2314824: 244
        //          67645097: 272
        //          219200290: 188
        //          335584924: 258
        //          default: 301
        //        }
        //   188: aload           10
        //   190: ldc             "Emphasize"
        //   192: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   195: ifeq            301
        //   198: iconst_0       
        //   199: istore          11
        //   201: nop            
        //   202: aload           10
        //   204: ldc             "Reverse"
        //   206: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   209: ifeq            301
        //   212: iconst_1       
        //   213: istore          11
        //   215: nop            
        //   216: aload           10
        //   218: ldc             "Chav"
        //   220: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   223: ifeq            301
        //   226: iconst_2       
        //   227: istore          11
        //   229: nop            
        //   230: aload           10
        //   232: ldc             "JustLearntEngrish"
        //   234: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   237: ifeq            301
        //   240: iconst_3       
        //   241: istore          11
        //   243: nop            
        //   244: aload           10
        //   246: ldc             "L33t"
        //   248: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   251: ifeq            301
        //   254: iconst_4       
        //   255: istore          11
        //   257: nop            
        //   258: aload           10
        //   260: ldc             "Disabled"
        //   262: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   265: ifeq            301
        //   268: iconst_5       
        //   269: istore          11
        //   271: nop            
        //   272: aload           10
        //   274: ldc             "Fancy"
        //   276: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   279: ifeq            301
        //   282: bipush          6
        //   284: istore          11
        //   286: nop            
        //   287: aload           10
        //   289: ldc             "Soviet"
        //   291: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   294: ifeq            301
        //   297: bipush          7
        //   299: istore          11
        //   301: iload           11
        //   303: tableswitch {
        //                0: 348
        //                1: 364
        //                2: 380
        //                3: 396
        //                4: 412
        //                5: 428
        //                6: 444
        //                7: 460
        //          default: 475
        //        }
        //   348: aload_0        
        //   349: getfield        r/k/d/m/player/ChatModifier.gw:Lr/k/d/o/m;
        //   352: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   355: checkcast       Ljava/lang/Boolean;
        //   358: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   361: istore          9
        //   363: nop            
        //   364: aload_0        
        //   365: getfield        r/k/d/m/player/ChatModifier.gg:Lr/k/d/o/m;
        //   368: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   371: checkcast       Ljava/lang/Boolean;
        //   374: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   377: istore          9
        //   379: nop            
        //   380: aload_0        
        //   381: getfield        r/k/d/m/player/ChatModifier.gu:Lr/k/d/o/m;
        //   384: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   387: checkcast       Ljava/lang/Boolean;
        //   390: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   393: istore          9
        //   395: nop            
        //   396: aload_0        
        //   397: getfield        r/k/d/m/player/ChatModifier.gz:Lr/k/d/o/m;
        //   400: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   403: checkcast       Ljava/lang/Boolean;
        //   406: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   409: istore          9
        //   411: nop            
        //   412: aload_0        
        //   413: getfield        r/k/d/m/player/ChatModifier.gc:Lr/k/d/o/m;
        //   416: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   419: checkcast       Ljava/lang/Boolean;
        //   422: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   425: istore          9
        //   427: nop            
        //   428: aload_0        
        //   429: getfield        r/k/d/m/player/ChatModifier.ga:Lr/k/d/o/m;
        //   432: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   435: checkcast       Ljava/lang/Boolean;
        //   438: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   441: istore          9
        //   443: nop            
        //   444: aload_0        
        //   445: getfield        r/k/d/m/player/ChatModifier.gm:Lr/k/d/o/m;
        //   448: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   451: checkcast       Ljava/lang/Boolean;
        //   454: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   457: istore          9
        //   459: nop            
        //   460: aload_0        
        //   461: getfield        r/k/d/m/player/ChatModifier.gh:Lr/k/d/o/m;
        //   464: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   467: checkcast       Ljava/lang/Boolean;
        //   470: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   473: istore          9
        //   475: iload           9
        //   477: ifeq            487
        //   480: aload           8
        //   482: aload_3        
        //   483: invokevirtual   r/k/b/c/y/j.s:(Ljava/lang/String;)Ljava/lang/String;
        //   486: astore_3       
        //   487: nop            
        //   488: astore          9
        //   490: aload           9
        //   492: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   495: iinc            7, 1
        //   498: nop            
        //   499: ldc             Lnet/minecraft/network/play/client/CPacketChatMessage;.class
        //   501: aload_2        
        //   502: aload_3        
        //   503: iconst_2       
        //   504: anewarray       Ljava/lang/String;
        //   507: dup            
        //   508: iconst_0       
        //   509: ldc             "message"
        //   511: aastore        
        //   512: dup            
        //   513: iconst_1       
        //   514: ldc             "field_149440_a"
        //   516: aastore        
        //   517: invokestatic    net/minecraftforge/fml/common/ObfuscationReflectionHelper.setPrivateValue:(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/String;)V
        //   520: nop            
        //   521: astore          5
        //   523: new             Ljava/lang/StringBuilder;
        //   526: dup            
        //   527: invokespecial   java/lang/StringBuilder.<init>:()V
        //   530: ldc             "Disabled chat modifier due to error: "
        //   532: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   535: aload           5
        //   537: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   540: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   543: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   546: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   549: aload_0        
        //   550: iconst_0       
        //   551: invokevirtual   r/k/d/m/player/ChatModifier.s:(Z)V
        //   554: aload           5
        //   556: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   559: return         
        //    StackMapTable: 00 1E FF 00 04 00 0F 07 00 5C 07 00 73 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 39 00 0F 07 00 5C 07 00 73 07 00 7B 07 00 95 00 00 00 00 00 00 00 00 01 01 01 00 00 01 40 01 FF 00 0F 00 0F 07 00 5C 07 00 73 07 00 7B 07 00 95 01 07 00 9D 01 01 00 00 00 00 01 01 01 00 00 FF 00 69 00 0F 07 00 5C 07 00 73 07 00 7B 07 00 95 01 07 00 9D 01 01 07 00 A6 01 07 00 95 01 01 01 01 00 00 0D 0D 0D 0D 0D 0D 0E 0D 2E 0F 0F 0F 0F 0F 0F 0F 0E 0B FF 00 00 00 0F 07 00 5C 07 00 73 07 00 7B 07 00 95 01 07 00 9D 01 01 07 00 A6 00 00 00 01 01 01 00 01 07 00 71 06 FF 00 03 00 0F 07 00 5C 07 00 73 07 00 7B 07 00 95 01 00 00 00 00 00 00 00 01 01 01 00 00 55 07 00 71 FF 00 25 00 0F 07 00 5C 07 00 73 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  96     487    488    499    Ljava/lang/Exception;
        //  499    520    521    559    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
