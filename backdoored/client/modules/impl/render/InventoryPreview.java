package r.k.d.m.render;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.NonNullList;
import r.k.i.o;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import net.minecraft.util.ResourceLocation;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Inventory Preview", description = "Shows you a preview of whats in your inv", category = y.RENDER)
public class InventoryPreview extends g
{
    private static final ResourceLocation resourceLocation;
    private static final int seu;
    private static final int sez;
    private static final int sec;
    private m<Integer> sea;
    private m<Integer> sem;
    public static final boolean seh;
    public static final int sod;
    public static final boolean sos;
    
    public InventoryPreview() {
        super();
        this.sea = (m<Integer>)new x("x", this, 2, 0, InventoryPreview.mc.displayWidth + 100);
        this.sem = (m<Integer>)new x("y", this, 2, 0, InventoryPreview.mc.displayHeight + 100);
    }
    
    public void a() {
        if (!this.lo()) {
            return;
        }
        final int intValue = this.sea.yv();
        final int intValue2 = this.sem.yv();
        final NonNullList mainInventory = InventoryPreview.mc.player.inventory.mainInventory;
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.translate(0.0f, 0.0f, 700.0f);
        InventoryPreview.mc.getTextureManager().bindTexture(InventoryPreview.resourceLocation);
        RenderHelper.disableStandardItemLighting();
        this.s(intValue, intValue2, 9, 3, 1973019);
        RenderHelper.enableGUIStandardItemLighting();
        int n = 9;
        if (n < mainInventory.size()) {
            final ItemStack itemStack = (ItemStack)mainInventory.get(n);
            final int n2 = intValue + 6 + n % 9 * 18;
            final int n3 = intValue2 + 6 + n / 9 * 18 - 18;
            if (!itemStack.isEmpty()) {
                InventoryPreview.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n2, n3);
                InventoryPreview.mc.getRenderItem().renderItemOverlays(o.fontRenderer, itemStack, n2, n3);
            }
            ++n;
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    private void s(final int n, final int n2, final int n3, final int n4, final int n5) {
        InventoryPreview.mc.getTextureManager().bindTexture(InventoryPreview.resourceLocation);
        GlStateManager.color(((n5 & 0xFF0000) >> 16) / 255.0f, ((n5 & 0xFF00) >> 8) / 255.0f, (n5 & 0xFF) / 255.0f);
        RenderHelper.disableStandardItemLighting();
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2 + 5 + 18 * n4, 25.0f, 25.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2, 25.0f, 0.0f, 5, 5, 256.0f, 256.0f);
        Gui.drawModalRectWithCustomSizedTexture(n, n2 + 5 + 18 * n4, 0.0f, 25.0f, 5, 5, 256.0f, 256.0f);
        int n6 = 0;
        if (n6 < n4) {
            Gui.drawModalRectWithCustomSizedTexture(n, n2 + 5 + 18 * n6, 0.0f, 6.0f, 5, 18, 256.0f, 256.0f);
            Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n3, n2 + 5 + 18 * n6, 25.0f, 6.0f, 5, 18, 256.0f, 256.0f);
            int n7 = 0;
            if (n7 < n3) {
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2, 6.0f, 0.0f, 18, 5, 256.0f, 256.0f);
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2 + 5 + 18 * n4, 6.0f, 25.0f, 18, 5, 256.0f, 256.0f);
                Gui.drawModalRectWithCustomSizedTexture(n + 5 + 18 * n7, n2 + 5 + 18 * n6, 6.0f, 6.0f, 18, 18, 256.0f, 256.0f);
                ++n7;
            }
            ++n6;
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    static {
        sec = 18;
        sez = 1;
        seu = 5;
        resourceLocation = new ResourceLocation("backdoored", "textures/inv_slot.png");
    }
}
