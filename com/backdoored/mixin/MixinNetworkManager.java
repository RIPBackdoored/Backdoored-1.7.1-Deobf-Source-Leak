package com.backdoored.mixin;

import io.netty.handler.timeout.TimeoutException;
import \u0000r.\u0000k.\u0000r.\u0000h.\u0000p;
import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000h.\u0000y;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { NetworkManager.class }, priority = 999999999)
public class MixinNetworkManager
{
    public MixinNetworkManager() {
        super();
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacket(final Packet<?> packet, final CallbackInfo callbackInfo) {
        final \u0000y \u0000y = new \u0000y((Packet)packet);
        MinecraftForge.EVENT_BUS.post((Event)\u0000y);
        if (\u0000y.isCanceled() && callbackInfo.isCancellable()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void onChannelRead(final ChannelHandlerContext channelHandlerContext, final Packet<?> packet, final CallbackInfo callbackInfo) {
        final \u0000p \u0000p = new \u0000p((Packet)packet);
        MinecraftForge.EVENT_BUS.post((Event)\u0000p);
        if (\u0000p.isCanceled() && callbackInfo.isCancellable()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exceptionCaught(final ChannelHandlerContext channelHandlerContext, final Throwable t, final CallbackInfo callbackInfo) {
        if (!(t instanceof TimeoutException)) {
            t.printStackTrace();
            callbackInfo.cancel();
        }
    }
}
