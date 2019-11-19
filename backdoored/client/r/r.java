package r.k.r;

import net.minecraft.util.math.BlockPos;

public class r extends cz
{
    public final BlockPos blockPos;
    public static final int yh;
    public static final boolean xd;
    
    public r(final BlockPos blockPos) {
        super();
        this.blockPos = blockPos;
    }
    
    public boolean isCancelable() {
        return true;
    }
}
