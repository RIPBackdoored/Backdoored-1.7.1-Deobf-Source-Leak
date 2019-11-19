package r.k.d.m.h.u;

import r.k.h;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class s extends Gui
{
    private static Minecraft mc;
    private ResourceLocation resourceLocation;
    private ResourceLocation resourceLocation;
    private static final String wx;
    private static int wk;
    public static final int wq;
    public static final boolean wv;
    
    public s() {
        super();
        this.resourceLocation = new ResourceLocation("backdoored", "textures/dev-donor-client.png");
        this.resourceLocation = new ResourceLocation("backdoored", "textures/backdoored-standard-client.png");
    }
    
    static {
        wx = "Backdoored 1.7.1";
        s.mc = h.mc;
        s.wk = 2;
    }
}
