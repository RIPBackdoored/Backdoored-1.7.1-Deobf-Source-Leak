package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Final;
import java.util.Random;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiMainMenu.class })
public class MixinGuiMainMenu
{
    private static String[] devs;
    @Shadow
    private String field_73975_c;
    @Final
    @Shadow
    private static Random field_175374_h;
    
    public MixinGuiMainMenu() {
        super();
    }
    
    @Inject(method = { "Lnet/minecraft/client/gui/GuiMainMenu;<init>()V" }, at = { @At("RETURN") })
    public void postConstructor(final CallbackInfo callbackInfo) {
        this.field_73975_c = getRandomSplash();
    }
    
    private static String getRandomSplash() {
        return MixinGuiMainMenu.devs[MixinGuiMainMenu.field_175374_h.nextInt(MixinGuiMainMenu.devs.length)] + " owns me and all";
    }
    
    static {
        MixinGuiMainMenu.devs = new String[] { "cookiedragon234", "tigermouthbear", "carbolemons" };
    }
}
