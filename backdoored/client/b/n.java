package r.k.b;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;

public final class n
{
    public static final boolean pl;
    public static final int py;
    public static final boolean px;
    public static final boolean pl;
    public static final int py;
    public static final boolean px;
    
    public n() {
        super();
    }
    
    public static boolean s(final ItemStack itemStack) {
        return itemStack.hasTagCompound();
    }
    
    public static void l(final ItemStack itemStack) {
        if (!s(itemStack)) {
            d(itemStack, new NBTTagCompound());
        }
    }
    
    public static void d(final ItemStack itemStack, final NBTTagCompound nbtTagCompound) {
    }
    
    public static NBTTagCompound y(final ItemStack itemStack) {
        return itemStack.getTagCompound();
    }
    
    public static void d(final ItemStack itemStack, final String s, final boolean b) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final byte b) {
        y(itemStack).setByte(s, b);
    }
    
    public static void d(final ItemStack itemStack, final String s, final short n) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final int n) {
        y(itemStack).setInteger(s, n);
    }
    
    public static void d(final ItemStack itemStack, final String s, final long n) {
        y(itemStack).setLong(s, n);
    }
    
    public static void d(final ItemStack itemStack, final String s, final float n) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final double n) {
        y(itemStack).setDouble(s, n);
    }
    
    public static void d(final ItemStack p0, final String p1, final NBTTagCompound p2) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public n() {
        super();
    }
    
    public static boolean s(final ItemStack itemStack) {
        return itemStack.hasTagCompound();
    }
    
    public static void l(final ItemStack itemStack) {
        if (!s(itemStack)) {
            d(itemStack, new NBTTagCompound());
        }
    }
    
    public static void d(final ItemStack itemStack, final NBTTagCompound nbtTagCompound) {
    }
    
    public static NBTTagCompound y(final ItemStack itemStack) {
        return itemStack.getTagCompound();
    }
    
    public static void d(final ItemStack itemStack, final String s, final boolean b) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final byte b) {
        y(itemStack).setByte(s, b);
    }
    
    public static void d(final ItemStack itemStack, final String s, final short n) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final int n) {
        y(itemStack).setInteger(s, n);
    }
    
    public static void d(final ItemStack itemStack, final String s, final long n) {
        y(itemStack).setLong(s, n);
    }
    
    public static void d(final ItemStack itemStack, final String s, final float n) {
    }
    
    public static void d(final ItemStack itemStack, final String s, final double n) {
        y(itemStack).setDouble(s, n);
    }
    
    public static void d(final ItemStack itemStack, final String s, final NBTTagCompound nbtTagCompound) {
        if (!s.equalsIgnoreCase("ench")) {}
    }
    
    public static void d(final ItemStack itemStack, final String s, final String s2) {
        y(itemStack).setString(s, s2);
    }
    
    public static void d(final ItemStack itemStack, final String s, final NBTTagList list) {
        y(itemStack).setTag(s, (NBTBase)list);
    }
    
    public static boolean d(final ItemStack p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: aload_0        
        //     9: invokestatic    r/k/b/n.s:(Lnet/minecraft/item/ItemStack;)Z
        //    12: ifeq            28
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.hasKey:(Ljava/lang/String;)Z
        //    23: ifeq            28
        //    26: iconst_1       
        //    27: nop            
        //    28: iconst_0       
        //    29: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Deprecated
    public static boolean s(final ItemStack itemStack, final String s) {
        return d(itemStack, s);
    }
    
    public static boolean s(final ItemStack p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            24
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.getBoolean:(Ljava/lang/String;)Z
        //    23: nop            
        //    24: iload_2        
        //    25: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static byte s(final ItemStack p0, final String p1, final byte p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            7
        //     4: bipush          125
        //     6: ireturn        
        //     7: nop            
        //     8: aload_0        
        //     9: aload_1        
        //    10: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    13: ifeq            25
        //    16: aload_0        
        //    17: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    20: aload_1        
        //    21: invokevirtual   net/minecraft/nbt/NBTTagCompound.getByte:(Ljava/lang/String;)B
        //    24: nop            
        //    25: iload_2        
        //    26: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static short s(final ItemStack p0, final String p1, final short p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            8
        //     4: sipush          7412
        //     7: ireturn        
        //     8: nop            
        //     9: aload_0        
        //    10: aload_1        
        //    11: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    14: ifeq            26
        //    17: aload_0        
        //    18: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    21: aload_1        
        //    22: invokevirtual   net/minecraft/nbt/NBTTagCompound.getShort:(Ljava/lang/String;)S
        //    25: nop            
        //    26: iload_2        
        //    27: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static int s(final ItemStack p0, final String p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            7
        //     4: ldc             -1756634378
        //     6: ireturn        
        //     7: nop            
        //     8: aload_0        
        //     9: aload_1        
        //    10: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    13: ifeq            25
        //    16: aload_0        
        //    17: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    20: aload_1        
        //    21: invokevirtual   net/minecraft/nbt/NBTTagCompound.getInteger:(Ljava/lang/String;)I
        //    24: nop            
        //    25: iload_2        
        //    26: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static long s(final ItemStack p0, final String p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            8
        //     4: ldc2_w          -6977438359304890777
        //     7: lreturn        
        //     8: nop            
        //     9: aload_0        
        //    10: aload_1        
        //    11: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    14: ifeq            26
        //    17: aload_0        
        //    18: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    21: aload_1        
        //    22: invokevirtual   net/minecraft/nbt/NBTTagCompound.getLong:(Ljava/lang/String;)J
        //    25: nop            
        //    26: lload_2        
        //    27: lreturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static float s(final ItemStack p0, final String p1, final float p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: fconst_0       
        //     5: freturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            24
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.getFloat:(Ljava/lang/String;)F
        //    23: nop            
        //    24: fload_2        
        //    25: freturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static double s(final ItemStack p0, final String p1, final double p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: dconst_0       
        //     5: dreturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            24
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.getDouble:(Ljava/lang/String;)D
        //    23: nop            
        //    24: dload_2        
        //    25: dreturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static NBTTagCompound l(final ItemStack p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            24
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.getCompoundTag:(Ljava/lang/String;)Lnet/minecraft/nbt/NBTTagCompound;
        //    23: nop            
        //    24: iload_2        
        //    25: ifeq            30
        //    28: aconst_null    
        //    29: nop            
        //    30: new             Lnet/minecraft/nbt/NBTTagCompound;
        //    33: dup            
        //    34: invokespecial   net/minecraft/nbt/NBTTagCompound.<init>:()V
        //    37: areturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String s(final ItemStack p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            24
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/nbt/NBTTagCompound.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    23: nop            
        //    24: aload_2        
        //    25: areturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static NBTTagList d(final ItemStack p0, final String p1, final int p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/n.d:(Lnet/minecraft/item/ItemStack;Ljava/lang/String;)Z
        //    12: ifeq            25
        //    15: aload_0        
        //    16: invokestatic    r/k/b/n.y:(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagCompound;
        //    19: aload_1        
        //    20: iload_2        
        //    21: invokevirtual   net/minecraft/nbt/NBTTagCompound.getTagList:(Ljava/lang/String;I)Lnet/minecraft/nbt/NBTTagList;
        //    24: nop            
        //    25: iload_3        
        //    26: ifeq            31
        //    29: aconst_null    
        //    30: nop            
        //    31: new             Lnet/minecraft/nbt/NBTTagList;
        //    34: dup            
        //    35: invokespecial   net/minecraft/nbt/NBTTagList.<init>:()V
        //    38: areturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
