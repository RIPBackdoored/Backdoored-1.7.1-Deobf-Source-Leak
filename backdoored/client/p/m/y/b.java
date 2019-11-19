package r.k.p.m.y;

import r.k.b.k;
import r.k.p.c.h;

public class b extends h
{
    public static final int lds;
    public static final boolean ldl;
    
    public b() {
        super("Watermark");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        final String s = "Backdoored 1.7.1";
        k.d(s, this.z().slo, this.z().slt);
        this.c().slo = b.mc.fontRenderer.getStringWidth(s);
        this.c().slt = b.mc.fontRenderer.FONT_HEIGHT;
    }
}
