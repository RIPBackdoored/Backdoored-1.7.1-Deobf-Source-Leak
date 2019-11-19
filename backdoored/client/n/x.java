package r.k.n;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.math.BlockPos;

public class x extends c
{
    boolean swn;
    int swi;
    boolean swp;
    int swr;
    BlockPos[] blockPos;
    public static final boolean swb;
    public static final int sww;
    public static final boolean swg;
    boolean swn;
    int swi;
    boolean swp;
    int swr;
    BlockPos[] blockPos;
    public static final boolean swb;
    public static final int sww;
    public static final boolean swg;
    
    public x() {
        super(new String[] { "nomadbase", "fitbase", "autonomadbase" });
        this.swn = false;
        this.swi = 0;
        this.swr = 0;
    }
    
    @Override
    public boolean d(final String[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public x() {
        super(new String[] { "nomadbase", "fitbase", "autonomadbase" });
        this.swn = false;
        this.swi = 0;
        this.swr = 0;
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length == 0) {
            this.swr = 0;
            this.swn = true;
        }
        if ((array.length > 1 && array[0].equalsIgnoreCase("delay")) || array[0].equalsIgnoreCase("setdelay")) {
            this.swi = Integer.valueOf(array[1]);
            if (this.swi == 0) {
                this.swp = false;
            }
        }
        return true;
    }
    
    @SubscribeEvent
    public void s(final TickEvent.ClientTickEvent clientTickEvent) {
        if (!this.swn) {
            return;
        }
        if (this.swp && this.swr % this.swi != 0) {
            ++this.swr;
            return;
        }
        final BlockPos[] array = { new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, -1, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, -1, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, -1, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, -1, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, -1, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, -1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, -1, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, -1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, -1, -1)) };
        final BlockPos[] array2 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 1, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 1, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 1, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 1, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 1, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 1, -2)) };
        final BlockPos[] array3 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 2, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 2, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 2, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 2, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 2, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 2, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 2, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 2, -2)) };
        final BlockPos[] array4 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 2, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 2, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 2, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 2, -2)) };
        final int n = MathHelper.floor(this.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
        final BlockPos[] array5 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, -2)) };
        final BlockPos[] array6 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 1)) };
        if (n == 1) {
            final BlockPos[] array7 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, -2)) };
            final BlockPos[] array8 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, -1)) };
        }
        if (n == 2) {
            final BlockPos[] array9 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, -2)) };
            final BlockPos[] array10 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, -1)) };
        }
        final BlockPos[] array11 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 0, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, 2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 0, -2)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 0, -2)) };
        final BlockPos[] array12 = { new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-2, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(-1, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(0, 3, -1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 0)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, 1)), new BlockPos((Vec3i)this.mc.player.getPosition().add(1, 3, -1)) };
        final int currentItem = this.mc.player.inventory.currentItem;
        int currentItem2 = -1;
        int n2 = 0;
        if (n2 < 9) {
            if (this.mc.player.inventory.getStackInSlot(n2) != ItemStack.EMPTY && this.mc.player.inventory.getStackInSlot(n2).getItem() instanceof ItemBlock) {
                if (!Block.getBlockFromItem(this.mc.player.inventory.getStackInSlot(n2).getItem()).getDefaultState().isFullBlock()) {}
                currentItem2 = n2;
            }
            ++n2;
        }
        if (currentItem2 != -1) {
            this.mc.player.inventory.currentItem = currentItem2;
        }
        h.o("No blocks found in hotbar!", "red");
        this.swn = false;
    }
    
    @Override
    public String k() {
        return "-nomadbase or -nomadbase setdelay <0/1/2/..> (6 is the best)";
    }
}
