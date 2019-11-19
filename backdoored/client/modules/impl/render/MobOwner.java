package r.k.d.m.render;

import java.util.Iterator;
import r.k.s.o;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.Entity;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Mob Owner", description = "Show you owners of mobs", category = y.RENDER)
public class MobOwner extends g
{
    public static final boolean sux;
    public static final int suk;
    public static final boolean suq;
    
    public MobOwner() {
        super();
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final Iterator<Entity> iterator = MobOwner.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if (entity instanceof EntityTameable) {
                final EntityTameable entityTameable = (EntityTameable)entity;
                if (entityTameable.isTamed() && entityTameable.getOwner() != null) {
                    entityTameable.setAlwaysRenderNameTag(true);
                    entityTameable.setCustomNameTag("Owner: " + entityTameable.getOwner().getDisplayName().getFormattedText());
                }
            }
            if (entity instanceof AbstractHorse) {
                final AbstractHorse abstractHorse = (AbstractHorse)entity;
                if (abstractHorse.isTame() && abstractHorse.getOwnerUniqueId() != null) {
                    abstractHorse.setAlwaysRenderNameTag(true);
                    abstractHorse.setCustomNameTag("Owner: " + o.n(abstractHorse.getOwnerUniqueId().toString()));
                }
            }
        }
    }
    
    public void t() {
        final Iterator<Entity> iterator = MobOwner.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if (!(entity instanceof EntityTameable)) {
                if (!(entity instanceof AbstractHorse)) {
                    return;
                }
            }
            try {
                entity.setAlwaysRenderNameTag(false);
            }
            catch (Exception ex) {}
        }
    }
}
