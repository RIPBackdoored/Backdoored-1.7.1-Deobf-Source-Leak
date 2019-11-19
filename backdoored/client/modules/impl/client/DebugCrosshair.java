package r.k.d.m.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Debug Crosshair", description = "Show f3 crosshair", category = y.CLIENT)
public class DebugCrosshair extends g
{
    public static final int rl;
    public static final boolean ry;
    
    public DebugCrosshair() {
        super();
    }
    
    @SubscribeEvent
    public void d(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (this.lo() && renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            renderGameOverlayEvent.setCanceled(true);
            final float n;
            d(renderGameOverlayEvent.getPartialTicks(), new ScaledResolution(DebugCrosshair.mc).getScaledWidth(), new ScaledResolution(DebugCrosshair.mc).getScaledHeight(), n);
        }
    }
    
    private static void d(final float n, final int n2, final int n3, final float n4) {
        final GameSettings gameSettings = DebugCrosshair.mc.gameSettings;
        if (gameSettings.thirdPersonView == 0) {
            if (DebugCrosshair.mc.playerController.isSpectator() && DebugCrosshair.mc.pointedEntity == null) {
                final RayTraceResult objectMouseOver = DebugCrosshair.mc.objectMouseOver;
                if (objectMouseOver == null || objectMouseOver.typeOfHit != RayTraceResult.Type.BLOCK) {
                    return;
                }
                final BlockPos blockPos = objectMouseOver.getBlockPos();
                DebugCrosshair.mc.world.getBlockState(blockPos);
                if (!(DebugCrosshair.mc.world.getTileEntity(blockPos) instanceof IInventory)) {
                    return;
                }
            }
            if (!gameSettings.hideGUI) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)(n2 / 2), (float)(n3 / 2), n4);
                final Entity renderViewEntity = DebugCrosshair.mc.getRenderViewEntity();
                if (renderViewEntity != null) {
                    GlStateManager.rotate(renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * n, -1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * n, 0.0f, 1.0f, 0.0f);
                    GlStateManager.scale(-1.0f, -1.0f, -1.0f);
                    OpenGlHelper.renderDirections(10);
                    GlStateManager.popMatrix();
                }
            }
        }
    }
}
