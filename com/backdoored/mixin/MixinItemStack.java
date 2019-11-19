package com.backdoored.mixin;

import net.minecraft.client.util.ITooltipFlag;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemStack.class })
public class MixinItemStack
{
    @Shadow
    int field_77991_e;
    int actualDamage;
    
    public MixinItemStack() {
        super();
    }
    
    @Inject(method = { "<init>(Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)V" }, at = { @At("RETURN") })
    private void postInit(final Item item, final int n, final int n2, final NBTTagCompound nbtTagCompound, final CallbackInfo callbackInfo) {
        this.field_77991_e = n2;
        this.actualDamage = n2;
    }
    
    @Redirect(method = { "<init>(Lnet/minecraft/nbt/NBTTagCompound;)V" }, at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"))
    private int max(final int n, final int actualDamage) {
        return this.actualDamage = actualDamage;
    }
    
    @Redirect(method = { "getTooltip" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemDamage()I"))
    private int getItemDamage(final ItemStack itemStack) {
        return this.actualDamage;
    }
    
    @Redirect(method = { "getTooltip" }, at = @At(value = "INVOKE", target = "net/minecraft/item/ItemStack.isItemDamaged()Z"))
    private boolean isItemDamaged(final ItemStack itemStack) {
        return true;
    }
    
    @Redirect(method = { "getTooltip" }, at = @At(value = "INVOKE", target = "net/minecraft/client/util/ITooltipFlag.isAdvanced()Z", ordinal = 2))
    private boolean isAdvanced(final ITooltipFlag tooltipFlag) {
        return true;
    }
}
