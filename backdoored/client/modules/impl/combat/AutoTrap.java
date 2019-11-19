package r.k.d.m.combat;

import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import r.k.b.i.p;
import net.minecraft.entity.player.EntityPlayer;
import r.k.b.c.h;
import r.k.b.b;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import r.k.b.v;
import r.k.d.o.h.i.x;
import r.k.d.o.h.i.s;
import java.time.Instant;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Trap", description = "Trap nearby players", category = y.COMBAT)
public class AutoTrap extends g
{
    private m<Double> bz;
    private m<Integer> bc;
    private Instant ba;
    public static final boolean bm;
    public static final int bh;
    public static final boolean wd;
    
    public AutoTrap() {
        super();
        this.bz = (m<Double>)new s("Range", this, 8.0, 0.0, 15.0);
        this.bc = (m<Integer>)new x("Millisecond delay", this, 1000, 100, 1500);
        this.ba = Instant.EPOCH;
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final Instant now = Instant.now();
        if (!v.s(this.ba, now, new Long(this.bc.yv()))) {
            return;
        }
        final int currentItem = AutoTrap.mc.player.inventory.currentItem;
        final int s = b.s(Item.getItemFromBlock(Blocks.OBSIDIAN));
        if (s == -1) {
            this.s(false);
            h.o("Obsidian was not found in your hotbar!", "red");
            return;
        }
        AutoTrap.mc.player.inventory.currentItem = s;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)AutoTrap.mc.world.playerEntities.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer = iterator.next();
            if (p.l(entityPlayer)) {}
            if (AutoTrap.mc.player.getDistance((Entity)entityPlayer) <= this.bz.yv() && entityPlayer != AutoTrap.mc.player && !p.l(entityPlayer)) {
                final BlockPos[] array = { b.d(entityPlayer, 1, 0, 0), b.d(entityPlayer, 1, 1, 0), b.d(entityPlayer, 0, 1, 1), b.d(entityPlayer, -1, 1, 0), b.d(entityPlayer, 0, 1, -1), b.d(entityPlayer, 0, 2, -1), b.d(entityPlayer, 0, 2, 0) };
                final int length = array.length;
                int n = 0;
                if (n < length) {
                    final BlockPos blockPos = array[n];
                    if (AutoTrap.mc.world.getBlockState(blockPos).getMaterial().isReplaceable() && AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).isEmpty()) {
                        b.y(blockPos);
                        this.ba = now;
                        return;
                    }
                    ++n;
                }
            }
        }
        AutoTrap.mc.player.inventory.currentItem = currentItem;
    }
}
