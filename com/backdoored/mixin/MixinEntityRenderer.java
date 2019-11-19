package com.backdoored.mixin;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { EntityRenderer.class }, priority = 999999999)
public class MixinEntityRenderer
{
    public MixinEntityRenderer() {
        super();
    }
    
    @Inject(method = { "setupFog" }, at = { @At("HEAD") }, cancellable = true)
    public void setupFog(final int n, final float n2, final CallbackInfo callbackInfo) {
    }
    
    @Redirect(method = { "setupFog" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ActiveRenderInfo;getBlockStateAtEntityViewpoint(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;F)Lnet/minecraft/block/state/IBlockState;"))
    public IBlockState getBlockStateAtEntityViewpoint(final World world, final Entity entity, final float n) {
        return ActiveRenderInfo.func_186703_a(world, entity, n);
    }
    
    @Redirect(method = { "orientCamera" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
    public RayTraceResult rayTraceBlocks(final WorldClient worldClient, final Vec3d vec3d, final Vec3d vec3d2) {
        return worldClient.func_147447_a(vec3d, vec3d2, false, true, true);
    }
    
    @Redirect(method = { "orientCamera" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.translate(FFF)V", ordinal = 2))
    private void getViewDistance(final float n, final float n2, final float n3) {
        GlStateManager.func_179109_b(n, n2, n3);
    }
}
