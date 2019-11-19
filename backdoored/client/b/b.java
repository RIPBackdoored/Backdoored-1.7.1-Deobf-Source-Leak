package r.k.b;

import net.minecraft.item.ItemBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import r.k.h;

public class b implements h
{
    public static final boolean str;
    public static final int stf;
    public static final boolean stb;
    
    public b() {
        super();
    }
    
    public static void y(final BlockPos blockPos) {
        d(EnumHand.MAIN_HAND, blockPos);
    }
    
    public static void d(final EnumHand enumHand, final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(b.mc.player.posX, b.mc.player.posY + b.mc.player.getEyeHeight(), b.mc.player.posZ);
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        final int n = 0;
        if (n < length) {
            final EnumFacing enumFacing = values[n];
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (!b.mc.world.getBlockState(offset).getBlock().canCollideCheck(b.mc.world.getBlockState(offset), false)) {}
            final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
            if (vec3d.squareDistanceTo(add) > 18.0625) {}
            final double n2 = add.x - vec3d.x;
            final double n3 = add.y - vec3d.y;
            final double n4 = add.z - vec3d.z;
            final float[] array = { b.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(n4, n2)) - 90.0f - b.mc.player.rotationYaw), b.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(n3, Math.sqrt(n2 * n2 + n4 * n4)))) - b.mc.player.rotationPitch) };
            b.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(array[0], array[1], b.mc.player.onGround));
            b.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)b.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b.mc.playerController.processRightClickBlock(b.mc.player, b.mc.world, offset, opposite, add, enumHand);
            b.mc.player.swingArm(enumHand);
            b.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)b.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public static int s(final Block block) {
        return s(new ItemStack(block).getItem());
    }
    
    public static int s(final Item item) {
        try {
            int n = 0;
            if (n < 9) {
                if (item == b.mc.player.inventory.getStackInSlot(n).getItem()) {
                    return n;
                }
                ++n;
            }
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public static double[] d(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static void d(final float n, final float rotationPitch) {
        b.mc.player.rotationPitch = rotationPitch;
    }
    
    public static void d(final double[] array) {
        b.mc.player.rotationYaw = (float)array[0];
        b.mc.player.rotationPitch = (float)array[1];
    }
    
    public static void x(final BlockPos blockPos) {
        d(d(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (EntityPlayer)b.mc.player));
    }
    
    public static BlockPos d(final EntityPlayer entityPlayer, final int n, final int n2, final int n3) {
        final int[] array = { (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ };
        if (entityPlayer.posX < 0.0 && entityPlayer.posZ < 0.0) {
            final BlockPos blockPos = new BlockPos(array[0] + n - 1, array[1] + n2, array[2] + n3 - 1);
        }
        if (entityPlayer.posZ > 0.0) {
            final BlockPos blockPos2 = new BlockPos(array[0] + n - 1, array[1] + n2, array[2] + n3);
        }
        if (entityPlayer.posX > 0.0 && entityPlayer.posZ < 0.0) {
            final BlockPos blockPos3 = new BlockPos(array[0] + n, array[1] + n2, array[2] + n3 - 1);
        }
        return new BlockPos(array[0] + n, array[1] + n2, array[2] + n3);
    }
    
    public static int xx() {
        int n = 0;
        if (n < 9) {
            if (b.mc.player.inventory.getStackInSlot(n) != ItemStack.EMPTY && b.mc.player.inventory.getStackInSlot(n).getItem() instanceof ItemBlock) {
                if (!Block.getBlockFromItem(b.mc.player.inventory.getStackInSlot(n).getItem()).getDefaultState().isFullBlock()) {}
                return n;
            }
            ++n;
        }
        return -1;
    }
}
