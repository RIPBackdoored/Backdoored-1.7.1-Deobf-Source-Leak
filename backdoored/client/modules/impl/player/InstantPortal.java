package r.k.d.m.player;

import r.k.r.t;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.q;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Instant Portal", description = "ez pz", category = y.PLAYER)
public class InstantPortal extends g
{
    private m<Integer> sjh;
    private m<Integer> sed;
    public static final int ses;
    public static final boolean sel;
    
    public InstantPortal() {
        super();
        this.sjh = (m<Integer>)new x("Cooldown", this, 2, 0, 10);
        this.sed = (m<Integer>)new x("Wait Time", this, 2, 0, 80);
    }
    
    @SubscribeEvent
    public void d(final q q) {
        if (this.lo() && (q.entityPlayer == null || q.entityPlayer.getUniqueID().equals(InstantPortal.mc.player.getUniqueID()))) {
            q.rq = this.sjh.yv();
        }
    }
    
    @SubscribeEvent
    public void d(final t t) {
        if (this.lo() && (t.entity == null || t.entity.getUniqueID().equals(InstantPortal.mc.player.getUniqueID()))) {
            t.fs = this.sed.yv();
        }
    }
}
