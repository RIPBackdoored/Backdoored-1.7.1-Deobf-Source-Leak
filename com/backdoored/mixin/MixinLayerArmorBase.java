package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import \u0000r.\u0000k.\u0000b.\u0000k;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000j.\u0000i;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { LayerArmorBase.class }, priority = 999999999)
public class MixinLayerArmorBase
{
    public MixinLayerArmorBase() {
        super();
    }
    
    @Redirect(method = { "renderEnchantedGlint" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.color(FFFF)V"))
    private static void renderEnchantedGlint(float n, float n2, float n3, float n4) {
        if (\u0000i.xk != null && \u0000i.xk.lo()) {
            final Color lz = \u0000k.lz();
            n = (float)lz.getRed();
            n3 = (float)lz.getBlue();
            n2 = (float)lz.getGreen();
            n4 = (float)lz.getAlpha();
        }
        GlStateManager.func_179131_c(n, n2, n3, n4);
    }
}
