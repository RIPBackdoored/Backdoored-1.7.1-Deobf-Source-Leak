package r.k.d.m.player;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketChunkData;
import r.k.r.h.p;
import java.util.function.Predicate;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000p.\u0000g;
import java.io.File;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chunk Logger", description = "Log chunks that contain a specified ammount of chest", category = y.PLAYER)
public class ChunkLogger extends g
{
    private File ru;
    public static final boolean rz;
    public static final int rc;
    public static final boolean ra;
    
    public ChunkLogger() {
        super();
        this.ru = new File("Backdoored/ChunkLogs.txt");
    }
    
    public void v() {
        this.s(false);
    }
    
    public void j() {
        ChunkLogger.mc.world.loadedTileEntityList.stream().filter(\u0000g::d);
    }
    
    @SubscribeEvent
    public void l(final p p) {
        if (p.packet instanceof SPacketChunkData) {
            final SPacketChunkData sPacketChunkData = (SPacketChunkData)p.packet;
        }
    }
    
    @SubscribeEvent
    public void d(final ChunkEvent.Load p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: aload_0        
        //     8: invokevirtual   r/k/d/m/player/ChunkLogger.lo:()Z
        //    11: ifne            15
        //    14: return         
        //    15: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //    18: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    21: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getPersistentChunks:()Lcom/google/common/collect/ImmutableSetMultimap;
        //    24: invokevirtual   com/google/common/collect/ImmutableSetMultimap.keys:()Lcom/google/common/collect/ImmutableMultiset;
        //    27: invokevirtual   com/google/common/collect/ImmutableMultiset.iterator:()Lcom/google/common/collect/UnmodifiableIterator;
        //    30: astore_2       
        //    31: aload_2        
        //    32: invokeinterface java/util/Iterator.hasNext:()Z
        //    37: ifeq            687
        //    40: aload_2        
        //    41: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    46: checkcast       Lnet/minecraft/util/math/ChunkPos;
        //    49: astore_3       
        //    50: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //    53: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    56: aload_3        
        //    57: getfield        net/minecraft/util/math/ChunkPos.x:I
        //    60: aload_3        
        //    61: getfield        net/minecraft/util/math/ChunkPos.z:I
        //    64: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getChunk:(II)Lnet/minecraft/world/chunk/Chunk;
        //    67: astore          4
        //    69: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    72: new             Ljava/lang/StringBuilder;
        //    75: dup            
        //    76: invokespecial   java/lang/StringBuilder.<init>:()V
        //    79: aload           4
        //    81: getfield        net/minecraft/world/chunk/Chunk.x:I
        //    84: bipush          16
        //    86: imul           
        //    87: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    90: ldc             " "
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: aload           4
        //    97: getfield        net/minecraft/world/chunk/Chunk.z:I
        //   100: bipush          16
        //   102: imul           
        //   103: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   112: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   115: aload           4
        //   117: invokevirtual   net/minecraft/world/chunk/Chunk.getTileEntityMap:()Ljava/util/Map;
        //   120: invokeinterface java/util/Map.size:()I
        //   125: invokevirtual   java/io/PrintStream.println:(I)V
        //   128: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   131: aload           4
        //   133: invokevirtual   net/minecraft/world/chunk/Chunk.getTileEntityMap:()Ljava/util/Map;
        //   136: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   141: invokeinterface java/util/Collection.size:()I
        //   146: invokevirtual   java/io/PrintStream.println:(I)V
        //   149: aload           4
        //   151: invokevirtual   net/minecraft/world/chunk/Chunk.getTileEntityMap:()Ljava/util/Map;
        //   154: invokeinterface java/util/Map.size:()I
        //   159: iconst_1       
        //   160: if_icmpge       164
        //   163: return         
        //   164: iconst_0       
        //   165: istore          6
        //   167: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   170: new             Ljava/lang/StringBuilder;
        //   173: dup            
        //   174: invokespecial   java/lang/StringBuilder.<init>:()V
        //   177: ldc             "tiles: "
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: aload           4
        //   184: invokevirtual   net/minecraft/world/chunk/Chunk.getTileEntityMap:()Ljava/util/Map;
        //   187: invokeinterface java/util/Map.size:()I
        //   192: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   198: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   201: aload           5
        //   203: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   208: astore          7
        //   210: aload           7
        //   212: invokeinterface java/util/Iterator.hasNext:()Z
        //   217: ifeq            266
        //   220: aload           7
        //   222: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   227: checkcast       Lnet/minecraft/tileentity/TileEntity;
        //   230: astore          8
        //   232: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   235: aload           8
        //   237: instanceof      Lnet/minecraft/tileentity/TileEntityChest;
        //   240: invokevirtual   java/io/PrintStream.println:(Z)V
        //   243: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   246: aload           8
        //   248: invokevirtual   net/minecraft/tileentity/TileEntity.getPos:()Lnet/minecraft/util/math/BlockPos;
        //   251: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   254: aload           8
        //   256: instanceof      Lnet/minecraft/tileentity/TileEntityChest;
        //   259: ifeq            265
        //   262: iinc            6, 1
        //   265: nop            
        //   266: iconst_0       
        //   267: istore          7
        //   269: aload           5
        //   271: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   276: astore          8
        //   278: aload           8
        //   280: invokeinterface java/util/Iterator.hasNext:()Z
        //   285: ifeq            312
        //   288: aload           8
        //   290: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   295: checkcast       Lnet/minecraft/tileentity/TileEntity;
        //   298: astore          9
        //   300: aload           9
        //   302: instanceof      Lnet/minecraft/tileentity/TileEntityBed;
        //   305: ifeq            311
        //   308: iinc            7, 1
        //   311: nop            
        //   312: iconst_0       
        //   313: istore          8
        //   315: aload           5
        //   317: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   322: astore          9
        //   324: aload           9
        //   326: invokeinterface java/util/Iterator.hasNext:()Z
        //   331: ifeq            359
        //   334: aload           9
        //   336: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   341: checkcast       Lnet/minecraft/tileentity/TileEntity;
        //   344: astore          10
        //   346: aload           10
        //   348: instanceof      Lnet/minecraft/tileentity/TileEntityEndPortal;
        //   351: ifeq            358
        //   354: iconst_1       
        //   355: istore          8
        //   357: nop            
        //   358: nop            
        //   359: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   362: ldc             "\nChunk Loaded %d %d %s"
        //   364: iconst_3       
        //   365: anewarray       Ljava/lang/Object;
        //   368: dup            
        //   369: iconst_0       
        //   370: iload           6
        //   372: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   375: aastore        
        //   376: dup            
        //   377: iconst_1       
        //   378: iload           7
        //   380: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   383: aastore        
        //   384: dup            
        //   385: iconst_2       
        //   386: iload           8
        //   388: invokestatic    java/lang/String.valueOf:(Z)Ljava/lang/String;
        //   391: aastore        
        //   392: invokevirtual   java/io/PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
        //   395: pop            
        //   396: iload           6
        //   398: ifgt            411
        //   401: iload           7
        //   403: ifgt            411
        //   406: iload           8
        //   408: ifeq            686
        //   411: invokestatic    java/lang/System.currentTimeMillis:()J
        //   414: lstore          9
        //   416: new             Ljava/util/Date;
        //   419: dup            
        //   420: lload           9
        //   422: invokespecial   java/util/Date.<init>:(J)V
        //   425: astore          11
        //   427: ldc_w           "Singleplayer"
        //   430: astore          12
        //   432: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //   435: invokevirtual   net/minecraft/client/Minecraft.getCurrentServerData:()Lnet/minecraft/client/multiplayer/ServerData;
        //   438: ifnull          452
        //   441: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //   444: invokevirtual   net/minecraft/client/Minecraft.getCurrentServerData:()Lnet/minecraft/client/multiplayer/ServerData;
        //   447: getfield        net/minecraft/client/multiplayer/ServerData.serverIP:Ljava/lang/String;
        //   450: astore          12
        //   452: ldc_w           "(%s) %s %s: %d chests, %d beds, %d end portals"
        //   455: bipush          6
        //   457: anewarray       Ljava/lang/Object;
        //   460: dup            
        //   461: iconst_0       
        //   462: aload           11
        //   464: aastore        
        //   465: dup            
        //   466: iconst_1       
        //   467: aload           12
        //   469: aastore        
        //   470: dup            
        //   471: iconst_2       
        //   472: new             Ljava/lang/StringBuilder;
        //   475: dup            
        //   476: invokespecial   java/lang/StringBuilder.<init>:()V
        //   479: ldc_w           "("
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: aload           4
        //   487: getfield        net/minecraft/world/chunk/Chunk.x:I
        //   490: bipush          16
        //   492: imul           
        //   493: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   496: ldc_w           ", "
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: aload           4
        //   504: getfield        net/minecraft/world/chunk/Chunk.z:I
        //   507: bipush          16
        //   509: imul           
        //   510: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   513: ldc_w           ")"
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   522: aastore        
        //   523: dup            
        //   524: iconst_3       
        //   525: iload           6
        //   527: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   530: aastore        
        //   531: dup            
        //   532: iconst_4       
        //   533: iload           7
        //   535: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   538: aastore        
        //   539: dup            
        //   540: iconst_5       
        //   541: iload           8
        //   543: ifeq            548
        //   546: iconst_1       
        //   547: nop            
        //   548: iconst_0       
        //   549: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   552: aastore        
        //   553: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   556: astore          13
        //   558: new             Ljava/io/FileWriter;
        //   561: dup            
        //   562: aload_0        
        //   563: getfield        r/k/d/m/player/ChunkLogger.ru:Ljava/io/File;
        //   566: iconst_1       
        //   567: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;Z)V
        //   570: astore          14
        //   572: new             Ljava/io/BufferedWriter;
        //   575: dup            
        //   576: aload           14
        //   578: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //   581: astore          15
        //   583: aload           15
        //   585: new             Ljava/lang/StringBuilder;
        //   588: dup            
        //   589: invokespecial   java/lang/StringBuilder.<init>:()V
        //   592: aload           13
        //   594: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   597: ldc_w           "\n"
        //   600: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   603: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   606: invokevirtual   java/io/BufferedWriter.write:(Ljava/lang/String;)V
        //   609: aload           15
        //   611: invokevirtual   java/io/BufferedWriter.close:()V
        //   614: aload           14
        //   616: invokevirtual   java/io/FileWriter.close:()V
        //   619: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   622: new             Ljava/lang/StringBuilder;
        //   625: dup            
        //   626: invokespecial   java/lang/StringBuilder.<init>:()V
        //   629: ldc_w           "Found Chunk "
        //   632: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   635: aload           13
        //   637: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   640: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   643: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   646: new             Ljava/lang/StringBuilder;
        //   649: dup            
        //   650: invokespecial   java/lang/StringBuilder.<init>:()V
        //   653: ldc_w           "Found Chunk "
        //   656: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   659: aload           13
        //   661: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   664: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   667: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   670: nop            
        //   671: astore          14
        //   673: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   676: aload           13
        //   678: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   681: aload           14
        //   683: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   686: nop            
        //   687: return         
        //    StackMapTable: 00 16 FF 00 04 00 13 07 00 2B 07 00 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 09 00 13 07 00 2B 07 00 0F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 0F 00 13 07 00 2B 07 00 0F 07 00 8F 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 25 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FB 00 5E FF 00 2D 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 07 00 91 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 36 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 07 00 91 07 00 DE 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 07 00 91 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 0B 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 07 00 91 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 20 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 07 00 91 07 00 DE 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 07 00 91 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 0B 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 07 00 91 00 00 00 00 00 00 01 01 01 00 00 FF 00 21 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 07 00 91 07 00 DE 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 07 00 91 00 00 00 00 00 00 01 01 01 00 00 33 FF 00 28 00 12 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 04 07 01 06 07 00 F9 00 00 00 01 01 01 00 00 FF 00 5F 00 12 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 04 07 01 06 07 00 F9 00 00 00 01 01 01 00 04 07 00 F9 07 01 1F 07 01 1F 01 FF 00 00 00 12 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 04 07 01 06 07 00 F9 00 00 00 01 01 01 00 05 07 00 F9 07 01 1F 07 01 1F 01 01 FF 00 79 00 12 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 04 07 01 06 07 00 F9 07 00 F9 00 00 01 01 01 00 01 07 00 79 FF 00 0E 00 13 07 00 2B 07 00 0F 07 00 8F 07 00 9A 07 00 A6 07 00 A8 01 01 01 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 13 07 00 2B 07 00 0F 07 00 8F 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  558    670    671    686    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static /* synthetic */ boolean d(final TileEntity p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //    10: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    13: aload_0        
        //    14: invokevirtual   net/minecraft/tileentity/TileEntity.getPos:()Lnet/minecraft/util/math/BlockPos;
        //    17: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    20: i2d            
        //    21: getstatic       r/k/d/m/player/ChunkLogger.mc:Lnet/minecraft/client/Minecraft;
        //    24: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    27: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //    30: aload_0        
        //    31: invokevirtual   net/minecraft/tileentity/TileEntity.getPos:()Lnet/minecraft/util/math/BlockPos;
        //    34: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    37: i2d            
        //    38: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //    41: ldc2_w          500.0
        //    44: dcmpg          
        //    45: ifge            50
        //    48: iconst_1       
        //    49: nop            
        //    50: iconst_0       
        //    51: ireturn        
        //    StackMapTable: 00 04 FE 00 04 00 00 01 01 FF 00 2B 00 04 07 00 DE 01 00 01 00 00 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
