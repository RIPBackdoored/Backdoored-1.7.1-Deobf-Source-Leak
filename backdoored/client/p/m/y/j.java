package r.k.p.m.y;

import r.k.b.k;
import net.minecraft.client.Minecraft;
import r.k.p.c.h;

public class j extends h
{
    public static final int siz;
    public static final boolean sic;
    
    public j() {
        super("Fps");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        if (!this.xb()) {
            return;
        }
        final String string = Minecraft.getDebugFPS() + " fps";
        k.d(string, this.z().slo, this.z().slt);
        this.c().slo = j.mc.fontRenderer.getStringWidth(string);
        this.c().slt = j.mc.fontRenderer.FONT_HEIGHT;
    }
}
