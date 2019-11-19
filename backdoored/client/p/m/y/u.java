package r.k.p.m.y;

import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import r.k.p.m.m;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import r.k.p.c.h;

public class u extends h
{
    private static final Color ma;
    public static final boolean mm;
    public static final int mh;
    public static final boolean hd;
    
    public u() {
        super("Inventory Preview");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        final int slo = 18 * 9;
        final int slt = 54;
        this.c().slo = slo;
        this.c().slt = slt;
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        final NonNullList mainInventory = u.mc.player.inventory.mainInventory;
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.translate(0.0f, 0.0f, 700.0f);
        RenderHelper.disableStandardItemLighting();
        if (!(u.mc.currentScreen instanceof m)) {
            r.k.b.u.m.l(this.z().slo, this.z().slt, this.z().slo + this.c().slo, this.z().slt + this.c().slt, u.ma.getRGB());
        }
        RenderHelper.enableGUIStandardItemLighting();
        int n4 = 9;
        if (n4 < mainInventory.size()) {
            final ItemStack itemStack = (ItemStack)mainInventory.get(n4);
            final int n5 = this.z().slo + n4 % 9 * 18;
            final int n6 = this.z().slt + n4 / 9 * 18 - 18;
            if (!itemStack.isEmpty()) {
                u.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n5, n6);
                u.mc.getRenderItem().renderItemOverlays(u.mc.fontRenderer, itemStack, n5, n6);
            }
            ++n4;
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    static {
        ma = new Color(64, 64, 64, 127);
    }
}
