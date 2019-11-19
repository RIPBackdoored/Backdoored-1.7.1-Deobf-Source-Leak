package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import \u0000r.\u0000k.\u0000r.\u0000c;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.BlockLadder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockLadder.class })
public class MixinBlockLadder
{
    @Shadow
    @Final
    public static PropertyDirection field_176382_a;
    
    public MixinBlockLadder() {
        super();
    }
    
    @Inject(method = { "getBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    public void getBoundingBox(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos, final CallbackInfoReturnable<AxisAlignedBB> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new \u0000c(blockState, blockAccess, blockPos, MixinBlockLadder.field_176382_a, (CallbackInfoReturnable)callbackInfoReturnable));
    }
}
