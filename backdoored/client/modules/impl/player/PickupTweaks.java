package r.k.d.m.player;

import java.util.Iterator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Pickup Tweaks", description = "Improve item picking up", category = y.PLAYER)
public class PickupTweaks extends g
{
    public static final boolean ug;
    public static final int uu;
    public static final boolean uz;
    
    public PickupTweaks() {
        super();
    }
    
    public void j() {
        if (this.lo()) {
            final Iterator<Entity> iterator = PickupTweaks.mc.world.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (entity instanceof EntityItem) {
                    final EntityItem entityItem = (EntityItem)entity;
                    entity.setCustomNameTag(String.valueOf(entityItem.getAge() / entityItem.lifespan * 100.0));
                }
            }
        }
    }
}
