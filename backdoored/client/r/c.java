package r.k.r;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;

public class c extends cz
{
    public IBlockState iBlockState;
    public IBlockAccess iBlockAccess;
    public BlockPos blockPos;
    public CallbackInfoReturnable<AxisAlignedBB> sg;
    public PropertyDirection propertyDirection;
    public static final int sz;
    
    public c(final IBlockState iBlockState, final IBlockAccess iBlockAccess, final BlockPos blockPos, final PropertyDirection propertyDirection, final CallbackInfoReturnable<AxisAlignedBB> sg) {
        super();
        this.iBlockState = iBlockState;
        this.iBlockAccess = iBlockAccess;
        this.blockPos = blockPos;
        this.propertyDirection = propertyDirection;
        this.sg = sg;
    }
}
