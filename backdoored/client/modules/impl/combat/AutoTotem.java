package r.k.d.m.combat;

import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import r.k.d.o.h.i.x;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Totem", description = "Works in guis", category = y.COMBAT)
public class AutoTotem extends g
{
    private boolean ed;
    private m<Boolean> es;
    private m<Integer> el;
    private m<Boolean> ey;
    private m<Integer> ex;
    public static final boolean ek;
    public static final int eq;
    public static final boolean ev;
    
    public AutoTotem() {
        super();
        this.ed = true;
        this.es = (m<Boolean>)new p("Always Holding", this, true);
        this.el = (m<Integer>)new x("Min Health to Equip", this, 6, 0, 20);
        this.ey = (m<Boolean>)new p("Refill Hotbar Slot", this, false);
        this.ex = (m<Integer>)new x("Hotbar Slot", this, 9, 0, 9);
    }
    
    public void j() {
        if (this.lo()) {
            if (AutoTotem.mc.player.getHealth() <= this.el.yv() && !this.es.yv()) {
                this.ed = true;
            }
            if (this.ed && AutoTotem.mc.player.getHeldItemOffhand().isEmpty()) {
                final int d = this.d(Items.TOTEM_OF_UNDYING);
                if (d != -1) {
                    AutoTotem.mc.playerController.windowClick(0, d, 0, ClickType.PICKUP_ALL, (EntityPlayer)AutoTotem.mc.player);
                    AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP_ALL, (EntityPlayer)AutoTotem.mc.player);
                }
            }
            if (this.es.yv()) {
                this.ed = true;
            }
            if (!this.es.yv()) {
                this.ed = false;
            }
            if (this.ey.yv() && AutoTotem.mc.player.inventory.getStackInSlot((int)this.ex.yv()).isEmpty()) {
                AutoTotem.mc.playerController.windowClick(0, this.d(Items.TOTEM_OF_UNDYING), 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(0, (int)this.ex.yv(), 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            }
        }
    }
    
    private int d(final Item item) {
        int n = 9;
        if (n <= 44) {
            if (AutoTotem.mc.player.inventoryContainer.getSlot(n).getStack().getItem() == item) {
                return n;
            }
            ++n;
        }
        return -1;
    }
}
