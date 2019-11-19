package r.k.n;

import r.k.b.c.h;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import r.k.r.h.p;
import net.minecraftforge.common.MinecraftForge;

public class y extends c
{
    private int spm;
    public static final int sph;
    public static final boolean srd;
    
    public y() {
        super("Teleport Finder");
        this.spm = 0;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    private void x(final p p) {
        if (p.packet instanceof SPacketPlayerPosLook) {
            this.spm = ((SPacketPlayerPosLook)p.packet).getTeleportId();
        }
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length < 1) {
            return false;
        }
        if (array[0].equalsIgnoreCase("id")) {
            h.sj("ID: " + this.spm);
            return true;
        }
        return false;
    }
    
    @Override
    public String k() {
        return null;
    }
}
