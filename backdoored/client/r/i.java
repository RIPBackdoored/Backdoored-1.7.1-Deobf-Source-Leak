package r.k.r;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class i extends cz
{
    public final IBlockState iBlockState;
    public final IBlockAccess iBlockAccess;
    public final BlockPos blockPos;
    public final EnumFacing enumFacing;
    public static final int pi;
    public static final boolean pp;
    
    public i(final IBlockState iBlockState, final IBlockAccess iBlockAccess, final BlockPos blockPos, final EnumFacing enumFacing) {
        super();
        this.iBlockState = iBlockState;
        this.iBlockAccess = iBlockAccess;
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
    }
    
    public boolean isCancelable() {
        return true;
    }
}
