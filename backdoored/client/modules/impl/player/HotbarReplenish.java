package r.k.d.m.player;

import net.minecraft.entity.player.InventoryPlayer;
import java.util.List;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import r.k.b.c.h;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Hotbar Replenish", description = "Replenish items in your hotbar when they are used", category = y.PLAYER)
public class HotbarReplenish extends g
{
    private m<Integer> so;
    private m<Integer> st;
    private int sn;
    public static final boolean si;
    public static final int sp;
    public static final boolean sr;
    
    public HotbarReplenish() {
        this.so = (m<Integer>)new x("Cooldown in ticks", this, 100, 0, 200);
        this.st = (m<Integer>)new x("Min Stack Size (percent)", this, 20, 1, 99);
        this.sn = 0;
    }
    
    public void v() {
        this.s(false);
        h.o("Still in development...", "red");
    }
    
    public void j() {
        --this.sn;
        if (this.sn <= 0) {
            final Iterator<ItemStack> iterator = e().iterator();
            if (iterator.hasNext()) {
                final ItemStack itemStack = iterator.next();
                if (itemStack == null || d(itemStack) < this.st.yv()) {}
            }
            this.sn = this.so.yv();
        }
    }
    
    private static int d(final ItemStack itemStack) {
        return (int)Math.ceil(itemStack.getCount() * 100.0f / itemStack.getMaxStackSize());
    }
    
    private static List<ItemStack> e() {
        return (List<ItemStack>)HotbarReplenish.mc.player.inventory.mainInventory.subList(0, InventoryPlayer.getHotbarSize());
    }
}
