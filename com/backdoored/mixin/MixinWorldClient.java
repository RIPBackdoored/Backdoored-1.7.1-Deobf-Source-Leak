package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000cu;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WorldClient.class })
public class MixinWorldClient
{
    public MixinWorldClient() {
        super();
    }
    
    @Redirect(method = { "makeFireworks" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEffect(Lnet/minecraft/client/particle/Particle;)V"))
    private void makeFireworkParticles(final ParticleManager particleManager, final Particle particle) {
        final \u0000cu \u0000cu = new \u0000cu();
        MinecraftForge.EVENT_BUS.post((Event)\u0000cu);
        if (!\u0000cu.isCanceled()) {
            particleManager.func_78873_a(particle);
        }
    }
}
