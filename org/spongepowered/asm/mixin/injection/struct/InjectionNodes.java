package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.util.Bytecode;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.ArrayList;

public class InjectionNodes extends ArrayList<InjectionNode>
{
    private static final long serialVersionUID = 1L;
    
    public InjectionNodes() {
        super();
    }
    
    public InjectionNode add(final AbstractInsnNode abstractInsnNode) {
        InjectionNode value = this.get(abstractInsnNode);
        if (value == null) {
            value = new InjectionNode(abstractInsnNode);
            this.add(value);
        }
        return value;
    }
    
    public InjectionNode get(final AbstractInsnNode abstractInsnNode) {
        for (final InjectionNode injectionNode : this) {
            if (injectionNode.matches(abstractInsnNode)) {
                return injectionNode;
            }
        }
        return null;
    }
    
    public boolean contains(final AbstractInsnNode abstractInsnNode) {
        return this.get(abstractInsnNode) != null;
    }
    
    public void replace(final AbstractInsnNode abstractInsnNode, final AbstractInsnNode abstractInsnNode2) {
        final InjectionNode value = this.get(abstractInsnNode);
        if (value != null) {
            value.replace(abstractInsnNode2);
        }
    }
    
    public void remove(final AbstractInsnNode abstractInsnNode) {
        final InjectionNode value = this.get(abstractInsnNode);
        if (value != null) {
            value.remove();
        }
    }
}
