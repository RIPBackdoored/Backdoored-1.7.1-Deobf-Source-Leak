package r.k.d.m.player;

import r.k.b.c.h;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "No Break Delay", description = "like fast place but for breaking", category = y.PLAYER)
public class NoBreakDelay extends g
{
    public static final boolean sst;
    public static final int ssn;
    public static final boolean ssi;
    
    public NoBreakDelay() {
        super();
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        try {
            ObfuscationReflectionHelper.setPrivateValue((Class)PlayerControllerMP.class, (Object)NoBreakDelay.mc.playerController, (Object)0, new String[] { "blockHitDelay", "field_78781_i" });
            throw null;
        }
        catch (Exception ex) {
            this.s(false);
            h.sj("Disabled fastplace due to error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
