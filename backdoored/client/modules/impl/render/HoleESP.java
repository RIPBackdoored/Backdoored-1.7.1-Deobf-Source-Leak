package r.k.d.m.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import java.util.Map;
import java.util.Iterator;
import r.k.d.o.h.p;
import r.k.d.o.h.i.x;
import net.minecraft.util.math.BlockPos;
import java.util.HashMap;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Hole ESP", description = "See holes to camp in during pvp", category = y.RENDER)
public class HoleESP extends g
{
    private final m<Integer> nf;
    private final m<Boolean> nb;
    private final m<Boolean> nw;
    private final m<Integer> ng;
    private final m<Integer> nu;
    private HashMap<BlockPos, p$s> nz;
    public static final boolean nc;
    public static final int na;
    public static final boolean nm;
    
    public HoleESP() {
        super();
        this.nf = (m<Integer>)new x("Hole Radius", this, 10, 1, 50);
        this.nb = (m<Boolean>)new p("Hole height 3", this, false);
        this.nw = (m<Boolean>)new p("Render Bottom", this, false);
        this.ng = (m<Integer>)new x("Max Y", this, 125, 0, 125);
        this.nu = (m<Integer>)new x("Max Holes", this, 20, 1, 50);
        this.nz = new HashMap<BlockPos, p$s>();
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        this.nz.clear();
        final Iterable allInBox = BlockPos.getAllInBox(HoleESP.mc.player.getPosition().add(-this.nf.yv(), -this.nf.yv(), -this.nf.yv()), HoleESP.mc.player.getPosition().add((int)this.nf.yv(), (int)this.nf.yv(), (int)this.nf.yv()));
        int n = 0;
        final Iterator<BlockPos> iterator = allInBox.iterator();
        if (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            if (n > this.nu.yv()) {
                return;
            }
            final p$s d = this.d(blockPos, this.nb.yv());
            if (d != p$s.sgo) {
                this.nz.put(blockPos, d);
                ++n;
            }
        }
    }
    
    public p$s d(final BlockPos blockPos) {
        return this.d(blockPos, false);
    }
    
    public p$s d(final BlockPos p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: aload_1        
        //     9: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    12: aload_0        
        //    13: getfield        r/k/d/m/render/HoleESP.ng:Lr/k/d/o/m;
        //    16: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    19: checkcast       Ljava/lang/Integer;
        //    22: invokevirtual   java/lang/Integer.intValue:()I
        //    25: if_icmple       32
        //    28: getstatic       r/k/d/m/j/p$s.sgo:Lr/k/d/m/j/p$s;
        //    31: areturn        
        //    32: bipush          8
        //    34: anewarray       Lnet/minecraft/block/state/IBlockState;
        //    37: dup            
        //    38: iconst_0       
        //    39: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //    42: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    45: aload_1        
        //    46: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    49: aastore        
        //    50: dup            
        //    51: iconst_1       
        //    52: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //    55: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    58: aload_1        
        //    59: iconst_0       
        //    60: iconst_1       
        //    61: iconst_0       
        //    62: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    65: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    68: aastore        
        //    69: dup            
        //    70: iconst_2       
        //    71: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //    74: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    77: aload_1        
        //    78: iconst_0       
        //    79: iconst_2       
        //    80: iconst_0       
        //    81: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    84: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    87: aastore        
        //    88: dup            
        //    89: iconst_3       
        //    90: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //    93: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    96: aload_1        
        //    97: iconst_0       
        //    98: iconst_m1      
        //    99: iconst_0       
        //   100: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   103: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   106: aastore        
        //   107: dup            
        //   108: iconst_4       
        //   109: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //   112: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   115: aload_1        
        //   116: iconst_1       
        //   117: iconst_0       
        //   118: iconst_0       
        //   119: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   122: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   125: aastore        
        //   126: dup            
        //   127: iconst_5       
        //   128: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //   131: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   134: aload_1        
        //   135: iconst_0       
        //   136: iconst_0       
        //   137: iconst_1       
        //   138: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   141: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   144: aastore        
        //   145: dup            
        //   146: bipush          6
        //   148: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //   151: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   154: aload_1        
        //   155: iconst_m1      
        //   156: iconst_0       
        //   157: iconst_0       
        //   158: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   161: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   164: aastore        
        //   165: dup            
        //   166: bipush          7
        //   168: getstatic       r/k/d/m/render/HoleESP.mc:Lnet/minecraft/client/Minecraft;
        //   171: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   174: aload_1        
        //   175: iconst_0       
        //   176: iconst_0       
        //   177: iconst_m1      
        //   178: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   181: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   184: aastore        
        //   185: astore_3       
        //   186: aload_3        
        //   187: iconst_0       
        //   188: aaload         
        //   189: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   194: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   197: ifne            317
        //   200: aload_3        
        //   201: iconst_1       
        //   202: aaload         
        //   203: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   208: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   211: ifne            317
        //   214: aload_3        
        //   215: iconst_2       
        //   216: aaload         
        //   217: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   222: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   225: ifeq            228
        //   228: aload_3        
        //   229: iconst_3       
        //   230: aaload         
        //   231: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   236: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   239: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   242: ifeq            317
        //   245: aload_3        
        //   246: iconst_4       
        //   247: aaload         
        //   248: aload_3        
        //   249: iconst_5       
        //   250: aaload         
        //   251: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   256: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   259: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   262: ifeq            317
        //   265: aload_3        
        //   266: bipush          6
        //   268: aaload         
        //   269: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   274: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   277: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   280: ifeq            317
        //   283: aload_3        
        //   284: bipush          7
        //   286: aaload         
        //   287: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   292: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   295: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   298: ifeq            317
        //   301: aload_3        
        //   302: iconst_2       
        //   303: aaload         
        //   304: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   309: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   312: ifeq            315
        //   315: iconst_1       
        //   316: nop            
        //   317: iconst_0       
        //   318: istore          4
        //   320: iload           4
        //   322: ifeq            329
        //   325: getstatic       r/k/d/m/j/p$s.sgv:Lr/k/d/m/j/p$s;
        //   328: areturn        
        //   329: aload_3        
        //   330: iconst_0       
        //   331: aaload         
        //   332: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   337: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   340: ifne            547
        //   343: aload_3        
        //   344: iconst_1       
        //   345: aaload         
        //   346: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   351: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   354: ifne            547
        //   357: aload_3        
        //   358: iconst_2       
        //   359: aaload         
        //   360: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   365: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   368: ifeq            371
        //   371: aload_3        
        //   372: iconst_3       
        //   373: aaload         
        //   374: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   379: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   382: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   385: ifne            405
        //   388: aload_3        
        //   389: iconst_3       
        //   390: aaload         
        //   391: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   396: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   399: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   402: ifeq            547
        //   405: aload_3        
        //   406: iconst_4       
        //   407: aaload         
        //   408: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   413: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   416: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   419: ifne            439
        //   422: aload_3        
        //   423: iconst_4       
        //   424: aaload         
        //   425: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   430: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   433: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   436: ifeq            547
        //   439: aload_3        
        //   440: iconst_5       
        //   441: aaload         
        //   442: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   447: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   450: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   453: ifne            473
        //   456: aload_3        
        //   457: iconst_5       
        //   458: aaload         
        //   459: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   464: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   467: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   470: ifeq            547
        //   473: aload_3        
        //   474: bipush          6
        //   476: aaload         
        //   477: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   482: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   485: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   488: ifne            509
        //   491: aload_3        
        //   492: bipush          6
        //   494: aaload         
        //   495: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   500: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   503: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   506: ifeq            547
        //   509: aload_3        
        //   510: bipush          7
        //   512: aaload         
        //   513: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   518: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   521: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   524: ifne            545
        //   527: aload_3        
        //   528: bipush          7
        //   530: aaload         
        //   531: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   536: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   539: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   542: ifeq            547
        //   545: iconst_1       
        //   546: nop            
        //   547: iconst_0       
        //   548: istore          5
        //   550: iload           5
        //   552: ifeq            559
        //   555: getstatic       r/k/d/m/j/p$s.sgj:Lr/k/d/m/j/p$s;
        //   558: areturn        
        //   559: aload_3        
        //   560: iconst_0       
        //   561: aaload         
        //   562: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   567: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   570: ifne            675
        //   573: aload_3        
        //   574: iconst_1       
        //   575: aaload         
        //   576: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   581: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   584: ifne            675
        //   587: aload_3        
        //   588: iconst_2       
        //   589: aaload         
        //   590: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   595: invokevirtual   net/minecraft/block/material/Material.blocksMovement:()Z
        //   598: ifeq            601
        //   601: aload_3        
        //   602: iconst_3       
        //   603: aaload         
        //   604: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   609: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   612: ifeq            675
        //   615: aload_3        
        //   616: iconst_4       
        //   617: aaload         
        //   618: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   623: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   626: ifeq            675
        //   629: aload_3        
        //   630: iconst_5       
        //   631: aaload         
        //   632: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   637: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   640: ifeq            675
        //   643: aload_3        
        //   644: bipush          6
        //   646: aaload         
        //   647: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   652: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   655: ifeq            675
        //   658: aload_3        
        //   659: bipush          7
        //   661: aaload         
        //   662: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //   667: invokevirtual   net/minecraft/block/material/Material.isSolid:()Z
        //   670: ifeq            675
        //   673: iconst_1       
        //   674: nop            
        //   675: iconst_0       
        //   676: istore          6
        //   678: getstatic       r/k/d/m/j/p$s.sgo:Lr/k/d/m/j/p$s;
        //   681: areturn        
        //    StackMapTable: 00 15 FF 00 04 00 0A 07 00 11 07 00 7B 01 00 00 00 00 00 00 01 00 00 01 FF 00 19 00 0A 07 00 11 07 00 7B 01 00 00 00 00 01 01 01 00 00 FF 00 C3 00 0A 07 00 11 07 00 7B 01 07 00 BF 00 00 00 01 01 01 00 00 13 FB 00 42 01 40 01 FF 00 0A 00 0A 07 00 11 07 00 7B 01 07 00 BF 01 00 00 01 01 01 00 00 29 21 21 21 23 23 01 40 01 FF 00 0A 00 0A 07 00 11 07 00 7B 01 07 00 BF 01 01 00 01 01 01 00 00 29 FB 00 49 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void m() {
        if (!this.lo()) {
            return;
        }
        r.k.b.u.p.d(255.0f, 0.0f, 255.0f, 0.2f);
        if (this.nz != null) {
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            final Iterator<Map.Entry<BlockPos, p$s>> iterator = this.nz.entrySet().iterator();
            if (iterator.hasNext()) {
                final Map.Entry<BlockPos, p$s> entry = iterator.next();
                final p$s p$s = entry.getValue();
                GL11.glLineWidth(2.0f);
                AxisAlignedBB k = r.k.b.u.p.k(entry.getKey());
                if (this.nw.yv()) {
                    k = new AxisAlignedBB(k.minX, k.minY, k.minZ, k.maxX, k.minY + 0.001, k.maxZ);
                }
                if (p$s == r.k.d.m.j.p$s.sgv) {
                    RenderGlobal.renderFilledBox(k, 1.0f, 0.0f, 0.0f, 0.2f);
                    RenderGlobal.drawSelectionBoundingBox(k, 1.0f, 0.0f, 0.0f, 0.2f);
                    ++n;
                }
                if (p$s == r.k.d.m.j.p$s.sgj) {
                    RenderGlobal.renderFilledBox(k, 1.0f, 0.498f, 0.3137f, 0.2f);
                    RenderGlobal.drawSelectionBoundingBox(k, 1.0f, 0.498f, 0.3137f, 0.2f);
                    ++n2;
                }
                if (p$s == r.k.d.m.j.p$s.sge) {
                    RenderGlobal.renderFilledBox(k, 255.0f, 255.0f, 255.0f, 0.2f);
                    RenderGlobal.drawSelectionBoundingBox(k, 255.0f, 255.0f, 255.0f, 0.2f);
                    ++n3;
                }
            }
        }
        r.k.b.u.p.xn();
    }
}
