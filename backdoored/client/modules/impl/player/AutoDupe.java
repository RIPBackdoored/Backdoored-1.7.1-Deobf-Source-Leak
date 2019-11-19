package r.k.d.m.player;

import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Dupe", description = "", category = y.PLAYER)
public class AutoDupe extends g
{
    private boolean sdv;
    public static final int sdj;
    public static final boolean sde;
    
    public AutoDupe() {
        this.sdv = false;
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        if (!this.sdv) {
            AutoDupe.mc.player.sendChatMessage(".vanish dismount");
            this.sdv = true;
        }
    }
}
