package r.k.d.m.render;

import r.k.d.o.h.i.s;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chest ESP", description = "yes", category = y.RENDER)
public class ChestESP extends g
{
    private m<Boolean> sfo;
    private m<Boolean> sft;
    private m<Boolean> sfn;
    private m<Boolean> sfi;
    private m<Boolean> sfp;
    private m<Boolean> sfr;
    private m<Double> sff;
    private m<Double> sfb;
    private m<Double> sfw;
    private m<Double> sfg;
    private m<Double> sfu;
    private m<Double> sfz;
    private m<Double> sfc;
    private m<Double> sfa;
    private m<Double> sfm;
    private m<Double> sfh;
    private m<Double> sbd;
    private m<Double> sbs;
    public static final boolean sbl;
    public static final int sby;
    public static final boolean sbx;
    
    public ChestESP() {
        super();
        this.sfo = (m<Boolean>)new p("No Nether", this, false);
        this.sft = (m<Boolean>)new p("Chams", this, true);
        this.sfn = (m<Boolean>)new p("Outlines", this, true);
        this.sfp = (m<Boolean>)new p("Ender Chests", this, true);
        this.sfr = (m<Boolean>)new p("Beds", this, true);
        this.sff = (m<Double>)new s("Chests R", this, 1.0, 0.0, 1.0);
        this.sfb = (m<Double>)new s("Chests G", this, 1.0, 0.0, 1.0);
        this.sfw = (m<Double>)new s("Chests B", this, 0.0, 0.0, 1.0);
        this.sfg = (m<Double>)new s("Chests A", this, 1.0, 0.0, 1.0);
        this.sfu = (m<Double>)new s("Beds R", this, 1.0, 0.0, 1.0);
        this.sfz = (m<Double>)new s("Beds G", this, 1.0, 0.0, 1.0);
        this.sfc = (m<Double>)new s("Beds B", this, 0.0, 0.0, 1.0);
        this.sfa = (m<Double>)new s("Beds A", this, 1.0, 0.0, 1.0);
        this.sfm = (m<Double>)new s("E Chests R", this, 0.0, 0.0, 1.0);
        this.sfh = (m<Double>)new s("E Chests G", this, 1.0, 0.0, 1.0);
        this.sbd = (m<Double>)new s("E Chests B", this, 0.0, 0.0, 1.0);
        this.sbs = (m<Double>)new s("E Chests A", this, 1.0, 0.0, 1.0);
    }
    
    public void m() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: aload_0        
        //     7: invokevirtual   r/k/d/m/render/ChestESP.lo:()Z
        //    10: ifne            14
        //    13: return         
        //    14: aload_0        
        //    15: getfield        r/k/d/m/render/ChestESP.sfo:Lr/k/d/o/m;
        //    18: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    21: checkcast       Ljava/lang/Boolean;
        //    24: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    27: ifeq            44
        //    30: getstatic       r/k/d/m/render/ChestESP.mc:Lnet/minecraft/client/Minecraft;
        //    33: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    36: getfield        net/minecraft/client/entity/EntityPlayerSP.dimension:I
        //    39: iconst_m1      
        //    40: if_icmpne       44
        //    43: return         
        //    44: sipush          3042
        //    47: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //    50: sipush          770
        //    53: sipush          771
        //    56: invokestatic    org/lwjgl/opengl/GL11.glBlendFunc:(II)V
        //    59: sipush          2848
        //    62: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //    65: fconst_2       
        //    66: invokestatic    org/lwjgl/opengl/GL11.glLineWidth:(F)V
        //    69: sipush          3553
        //    72: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //    75: sipush          2884
        //    78: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //    81: sipush          2929
        //    84: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //    87: getstatic       r/k/d/m/render/ChestESP.mc:Lnet/minecraft/client/Minecraft;
        //    90: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //    93: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosX:D
        //    96: dstore_1       
        //    97: getstatic       r/k/d/m/render/ChestESP.mc:Lnet/minecraft/client/Minecraft;
        //   100: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   103: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosY:D
        //   106: dstore_3       
        //   107: getstatic       r/k/d/m/render/ChestESP.mc:Lnet/minecraft/client/Minecraft;
        //   110: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   113: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosZ:D
        //   116: dstore          5
        //   118: invokestatic    org/lwjgl/opengl/GL11.glPushMatrix:()V
        //   121: dload_1        
        //   122: dneg           
        //   123: dload_3        
        //   124: dneg           
        //   125: dload           5
        //   127: dneg           
        //   128: invokestatic    org/lwjgl/opengl/GL11.glTranslated:(DDD)V
        //   131: getstatic       r/k/d/m/render/ChestESP.mc:Lnet/minecraft/client/Minecraft;
        //   134: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   137: getfield        net/minecraft/client/multiplayer/WorldClient.loadedTileEntityList:Ljava/util/List;
        //   140: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   145: astore          7
        //   147: aload           7
        //   149: invokeinterface java/util/Iterator.hasNext:()Z
        //   154: ifeq            620
        //   157: aload           7
        //   159: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   164: checkcast       Lnet/minecraft/tileentity/TileEntity;
        //   167: astore          8
        //   169: aload_0        
        //   170: getfield        r/k/d/m/render/ChestESP.sfi:Lr/k/d/o/m;
        //   173: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   176: checkcast       Ljava/lang/Boolean;
        //   179: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   182: ifeq            195
        //   185: aload           8
        //   187: instanceof      Lnet/minecraft/tileentity/TileEntityChest;
        //   190: ifeq            195
        //   193: iconst_1       
        //   194: nop            
        //   195: iconst_0       
        //   196: istore          9
        //   198: aload_0        
        //   199: getfield        r/k/d/m/render/ChestESP.sfr:Lr/k/d/o/m;
        //   202: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   205: checkcast       Ljava/lang/Boolean;
        //   208: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   211: ifeq            224
        //   214: aload           8
        //   216: instanceof      Lnet/minecraft/tileentity/TileEntityBed;
        //   219: ifeq            224
        //   222: iconst_1       
        //   223: nop            
        //   224: iconst_0       
        //   225: istore          10
        //   227: aload_0        
        //   228: getfield        r/k/d/m/render/ChestESP.sfp:Lr/k/d/o/m;
        //   231: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   234: checkcast       Ljava/lang/Boolean;
        //   237: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   240: ifeq            253
        //   243: aload           8
        //   245: instanceof      Lnet/minecraft/tileentity/TileEntityEnderChest;
        //   248: ifeq            253
        //   251: iconst_1       
        //   252: nop            
        //   253: iconst_0       
        //   254: istore          11
        //   256: nop            
        //   257: fconst_0       
        //   258: fstore          12
        //   260: fconst_0       
        //   261: fstore          13
        //   263: fconst_0       
        //   264: fstore          14
        //   266: fconst_0       
        //   267: fstore          15
        //   269: iload           9
        //   271: ifeq            334
        //   274: aload_0        
        //   275: getfield        r/k/d/m/render/ChestESP.sff:Lr/k/d/o/m;
        //   278: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   281: checkcast       Ljava/lang/Double;
        //   284: invokevirtual   java/lang/Double.floatValue:()F
        //   287: fstore          12
        //   289: aload_0        
        //   290: getfield        r/k/d/m/render/ChestESP.sfb:Lr/k/d/o/m;
        //   293: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   296: checkcast       Ljava/lang/Double;
        //   299: invokevirtual   java/lang/Double.floatValue:()F
        //   302: fstore          13
        //   304: aload_0        
        //   305: getfield        r/k/d/m/render/ChestESP.sfw:Lr/k/d/o/m;
        //   308: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   311: checkcast       Ljava/lang/Double;
        //   314: invokevirtual   java/lang/Double.floatValue:()F
        //   317: fstore          14
        //   319: aload_0        
        //   320: getfield        r/k/d/m/render/ChestESP.sfg:Lr/k/d/o/m;
        //   323: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   326: checkcast       Ljava/lang/Double;
        //   329: invokevirtual   java/lang/Double.floatValue:()F
        //   332: fstore          15
        //   334: iload           10
        //   336: ifeq            399
        //   339: aload_0        
        //   340: getfield        r/k/d/m/render/ChestESP.sfu:Lr/k/d/o/m;
        //   343: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   346: checkcast       Ljava/lang/Double;
        //   349: invokevirtual   java/lang/Double.floatValue:()F
        //   352: fstore          12
        //   354: aload_0        
        //   355: getfield        r/k/d/m/render/ChestESP.sfz:Lr/k/d/o/m;
        //   358: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   361: checkcast       Ljava/lang/Double;
        //   364: invokevirtual   java/lang/Double.floatValue:()F
        //   367: fstore          13
        //   369: aload_0        
        //   370: getfield        r/k/d/m/render/ChestESP.sfc:Lr/k/d/o/m;
        //   373: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   376: checkcast       Ljava/lang/Double;
        //   379: invokevirtual   java/lang/Double.floatValue:()F
        //   382: fstore          14
        //   384: aload_0        
        //   385: getfield        r/k/d/m/render/ChestESP.sfa:Lr/k/d/o/m;
        //   388: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   391: checkcast       Ljava/lang/Double;
        //   394: invokevirtual   java/lang/Double.floatValue:()F
        //   397: fstore          15
        //   399: iload           11
        //   401: ifeq            464
        //   404: aload_0        
        //   405: getfield        r/k/d/m/render/ChestESP.sfm:Lr/k/d/o/m;
        //   408: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   411: checkcast       Ljava/lang/Double;
        //   414: invokevirtual   java/lang/Double.floatValue:()F
        //   417: fstore          12
        //   419: aload_0        
        //   420: getfield        r/k/d/m/render/ChestESP.sfh:Lr/k/d/o/m;
        //   423: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   426: checkcast       Ljava/lang/Double;
        //   429: invokevirtual   java/lang/Double.floatValue:()F
        //   432: fstore          13
        //   434: aload_0        
        //   435: getfield        r/k/d/m/render/ChestESP.sbd:Lr/k/d/o/m;
        //   438: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   441: checkcast       Ljava/lang/Double;
        //   444: invokevirtual   java/lang/Double.floatValue:()F
        //   447: fstore          14
        //   449: aload_0        
        //   450: getfield        r/k/d/m/render/ChestESP.sbs:Lr/k/d/o/m;
        //   453: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   456: checkcast       Ljava/lang/Double;
        //   459: invokevirtual   java/lang/Double.floatValue:()F
        //   462: fstore          15
        //   464: aload           8
        //   466: invokevirtual   net/minecraft/tileentity/TileEntity.getPos:()Lnet/minecraft/util/math/BlockPos;
        //   469: invokestatic    r/k/b/u/p.k:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;
        //   472: astore          16
        //   474: aload           8
        //   476: instanceof      Lnet/minecraft/tileentity/TileEntityChest;
        //   479: ifeq            559
        //   482: aload           8
        //   484: checkcast       Lnet/minecraft/tileentity/TileEntityChest;
        //   487: astore          17
        //   489: aload           17
        //   491: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestXPos:Lnet/minecraft/tileentity/TileEntityChest;
        //   494: ifnonnull       147
        //   497: aload           17
        //   499: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestZPos:Lnet/minecraft/tileentity/TileEntityChest;
        //   502: ifnull          506
        //   505: nop            
        //   506: aload           17
        //   508: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestXNeg:Lnet/minecraft/tileentity/TileEntityChest;
        //   511: ifnull          533
        //   514: aload           16
        //   516: aload           17
        //   518: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestXNeg:Lnet/minecraft/tileentity/TileEntityChest;
        //   521: invokevirtual   net/minecraft/tileentity/TileEntityChest.getPos:()Lnet/minecraft/util/math/BlockPos;
        //   524: invokestatic    r/k/b/u/p.k:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;
        //   527: invokevirtual   net/minecraft/util/math/AxisAlignedBB.union:(Lnet/minecraft/util/math/AxisAlignedBB;)Lnet/minecraft/util/math/AxisAlignedBB;
        //   530: astore          16
        //   532: nop            
        //   533: aload           17
        //   535: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestZNeg:Lnet/minecraft/tileentity/TileEntityChest;
        //   538: ifnull          559
        //   541: aload           16
        //   543: aload           17
        //   545: getfield        net/minecraft/tileentity/TileEntityChest.adjacentChestZNeg:Lnet/minecraft/tileentity/TileEntityChest;
        //   548: invokevirtual   net/minecraft/tileentity/TileEntityChest.getPos:()Lnet/minecraft/util/math/BlockPos;
        //   551: invokestatic    r/k/b/u/p.k:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;
        //   554: invokevirtual   net/minecraft/util/math/AxisAlignedBB.union:(Lnet/minecraft/util/math/AxisAlignedBB;)Lnet/minecraft/util/math/AxisAlignedBB;
        //   557: astore          16
        //   559: fload           12
        //   561: fload           13
        //   563: fload           14
        //   565: fload           15
        //   567: invokestatic    org/lwjgl/opengl/GL11.glColor4f:(FFFF)V
        //   570: aload_0        
        //   571: getfield        r/k/d/m/render/ChestESP.sft:Lr/k/d/o/m;
        //   574: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   577: checkcast       Ljava/lang/Boolean;
        //   580: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   583: ifeq            591
        //   586: aload           16
        //   588: invokestatic    r/k/b/u/p.s:(Lnet/minecraft/util/math/AxisAlignedBB;)V
        //   591: aload_0        
        //   592: getfield        r/k/d/m/render/ChestESP.sfn:Lr/k/d/o/m;
        //   595: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   598: checkcast       Ljava/lang/Boolean;
        //   601: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   604: ifeq            612
        //   607: aload           16
        //   609: invokestatic    r/k/b/u/p.l:(Lnet/minecraft/util/math/AxisAlignedBB;)V
        //   612: fconst_1       
        //   613: fconst_1       
        //   614: fconst_1       
        //   615: fconst_1       
        //   616: invokestatic    org/lwjgl/opengl/GL11.glColor4f:(FFFF)V
        //   619: nop            
        //   620: invokestatic    org/lwjgl/opengl/GL11.glPopMatrix:()V
        //   623: sipush          2929
        //   626: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   629: sipush          3553
        //   632: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   635: sipush          3042
        //   638: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   641: sipush          2848
        //   644: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   647: return         
        //    StackMapTable: 00 15 FF 00 04 00 15 07 00 45 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 FF 00 00 00 15 07 00 45 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 00 00 FF 00 08 00 15 07 00 45 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 1D FF 00 66 00 12 07 00 45 03 03 03 07 00 DB 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 2F 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 00 00 00 00 00 00 00 00 00 01 01 01 00 00 40 01 FF 00 1B 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 00 00 00 00 00 00 00 00 01 01 01 00 00 40 01 FF 00 1B 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 01 00 00 00 00 00 00 00 01 01 01 00 00 40 01 FF 00 02 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 01 01 00 00 00 00 00 00 01 01 01 00 00 FF 00 4C 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 01 01 02 02 02 02 00 00 01 01 01 00 00 FB 00 40 FB 00 40 FF 00 29 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 01 01 02 02 02 02 07 01 04 07 00 E7 01 01 01 00 00 1A FF 00 19 00 12 07 00 45 03 03 03 07 00 DB 07 00 E3 01 01 01 02 02 02 02 07 01 04 00 01 01 01 00 00 1F 14 FF 00 07 00 12 07 00 45 03 03 03 07 00 DB 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
