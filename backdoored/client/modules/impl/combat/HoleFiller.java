package r.k.d.m.combat;

import r.k.d.o.h.i.s;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Hole Filler", description = "Fill holes that enemies could jump into", category = y.COMBAT)
public class HoleFiller extends g
{
    private m<Double> snx;
    private m<Double> snk;
    public static final boolean snq;
    public static final int snv;
    public static final boolean snj;
    
    public HoleFiller() {
        super();
        this.snx = (m<Double>)new s("Radius", this, 3.0, 1.0, 5.0);
        this.snk = (m<Double>)new s("Range", this, 5.0, 1.0, 10.0);
    }
    
    public void j() {
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
        //     8: invokevirtual   r/k/d/m/combat/HoleFiller.lo:()Z
        //    11: ifeq            524
        //    14: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //    17: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    20: getfield        net/minecraft/client/multiplayer/WorldClient.playerEntities:Ljava/util/List;
        //    23: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    28: astore_1       
        //    29: aload_1        
        //    30: invokeinterface java/util/Iterator.hasNext:()Z
        //    35: ifeq            524
        //    38: aload_1        
        //    39: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    44: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //    47: astore_2       
        //    48: aload_2        
        //    49: invokevirtual   net/minecraft/entity/player/EntityPlayer.getUniqueID:()Ljava/util/UUID;
        //    52: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //    55: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    58: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getUniqueID:()Ljava/util/UUID;
        //    61: invokevirtual   java/util/UUID.equals:(Ljava/lang/Object;)Z
        //    64: ifne            523
        //    67: aload_0        
        //    68: getfield        r/k/d/m/combat/HoleFiller.snx:Lr/k/d/o/m;
        //    71: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    74: checkcast       Ljava/lang/Double;
        //    77: invokevirtual   java/lang/Double.doubleValue:()D
        //    80: dstore_3       
        //    81: aload_2        
        //    82: invokevirtual   net/minecraft/entity/player/EntityPlayer.getPosition:()Lnet/minecraft/util/math/BlockPos;
        //    85: astore          5
        //    87: aload           5
        //    89: dload_3        
        //    90: dneg           
        //    91: dload_3        
        //    92: dneg           
        //    93: dload_3        
        //    94: dneg           
        //    95: invokevirtual   net/minecraft/util/math/BlockPos.add:(DDD)Lnet/minecraft/util/math/BlockPos;
        //    98: aload           5
        //   100: dload_3        
        //   101: dload_3        
        //   102: dload_3        
        //   103: invokevirtual   net/minecraft/util/math/BlockPos.add:(DDD)Lnet/minecraft/util/math/BlockPos;
        //   106: invokestatic    net/minecraft/util/math/BlockPos.getAllInBox:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;
        //   109: astore          6
        //   111: aload           6
        //   113: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   118: astore          7
        //   120: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   123: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   126: aload           8
        //   128: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistanceSqToCenter:(Lnet/minecraft/util/math/BlockPos;)D
        //   131: aload_0        
        //   132: getfield        r/k/d/m/combat/HoleFiller.snk:Lr/k/d/o/m;
        //   135: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   138: checkcast       Ljava/lang/Double;
        //   141: invokevirtual   java/lang/Double.doubleValue:()D
        //   144: dcmpl          
        //   145: ifle            149
        //   148: nop            
        //   149: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   152: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   155: aload           8
        //   157: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   160: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   165: invokevirtual   net/minecraft/block/material/Material.isReplaceable:()Z
        //   168: ifeq            522
        //   171: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   174: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   177: aload           8
        //   179: iconst_0       
        //   180: iconst_1       
        //   181: iconst_0       
        //   182: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   185: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   188: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   193: invokevirtual   net/minecraft/block/material/Material.isReplaceable:()Z
        //   196: ifeq            522
        //   199: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   202: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   205: aload           8
        //   207: iconst_0       
        //   208: iconst_m1      
        //   209: iconst_0       
        //   210: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   213: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   216: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   221: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   224: ifeq            425
        //   227: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   230: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   233: aload           8
        //   235: iconst_1       
        //   236: iconst_0       
        //   237: iconst_0       
        //   238: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   241: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   244: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   249: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   252: ifeq            425
        //   255: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   258: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   261: aload           8
        //   263: iconst_0       
        //   264: iconst_0       
        //   265: iconst_1       
        //   266: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   269: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   272: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   277: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   280: ifeq            425
        //   283: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   286: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   289: aload           8
        //   291: iconst_m1      
        //   292: iconst_0       
        //   293: iconst_0       
        //   294: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   297: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   300: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   305: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   308: ifeq            425
        //   311: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   314: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   317: aload           8
        //   319: iconst_0       
        //   320: iconst_0       
        //   321: iconst_m1      
        //   322: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   325: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   328: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   333: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   336: ifeq            425
        //   339: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   342: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   345: aload           8
        //   347: iconst_0       
        //   348: iconst_0       
        //   349: iconst_0       
        //   350: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   353: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   356: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   361: getstatic       net/minecraft/block/material/Material.AIR:Lnet/minecraft/block/material/Material;
        //   364: if_acmpne       425
        //   367: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   370: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   373: aload           8
        //   375: iconst_0       
        //   376: iconst_1       
        //   377: iconst_0       
        //   378: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   381: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   384: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   389: getstatic       net/minecraft/block/material/Material.AIR:Lnet/minecraft/block/material/Material;
        //   392: if_acmpne       425
        //   395: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   398: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   401: aload           8
        //   403: iconst_0       
        //   404: iconst_2       
        //   405: iconst_0       
        //   406: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   409: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   412: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   417: getstatic       net/minecraft/block/material/Material.AIR:Lnet/minecraft/block/material/Material;
        //   420: if_acmpne       425
        //   423: iconst_1       
        //   424: nop            
        //   425: iconst_0       
        //   426: istore          9
        //   428: iload           9
        //   430: ifeq            522
        //   433: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   436: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   439: ldc             Lnet/minecraft/entity/Entity;.class
        //   441: new             Lnet/minecraft/util/math/AxisAlignedBB;
        //   444: dup            
        //   445: aload           8
        //   447: invokespecial   net/minecraft/util/math/AxisAlignedBB.<init>:(Lnet/minecraft/util/math/BlockPos;)V
        //   450: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getEntitiesWithinAABB:(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
        //   453: invokeinterface java/util/List.isEmpty:()Z
        //   458: ifeq            522
        //   461: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   464: invokestatic    r/k/b/b.s:(Lnet/minecraft/block/Block;)I
        //   467: istore          10
        //   469: iload           10
        //   471: iconst_m1      
        //   472: if_icmpeq       522
        //   475: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   478: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   481: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   484: getfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   487: istore          11
        //   489: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   492: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   495: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   498: iload           10
        //   500: putfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   503: aload           8
        //   505: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   508: getstatic       r/k/d/m/combat/HoleFiller.mc:Lnet/minecraft/client/Minecraft;
        //   511: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   514: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   517: iload           11
        //   519: putfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   522: nop            
        //   523: nop            
        //   524: return         
        //    StackMapTable: 00 0A FF 00 04 00 0F 07 00 37 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 17 00 0F 07 00 37 07 00 53 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 5A 00 0E 07 00 37 07 00 53 07 00 5C 03 07 00 7E 07 00 88 07 00 53 07 00 7E 00 00 00 01 01 01 00 00 1C FB 01 13 40 01 FB 00 5F FF 00 00 00 0F 07 00 37 07 00 53 07 00 5C 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 0F 07 00 37 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
