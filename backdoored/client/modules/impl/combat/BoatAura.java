package r.k.d.m.combat;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.Entity;
import r.k.d.o.h.p;
import r.k.d.o.h.i.s;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Boat Aura", description = "Attack nearby boats", category = y.COMBAT)
public class BoatAura extends g
{
    private final m<Double> stm;
    private final m<Boolean> sth;
    private final m<Boolean> snd;
    public static final boolean sns;
    public static final int snl;
    public static final boolean sny;
    
    public BoatAura() {
        super();
        this.stm = (m<Double>)new s("Range", this, 5.0, 0.0, 10.0);
        this.sth = (m<Boolean>)new p("Boats", this, true);
        this.snd = (m<Boolean>)new p("Minecarts", this, true);
    }
    
    public void j() {
        if (this.lo()) {
            final Iterator<Entity> iterator = BoatAura.mc.world.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (!entity.getUniqueID().equals(BoatAura.mc.player.getUniqueID()) && ((entity instanceof EntityBoat && this.sth.yv()) || (entity instanceof EntityMinecart && this.snd.yv())) && BoatAura.mc.player.getDistance(entity) <= this.stm.yv()) {
                    BoatAura.mc.playerController.attackEntity((EntityPlayer)BoatAura.mc.player, entity);
                }
            }
        }
    }
}
