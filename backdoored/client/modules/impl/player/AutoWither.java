package r.k.d.m.player;

import net.minecraft.util.EnumFacing;
import r.k.h;
import r.k.b.b;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.ct;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Item;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Wither", description = "2 tick withers", category = y.PLAYER)
@g$i(name = "Auto Wither", description = "2 tick withers", category = y.PLAYER)
public class AutoWither extends g
{
    private Item item;
    private Item item;
    private BlockPos blockPos;
    private int svi;
    public static final boolean svp;
    public static final int svr;
    public static final boolean svf;
    private Item item;
    private Item item;
    private BlockPos blockPos;
    private int svi;
    public static final boolean svp;
    public static final int svr;
    public static final boolean svf;
    
    public AutoWither() {
        super();
        this.item = new ItemStack(Blocks.SOUL_SAND).getItem();
        this.item = new ItemStack((Block)Blocks.SKULL).getItem();
        this.blockPos = new BlockPos(0, 0, 0);
        this.svi = -1;
    }
    
    public void v() {
        ++this.svi;
        this.yc();
        ++this.svi;
    }
    
    @SubscribeEvent
    public void d(final ct ct) {
        if (!this.lo() || this.svi > 1) {
            this.svi = -1;
            this.s(false);
            return;
        }
        if (this.svi == 0) {
            this.yc();
        }
        if (this.svi == 1) {
            this.ya();
            this.svi = -1;
            this.s(false);
            return;
        }
        ++this.svi;
    }
    
    private boolean yc() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    11: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    14: ifnull          29
        //    17: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    20: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    23: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //    26: ifnonnull       49
        //    29: aload_0        
        //    30: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    33: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    36: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getPosition:()Lnet/minecraft/util/math/BlockPos;
        //    39: iconst_2       
        //    40: iconst_0       
        //    41: iconst_0       
        //    42: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    45: putfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //    48: nop            
        //    49: aload_0        
        //    50: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    53: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    56: invokevirtual   net/minecraft/util/math/RayTraceResult.getBlockPos:()Lnet/minecraft/util/math/BlockPos;
        //    59: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    62: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    65: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //    68: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    71: putfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //    74: aload_0        
        //    75: getfield        r/k/d/m/player/AutoWither.item:Lnet/minecraft/item/Item;
        //    78: invokestatic    r/k/b/b.s:(Lnet/minecraft/item/Item;)I
        //    81: istore_1       
        //    82: aload_0        
        //    83: invokespecial   r/k/d/m/player/AutoWither.ym:()I
        //    86: istore_2       
        //    StackMapTable: 00 06 FF 00 04 00 07 07 00 39 00 00 00 00 00 01 00 00 01 FF 00 16 00 07 07 00 39 00 00 00 01 01 01 00 00 13 18 FF 00 0C 00 07 07 00 39 01 01 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public AutoWither() {
        super();
        this.item = new ItemStack(Blocks.SOUL_SAND).getItem();
        this.item = new ItemStack((Block)Blocks.SKULL).getItem();
        this.blockPos = new BlockPos(0, 0, 0);
        this.svi = -1;
    }
    
    public void v() {
        ++this.svi;
        this.yc();
        ++this.svi;
    }
    
    @SubscribeEvent
    public void d(final ct ct) {
        if (!this.lo() || this.svi > 1) {
            this.svi = -1;
            this.s(false);
            return;
        }
        if (this.svi == 0) {
            this.yc();
        }
        if (this.svi == 1) {
            this.ya();
            this.svi = -1;
            this.s(false);
            return;
        }
        ++this.svi;
    }
    
    private boolean yc() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    11: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    14: ifnull          29
        //    17: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    20: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    23: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //    26: ifnonnull       49
        //    29: aload_0        
        //    30: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    33: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    36: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getPosition:()Lnet/minecraft/util/math/BlockPos;
        //    39: iconst_2       
        //    40: iconst_0       
        //    41: iconst_0       
        //    42: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    45: putfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //    48: nop            
        //    49: aload_0        
        //    50: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    53: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    56: invokevirtual   net/minecraft/util/math/RayTraceResult.getBlockPos:()Lnet/minecraft/util/math/BlockPos;
        //    59: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    62: getfield        net/minecraft/client/Minecraft.objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;
        //    65: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //    68: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    71: putfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //    74: aload_0        
        //    75: getfield        r/k/d/m/player/AutoWither.item:Lnet/minecraft/item/Item;
        //    78: invokestatic    r/k/b/b.s:(Lnet/minecraft/item/Item;)I
        //    81: istore_1       
        //    82: aload_0        
        //    83: invokespecial   r/k/d/m/player/AutoWither.ym:()I
        //    86: istore_2       
        //    87: iload_2        
        //    88: iconst_m1      
        //    89: if_icmpne       95
        //    92: ldc             "Wither Skull"
        //    94: nop            
        //    95: ldc             "Soul Sand"
        //    97: astore_3       
        //    98: new             Ljava/lang/StringBuilder;
        //   101: dup            
        //   102: invokespecial   java/lang/StringBuilder.<init>:()V
        //   105: aload_3        
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: ldc             " was not found in your hotbar!"
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: ldc             "red"
        //   119: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //   122: aload_0        
        //   123: iconst_0       
        //   124: invokevirtual   r/k/d/m/player/AutoWither.s:(Z)V
        //   127: iconst_0       
        //   128: ireturn        
        //   129: getstatic       r/k/d/m/player/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //   132: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   135: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   138: aload_0        
        //   139: getfield        r/k/d/m/player/AutoWither.item:Lnet/minecraft/item/Item;
        //   142: invokestatic    r/k/b/b.s:(Lnet/minecraft/item/Item;)I
        //   145: putfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   148: aload_0        
        //   149: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   152: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   155: invokestatic    r/k/d/m/player/AutoWither.yh:()Z
        //   158: ifeq            201
        //   161: aload_0        
        //   162: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   165: iconst_0       
        //   166: iconst_1       
        //   167: iconst_0       
        //   168: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   171: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   174: aload_0        
        //   175: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   178: iconst_1       
        //   179: iconst_1       
        //   180: iconst_0       
        //   181: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   184: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   187: aload_0        
        //   188: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   191: iconst_m1      
        //   192: iconst_1       
        //   193: iconst_0       
        //   194: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   197: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   200: nop            
        //   201: aload_0        
        //   202: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   205: iconst_0       
        //   206: iconst_1       
        //   207: iconst_0       
        //   208: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   211: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   214: aload_0        
        //   215: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   218: iconst_0       
        //   219: iconst_1       
        //   220: iconst_1       
        //   221: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   224: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   227: aload_0        
        //   228: getfield        r/k/d/m/player/AutoWither.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   231: iconst_0       
        //   232: iconst_1       
        //   233: iconst_m1      
        //   234: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   237: invokestatic    r/k/b/b.y:(Lnet/minecraft/util/math/BlockPos;)V
        //   240: iconst_1       
        //   241: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean ya() {
        final int ym = this.ym();
        if (ym != -1) {
            AutoWither.mc.player.inventory.currentItem = ym;
            if (yh()) {
                b.y(this.blockPos.add(0, 2, 0));
                b.y(this.blockPos.add(1, 2, 0));
                b.y(this.blockPos.add(-1, 2, 0));
            }
            b.y(this.blockPos.add(0, 2, 0));
            b.y(this.blockPos.add(0, 2, 1));
            b.y(this.blockPos.add(0, 2, -1));
            return true;
        }
        return false;
    }
    
    private int ym() {
        int n = 0;
        if (n < 9) {
            final ItemStack stackInSlot = h.mc.player.inventory.getStackInSlot(n);
            if (stackInSlot.getItem().getItemStackDisplayName(stackInSlot).equals("Wither Skeleton Skull")) {
                return n;
            }
            ++n;
        }
        return -1;
    }
    
    public static boolean yh() {
        return AutoWither.mc.player.getHorizontalFacing() != EnumFacing.WEST;
    }
}
