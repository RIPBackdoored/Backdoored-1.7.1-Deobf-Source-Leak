package r.k.d.m.misc;

import r.k.b.c.h;
import java.util.Iterator;
import r.k.d.m.b.b;
import r.k.b.r;
import java.util.Collection;
import r.k.d.o.h.u;
import java.util.ArrayList;
import r.k.b.k$h;
import r.k.d.o.m;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Visual Range", description = "Get notified when someone enters your render distance", category = y.MISC)
public class VisualRange extends g
{
    private List<EntityPlayer> oj;
    private m<k$h> oe;
    private m<i$s> oo;
    public static final boolean ot;
    public static final int on;
    public static final boolean oi;
    
    public VisualRange() {
        this.oj = new ArrayList<EntityPlayer>();
        this.oe = (m<k$h>)new u("Color", this, k$h.srk);
        this.oo = (m<i$s>)new u("Mode", this, i$s.sgy);
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final ArrayList<EntityPlayer> list = (ArrayList<EntityPlayer>)new ArrayList<Object>(VisualRange.mc.world.playerEntities);
        list.removeAll(this.oj);
        final Iterator<Object> iterator = list.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer = iterator.next();
            if (VisualRange.mc.world.playerEntities.contains(entityPlayer)) {
                this.d("PlayerPreviewElement '" + entityPlayer.getDisplayNameString() + "' entered your render distance at " + r.d(entityPlayer.getPositionVector(), new boolean[0]), this.oe.yv().toString());
                b.sqw.s(entityPlayer);
            }
            if (this.oj.contains(entityPlayer)) {
                this.d("PlayerPreviewElement '" + entityPlayer.getDisplayNameString() + "' left your render distance at " + r.d(entityPlayer.getPositionVector(), new boolean[0]), this.oe.yv().toString());
            }
            throw null;
        }
        this.oj = (List<EntityPlayer>)VisualRange.mc.world.playerEntities;
    }
    
    private void d(final String s, final String s2) {
        if (this.oo.yv() == i$s.sgy) {
            h.o(s, s2);
        }
        VisualRange.mc.player.sendChatMessage(s);
    }
}
