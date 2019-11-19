package r.k.r.h;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import r.k.r.cz;

@Cancelable
public class h extends cz
{
    public Packet packet;
    public static final int snr;
    public static final boolean snf;
    
    public h(final Packet<?> packet) {
        super();
        this.packet = packet;
    }
    
    public boolean isCancelable() {
        return true;
    }
}
