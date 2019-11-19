package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000j;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { ItemRenderer.class }, priority = 999999999)
public abstract class MixinItemRenderer
{
    public MixinItemRenderer() {
        super();
    }
    
    @Shadow
    public abstract void func_187457_a(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Inject(method = { "renderWaterOverlayTexture" }, at = { @At("HEAD") }, cancellable = true)
    private void renderWaterOverlayTexture(final float n, final CallbackInfo callbackInfo) {
    }
    
    @Redirect(method = { "renderItemInFirstPerson(F)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"))
    private void renderItemInFirstPerson(final ItemRenderer itemRenderer, final AbstractClientPlayer abstractClientPlayer, final float n, final float n2, final EnumHand enumHand, final float n3, final ItemStack itemStack, final float n4) {
        final \u0000j \u0000j = new \u0000j(itemRenderer, abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
        MinecraftForge.EVENT_BUS.post((Event)\u0000j);
        \u0000j.ar.func_187457_a(\u0000j.af, \u0000j.ab, \u0000j.aw, \u0000j.ag, \u0000j.au, \u0000j.az, \u0000j.ac);
    }
}
