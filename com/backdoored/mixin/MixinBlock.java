package com.backdoored.mixin;

import \u0000r.\u0000k.\u0000r.\u0000co;
import \u0000r.\u0000k.\u0000r.\u0000o;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000cq.\u0000y;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Block.class })
public class MixinBlock
{
    public MixinBlock() {
        super();
    }
    
    @Inject(method = { "shouldSideBeRendered" }, at = { @At("HEAD") }, cancellable = true)
    public void shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final \u0000y \u0000y = new \u0000y((Float)null);
        MinecraftForge.EVENT_BUS.post((Event)\u0000y);
        if (\u0000y.sca != null) {
            ((Block)this).func_149715_a((float)\u0000y.sca);
            callbackInfoReturnable.setReturnValue(true);
        }
    }
    
    @Inject(method = { "getAmbientOcclusionLightValue" }, at = { @At("RETURN") }, cancellable = true)
    private void getAmbientOcclusionLightValue(final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        final \u0000o \u0000o = new \u0000o((float)callbackInfoReturnable.getReturnValue());
        MinecraftForge.EVENT_BUS.post((Event)\u0000o);
        callbackInfoReturnable.setReturnValue(\u0000o.ll);
    }
    
    @Inject(method = { "getPackedLightmapCoords" }, at = { @At("HEAD") }, cancellable = true)
    private void getPackedLightmapCoordsWrapper(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos, final CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        final \u0000co \u0000co = new \u0000co((Integer)null);
        MinecraftForge.EVENT_BUS.post((Event)\u0000co);
        if (\u0000co.spf != null) {
            callbackInfoReturnable.setReturnValue(\u0000co.spf);
        }
    }
}
