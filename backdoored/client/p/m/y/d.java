package r.k.p.m.y;

import r.k.b.o.y;
import r.k.b.o.y$i;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import r.k.p.m.m;
import java.awt.Color;
import r.k.p.c.h;

public class d extends h
{
    private static final Color saf;
    public static final int sab;
    public static final boolean saw;
    
    public d() {
        super("Crystal Counter");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        this.c().slo = 18;
        this.c().slt = 18;
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        if (!(d.mc.currentScreen instanceof m)) {
            r.k.b.u.m.l(this.z().slo, this.z().slt, this.z().slo + this.c().slo, this.z().slt + this.c().slt, d.saf.getRGB());
        }
        final ItemStack itemStack = new ItemStack(Items.END_CRYSTAL);
        itemStack.setCount(r.k.b.o.y.d(Items.END_CRYSTAL, true, y$i.xl));
        r.k.b.u.m.d(this.z().slo, this.z().slt, itemStack);
    }
    
    static {
        saf = new Color(64, 64, 64, 127);
    }
}
