package r.k.c.c;

import java.util.List;
import org.lwjgl.opengl.GL11;
import r.k.h;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiScreen;

public class y extends GuiScreen
{
    private static final ResourceLocation resourceLocation;
    public boolean eo;
    public boolean et;
    public int en;
    public int ei;
    public int ep;
    public int er;
    public String ef;
    public boolean eb;
    private float[] ew;
    public String eg;
    public static ArrayList<y> eu;
    public static final int ez;
    public static final boolean ec;
    
    y(final int en, final int ei, final int ep, final int er, final String ef, final boolean eb, final float[] ew) {
        super();
        this.eo = false;
        this.eg = "FFFFFF";
        this.en = en;
        this.ei = ei;
        this.ep = ep;
        this.er = er;
        this.ef = ef;
        this.eb = eb;
        this.ew = ew;
        y.eu.add(this);
        this.mc = h.mc;
    }
    
    public void l(final int n, final int n2) {
        final int n3 = 1;
        h.mc.renderEngine.bindTexture(y.resourceLocation);
        GL11.glPushAttrib(1048575);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(this.ew[0], this.ew[1], this.ew[2], this.ew[3]);
        final List listFormattedStringToWidth = this.mc.fontRenderer.listFormattedStringToWidth(this.ef, this.ep - (n3 + 1));
        final int er = listFormattedStringToWidth.size() * this.mc.fontRenderer.FONT_HEIGHT + 15;
        if (er > this.mc.fontRenderer.FONT_HEIGHT + 15) {
            this.er = er;
        }
        this.drawTexturedModalRect(this.en, this.ei, 0, 0, this.ep, this.er);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        this.drawTexturedModalRect(this.en + n3, this.ei + n3, 0, 0, this.ep - n3 * 2, this.er - n3 * 2);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
        this.mc.fontRenderer.drawSplitString(this.ef, this.en + (n3 + 1) + (this.ep - (n3 + 1) - this.mc.fontRenderer.getStringWidth((String)listFormattedStringToWidth.get(0))) / 2, this.ei + this.er / 2 - this.mc.fontRenderer.FONT_HEIGHT * listFormattedStringToWidth.size() / 2, this.ep - (n3 + 1), Integer.parseInt(this.eg, 16));
        this.y(n, n2);
    }
    
    public void y(final int n, final int n2) {
    }
    
    static {
        resourceLocation = new ResourceLocation("backdoored", "textures/white.png");
        y.eu = new ArrayList<y>();
    }
}
