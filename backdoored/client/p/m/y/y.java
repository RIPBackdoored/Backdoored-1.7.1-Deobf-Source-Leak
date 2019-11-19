package r.k.p.m.y;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import r.k.p.m.m;
import java.awt.Color;
import r.k.p.c.h;

public class y extends h
{
    private static final Color sum;
    public static final int suh;
    public static final boolean szd;
    
    public y() {
        super("Totem Counter");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        this.c().slo = 18;
        this.c().slt = 18;
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        if (!(y.mc.currentScreen instanceof m)) {
            r.k.b.u.m.l(this.z().slo, this.z().slt, this.z().slo + this.c().slo, this.z().slt + this.c().slt, y.sum.getRGB());
        }
        final ItemStack itemStack = new ItemStack(Items.TOTEM_OF_UNDYING);
        itemStack.setCount(r.k.b.o.y.s(Items.TOTEM_OF_UNDYING, true));
        r.k.b.u.m.d(this.z().slo, this.z().slt, itemStack);
    }
    
    static {
        sum = new Color(64, 64, 64, 127);
    }
}
