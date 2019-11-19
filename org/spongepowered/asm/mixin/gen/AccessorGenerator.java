package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.MethodNode;

public abstract class AccessorGenerator
{
    protected final AccessorInfo info;
    
    public AccessorGenerator(final AccessorInfo info) {
        super();
        this.info = info;
    }
    
    protected final MethodNode createMethod(final int maxLocals, final int maxStack) {
        final MethodNode method = this.info.getMethod();
        final MethodNode methodNode = new MethodNode(327680, (method.access & 0xFFFFFBFF) | 0x1000, method.name, method.desc, null, null);
        (methodNode.visibleAnnotations = new ArrayList<AnnotationNode>()).add(this.info.getAnnotation());
        methodNode.maxLocals = maxLocals;
        methodNode.maxStack = maxStack;
        return methodNode;
    }
    
    public abstract MethodNode generate();
}
