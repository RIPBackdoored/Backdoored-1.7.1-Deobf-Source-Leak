package com.backdoored.mixin.minecraftforge;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EventBus.class })
public class MixinEventBus
{
    public MixinEventBus() {
        super();
    }
    
    @Redirect(method = { "post" }, at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/eventhandler/IEventListener;invoke(Lnet/minecraftforge/fml/common/eventhandler/Event;)V", remap = false), remap = false)
    private void invoke(final IEventListener eventListener, final Event event) {
        try {
            eventListener.invoke(event);
        }
        catch (Throwable t2) {
            final String string = "WARNING!!!! The event bus encountered an error while invoking event " + event.getClass().getName() + "! Luckily your using Backdoored TM client (Trademarked and patented) so we've prevented your client from crashing! Isn't that lucky!";
            FMLLog.log.warn(string);
            try {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(string));
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            t2.printStackTrace();
        }
    }
}
