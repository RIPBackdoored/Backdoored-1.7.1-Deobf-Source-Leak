package r.k.d.m.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import r.k.r.h.p;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Godmode Crystal Remover", description = "fixes crystals not removing when in god mode", category = y.COMBAT)
public class GodmodeCrystalRemover extends g
{
    public static final boolean szh;
    public static final int scd;
    public static final boolean scs;
    
    public GodmodeCrystalRemover() {
        super();
    }
    
    @SubscribeEvent
    public void y(final p p) {
        if (this.lo() && p.packet instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)p.packet;
            if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                final Iterator<Entity> iterator = GodmodeCrystalRemover.mc.world.loadedEntityList.iterator();
                if (iterator.hasNext()) {
                    final Entity entity = iterator.next();
                    if (entity instanceof EntityEnderCrystal && entity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) <= 6.0) {
                        entity.setDead();
                    }
                }
            }
        }
    }
}
