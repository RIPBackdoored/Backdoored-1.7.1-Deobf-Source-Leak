package r.k.d.m.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import r.k.b.r;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "InvisDetect", description = "Can help locate people in entity god mode", category = y.COMBAT)
public class InvisDetect extends g
{
    public static final int rx;
    public static final boolean rk;
    
    public InvisDetect() {
        super();
    }
    
    @SubscribeEvent
    public void d(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        if (playSoundAtEntityEvent.getEntity() == null) {
            return;
        }
        if (playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_PIG_STEP) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_HORSE_STEP) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_HORSE_STEP_WOOD) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_LLAMA_STEP)) {
            h.sj("Invis PlayerPreviewElement at: " + r.d(playSoundAtEntityEvent.getEntity().getPositionVector(), new boolean[0]));
        }
    }
}
