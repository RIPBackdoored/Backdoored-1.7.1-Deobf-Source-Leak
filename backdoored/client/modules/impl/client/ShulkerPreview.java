package r.k.d.m.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import r.k.i.o;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.NBTTagCompound;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import r.k.b.n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Shulker Preview", description = "Preview Shulkers via tooltip", category = y.CLIENT)
@g$i(name = "Shulker Preview", description = "Preview Shulkers via tooltip", category = y.CLIENT)
public class ShulkerPreview extends g
{
    static String[] op;
    private static final ResourceLocation resourceLocation;
    private static List<ResourceLocation> of;
    private static final int[][] ob;
    private static final int ow;
    private static final int og;
    private static final int ou;
    static final /* synthetic */ boolean oz;
    public static final boolean oc;
    public static final int oa;
    public static final boolean om;
    static String[] op;
    private static final ResourceLocation resourceLocation;
    private static List<ResourceLocation> of;
    private static final int[][] ob;
    private static final int ow;
    private static final int og;
    private static final int ou;
    static final /* synthetic */ boolean oz;
    public static final boolean oc;
    public static final int oa;
    public static final boolean om;
    
    public ShulkerPreview() {
        super();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void d(final ItemTooltipEvent itemTooltipEvent) {
        if (d(itemTooltipEvent.getItemStack(), ShulkerPreview.of) && itemTooltipEvent.getItemStack().hasTagCompound()) {
            NBTTagCompound nbtTagCompound = n.l(itemTooltipEvent.getItemStack(), "BlockEntityTag", true);
            if (nbtTagCompound != null) {
                if (!nbtTagCompound.hasKey("id", 8)) {
                    nbtTagCompound = nbtTagCompound.copy();
                    nbtTagCompound.setString("id", "minecraft:shulker_box");
                }
                final TileEntity create = TileEntity.create((World)ShulkerPreview.mc.world, nbtTagCompound);
                if (create != null && create.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing)null)) {
                    final List<? extends String> list2;
                    final ArrayList<String> list = new ArrayList<String>(list2);
                    int n = 1;
                    if (n < list.size()) {
                        final String s = list.get(n);
                        if (!s.startsWith("§") || s.startsWith("§o")) {
                            list2.remove(s);
                        }
                        ++n;
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void d(final RenderTooltipEvent.PostText p0) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ShulkerPreview() {
        super();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void d(final ItemTooltipEvent itemTooltipEvent) {
        if (d(itemTooltipEvent.getItemStack(), ShulkerPreview.of) && itemTooltipEvent.getItemStack().hasTagCompound()) {
            NBTTagCompound nbtTagCompound = n.l(itemTooltipEvent.getItemStack(), "BlockEntityTag", true);
            if (nbtTagCompound != null) {
                if (!nbtTagCompound.hasKey("id", 8)) {
                    nbtTagCompound = nbtTagCompound.copy();
                    nbtTagCompound.setString("id", "minecraft:shulker_box");
                }
                final TileEntity create = TileEntity.create((World)ShulkerPreview.mc.world, nbtTagCompound);
                if (create != null && create.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing)null)) {
                    final List<? extends String> list2;
                    final ArrayList<String> list = new ArrayList<String>(list2);
                    int n = 1;
                    if (n < list.size()) {
                        final String s = list.get(n);
                        if (!s.startsWith("§") || s.startsWith("§o")) {
                            list2.remove(s);
                        }
                        ++n;
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void d(final RenderTooltipEvent.PostText postText) {
        if (!this.lo()) {
            return;
        }
        if (d(postText.getStack(), ShulkerPreview.of) && postText.getStack().hasTagCompound()) {
            NBTTagCompound nbtTagCompound = n.l(postText.getStack(), "BlockEntityTag", true);
            if (nbtTagCompound != null) {
                if (!nbtTagCompound.hasKey("id", 8)) {
                    nbtTagCompound = nbtTagCompound.copy();
                    nbtTagCompound.setString("id", "minecraft:shulker_box");
                }
                final TileEntity create = TileEntity.create((World)ShulkerPreview.mc.world, nbtTagCompound);
                if (create != null && create.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing)null)) {
                    final ItemStack stack = postText.getStack();
                    final int n = postText.getX() - 5;
                    int n2 = postText.getY() - 70;
                    final IItemHandler itemHandler = (IItemHandler)create.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing)null);
                    if (!ShulkerPreview.oz && itemHandler == null) {
                        throw new AssertionError();
                    }
                    final int slots = itemHandler.getSlots();
                    int[] array = { Math.min(slots, 9), Math.max(slots / 9, 1) };
                    final int[][] ob = ShulkerPreview.ob;
                    final int length = ob.length;
                    int n3 = 0;
                    if (n3 < length) {
                        final int[] array2 = ob[n3];
                        if (array2[0] * array2[1] == slots) {
                            array = array2;
                        }
                        ++n3;
                    }
                    final int n4 = 10 + 18 * array[0];
                    if (n2 < 0) {
                        n2 = postText.getY() + postText.getLines().size() * 10 + 5;
                    }
                    if (n + n4 > new ScaledResolution(ShulkerPreview.mc).getScaledWidth()) {}
                    GlStateManager.pushMatrix();
                    RenderHelper.enableStandardItemLighting();
                    GlStateManager.enableRescaleNormal();
                    GlStateManager.color(1.0f, 1.0f, 1.0f);
                    GlStateManager.translate(0.0f, 0.0f, 700.0f);
                    ShulkerPreview.mc.getTextureManager().bindTexture(ShulkerPreview.resourceLocation);
                    RenderHelper.disableStandardItemLighting();
                    int n5 = -1;
                    if (((ItemBlock)stack.getItem()).getBlock() instanceof BlockShulkerBox) {
                        n5 = ItemDye.DYE_COLORS[((BlockShulkerBox)((ItemBlock)stack.getItem()).getBlock()).getColor().getDyeDamage()];
                    }
                    d(n, n2, array[0], array[1], n5);
                    final RenderItem renderItem = ShulkerPreview.mc.getRenderItem();
                    RenderHelper.enableGUIStandardItemLighting();
                    GlStateManager.enableDepth();
                    int n6 = 0;
                    if (n6 < slots) {
                        final ItemStack stackInSlot = itemHandler.getStackInSlot(n6);
                        final int n7 = n + 6 + n6 % 9 * 18;
                        final int n8 = n2 + 6 + n6 / 9 * 18;
                        if (!stackInSlot.isEmpty()) {
                            renderItem.renderItemAndEffectIntoGUI(stackInSlot, n7, n8);
                            renderItem.renderItemOverlays(o.fontRenderer, stackInSlot, n7, n8);
                        }
                        ++n6;
                    }
                    GlStateManager.disableDepth();
                    GlStateManager.disableRescaleNormal();
                    GlStateManager.popMatrix();
                }
            }
        }
    }
    
    private static void d(final int n, final int n2, final int n3, final int n4, final int n5) {
        ShulkerPreview.mc.getTextureManager().bindTexture(ShulkerPreview.resourceLocation);
        GlStateManager.color(((n5 & 0xFF0000) >> 16) / 255.0f, ((n5 & 0xFF00) >> 8) / 255.0f, (n5 & 0xFF) / 255.0f);
        RenderHelper.disableStandardItemLighting();
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2 + 5 + 18 * n4, 25.0f, 25.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2, 25.0f, 0.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n, n2 + 5 + 18 * n4, 0.0f, 25.0f, 5, 5, 256.0f, 256.0f);
        int n6 = 0;
        if (n6 < n4) {
            Gui.drawModalRectWithCustomSizedTexture(n, n2 + 5 + 18 * n6, 0.0f, 6.0f, 5, 18, 256.0f, 256.0f);
            Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2 + 5 + 18 * n6, 25.0f, 6.0f, 5, 18, 256.0f, 256.0f);
            int n7 = 0;
            if (n7 < n3) {
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2, 6.0f, 0.0f, 18, 5, 256.0f, 256.0f);
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2 + 5 + 18 * n4, 6.0f, 25.0f, 18, 5, 256.0f, 256.0f);
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2 + 5 + 18 * n6, 6.0f, 6.0f, 18, 18, 256.0f, 256.0f);
                ++n7;
            }
            ++n6;
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    private static boolean d(final ItemStack p0, final List<ResourceLocation> p1) {
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
        //     8: aload_0        
        //     9: invokevirtual   net/minecraft/item/ItemStack.isEmpty:()Z
        //    12: ifne            31
        //    15: aload_0        
        //    16: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //    19: invokevirtual   net/minecraft/item/Item.getRegistryName:()Lnet/minecraft/util/ResourceLocation;
        //    22: aload_1        
        //    23: invokestatic    r/k/d/m/client/ShulkerPreview.d:(Lnet/minecraft/util/ResourceLocation;Ljava/util/List;)Z
        //    26: ifeq            31
        //    29: iconst_1       
        //    30: nop            
        //    31: iconst_0       
        //    32: ireturn        
        //    Signature:
        //  (Lnet/minecraft/item/ItemStack;Ljava/util/List<Lnet/minecraft/util/ResourceLocation;>;)Z
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static boolean d(final ResourceLocation resourceLocation, final List<ResourceLocation> list) {
        return false;
    }
    
    private static /* synthetic */ String[] l(final int n) {
        return new String[n];
    }
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: putstatic       r/k/d/m/client/ShulkerPreview.ou:I
        //     5: iconst_1       
        //     6: putstatic       r/k/d/m/client/ShulkerPreview.og:I
        //     9: iconst_5       
        //    10: putstatic       r/k/d/m/client/ShulkerPreview.ow:I
        //    13: nop            
        //    14: goto            18
        //    17: return         
        //    18: nop            
        //    19: ldc_w           Lr/k/d/m/b/p;.class
        //    22: invokevirtual   java/lang/Class.desiredAssertionStatus:()Z
        //    25: ifne            30
        //    28: iconst_1       
        //    29: nop            
        //    30: iconst_0       
        //    31: putstatic       r/k/d/m/client/ShulkerPreview.oz:Z
        //    34: getstatic       net/minecraft/init/Blocks.WHITE_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    37: getstatic       net/minecraft/init/Blocks.ORANGE_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    40: getstatic       net/minecraft/init/Blocks.MAGENTA_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    43: getstatic       net/minecraft/init/Blocks.LIGHT_BLUE_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    46: getstatic       net/minecraft/init/Blocks.YELLOW_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    49: getstatic       net/minecraft/init/Blocks.LIME_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    52: bipush          10
        //    54: anewarray       Lnet/minecraft/block/Block;
        //    57: dup            
        //    58: iconst_0       
        //    59: getstatic       net/minecraft/init/Blocks.PINK_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    62: aastore        
        //    63: dup            
        //    64: iconst_1       
        //    65: getstatic       net/minecraft/init/Blocks.GRAY_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    68: aastore        
        //    69: dup            
        //    70: iconst_2       
        //    71: getstatic       net/minecraft/init/Blocks.SILVER_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    74: aastore        
        //    75: dup            
        //    76: iconst_3       
        //    77: getstatic       net/minecraft/init/Blocks.CYAN_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    80: aastore        
        //    81: dup            
        //    82: iconst_4       
        //    83: getstatic       net/minecraft/init/Blocks.PURPLE_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    86: aastore        
        //    87: dup            
        //    88: iconst_5       
        //    89: getstatic       net/minecraft/init/Blocks.BLUE_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    92: aastore        
        //    93: dup            
        //    94: bipush          6
        //    96: getstatic       net/minecraft/init/Blocks.BROWN_SHULKER_BOX:Lnet/minecraft/block/Block;
        //    99: aastore        
        //   100: dup            
        //   101: bipush          7
        //   103: getstatic       net/minecraft/init/Blocks.GREEN_SHULKER_BOX:Lnet/minecraft/block/Block;
        //   106: aastore        
        //   107: dup            
        //   108: bipush          8
        //   110: getstatic       net/minecraft/init/Blocks.RED_SHULKER_BOX:Lnet/minecraft/block/Block;
        //   113: aastore        
        //   114: dup            
        //   115: bipush          9
        //   117: getstatic       net/minecraft/init/Blocks.BLACK_SHULKER_BOX:Lnet/minecraft/block/Block;
        //   120: aastore        
        //   121: invokestatic    com/google/common/collect/ImmutableSet.of:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;
        //   124: invokevirtual   com/google/common/collect/ImmutableSet.stream:()Ljava/util/stream/Stream;
        //   127: invokedynamic   BootstrapMethod #0, apply:()Ljava/util/function/Function;
        //   132: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   137: invokedynamic   BootstrapMethod #1, apply:()Ljava/util/function/Function;
        //   142: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   147: invokedynamic   BootstrapMethod #2, apply:()Ljava/util/function/IntFunction;
        //   152: invokeinterface java/util/stream/Stream.toArray:(Ljava/util/function/IntFunction;)[Ljava/lang/Object;
        //   157: checkcast       [Ljava/lang/String;
        //   160: putstatic       r/k/d/m/client/ShulkerPreview.op:[Ljava/lang/String;
        //   163: new             Lnet/minecraft/util/ResourceLocation;
        //   166: dup            
        //   167: ldc_w           "backdoored"
        //   170: ldc_w           "textures/inv_slot.png"
        //   173: invokespecial   net/minecraft/util/ResourceLocation.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   176: putstatic       r/k/d/m/client/ShulkerPreview.resourceLocation:Lnet/minecraft/util/ResourceLocation;
        //   179: getstatic       r/k/d/m/client/ShulkerPreview.op:[Ljava/lang/String;
        //   182: invokestatic    java/util/Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //   185: invokedynamic   BootstrapMethod #3, apply:()Ljava/util/function/Function;
        //   190: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   195: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   198: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   203: checkcast       Ljava/util/List;
        //   206: putstatic       r/k/d/m/client/ShulkerPreview.of:Ljava/util/List;
        //   209: bipush          7
        //   211: anewarray       [I
        //   214: dup            
        //   215: iconst_0       
        //   216: iconst_2       
        //   217: newarray        I
        //   219: dup            
        //   220: iconst_0       
        //   221: iconst_1       
        //   222: iastore        
        //   223: dup            
        //   224: iconst_1       
        //   225: iconst_1       
        //   226: iastore        
        //   227: aastore        
        //   228: dup            
        //   229: iconst_1       
        //   230: iconst_2       
        //   231: newarray        I
        //   233: dup            
        //   234: iconst_0       
        //   235: bipush          9
        //   237: iastore        
        //   238: dup            
        //   239: iconst_1       
        //   240: iconst_3       
        //   241: iastore        
        //   242: aastore        
        //   243: dup            
        //   244: iconst_2       
        //   245: iconst_2       
        //   246: newarray        I
        //   248: dup            
        //   249: iconst_0       
        //   250: bipush          9
        //   252: iastore        
        //   253: dup            
        //   254: iconst_1       
        //   255: iconst_5       
        //   256: iastore        
        //   257: aastore        
        //   258: dup            
        //   259: iconst_3       
        //   260: iconst_2       
        //   261: newarray        I
        //   263: dup            
        //   264: iconst_0       
        //   265: bipush          9
        //   267: iastore        
        //   268: dup            
        //   269: iconst_1       
        //   270: bipush          6
        //   272: iastore        
        //   273: aastore        
        //   274: dup            
        //   275: iconst_4       
        //   276: iconst_2       
        //   277: newarray        I
        //   279: dup            
        //   280: iconst_0       
        //   281: bipush          9
        //   283: iastore        
        //   284: dup            
        //   285: iconst_1       
        //   286: bipush          8
        //   288: iastore        
        //   289: aastore        
        //   290: dup            
        //   291: iconst_5       
        //   292: iconst_2       
        //   293: newarray        I
        //   295: dup            
        //   296: iconst_0       
        //   297: bipush          9
        //   299: iastore        
        //   300: dup            
        //   301: iconst_1       
        //   302: bipush          9
        //   304: iastore        
        //   305: aastore        
        //   306: dup            
        //   307: bipush          6
        //   309: iconst_2       
        //   310: newarray        I
        //   312: dup            
        //   313: iconst_0       
        //   314: bipush          12
        //   316: iastore        
        //   317: dup            
        //   318: iconst_1       
        //   319: bipush          9
        //   321: iastore        
        //   322: aastore        
        //   323: putstatic       r/k/d/m/client/ShulkerPreview.ob:[[I
        //   326: return         
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
