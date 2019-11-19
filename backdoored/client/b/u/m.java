package r.k.b.u;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import r.k.h;

public class m implements h
{
    public static final int sga;
    public static final boolean sgm;
    
    public m() {
        super();
    }
    
    public static void l(final int n, final int n2, final int n3, final int n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n5 & 0xFF) / 255.0f;
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        final float n9;
        buffer.pos((double)n, (double)n4, 0.0).color(n9, n7, n8, n6).endVertex();
        buffer.pos((double)n3, (double)n4, 0.0).color(n9, n7, n8, n6).endVertex();
        buffer.pos((double)n3, (double)n2, 0.0).color(n9, n7, n8, n6).endVertex();
        buffer.pos((double)n, (double)n2, 0.0).color(n9, n7, n8, n6).endVertex();
        instance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void d(final int n, final int n2, final int n3, final int n4, final Color color) {
        GL11.glDisable(3553);
        GL11.glColor4f((float)color.getRed(), (float)color.getBlue(), (float)color.getGreen(), (float)color.getAlpha());
        GL11.glPushMatrix();
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
    }
    
    public static void d(final int n, final int n2, final Block block) {
        d(n, n2, new ItemStack(block));
    }
    
    public static void d(final int n, final int n2, final ItemStack itemStack) {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.translate(0.0f, 0.0f, 700.0f);
        RenderHelper.enableGUIStandardItemLighting();
        m.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
