package r.k.d.m.t;

import net.minecraft.util.EnumFacing;

class g$s
{
    static final /* synthetic */ int[] syj;
    public static final boolean sye;
    public static final int syo;
    public static final boolean syt;
    
    static {
        syj = new int[EnumFacing.Axis.values().length];
        try {
            g$s.syj[EnumFacing.Axis.X.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            try {
                g$s.syj[EnumFacing.Axis.Z.ordinal()] = 2;
                throw null;
            }
            catch (NoSuchFieldError noSuchFieldError2) {}
        }
    }
}
