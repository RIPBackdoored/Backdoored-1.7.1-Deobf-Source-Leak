package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiScreen.class })
public class MixinGuiScreen
{
    @Shadow
    public Minecraft field_146297_k;
    
    public MixinGuiScreen() {
        super();
    }
    
    @Inject(method = { "Lnet/minecraft/client/gui/GuiScreen;drawWorldBackground(I)V" }, at = { @At("HEAD") }, cancellable = true)
    private void drawWorldBackgroundWrapper(final int n, final CallbackInfo callbackInfo) {
        if (this.field_146297_k.field_71441_e != null) {
            callbackInfo.cancel();
        }
    }
}
