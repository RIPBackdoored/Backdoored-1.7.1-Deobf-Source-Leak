package r.k.d.m.render;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Hitbox ESP", description = "See outlines of players through walls", category = y.RENDER, defaultOn = true, defaultIsVisible = false)
@g$i(name = "Hitbox ESP", description = "See outlines of players through walls", category = y.RENDER, defaultOn = true, defaultIsVisible = false)
public class HitboxESP extends g
{
    private m<Boolean> sgh;
    private m<Boolean> sud;
    public static final boolean sus;
    public static final int sul;
    public static final boolean suy;
    private m<Boolean> sgh;
    private m<Boolean> sud;
    public static final boolean sus;
    public static final int sul;
    public static final boolean suy;
    
    public HitboxESP() {
        this.sgh = (m<Boolean>)new p("Show friends hitbox", this, true);
        this.sud = (m<Boolean>)new p("Show others hitbox", this, false);
    }
    
    public void m() {
        if (HitboxESP.mc.world.playerEntities.size() <= 0) {
            return;
        }
        r.k.b.u.p.d(0.0f, 0.0f, 0.0f, 1.0f);
        this.xr();
        GL11.glColor4f(0.0f, 255.0f, 0.0f, 1.0f);
        this.xp();
        r.k.b.u.p.xn();
    }
    
    private void xp() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public HitboxESP() {
        this.sgh = (m<Boolean>)new p("Show friends hitbox", this, true);
        this.sud = (m<Boolean>)new p("Show others hitbox", this, false);
    }
    
    public void m() {
        if (HitboxESP.mc.world.playerEntities.size() <= 0) {
            return;
        }
        r.k.b.u.p.d(0.0f, 0.0f, 0.0f, 1.0f);
        this.xr();
        GL11.glColor4f(0.0f, 255.0f, 0.0f, 1.0f);
        this.xp();
        r.k.b.u.p.xn();
    }
    
    private void xp() {
        if (this.sgh.yv()) {
            final Iterator<EntityPlayer> iterator = HitboxESP.mc.world.playerEntities.iterator();
            if (iterator.hasNext()) {
                final EntityPlayer entityPlayer = iterator.next();
                if (r.k.b.i.p.l(entityPlayer) && !entityPlayer.getUniqueID().equals(HitboxESP.mc.player.getUniqueID())) {
                    entityPlayer.getEntityBoundingBox();
                }
            }
        }
    }
    
    private void xr() {
        if (this.sud.yv()) {
            final Iterator<EntityPlayer> iterator = HitboxESP.mc.world.playerEntities.iterator();
            if (iterator.hasNext()) {
                final EntityPlayer entityPlayer = iterator.next();
                if (!r.k.b.i.p.l(entityPlayer) && !entityPlayer.getUniqueID().equals(HitboxESP.mc.player.getUniqueID())) {
                    r.k.b.u.p.l(entityPlayer.getEntityBoundingBox());
                }
            }
        }
    }
}
