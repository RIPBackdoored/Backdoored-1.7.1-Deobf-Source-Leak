package r.k.c.c;

import org.lwjgl.opengl.GL11;
import r.k.d.o.m;

public class p extends h
{
    private m skh;
    public static final int sqd;
    public static final boolean sqs;
    
    public p(final m skh) {
        super(skh);
        this.skh = skh;
    }
    
    @Override
    public void y(final int n, final int n2) {
        r.k.h.mc.renderEngine.bindTexture(r.k.c.m.resourceLocation);
        GL11.glPushAttrib(1048575);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
        this.drawTexturedModalRect(this.en + 1, this.ei + this.er - 2, 0, 0, (this.ep - 2) * this.skh.yv().intValue() / Math.max(this.skh.yo().intValue() - this.skh.ye().intValue(), 1), 1);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
    
    @Override
    public m sw() {
        return this.skh;
    }
}
