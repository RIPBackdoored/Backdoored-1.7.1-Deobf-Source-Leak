package r.k.d.m.ui;

import r.k.d.o.h.i.x;
import r.k.d.o.h.u;
import net.minecraft.client.util.ITooltipFlag;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Item info", description = "Show extra info about the item your holding", category = y.UI)
public class ItemInfo extends g
{
    private final m<ITooltipFlag.TooltipFlags> kw;
    private final m<Integer> kg;
    private final m<Integer> ku;
    public static final boolean kz;
    public static final int kc;
    public static final boolean ka;
    
    public ItemInfo() {
        super();
        this.kw = (m<ITooltipFlag.TooltipFlags>)new u("Type", this, (Enum)ITooltipFlag.TooltipFlags.ADVANCED);
        this.kg = (m<Integer>)new x("x", this, 0, 0, (int)Math.round(ItemInfo.mc.displayWidth * 1.2));
        this.ku = (m<Integer>)new x("y", this, 0, 0, (int)Math.round(ItemInfo.mc.displayHeight * 1.2));
    }
    
    public void a() {
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
        //     8: invokevirtual   r/k/d/m/ui/ItemInfo.lo:()Z
        //    11: ifeq            256
        //    14: getstatic       r/k/d/m/ui/ItemInfo.mc:Lnet/minecraft/client/Minecraft;
        //    17: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    20: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //    23: invokevirtual   net/minecraft/entity/player/InventoryPlayer.getCurrentItem:()Lnet/minecraft/item/ItemStack;
        //    26: astore_1       
        //    27: aload_1        
        //    28: invokevirtual   net/minecraft/item/ItemStack.isEmpty:()Z
        //    31: ifne            256
        //    34: aload_1        
        //    35: getstatic       r/k/d/m/ui/ItemInfo.mc:Lnet/minecraft/client/Minecraft;
        //    38: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    41: aload_0        
        //    42: getfield        r/k/d/m/ui/ItemInfo.kw:Lr/k/d/o/m;
        //    45: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    48: checkcast       Lnet/minecraft/client/util/ITooltipFlag;
        //    51: invokevirtual   net/minecraft/item/ItemStack.getTooltip:(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/client/util/ITooltipFlag;)Ljava/util/List;
        //    54: astore_2       
        //    55: aload_1        
        //    56: invokevirtual   net/minecraft/item/ItemStack.getTagCompound:()Lnet/minecraft/nbt/NBTTagCompound;
        //    59: ifnull          138
        //    62: aload_1        
        //    63: invokevirtual   net/minecraft/item/ItemStack.getTagCompound:()Lnet/minecraft/nbt/NBTTagCompound;
        //    66: invokevirtual   net/minecraft/nbt/NBTTagCompound.getKeySet:()Ljava/util/Set;
        //    69: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    74: astore_3       
        //    75: aload_3        
        //    76: invokeinterface java/util/Iterator.hasNext:()Z
        //    81: ifeq            138
        //    84: aload_3        
        //    85: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    90: checkcast       Ljava/lang/String;
        //    93: astore          4
        //    95: aload_2        
        //    96: new             Ljava/lang/StringBuilder;
        //    99: dup            
        //   100: invokespecial   java/lang/StringBuilder.<init>:()V
        //   103: aload           4
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: ldc             ":"
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: aload_1        
        //   114: invokevirtual   net/minecraft/item/ItemStack.getTagCompound:()Lnet/minecraft/nbt/NBTTagCompound;
        //   117: aload           4
        //   119: invokevirtual   net/minecraft/nbt/NBTTagCompound.getTag:(Ljava/lang/String;)Lnet/minecraft/nbt/NBTBase;
        //   122: invokevirtual   net/minecraft/nbt/NBTBase.toString:()Ljava/lang/String;
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   131: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   136: pop            
        //   137: nop            
        //   138: iconst_1       
        //   139: istore          4
        //   141: aload_2        
        //   142: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   147: astore          5
        //   149: aload           5
        //   151: invokeinterface java/util/Iterator.hasNext:()Z
        //   156: ifeq            256
        //   159: aload           5
        //   161: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   166: checkcast       Ljava/lang/String;
        //   169: astore          6
        //   171: aload           6
        //   173: invokevirtual   java/lang/String.isEmpty:()Z
        //   176: ifeq            180
        //   179: nop            
        //   180: getstatic       r/k/d/m/ui/ItemInfo.mc:Lnet/minecraft/client/Minecraft;
        //   183: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //   186: aload           6
        //   188: aload_0        
        //   189: getfield        r/k/d/m/ui/ItemInfo.kg:Lr/k/d/o/m;
        //   192: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   195: checkcast       Ljava/lang/Integer;
        //   198: invokevirtual   java/lang/Integer.intValue:()I
        //   201: aload_0        
        //   202: getfield        r/k/d/m/ui/ItemInfo.ku:Lr/k/d/o/m;
        //   205: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   208: checkcast       Ljava/lang/Integer;
        //   211: invokevirtual   java/lang/Integer.intValue:()I
        //   214: iload_3        
        //   215: iadd           
        //   216: iload           4
        //   218: ifeq            228
        //   221: getstatic       java/awt/Color.WHITE:Ljava/awt/Color;
        //   224: invokevirtual   java/awt/Color.getRGB:()I
        //   227: nop            
        //   228: getstatic       java/awt/Color.LIGHT_GRAY:Ljava/awt/Color;
        //   231: invokevirtual   java/awt/Color.getRGB:()I
        //   234: invokevirtual   net/minecraft/client/gui/FontRenderer.drawString:(Ljava/lang/String;III)I
        //   237: pop            
        //   238: iconst_0       
        //   239: istore          4
        //   241: iload_3        
        //   242: getstatic       r/k/d/m/ui/ItemInfo.mc:Lnet/minecraft/client/Minecraft;
        //   245: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //   248: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //   251: iconst_2       
        //   252: iadd           
        //   253: iadd           
        //   254: istore_3       
        //   255: nop            
        //   256: return         
        //    StackMapTable: 00 09 FF 00 04 00 0A 07 00 51 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 45 00 0A 07 00 51 07 00 6F 07 00 8E 07 00 90 00 00 00 01 01 01 00 00 FF 00 3E 00 0A 07 00 51 07 00 6F 07 00 8E 01 00 00 00 01 01 01 00 00 FF 00 0A 00 0A 07 00 51 07 00 6F 07 00 8E 01 01 07 00 90 00 01 01 01 00 00 FF 00 1E 00 0A 07 00 51 07 00 6F 07 00 8E 01 01 07 00 90 07 00 98 01 01 01 00 00 FF 00 2F 00 0A 07 00 51 07 00 6F 07 00 8E 01 01 07 00 90 07 00 98 01 01 01 00 04 07 00 C7 07 00 98 01 01 FF 00 05 00 0A 07 00 51 07 00 6F 07 00 8E 01 01 07 00 90 07 00 98 01 01 01 00 05 07 00 C7 07 00 98 01 01 01 FF 00 15 00 0A 07 00 51 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
