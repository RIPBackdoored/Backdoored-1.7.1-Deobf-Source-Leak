package r.k.p.m.y;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntity;
import r.k.b.k;
import java.util.function.Predicate;
import \u0000r.\u0000k.\u0000p.\u0000m.\u0000y.\u0000f;
import r.k.p.c.h;

public class f extends h
{
    public static final int sjn;
    public static final boolean sji;
    
    public f() {
        super("Chest Count");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        final String string = f.mc.world.loadedTileEntityList.stream().filter(\u0000f::s).count() + " chests";
        k.d(string, this.z().slo, this.z().slt);
        this.c().slo = f.mc.fontRenderer.getStringWidth(string);
    }
    
    private static /* synthetic */ boolean s(final TileEntity tileEntity) {
        return tileEntity instanceof TileEntityChest;
    }
}
