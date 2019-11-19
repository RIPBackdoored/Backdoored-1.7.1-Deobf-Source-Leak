package r.k.d.m.player;

import r.k.b.c.h;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import r.k.d.o.h.u;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "FastPlace", description = "Place blocks or use items faster", category = y.PLAYER)
public class FastPlace extends g
{
    private m<l$s> ht;
    public static final boolean hn;
    public static final int hi;
    public static final boolean hp;
    
    public FastPlace() {
        this.ht = (m<l$s>)new u("Whitelist", this, l$s.jt);
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final Item item = FastPlace.mc.player.inventory.getCurrentItem().getItem();
        final boolean b = item instanceof ItemExpBottle;
        final boolean b2 = item instanceof ItemEndCrystal;
        Label_0111: {
            switch (l$x.tc[this.ht.yv().ordinal()]) {
                case 1:
                    this.lw();
                case 2:
                    if (b) {
                        this.lw();
                        break Label_0111;
                    }
                    break;
                case 3:
                    if (b2) {
                        this.lw();
                        break Label_0111;
                    }
                    break;
                case 4:
                    if (b) {
                        this.lw();
                        break;
                    }
                    break;
            }
        }
    }
    
    private void lw() {
        try {
            ObfuscationReflectionHelper.setPrivateValue((Class)Minecraft.class, (Object)FastPlace.mc, (Object)0, new String[] { "rightClickDelayTimer", "field_71467_ac" });
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.s(false);
            h.sj("Disabled fastplace due to error: " + ex.toString());
        }
    }
}
