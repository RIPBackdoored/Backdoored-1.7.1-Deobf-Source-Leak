package r.k.d.m.ui;

import java.util.Iterator;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import r.k.i.o;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Radar", description = "See nearby players", category = y.UI)
public class Radar extends g
{
    private m<Integer> sxn;
    private m<Integer> sxi;
    private m<Integer> sxp;
    public static final boolean sxr;
    public static final int sxf;
    public static final boolean sxb;
    
    public Radar() {
        super();
        this.sxn = (m<Integer>)new x("x", this, 0, 0, Radar.mc.displayWidth + 50);
        this.sxp = (m<Integer>)new x("Text Height", this, 20, 1, 50);
    }
    
    public void a() {
        if (!this.lo()) {
            return;
        }
        final int n = 0;
        final int font_HEIGHT = o.fontRenderer.FONT_HEIGHT;
        o.fontRenderer.FONT_HEIGHT = this.sxp.yv();
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)Radar.mc.world.playerEntities.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer = iterator.next();
            if (!entityPlayer.equals((Object)Radar.mc.player)) {
                o.fontRenderer.drawString(entityPlayer.getDisplayNameString(), (int)this.sxn.yv(), this.sxi.yv() + n, Color.WHITE.getRGB());
                final int n2 = n + (o.fontRenderer.FONT_HEIGHT + 2);
            }
        }
        o.fontRenderer.FONT_HEIGHT = font_HEIGHT;
    }
}
