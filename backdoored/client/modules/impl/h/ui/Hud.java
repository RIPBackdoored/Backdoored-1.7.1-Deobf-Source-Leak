package r.k.d.m.h.ui;

import r.k.p.m.p;
import org.lwjgl.input.Mouse;
import r.k.p.m.m;
import r.k.d.m.h.u.x;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Hud", description = "Hud Overlay", category = y.UI, defaultOn = true, defaultIsVisible = false)
public class Hud extends g
{
    public static x shf;
    public static final int shb;
    public static final boolean shw;
    
    public Hud() {
        Hud.shf = (x)this;
    }
    
    public void a() {
        if (this.lo() && !Hud.mc.gameSettings.showDebugInfo && !(Hud.mc.currentScreen instanceof m)) {
            p.s(Mouse.getX(), Mouse.getY(), Hud.mc.getRenderPartialTicks());
        }
    }
}
