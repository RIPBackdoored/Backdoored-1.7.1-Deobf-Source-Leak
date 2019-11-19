package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.injection.Constant;
import com.google.common.base.Strings;
import org.spongepowered.asm.mixin.injection.invoke.ModifyConstantInjector;
import org.spongepowered.asm.mixin.injection.code.Injector;
import java.util.Iterator;
import org.spongepowered.asm.mixin.injection.points.BeforeConstant;
import org.spongepowered.asm.lib.Type;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

public class ModifyConstantInjectionInfo extends InjectionInfo
{
    private static final String CONSTANT_ANNOTATION_CLASS;
    
    public ModifyConstantInjectionInfo(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final AnnotationNode annotationNode) {
        super(mixinTargetContext, methodNode, annotationNode, "constant");
    }
    
    @Override
    protected List<AnnotationNode> readInjectionPoints(final String s) {
        List<AnnotationNode> list = super.readInjectionPoints(s);
        if (list.isEmpty()) {
            final AnnotationNode element = new AnnotationNode(ModifyConstantInjectionInfo.CONSTANT_ANNOTATION_CLASS);
            element.visit("log", Boolean.TRUE);
            list = ImmutableList.<AnnotationNode>of(element);
        }
        return list;
    }
    
    @Override
    protected void parseInjectionPoints(final List<AnnotationNode> list) {
        final Type returnType = Type.getReturnType(this.method.desc);
        final Iterator<AnnotationNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.injectionPoints.add(new BeforeConstant(this.getContext(), iterator.next(), returnType.getDescriptor()));
        }
    }
    
    @Override
    protected Injector parseInjector(final AnnotationNode annotationNode) {
        return new ModifyConstantInjector(this);
    }
    
    @Override
    protected String getDescription() {
        return "Constant modifier method";
    }
    
    @Override
    public String getSliceId(final String s) {
        return Strings.nullToEmpty(s);
    }
    
    static {
        CONSTANT_ANNOTATION_CLASS = Constant.class.getName().replace('.', '/');
    }
}
