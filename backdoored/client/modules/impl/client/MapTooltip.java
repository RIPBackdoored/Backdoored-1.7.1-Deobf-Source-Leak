package r.k.d.m.client;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.world.storage.MapData;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraft.util.ResourceLocation;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Map Tooltip", description = "Tooltips to preview maps", category = y.CLIENT)
public class MapTooltip extends g
{
    private static final ResourceLocation resourceLocation;
    public static final int sof;
    public static final boolean sob;
    
    public MapTooltip() {
        super();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void d(final ItemTooltipEvent itemTooltipEvent) {
    }
    
    @SubscribeEvent
    public void d(final RenderTooltipEvent.PostText postText) {
        if (!this.lo()) {
            return;
        }
        if (!postText.getStack().isEmpty() && postText.getStack().getItem() instanceof ItemMap) {
            final MapData mapData = Items.FILLED_MAP.getMapData(postText.getStack(), (World)MapTooltip.mc.world);
            if (mapData != null) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0f, 1.0f, 1.0f);
                RenderHelper.disableStandardItemLighting();
                MapTooltip.mc.getTextureManager().bindTexture(MapTooltip.resourceLocation);
                final Tessellator instance = Tessellator.getInstance();
                final BufferBuilder buffer = instance.getBuffer();
                final int n = 7;
                final float n2 = 135.0f;
                final float n3 = 0.5f;
                GlStateManager.translate((float)postText.getX(), postText.getY() - n2 * n3 - 5.0f, 0.0f);
                GlStateManager.scale(n3, n3, n3);
                buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                buffer.pos((double)(-n), (double)n2, 0.0).tex(0.0, 1.0).endVertex();
                buffer.pos((double)n2, (double)n2, 0.0).tex(1.0, 1.0).endVertex();
                buffer.pos((double)n2, (double)(-n), 0.0).tex(1.0, 0.0).endVertex();
                buffer.pos((double)(-n), (double)(-n), 0.0).tex(0.0, 0.0).endVertex();
                instance.draw();
                MapTooltip.mc.entityRenderer.getMapItemRenderer().renderMap(mapData, false);
                GlStateManager.enableLighting();
                GlStateManager.popMatrix();
            }
        }
    }
    
    static {
        resourceLocation = new ResourceLocation("textures/map/map_background.png");
    }
}
