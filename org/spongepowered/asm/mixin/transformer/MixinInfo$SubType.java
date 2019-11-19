package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.Iterator;
import java.util.List;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import java.util.Collections;
import java.util.Collection;

abstract static class SubType
{
    protected final MixinInfo mixin;
    protected final String annotationType;
    protected final boolean targetMustBeInterface;
    protected boolean detached;
    
    SubType(final MixinInfo mixin, final String annotationType, final boolean targetMustBeInterface) {
        super();
        this.mixin = mixin;
        this.annotationType = annotationType;
        this.targetMustBeInterface = targetMustBeInterface;
    }
    
    Collection<String> getInterfaces() {
        return (Collection<String>)Collections.<Object>emptyList();
    }
    
    boolean isDetachedSuper() {
        return this.detached;
    }
    
    boolean isLoadable() {
        return false;
    }
    
    void validateTarget(final String s, final ClassInfo classInfo) {
        final boolean interface1 = classInfo.isInterface();
        if (interface1 != this.targetMustBeInterface) {
            throw new InvalidMixinException(this.mixin, this.annotationType + " target type mismatch: " + s + " is " + (interface1 ? "" : "not ") + "an interface in " + this);
        }
    }
    
    abstract void validate(final State p0, final List<ClassInfo> p1);
    
    abstract MixinPreProcessorStandard createPreProcessor(final MixinClassNode p0);
    
    static SubType getTypeFor(final MixinInfo mixinInfo) {
        if (!mixinInfo.getClassInfo().isInterface()) {
            return new Standard(mixinInfo);
        }
        boolean b = false;
        final Iterator<ClassInfo.Method> iterator = mixinInfo.getClassInfo().getMethods().iterator();
        while (iterator.hasNext()) {
            b |= !iterator.next().isAccessor();
        }
        if (b) {
            return new Interface(mixinInfo);
        }
        return new Accessor(mixinInfo);
    }
}
