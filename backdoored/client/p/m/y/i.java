package r.k.p.m.y;

import r.k.b.k;
import java.util.Date;
import java.text.SimpleDateFormat;
import r.k.p.c.h;

public class i extends h
{
    public static final int h;
    public static final boolean sd;
    
    public i() {
        super("Time");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        final String format = new SimpleDateFormat("HH:mm:ss").format(new Date());
        k.d(format, this.z().slo, this.z().slt);
        this.c().slo = i.mc.fontRenderer.getStringWidth(format);
        this.c().slt = i.mc.fontRenderer.FONT_HEIGHT;
    }
}
