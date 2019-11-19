package r.k.n;

import java.util.Iterator;
import r.k.b.c.h;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.inventory.GuiInventory;

public class r extends c
{
    public static final boolean vr;
    public static final int vf;
    public static final boolean vb;
    
    public r() {
        super(new String[] { "viewinv", "inventory", "inventoryview" });
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length < 1) {
            this.mc.displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)this.mc.player));
            return true;
        }
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)this.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            h.sj("Could not find player " + array[0]);
            return false;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (entityPlayer.getDisplayNameString().equalsIgnoreCase(array[0])) {
            this.mc.displayGuiScreen((GuiScreen)new GuiInventory(entityPlayer));
            return true;
        }
        throw null;
    }
    
    @Override
    public String k() {
        return "-viewinv FitMC";
    }
}
