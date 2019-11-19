package r.k.d.m.misc;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Highway Bot", description = "Automate highway building", category = y.MISC)
public class HighwayBot extends g
{
    public static final int scu;
    public static final boolean scz;
    
    public HighwayBot() {
        super();
    }
    
    public void j() {
        final BlockPos blockPos = new BlockPos(HighwayBot.mc.player.posX, HighwayBot.mc.player.posY, HighwayBot.mc.player.posZ);
        if (xc() == g$x.ldo) {
            final BlockPos[] array = { blockPos.add(1, 0, -1), blockPos.add(1, 0, 0), blockPos.add(1, 0, 1), blockPos.add(1, 1, -1), blockPos.add(1, 1, 0), blockPos.add(1, 1, 1), blockPos.add(1, 2, -1), blockPos.add(1, 2, 0), blockPos.add(1, 2, 1) };
            final BlockPos[] array2 = { blockPos.add(1, -1, -1), blockPos.add(1, -1, 0), blockPos.add(1, -1, 1), blockPos.add(1, 0, -2), blockPos.add(1, 0, 2) };
        }
    }
    
    public static g$x xc() {
        final EnumFacing.Axis axis = HighwayBot.mc.player.getHorizontalFacing().getAxis();
        final EnumFacing.AxisDirection axisDirection = HighwayBot.mc.player.getHorizontalFacing().getAxisDirection();
        switch (g$s.syj[axis.ordinal()]) {
            case 1:
                if (axisDirection == EnumFacing.AxisDirection.POSITIVE) {
                    return g$x.ldo;
                }
                return g$x.ldn;
            case 2:
                if (axisDirection == EnumFacing.AxisDirection.POSITIVE) {
                    return g$x.ldt;
                }
                return g$x.ldi;
            default:
                return g$x.ldo;
        }
    }
}
