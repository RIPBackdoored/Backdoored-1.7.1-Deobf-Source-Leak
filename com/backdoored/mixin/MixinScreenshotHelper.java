package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.shader.Framebuffer;
import javax.annotation.Nullable;
import java.io.File;
import net.minecraft.util.ScreenShotHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ScreenShotHelper.class })
public class MixinScreenshotHelper
{
    public MixinScreenshotHelper() {
        super();
    }
    
    @Redirect(method = { "Lnet/minecraft/util/ScreenShotHelper;saveScreenshot(Ljava/io/File;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/text/ITextComponent;" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ScreenShotHelper;saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/text/ITextComponent;"))
    private static ITextComponent saveScreenshot(final File file, @Nullable final String s, final int n, final int n2, final Framebuffer framebuffer) {
        final \u0000f \u0000f = new \u0000f(file, s, n, n2, framebuffer);
        MinecraftForge.EVENT_BUS.post((Event)\u0000f);
        if (!\u0000f.isCanceled()) {
            return ScreenShotHelper.func_148259_a(file, (String)null, n, n2, framebuffer);
        }
        return \u0000f.sqv;
    }
}
