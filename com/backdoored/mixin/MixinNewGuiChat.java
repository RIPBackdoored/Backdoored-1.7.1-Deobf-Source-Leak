package com.backdoored.mixin;

import org.spongepowered.asm.mixin.Overwrite;
import java.util.Iterator;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000e;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.gui.ChatLine;
import java.util.List;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiNewChat.class })
public abstract class MixinNewGuiChat
{
    @Shadow
    @Final
    private Minecraft field_146247_f;
    @Shadow
    private int field_146250_j;
    @Shadow
    private boolean field_146251_k;
    @Shadow
    @Final
    private List<ChatLine> field_146253_i;
    @Shadow
    @Final
    private List<ChatLine> field_146252_h;
    
    public MixinNewGuiChat() {
        super();
    }
    
    @Shadow
    public abstract void func_146242_c(final int p0);
    
    @Shadow
    public abstract int func_146228_f();
    
    @Shadow
    public abstract float func_146244_h();
    
    @Shadow
    public abstract boolean func_146241_e();
    
    @Shadow
    public abstract void func_146229_b(final int p0);
    
    @Overwrite
    private void func_146237_a(final ITextComponent textComponent, final int n, final int n2, final boolean b) {
        if (n != 0) {
            this.func_146242_c(n);
        }
        for (final ITextComponent textComponent2 : GuiUtilRenderComponents.func_178908_a(textComponent, MathHelper.func_76141_d(this.func_146228_f() / this.func_146244_h()), this.field_146247_f.field_71466_p, false, false)) {
            if (this.func_146241_e() && this.field_146250_j > 0) {
                this.field_146251_k = true;
                this.func_146229_b(1);
            }
            this.field_146253_i.add(0, new ChatLine(n2, textComponent2, n));
        }
        final \u0000e \u0000e = new \u0000e();
        MinecraftForge.EVENT_BUS.post((Event)\u0000e);
        if (\u0000e.getResult() == Event.Result.ALLOW) {
            this.field_146252_h.add(0, new ChatLine(n2, textComponent, n));
        }
        else {
            while (this.field_146253_i.size() > 100) {
                this.field_146253_i.remove(this.field_146253_i.size() - 1);
            }
            this.field_146252_h.add(0, new ChatLine(n2, textComponent, n));
            while (this.field_146252_h.size() > 100) {
                this.field_146252_h.remove(this.field_146252_h.size() - 1);
            }
        }
    }
}
