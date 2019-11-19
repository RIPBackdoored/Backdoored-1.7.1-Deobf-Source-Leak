package com.backdoored.mixin;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Event;
import \u0000r.\u0000k.\u0000r.\u0000v;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.entity.player.EntityPlayer;
import \u0000r.\u0000k.\u0000h;
import net.minecraft.network.play.server.SPacketCombatEvent;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { NetHandlerPlayClient.class }, priority = 999999999)
public class MixinNetHandlerPlayClient
{
    @Shadow
    private boolean field_147309_h;
    @Shadow
    private WorldClient field_147300_g;
    @Shadow
    private Minecraft field_147299_f;
    
    public MixinNetHandlerPlayClient() {
        super();
    }
    
    @Inject(method = { "handleChunkData" }, at = { @At("RETURN") })
    private void postHandleChunkData(final SPacketChunkData sPacketChunkData, final CallbackInfo callbackInfo) {
    }
    
    @Inject(method = { "handleCombatEvent" }, at = { @At("INVOKE") })
    private void onPlayerDeath(final SPacketCombatEvent sPacketCombatEvent, final CallbackInfo callbackInfo) {
        if (sPacketCombatEvent.field_179776_a == SPacketCombatEvent.Event.ENTITY_DIED) {
            System.out.println("A player died! " + sPacketCombatEvent.field_179775_c);
            final Entity func_73045_a = \u0000h.sbb.field_71441_e.func_73045_a(sPacketCombatEvent.field_179775_c);
            if (func_73045_a instanceof EntityPlayer) {
                MinecraftForge.EVENT_BUS.post((Event)new \u0000v((EntityPlayer)func_73045_a));
            }
        }
    }
}
