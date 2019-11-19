package com.backdoored.mixin;

import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.network.NetworkManager;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiConnecting.class })
public class MixinGuiConnecting extends GuiScreen
{
    @Shadow
    private NetworkManager field_146371_g;
    
    public MixinGuiConnecting() {
        super();
    }
    
    @Overwrite
    public void func_73863_a(final int n, final int n2, final float n3) {
        this.func_146276_q_();
        String field_78845_b = "Unknown";
        final ServerData func_147104_D = this.field_146297_k.func_147104_D();
        if (func_147104_D != null && func_147104_D.field_78845_b != null) {
            field_78845_b = func_147104_D.field_78845_b;
        }
        if (this.field_146371_g == null) {
            this.func_73732_a(this.field_146289_q, "Connecting to " + field_78845_b + "...", this.field_146294_l / 2, this.field_146295_m / 2 - 50, 16777215);
        }
        else {
            this.func_73732_a(this.field_146289_q, "Logging into " + field_78845_b + "...", this.field_146294_l / 2, this.field_146295_m / 2 - 50, 16777215);
        }
        super.func_73863_a(n, n2, n3);
    }
}
