package r.k.b.o;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import r.k.h;

public class y implements h
{
    public static final boolean uf;
    public static final int ub;
    public static final boolean uw;
    
    public y() {
        super();
    }
    
    public static int d(final Item item, final boolean b) {
        int n = 9;
        if (b) {
            n = 0;
        }
        int n2 = n;
        if (n2 <= 44) {
            if (y.mc.player.inventoryContainer.getSlot(n2).getStack().getItem() == item) {
                return n2;
            }
            ++n2;
        }
        return -1;
    }
    
    public static void d(final Block block, final boolean b) {
        s(new ItemStack(block).getItem(), b);
    }
    
    public static int s(final Item item, final boolean b) {
        return d(item, b, y$i.xs);
    }
    
    public static void d(final Block block, final boolean b, final y$i y$i) {
        d(new ItemStack(block).getItem(), b, y$i);
    }
    
    public static int d(final Item item, final boolean b, final y$i y$i) {
        int n = 0;
        int n2 = 9;
        if (b) {
            n2 = 0;
        }
        int n3 = n2;
        if (n3 <= 44) {
            final ItemStack stack = y.mc.player.inventoryContainer.getSlot(n3).getStack();
            if (stack.getItem() == item) {
                if (y$i == y$i.xs) {
                    ++n;
                }
                n += stack.getCount();
            }
            ++n3;
        }
        return n;
    }
}
