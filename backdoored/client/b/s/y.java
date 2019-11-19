package r.k.b.s;

import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import r.k.h;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;

public class y
{
    public static final int llh;
    public static final boolean lyd;
    
    public y() {
        super();
    }
    
    public static void d(final String s, final Vec3d vec3d) {
        final float floatValue = (float)ObfuscationReflectionHelper.getPrivateValue((Class)RenderManager.class, (Object)h.mc.getRenderManager(), new String[] { "playerViewX", "field_78732_j" });
        final float floatValue2 = (float)ObfuscationReflectionHelper.getPrivateValue((Class)RenderManager.class, (Object)h.mc.getRenderManager(), new String[] { "playerViewY", "field_78735_i" });
        final float n = 1.6f;
        final float n2 = (float)(0.01666666753590107 * h.mc.player.getDistance(vec3d.x, vec3d.y, vec3d.z) / 2.0);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)vec3d.x, (float)vec3d.y, (float)vec3d.z);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-floatValue2, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(floatValue, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-n2, -n2, n2);
        GL11.glDepthMask(false);
        GL11.glDisable(2896);
        Tessellator.getInstance().getBuffer();
        final int n3 = (int)(-h.mc.player.getDistance(vec3d.x, vec3d.y, vec3d.z)) / (int)n;
        GL11.glDisable(3553);
        h.mc.fontRenderer.drawStringWithShadow(s, (float)(-(h.mc.fontRenderer.getStringWidth(s) / 2)), (float)n3, 16777215);
        h.mc.entityRenderer.disableLightmap();
        GL11.glLineWidth(1.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2896);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }
}
