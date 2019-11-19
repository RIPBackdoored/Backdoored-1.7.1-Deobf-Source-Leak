package r.k.d.m.combat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import r.k.b.c.h;
import r.k.b.b;
import net.minecraft.item.Item;
import r.k.d.o.h.p;
import r.k.d.o.h.u;
import net.minecraft.util.math.BlockPos;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto32k", description = "Instantly places shulker and hopper and grabs a 32k sword", category = y.COMBAT)
@g$i(name = "Auto32k", description = "Instantly places shulker and hopper and grabs a 32k sword", category = y.COMBAT)
public class Auto32k extends g
{
    private m<y$s> syi;
    private m<Boolean> syp;
    private boolean syr;
    private boolean syf;
    private BlockPos blockPos;
    private int syw;
    public static final boolean syg;
    public static final int syu;
    public static final boolean syz;
    private m<y$s> syi;
    private m<Boolean> syp;
    private boolean syr;
    private boolean syf;
    private BlockPos blockPos;
    private int syw;
    public static final boolean syg;
    public static final int syu;
    public static final boolean syz;
    
    public Auto32k() {
        super();
        this.syi = (m<y$s>)new u("Mode", this, y$s.svc);
        this.syp = (m<Boolean>)new p("SecretClose", this, false);
        this.syr = false;
        this.syf = false;
    }
    
    public void v() {
        if (Auto32k.mc.objectMouseOver == null || Auto32k.mc.objectMouseOver.sideHit == null) {
            return;
        }
        if (!this.yr()) {
            this.s(false);
        }
    }
    
    private boolean yr() {
        this.blockPos = null;
        if (Auto32k.mc.objectMouseOver == null || Auto32k.mc.objectMouseOver.sideHit == null) {
            return false;
        }
        this.blockPos = Auto32k.mc.objectMouseOver.getBlockPos().offset(Auto32k.mc.objectMouseOver.sideHit);
        if (b.s(Item.getItemById(154)) == -1) {
            h.o("A hopper was not found in your hotbar!", "red");
            this.s(false);
            return false;
        }
        int n = 219;
        if (n <= 234) {
            if (b.s(Item.getItemById(n)) != -1) {}
            if (n == 234) {
                h.o("A shulker was not found in your hotbar!", "red");
                this.s(false);
                return false;
            }
            ++n;
        }
        if (this.syi.yv() == y$s.svz) {
            this.yw();
        }
        if (this.syi.yv() == y$s.svc) {
            this.yf();
        }
        return true;
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        if (this.syr || !x(Auto32k.mc.player.inventory.getCurrentItem())) {
            int n = 0;
            if (n < Auto32k.mc.player.openContainer.inventorySlots.size()) {
                if (x(((Slot)Auto32k.mc.player.openContainer.inventorySlots.get(n)).getStack())) {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.openContainer.windowId, n, 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    int currentItem = 0;
                    if (currentItem < 9) {
                        if (x(Auto32k.mc.player.inventory.getStackInSlot(currentItem))) {
                            Auto32k.mc.player.inventory.currentItem = currentItem;
                        }
                        ++currentItem;
                    }
                    if (this.syp.yv()) {
                        Auto32k.mc.player.closeScreen();
                    }
                    this.s(this.syr = false);
                    return;
                }
                ++n;
            }
        }
        if (this.syf && Auto32k.mc.player.openContainer != null) {
            int n2 = 219;
            if (n2 <= 234) {
                if (b.s(Item.getItemById(n2)) != -1) {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.inventoryContainer.windowId, b.s(Item.getItemById(n2)), 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    this.syf = false;
                    Auto32k.mc.player.closeScreen();
                    this.yb();
                    return;
                }
                ++n2;
            }
        }
    }
    
    private void yf() {
        if (b.xx() == -1) {
            h.o("No blocks found in hotbar!", "red");
            this.s(false);
            return;
        }
        if (b.s(Item.getItemById(23)) == -1) {
            h.o("No dispenser found in hotbar!", "red");
            this.s(false);
            return;
        }
        if (b.s(Item.getItemById(152)) == -1) {
            h.o("No redstone block found in hotbar!", "red");
            this.s(false);
            return;
        }
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(154));
        switch (this.syw = (MathHelper.floor(Auto32k.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3)) {
            case 0: {
                final BlockPos blockPos = new BlockPos((Vec3i)this.blockPos.add(0, 0, 1));
            }
            case 1: {
                final BlockPos blockPos2 = new BlockPos((Vec3i)this.blockPos.add(-1, 0, 0));
            }
            case 2: {
                final BlockPos blockPos3 = new BlockPos((Vec3i)this.blockPos.add(0, 0, -1));
                break;
            }
        }
        final BlockPos blockPos4 = new BlockPos((Vec3i)this.blockPos.add(1, 0, 0));
        Auto32k.mc.player.inventory.currentItem = b.xx();
        b.y(blockPos4);
        switch (this.syw) {
            case 0: {
                final BlockPos blockPos5 = new BlockPos((Vec3i)this.blockPos.add(0, 1, 1));
            }
            case 1: {
                final BlockPos blockPos6 = new BlockPos((Vec3i)this.blockPos.add(-1, 1, 0));
            }
            case 2: {
                final BlockPos blockPos7 = new BlockPos((Vec3i)this.blockPos.add(0, 1, -1));
                break;
            }
        }
        final BlockPos blockPos8 = new BlockPos((Vec3i)this.blockPos.add(1, 1, 0));
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(23));
        b.y(blockPos8);
        Auto32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos8, EnumFacing.UP, EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
        this.syf = true;
    }
    
    private void yb() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Auto32k() {
        super();
        this.syi = (m<y$s>)new u("Mode", this, y$s.svc);
        this.syp = (m<Boolean>)new p("SecretClose", this, false);
        this.syr = false;
        this.syf = false;
    }
    
    public void v() {
        if (Auto32k.mc.objectMouseOver == null || Auto32k.mc.objectMouseOver.sideHit == null) {
            return;
        }
        if (!this.yr()) {
            this.s(false);
        }
    }
    
    private boolean yr() {
        this.blockPos = null;
        if (Auto32k.mc.objectMouseOver == null || Auto32k.mc.objectMouseOver.sideHit == null) {
            return false;
        }
        this.blockPos = Auto32k.mc.objectMouseOver.getBlockPos().offset(Auto32k.mc.objectMouseOver.sideHit);
        if (b.s(Item.getItemById(154)) == -1) {
            h.o("A hopper was not found in your hotbar!", "red");
            this.s(false);
            return false;
        }
        int n = 219;
        if (n <= 234) {
            if (b.s(Item.getItemById(n)) != -1) {}
            if (n == 234) {
                h.o("A shulker was not found in your hotbar!", "red");
                this.s(false);
                return false;
            }
            ++n;
        }
        if (this.syi.yv() == y$s.svz) {
            this.yw();
        }
        if (this.syi.yv() == y$s.svc) {
            this.yf();
        }
        return true;
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        if (this.syr || !x(Auto32k.mc.player.inventory.getCurrentItem())) {
            int n = 0;
            if (n < Auto32k.mc.player.openContainer.inventorySlots.size()) {
                if (x(((Slot)Auto32k.mc.player.openContainer.inventorySlots.get(n)).getStack())) {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.openContainer.windowId, n, 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    int currentItem = 0;
                    if (currentItem < 9) {
                        if (x(Auto32k.mc.player.inventory.getStackInSlot(currentItem))) {
                            Auto32k.mc.player.inventory.currentItem = currentItem;
                        }
                        ++currentItem;
                    }
                    if (this.syp.yv()) {
                        Auto32k.mc.player.closeScreen();
                    }
                    this.s(this.syr = false);
                    return;
                }
                ++n;
            }
        }
        if (this.syf && Auto32k.mc.player.openContainer != null) {
            int n2 = 219;
            if (n2 <= 234) {
                if (b.s(Item.getItemById(n2)) != -1) {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.inventoryContainer.windowId, b.s(Item.getItemById(n2)), 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    this.syf = false;
                    Auto32k.mc.player.closeScreen();
                    this.yb();
                    return;
                }
                ++n2;
            }
        }
    }
    
    private void yf() {
        if (b.xx() == -1) {
            h.o("No blocks found in hotbar!", "red");
            this.s(false);
            return;
        }
        if (b.s(Item.getItemById(23)) == -1) {
            h.o("No dispenser found in hotbar!", "red");
            this.s(false);
            return;
        }
        if (b.s(Item.getItemById(152)) == -1) {
            h.o("No redstone block found in hotbar!", "red");
            this.s(false);
            return;
        }
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(154));
        switch (this.syw = (MathHelper.floor(Auto32k.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3)) {
            case 0: {
                final BlockPos blockPos = new BlockPos((Vec3i)this.blockPos.add(0, 0, 1));
            }
            case 1: {
                final BlockPos blockPos2 = new BlockPos((Vec3i)this.blockPos.add(-1, 0, 0));
            }
            case 2: {
                final BlockPos blockPos3 = new BlockPos((Vec3i)this.blockPos.add(0, 0, -1));
                break;
            }
        }
        final BlockPos blockPos4 = new BlockPos((Vec3i)this.blockPos.add(1, 0, 0));
        Auto32k.mc.player.inventory.currentItem = b.xx();
        b.y(blockPos4);
        switch (this.syw) {
            case 0: {
                final BlockPos blockPos5 = new BlockPos((Vec3i)this.blockPos.add(0, 1, 1));
            }
            case 1: {
                final BlockPos blockPos6 = new BlockPos((Vec3i)this.blockPos.add(-1, 1, 0));
            }
            case 2: {
                final BlockPos blockPos7 = new BlockPos((Vec3i)this.blockPos.add(0, 1, -1));
                break;
            }
        }
        final BlockPos blockPos8 = new BlockPos((Vec3i)this.blockPos.add(1, 1, 0));
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(23));
        b.y(blockPos8);
        Auto32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos8, EnumFacing.UP, EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
        this.syf = true;
    }
    
    private void yb() {
        final BlockPos blockPos3;
        switch (this.syw) {
            case 0: {
                final BlockPos blockPos = new BlockPos((Vec3i)this.blockPos.add(0, 2, 1));
            }
            case 1: {
                final BlockPos blockPos2 = new BlockPos((Vec3i)this.blockPos.add(-1, 2, 0));
            }
            case 2:
                blockPos3 = new BlockPos((Vec3i)this.blockPos.add(0, 2, -1));
                break;
        }
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(152));
        b.y(blockPos3);
        this.yg();
    }
    
    private void yw() {
        Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(154));
        b.y(this.blockPos);
        int n = 219;
        if (n <= 234) {
            if (b.s(Item.getItemById(n)) != -1) {
                Auto32k.mc.player.inventory.currentItem = b.s(Item.getItemById(n));
            }
            ++n;
        }
        b.y(new BlockPos(this.blockPos.getX(), this.blockPos.getY() + 1, this.blockPos.getZ()));
        this.yg();
    }
    
    private void yg() {
        if (this.syp.yv()) {
            g.d("Secret Close", false);
            g.d("Secret Close", true);
        }
        Auto32k.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.blockPos, EnumFacing.UP, EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
        this.syr = true;
    }
    
    static boolean x(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        if (itemStack.getEnchantmentTagList().getTagType() == 0) {
            return false;
        }
        final NBTTagList list = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        int n = 0;
        if (n < list.tagCount()) {
            final NBTTagCompound compoundTag = list.getCompoundTagAt(n);
            if (compoundTag.getInteger("id") == 16) {
                if (compoundTag.getInteger("lvl") >= 16) {
                    return true;
                }
            }
            else {
                ++n;
            }
        }
        return false;
    }
}
