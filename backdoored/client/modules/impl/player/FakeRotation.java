package r.k.d.m.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.network.play.client.CPacketPlayer;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Fake Rotation", description = "Fake your rotation", category = y.PLAYER)
public class FakeRotation extends g
{
    public static final boolean smz;
    public static final int smc;
    public static final boolean sma;
    
    public FakeRotation() {
        super();
    }
    
    @SubscribeEvent
    public void s(final r.k.r.h.y y) {
        if (!this.lo()) {
            return;
        }
        if (y.packet instanceof CPacketPlayer) {
            try {
                ObfuscationReflectionHelper.setPrivateValue((Class)CPacketPlayer.class, (Object)y.packet, (Object)(-90), new String[] { "pitch", "field_149473_f" });
            }
            catch (Exception ex) {
                h.o("Disabled fake rotation due to error: " + ex.toString(), "red");
                ex.printStackTrace();
            }
        }
    }
}
