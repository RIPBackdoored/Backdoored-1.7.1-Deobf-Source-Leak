package r.k.d.m.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.entity.passive.AbstractHorse;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Donkey Drop", description = "Drop all items in donkeys inv", category = y.MISC)
public class DonkeyDrop extends g
{
    public static final boolean hs;
    public static final int hl;
    public static final boolean hy;
    
    public DonkeyDrop() {
        super();
    }
    
    public void j() {
        if (this.lo() && DonkeyDrop.mc.player.getRidingEntity() instanceof AbstractHorse && DonkeyDrop.mc.player.openContainer instanceof ContainerHorseInventory) {
            int n = 2;
            if (n < 17) {
                final ItemStack itemStack = (ItemStack)DonkeyDrop.mc.player.openContainer.getInventory().get(n);
                if (!itemStack.isEmpty() && itemStack.getItem() != Items.AIR) {
                    DonkeyDrop.mc.playerController.windowClick(DonkeyDrop.mc.player.openContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)DonkeyDrop.mc.player);
                    DonkeyDrop.mc.playerController.windowClick(DonkeyDrop.mc.player.openContainer.windowId, -999, 0, ClickType.PICKUP, (EntityPlayer)DonkeyDrop.mc.player);
                }
                ++n;
            }
        }
        this.s(false);
    }
}
