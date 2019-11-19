package r.k.d.m.combat;

import r.k.b.o.h;
import net.minecraft.init.Items;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Log", description = "Automaticall Disconnect", category = y.COMBAT)
public class AutoLog extends g
{
    private final m<Integer> spw;
    public static final int spg;
    public static final boolean spu;
    
    public AutoLog() {
        super();
        this.spw = (m<Integer>)new x("Min Totems", this, 0, 0, 5);
    }
    
    public void j() {
        if (this.lo() && r.k.b.o.y.s(Items.TOTEM_OF_UNDYING, true) <= this.spw.yv()) {
            h.lu();
        }
    }
}
