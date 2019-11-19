package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import com.google.common.base.Function;

static final class Annotations$1 implements Function<AnnotationNode, String> {
    Annotations$1() {
        super();
    }
    
    @Override
    public String apply(final AnnotationNode annotationNode) {
        return annotationNode.desc;
    }
    
    @Override
    public /* bridge */ Object apply(final Object o) {
        return this.apply((AnnotationNode)o);
    }
}