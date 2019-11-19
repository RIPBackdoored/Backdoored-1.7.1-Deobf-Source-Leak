package r.k.d.m.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import r.k.b.b;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Surround", description = "Surrounds your feet with obsidian", category = y.COMBAT)
public class Surround extends g
{
    private BlockPos blockPos;
    public static final boolean jw;
    public static final int jg;
    public static final boolean ju;
    
    public Surround() {
        super();
        this.blockPos = new BlockPos(0, -100, 0);
    }
    
    public void j() {
        if (!this.lo() || !Surround.mc.player.onGround) {
            return;
        }
        final int currentItem = Surround.mc.player.inventory.currentItem;
        final int s = b.s(Blocks.OBSIDIAN);
        if (s != -1) {
            final BlockPos blockPos = new BlockPos(Surround.mc.player.getPositionVector());
            if (blockPos.equals((Object)this.blockPos)) {
                final BlockPos[] array = { blockPos.add(0, -1, 1), blockPos.add(1, -1, 0), blockPos.add(0, -1, -1), blockPos.add(-1, -1, 0), blockPos.add(0, 0, 1), blockPos.add(1, 0, 0), blockPos.add(0, 0, -1), blockPos.add(-1, 0, 0) };
                final int length = array.length;
                int n = 0;
                if (n < length) {
                    final BlockPos blockPos2 = array[n];
                    if (Surround.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable() && Surround.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos2)).isEmpty()) {
                        Surround.mc.player.inventory.currentItem = s;
                        b.y(blockPos2);
                    }
                    ++n;
                }
                Surround.mc.player.inventory.currentItem = currentItem;
            }
            this.s(false);
        }
    }
    
    public void v() {
        this.blockPos = new BlockPos(Surround.mc.player.getPositionVector());
    }
}
