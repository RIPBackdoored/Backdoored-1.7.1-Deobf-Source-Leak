package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.common.base.Joiner;
import org.spongepowered.asm.mixin.injection.points.BeforeConstant;
import org.spongepowered.asm.mixin.injection.points.BeforeFinalReturn;
import org.spongepowered.asm.mixin.injection.modify.AfterStoreLocal;
import org.spongepowered.asm.mixin.injection.modify.BeforeLoadLocal;
import org.spongepowered.asm.mixin.injection.points.AfterInvoke;
import org.spongepowered.asm.mixin.injection.points.MethodHead;
import org.spongepowered.asm.mixin.injection.points.JumpInsnPoint;
import org.spongepowered.asm.mixin.injection.points.BeforeStringInvoke;
import org.spongepowered.asm.mixin.injection.points.BeforeReturn;
import org.spongepowered.asm.mixin.injection.points.BeforeNew;
import org.spongepowered.asm.mixin.injection.points.BeforeInvoke;
import org.spongepowered.asm.mixin.injection.points.BeforeFieldAccess;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.lang.reflect.Constructor;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Annotations;
import java.util.Arrays;
import java.util.Iterator;
import com.google.common.collect.ImmutableList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import java.util.List;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import java.util.Map;

public abstract class InjectionPoint
{
    public static final int DEFAULT_ALLOWED_SHIFT_BY = 0;
    public static final int MAX_ALLOWED_SHIFT_BY = 5;
    private static Map<String, Class<? extends InjectionPoint>> types;
    private final String slice;
    private final Selector selector;
    private final String id;
    
    protected InjectionPoint() {
        this("", Selector.DEFAULT, null);
    }
    
    protected InjectionPoint(final InjectionPointData injectionPointData) {
        this(injectionPointData.getSlice(), injectionPointData.getSelector(), injectionPointData.getId());
    }
    
    public InjectionPoint(final String slice, final Selector selector, final String id) {
        super();
        this.slice = slice;
        this.selector = selector;
        this.id = id;
    }
    
    public String getSlice() {
        return this.slice;
    }
    
    public Selector getSelector() {
        return this.selector;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean checkPriority(final int n, final int n2) {
        return n < n2;
    }
    
    public abstract boolean find(final String p0, final InsnList p1, final Collection<AbstractInsnNode> p2);
    
    @Override
    public String toString() {
        return String.format("@At(\"%s\")", this.getAtCode());
    }
    
    protected static AbstractInsnNode nextNode(final InsnList list, final AbstractInsnNode abstractInsnNode) {
        final int n = list.indexOf(abstractInsnNode) + 1;
        if (n > 0 && n < list.size()) {
            return list.get(n);
        }
        return abstractInsnNode;
    }
    
    public static InjectionPoint and(final InjectionPoint... array) {
        return new Intersection(array);
    }
    
    public static InjectionPoint or(final InjectionPoint... array) {
        return new Union(array);
    }
    
    public static InjectionPoint after(final InjectionPoint injectionPoint) {
        return new Shift(injectionPoint, 1);
    }
    
    public static InjectionPoint before(final InjectionPoint injectionPoint) {
        return new Shift(injectionPoint, -1);
    }
    
    public static InjectionPoint shift(final InjectionPoint injectionPoint, final int n) {
        return new Shift(injectionPoint, n);
    }
    
    public static List<InjectionPoint> parse(final IInjectionPointContext injectionPointContext, final List<AnnotationNode> list) {
        return parse(injectionPointContext.getContext(), injectionPointContext.getMethod(), injectionPointContext.getAnnotation(), list);
    }
    
    public static List<InjectionPoint> parse(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final List<AnnotationNode> list) {
        final ImmutableList.Builder builder = ImmutableList.builder();
        final Iterator<AnnotationNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            final InjectionPoint parse = parse(mixinContext, methodNode, annotationNode, iterator.next());
            if (parse != null) {
                builder.add(parse);
            }
        }
        return builder.build();
    }
    
    public static InjectionPoint parse(final IInjectionPointContext injectionPointContext, final At at) {
        return parse(injectionPointContext.getContext(), injectionPointContext.getMethod(), injectionPointContext.getAnnotation(), at.value(), at.shift(), at.by(), Arrays.<String>asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode(), at.id());
    }
    
    public static InjectionPoint parse(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final At at) {
        return parse(mixinContext, methodNode, annotationNode, at.value(), at.shift(), at.by(), Arrays.<String>asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode(), at.id());
    }
    
    public static InjectionPoint parse(final IInjectionPointContext injectionPointContext, final AnnotationNode annotationNode) {
        return parse(injectionPointContext.getContext(), injectionPointContext.getMethod(), injectionPointContext.getAnnotation(), annotationNode);
    }
    
    public static InjectionPoint parse(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final AnnotationNode annotationNode2) {
        final String s = Annotations.<String>getValue(annotationNode2, "value");
        Object of = Annotations.<List<String>>getValue(annotationNode2, "args");
        final String s2 = Annotations.<String>getValue(annotationNode2, "target", "");
        final String s3 = Annotations.<String>getValue(annotationNode2, "slice", "");
        final At.Shift shift = Annotations.<At.Shift>getValue(annotationNode2, "shift", At.Shift.class, At.Shift.NONE);
        final int intValue = Annotations.<Integer>getValue(annotationNode2, "by", 0);
        final int intValue2 = Annotations.<Integer>getValue(annotationNode2, "ordinal", -1);
        final int intValue3 = Annotations.<Integer>getValue(annotationNode2, "opcode", 0);
        final String s4 = Annotations.<String>getValue(annotationNode2, "id");
        if (of == null) {
            of = ImmutableList.<Object>of();
        }
        return parse(mixinContext, methodNode, annotationNode, s, shift, intValue, (List<String>)of, s2, s3, intValue2, intValue3, s4);
    }
    
    public static InjectionPoint parse(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final String s, final At.Shift shift, final int n, final List<String> list, final String s2, final String s3, final int n2, final int n3, final String s4) {
        final InjectionPointData injectionPointData = new InjectionPointData(mixinContext, methodNode, annotationNode, s, list, s2, s3, n2, n3, s4);
        return shift(mixinContext, methodNode, annotationNode, create(mixinContext, injectionPointData, findClass(mixinContext, injectionPointData)), shift, n);
    }
    
    private static Class<? extends InjectionPoint> findClass(final IMixinContext mixinContext, final InjectionPointData injectionPointData) {
        final String type = injectionPointData.getType();
        Class<?> forName = InjectionPoint.types.get(type);
        if (forName == null) {
            if (type.matches("^([A-Za-z_][A-Za-z0-9_]*\\.)+[A-Za-z_][A-Za-z0-9_]*$")) {
                try {
                    forName = Class.forName(type);
                    InjectionPoint.types.put(type, (Class<? extends InjectionPoint>)forName);
                    return (Class<? extends InjectionPoint>)forName;
                }
                catch (Exception ex) {
                    throw new InvalidInjectionException(mixinContext, injectionPointData + " could not be loaded or is not a valid InjectionPoint", ex);
                }
            }
            throw new InvalidInjectionException(mixinContext, injectionPointData + " is not a valid injection point specifier");
        }
        return (Class<? extends InjectionPoint>)forName;
    }
    
    private static InjectionPoint create(final IMixinContext mixinContext, final InjectionPointData injectionPointData, final Class<? extends InjectionPoint> clazz) {
        Constructor<? extends InjectionPoint> declaredConstructor;
        try {
            declaredConstructor = clazz.getDeclaredConstructor(InjectionPointData.class);
            declaredConstructor.setAccessible(true);
        }
        catch (NoSuchMethodException ex) {
            throw new InvalidInjectionException(mixinContext, clazz.getName() + " must contain a constructor which accepts an InjectionPointData", ex);
        }
        InjectionPoint injectionPoint;
        try {
            injectionPoint = (InjectionPoint)declaredConstructor.newInstance(injectionPointData);
        }
        catch (Exception ex2) {
            throw new InvalidInjectionException(mixinContext, "Error whilst instancing injection point " + clazz.getName() + " for " + injectionPointData.getAt(), ex2);
        }
        return injectionPoint;
    }
    
    private static InjectionPoint shift(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final InjectionPoint injectionPoint, final At.Shift shift, final int n) {
        if (injectionPoint != null) {
            if (shift == At.Shift.BEFORE) {
                return before(injectionPoint);
            }
            if (shift == At.Shift.AFTER) {
                return after(injectionPoint);
            }
            if (shift == At.Shift.BY) {
                validateByValue(mixinContext, methodNode, annotationNode, injectionPoint, n);
                return shift(injectionPoint, n);
            }
        }
        return injectionPoint;
    }
    
    private static void validateByValue(final IMixinContext mixinContext, final MethodNode methodNode, final AnnotationNode annotationNode, final InjectionPoint injectionPoint, final int n) {
        final ShiftByViolationBehaviour shiftByViolationBehaviour = mixinContext.getMixin().getConfig().getEnvironment().<ShiftByViolationBehaviour>getOption(MixinEnvironment.Option.SHIFT_BY_VIOLATION_BEHAVIOUR, ShiftByViolationBehaviour.WARN);
        if (shiftByViolationBehaviour == ShiftByViolationBehaviour.IGNORE) {
            return;
        }
        String s = "the maximum allowed value: ";
        String s2 = "Increase the value of maxShiftBy to suppress this warning.";
        int maxShiftByValue = 0;
        if (mixinContext instanceof MixinTargetContext) {
            maxShiftByValue = ((MixinTargetContext)mixinContext).getMaxShiftByValue();
        }
        if (n <= maxShiftByValue) {
            return;
        }
        if (n > 5) {
            s = "MAX_ALLOWED_SHIFT_BY=";
            s2 = "You must use an alternate query or a custom injection point.";
            maxShiftByValue = 5;
        }
        final String format = String.format("@%s(%s) Shift.BY=%d on %s::%s exceeds %s%d. %s", Bytecode.getSimpleName(annotationNode), injectionPoint, n, mixinContext, methodNode.name, s, maxShiftByValue, s2);
        if (shiftByViolationBehaviour == ShiftByViolationBehaviour.WARN && maxShiftByValue < 5) {
            LogManager.getLogger("mixin").warn(format);
            return;
        }
        throw new InvalidInjectionException(mixinContext, format);
    }
    
    protected String getAtCode() {
        final AtCode atCode = this.getClass().<AtCode>getAnnotation(AtCode.class);
        return (atCode == null) ? this.getClass().getName() : atCode.value();
    }
    
    public static void register(final Class<? extends InjectionPoint> clazz) {
        final AtCode atCode = clazz.<AtCode>getAnnotation(AtCode.class);
        if (atCode == null) {
            throw new IllegalArgumentException("Injection point class " + clazz + " is not annotated with @AtCode");
        }
        final Class<? extends InjectionPoint> clazz2 = InjectionPoint.types.get(atCode.value());
        if (clazz2 != null && !clazz2.equals(clazz)) {
            LogManager.getLogger("mixin").debug("Overriding InjectionPoint {} with {} (previously {})", new Object[] { atCode.value(), clazz.getName(), clazz2.getName() });
        }
        InjectionPoint.types.put(atCode.value(), clazz);
    }
    
    static {
        InjectionPoint.types = new HashMap<String, Class<? extends InjectionPoint>>();
        register(BeforeFieldAccess.class);
        register(BeforeInvoke.class);
        register(BeforeNew.class);
        register(BeforeReturn.class);
        register(BeforeStringInvoke.class);
        register(JumpInsnPoint.class);
        register(MethodHead.class);
        register(AfterInvoke.class);
        register(BeforeLoadLocal.class);
        register(AfterStoreLocal.class);
        register(BeforeFinalReturn.class);
        register(BeforeConstant.class);
    }
}
