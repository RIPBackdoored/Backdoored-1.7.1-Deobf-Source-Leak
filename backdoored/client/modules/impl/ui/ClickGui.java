package r.k.d.m.ui;

import net.minecraft.client.gui.GuiScreen;
import r.k.c.n;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "ClickGui", description = "Backdoored's main GUI", category = y.UI, defaultIsVisible = false, defaultBind = "SEMICOLON")
public class ClickGui extends g
{
    public static final int smg;
    public static final boolean smu;
    
    public ClickGui() {
        super();
    }
    
    public void v() {
        ClickGui.mc.displayGuiScreen((GuiScreen)new n());
        this.s(false);
    }
}
