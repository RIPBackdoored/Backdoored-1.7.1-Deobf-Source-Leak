package r.k.d.m.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import r.k.b.c.h;
import r.k.b.b;
import r.k.d.o.h.p;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Scaffold", description = "Automatically bridges for you", category = y.PLAYER)
@g$i(name = "Scaffold", description = "Automatically bridges for you", category = y.PLAYER)
public class Scaffold extends g
{
    private m<Integer> ldf;
    private m<Boolean> ldb;
    private m<Boolean> ldw;
    public static final boolean ldg;
    public static final int ldu;
    public static final boolean ldz;
    private m<Integer> ldf;
    private m<Boolean> ldb;
    private m<Boolean> ldw;
    public static final boolean ldg;
    public static final int ldu;
    public static final boolean ldz;
    
    public Scaffold() {
        super();
        this.ldf = (m<Integer>)new x("Radius", this, 0, 0, 2);
        this.ldb = (m<Boolean>)new p("Down", this, true);
        this.ldw = (m<Boolean>)new p("Tower", this, true);
    }
    
    public void j() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Scaffold() {
        super();
        this.ldf = (m<Integer>)new x("Radius", this, 0, 0, 2);
        this.ldb = (m<Boolean>)new p("Down", this, true);
        this.ldw = (m<Boolean>)new p("Tower", this, true);
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final int currentItem = Scaffold.mc.player.inventory.currentItem;
        final int xx = b.xx();
        if (xx != -1) {
            Scaffold.mc.player.inventory.currentItem = xx;
        }
        h.o("No blocks found in hotbar!", "red");
        this.s(false);
    }
    
    @SubscribeEvent
    public void d(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
    }
    
    @SubscribeEvent
    public void d(final LivingEvent.LivingJumpEvent livingJumpEvent) {
        if (this.lo() && this.ldw.yv()) {
            final EntityPlayerSP player = Scaffold.mc.player;
            player.motionY += 0.1;
        }
    }
}
