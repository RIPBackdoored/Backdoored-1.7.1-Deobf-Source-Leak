package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import javax.lang.model.element.VariableElement;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.element.ExecutableElement;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import com.google.common.base.Strings;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import java.util.Iterator;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class AnnotatedMixinElementHandlerAccessor extends AnnotatedMixinElementHandler implements IMixinContext
{
    public AnnotatedMixinElementHandlerAccessor(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    @Override
    public ReferenceMapper getReferenceMapper() {
        return null;
    }
    
    @Override
    public String getClassName() {
        return this.mixin.getClassRef().replace('/', '.');
    }
    
    @Override
    public String getClassRef() {
        return this.mixin.getClassRef();
    }
    
    @Override
    public String getTargetClassRef() {
        throw new UnsupportedOperationException("Target class not available at compile time");
    }
    
    @Override
    public IMixinInfo getMixin() {
        throw new UnsupportedOperationException("MixinInfo not available at compile time");
    }
    
    @Override
    public Extensions getExtensions() {
        throw new UnsupportedOperationException("Mixin Extensions not available at compile time");
    }
    
    @Override
    public boolean getOption(final MixinEnvironment.Option option) {
        throw new UnsupportedOperationException("Options not available at compile time");
    }
    
    @Override
    public int getPriority() {
        throw new UnsupportedOperationException("Priority not available at compile time");
    }
    
    @Override
    public Target getTargetMethod(final MethodNode methodNode) {
        throw new UnsupportedOperationException("Target not available at compile time");
    }
    
    public void registerAccessor(final AnnotatedElementAccessor annotatedElementAccessor) {
        if (annotatedElementAccessor.getAccessorType() == null) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unsupported accessor type");
            return;
        }
        final String accessorTargetName = this.getAccessorTargetName(annotatedElementAccessor);
        if (accessorTargetName == null) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Cannot inflect accessor target name");
            return;
        }
        annotatedElementAccessor.setTargetName(accessorTargetName);
        for (final TypeHandle typeHandle : this.mixin.getTargets()) {
            if (annotatedElementAccessor.getAccessorType() == AccessorInfo.AccessorType.METHOD_PROXY) {
                this.registerInvokerForTarget((AnnotatedElementInvoker)annotatedElementAccessor, typeHandle);
            }
            else {
                this.registerAccessorForTarget(annotatedElementAccessor, typeHandle);
            }
        }
    }
    
    private void registerAccessorForTarget(final AnnotatedElementAccessor annotatedElementAccessor, final TypeHandle typeHandle) {
        FieldHandle field = typeHandle.findField(annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getTargetTypeName(), false);
        if (field == null) {
            if (!typeHandle.isImaginary()) {
                annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.ERROR, "Could not locate @Accessor target " + annotatedElementAccessor + " in target " + typeHandle);
                return;
            }
            field = new FieldHandle(typeHandle.getName(), annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getDesc());
        }
        if (!annotatedElementAccessor.shouldRemap()) {
            return;
        }
        final ObfuscationData<MappingField> obfField = this.obf.getDataProvider().getObfField(field.asMapping(false).move(typeHandle.getName()));
        if (obfField.isEmpty()) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + (this.mixin.isMultiTarget() ? (" in target " + typeHandle) : "") + " for @Accessor target " + annotatedElementAccessor);
            return;
        }
        final ObfuscationData<MappingField> stripOwnerData = AnnotatedMixinElementHandler.<MappingField>stripOwnerData(obfField);
        try {
            this.obf.getReferenceManager().addFieldMapping(this.mixin.getClassRef(), annotatedElementAccessor.getTargetName(), annotatedElementAccessor.getContext(), stripOwnerData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            annotatedElementAccessor.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Accessor target " + annotatedElementAccessor + ": " + ex.getNew() + " for target " + typeHandle + " conflicts with existing mapping " + ex.getOld());
        }
    }
    
    private void registerInvokerForTarget(final AnnotatedElementInvoker annotatedElementInvoker, final TypeHandle typeHandle) {
        MethodHandle method = typeHandle.findMethod(annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getTargetTypeName(), false);
        if (method == null) {
            if (!typeHandle.isImaginary()) {
                annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.ERROR, "Could not locate @Invoker target " + annotatedElementInvoker + " in target " + typeHandle);
                return;
            }
            method = new MethodHandle(typeHandle, annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getDesc());
        }
        if (!annotatedElementInvoker.shouldRemap()) {
            return;
        }
        final ObfuscationData<MappingMethod> obfMethod = this.obf.getDataProvider().getObfMethod(method.asMapping(false).move(typeHandle.getName()));
        if (obfMethod.isEmpty()) {
            annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + (this.mixin.isMultiTarget() ? (" in target " + typeHandle) : "") + " for @Accessor target " + annotatedElementInvoker);
            return;
        }
        final ObfuscationData<MappingMethod> stripOwnerData = AnnotatedMixinElementHandler.<MappingMethod>stripOwnerData(obfMethod);
        try {
            this.obf.getReferenceManager().addMethodMapping(this.mixin.getClassRef(), annotatedElementInvoker.getTargetName(), annotatedElementInvoker.getContext(), stripOwnerData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            annotatedElementInvoker.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Invoker target " + annotatedElementInvoker + ": " + ex.getNew() + " for target " + typeHandle + " conflicts with existing mapping " + ex.getOld());
        }
    }
    
    private String getAccessorTargetName(final AnnotatedElementAccessor annotatedElementAccessor) {
        final String annotationValue = annotatedElementAccessor.getAnnotationValue();
        if (Strings.isNullOrEmpty(annotationValue)) {
            return this.inflectAccessorTarget(annotatedElementAccessor);
        }
        return annotationValue;
    }
    
    private String inflectAccessorTarget(final AnnotatedElementAccessor annotatedElementAccessor) {
        return AccessorInfo.inflectTarget(annotatedElementAccessor.getSimpleName(), annotatedElementAccessor.getAccessorType(), "", this, false);
    }
    
    @Override
    public /* bridge */ IReferenceMapper getReferenceMapper() {
        return this.getReferenceMapper();
    }
}
