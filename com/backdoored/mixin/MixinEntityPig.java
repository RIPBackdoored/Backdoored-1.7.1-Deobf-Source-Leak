package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000w;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import net.minecraft.entity.passive.EntityPig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPig.class })
public class MixinEntityPig
{
    public MixinEntityPig() {
        super();
    }
    
    @ModifyArgs(method = { "travel" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/EntityAnimal;travel(FFF)V"))
    private void travel(final Args args, final float n, final float n2, final float n3) {
        final \u0000w \u0000w = new \u0000w();
        MinecraftForge.EVENT_BUS.post((Event)\u0000w);
        if (\u0000w.getResult() == Event.Result.ALLOW || \u0000w.getResult() == Event.Result.DEFAULT) {
            args.setAll(n, n2, n3);
        }
        else {
            args.setAll(n, n2, 0);
        }
    }
}
