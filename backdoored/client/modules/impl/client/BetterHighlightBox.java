package r.k.d.m.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import r.k.i.c;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import r.k.d.o.h.i.s;
import r.k.d.o.h.i.x;
import r.k.d.o.h.i.t;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "BetterHighlightBox", description = "Better Highlight Box", category = y.CLIENT)
public class BetterHighlightBox extends g
{
    private m<Float> aj;
    private m<Integer> ae;
    private m<Integer> ao;
    private m<Integer> at;
    private m<Double> an;
    public static final int ai;
    public static final boolean ap;
    
    public BetterHighlightBox() {
        this.aj = (m<Float>)new t("Width", this, 5.0f, 0.0f, 50.0f);
        this.ae = (m<Integer>)new x("Red", this, 0, 0, 255);
        this.ao = (m<Integer>)new x("Green", this, 0, 0, 255);
        this.at = (m<Integer>)new x("Blue", this, 0, 0, 255);
        this.an = (m<Double>)new s("Alpha", this, 0.4, 0.0, 1.0);
    }
    
    @SubscribeEvent
    public void d(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        if (this.lo()) {
            final float partialTicks = drawBlockHighlightEvent.getPartialTicks();
            final EntityPlayer player = drawBlockHighlightEvent.getPlayer();
            final RayTraceResult target = drawBlockHighlightEvent.getTarget();
            if (target.typeOfHit == RayTraceResult.Type.BLOCK) {
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.glLineWidth((float)this.aj.yv());
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                final BlockPos blockPos = target.getBlockPos();
                final IBlockState blockState = BetterHighlightBox.mc.world.getBlockState(blockPos);
                if (blockState.getMaterial() != Material.AIR && BetterHighlightBox.mc.world.getWorldBorder().contains(blockPos)) {
                    c.drawSelectionBoundingBox(blockState.getSelectedBoundingBox((World)BetterHighlightBox.mc.world, blockPos).grow(0.0020000000949949026).offset(-(player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks), -(player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks), -(player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks)), (float)Math.min(Math.abs(this.ae.yv() - 255), 244), (float)Math.min(Math.abs(this.ao.yv() - 255), 244), (float)Math.min(Math.abs(this.at.yv() - 255), 244), this.an.yv().floatValue());
                }
                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
            }
            drawBlockHighlightEvent.setCanceled(true);
        }
    }
}
