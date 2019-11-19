package r.k.d.m.client;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import java.io.File;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.text.TextComponentString;
import r.k.r.f;
import java.nio.IntBuffer;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Better Screenshot", description = "Asyncronous Screenshots", category = y.CLIENT)
public class BetterScreenshot extends g
{
    private IntBuffer jz;
    private int[] jc;
    public static final boolean ja;
    public static final int jm;
    public static final boolean jh;
    
    public BetterScreenshot() {
        super();
    }
    
    @SubscribeEvent
    public void d(final f f) {
        if (this.lo()) {
            f.setCanceled(true);
            this.d(f.sql, f.sqx, f.sqk, f.framebuffer);
            f.iTextComponent = (ITextComponent)new TextComponentString("Creating screenshot...");
        }
    }
    
    public void d(final File file, int framebufferTextureWidth, int framebufferTextureHeight, final Framebuffer framebuffer) {
        final File file2 = new File(file, "screenshots");
        file2.mkdir();
        if (OpenGlHelper.isFramebufferEnabled()) {
            framebufferTextureWidth = framebuffer.framebufferTextureWidth;
            framebufferTextureHeight = framebuffer.framebufferTextureHeight;
        }
        final int n = framebufferTextureWidth * framebufferTextureHeight;
        if (this.jz == null || this.jz.capacity() < n) {
            this.jz = BufferUtils.createIntBuffer(n);
            this.jc = new int[n];
        }
        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        this.jz.clear();
        GlStateManager.bindTexture(framebuffer.framebufferTexture);
        GL11.glGetTexImage(3553, 0, 32993, 33639, this.jz);
        GL11.glReadPixels(0, 0, framebufferTextureWidth, framebufferTextureHeight, 32993, 33639, this.jz);
        this.jz.get(this.jc);
        new Thread(new l(framebufferTextureWidth, framebufferTextureHeight, this.jc, BetterScreenshot.mc.getFramebuffer(), file2), "Screenshot creation thread").start();
    }
}
