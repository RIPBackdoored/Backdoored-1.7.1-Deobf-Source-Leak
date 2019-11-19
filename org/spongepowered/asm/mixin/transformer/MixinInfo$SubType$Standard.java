package org.spongepowered.asm.mixin.transformer;

import java.util.Iterator;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import java.util.List;

static class Standard extends SubType
{
    Standard(final MixinInfo mixinInfo) {
        super(mixinInfo, "@Mixin", false);
    }
    
    @Override
    void validate(final State state, final List<ClassInfo> list) {
        final MixinClassNode classNode = state.getClassNode();
        for (final ClassInfo classInfo : list) {
            if (classNode.superName.equals(classInfo.getSuperName())) {
                continue;
            }
            if (!classInfo.hasSuperClass(classNode.superName, ClassInfo.Traversal.SUPER)) {
                final ClassInfo forName = ClassInfo.forName(classNode.superName);
                if (forName.isMixin()) {
                    for (final ClassInfo classInfo2 : forName.getTargets()) {
                        if (list.contains(classInfo2)) {
                            throw new InvalidMixinException(this.mixin, "Illegal hierarchy detected. Derived mixin " + this + " targets the same class " + classInfo2.getClassName() + " as its superclass " + forName.getClassName());
                        }
                    }
                }
                throw new InvalidMixinException(this.mixin, "Super class '" + classNode.superName.replace('/', '.') + "' of " + this.mixin.getName() + " was not found in the hierarchy of target class '" + classInfo + "'");
            }
            this.detached = true;
        }
    }
    
    @Override
    MixinPreProcessorStandard createPreProcessor(final MixinClassNode mixinClassNode) {
        return new MixinPreProcessorStandard(this.mixin, mixinClassNode);
    }
}
