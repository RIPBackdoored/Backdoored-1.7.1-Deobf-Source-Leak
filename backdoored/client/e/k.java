package r.k.e;

import r.k.s.o;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import r.k.h;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

public class k extends GuiMainMenu
{
    public static final boolean slv;
    public static final int slj;
    public static final boolean sle;
    
    public k() {
        super();
    }
    
    public void func_73866_w_() {
        this.buttonList.add(new GuiButton(62, 2, 2, 98, 20, "Login"));
    }
    
    protected void func_146284_a(final GuiButton guiButton) throws IOException {
        System.out.println("Button pressed: " + guiButton.displayString);
        if (guiButton.displayString.equals("Login")) {
            h.mc.displayGuiScreen((GuiScreen)new c((GuiScreen)this));
        }
        super.actionPerformed(guiButton);
    }
    
    public void func_73863_a(final int n, final int n2, final float n3) {
        String s = "[ONLINE]";
        Color color = Color.GREEN;
        if (!o.sn()) {
            s = "[OFFLINE]";
            color = Color.RED;
        }
        String s2 = "[ONLINE]";
        Color color2 = Color.GREEN;
        if (!o.si()) {
            s2 = "[OFFLINE]";
            color2 = Color.RED;
        }
        this.mc.fontRenderer.drawString("Auth Server:     " + s, 2.0f, 25.0f, color.getRGB(), true);
        this.mc.fontRenderer.drawString("Session Server: " + s2, 2.0f, (float)(25 + this.mc.fontRenderer.FONT_HEIGHT + 2), color2.getRGB(), true);
    }
}
