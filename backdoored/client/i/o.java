package r.k.i;

import r.k.h;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.FontRenderer;

public class o
{
    public static final FontRenderer fontRenderer;
    public static final Class<GlStateManager> lsq;
    public static final c lsv;
    public static final int lsj;
    public static final boolean lse;
    
    public o() {
        super();
    }
    
    static {
        fontRenderer = h.mc.fontRenderer;
        lsq = GlStateManager.class;
        lsv = new c(h.mc);
    }
}
