package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.InsnList;

static class Context extends LocalVariableDiscriminator.Context
{
    final InsnList insns;
    
    public Context(final Type type, final boolean b, final Target target, final AbstractInsnNode abstractInsnNode) {
        super(type, b, target, abstractInsnNode);
        this.insns = new InsnList();
    }
}
