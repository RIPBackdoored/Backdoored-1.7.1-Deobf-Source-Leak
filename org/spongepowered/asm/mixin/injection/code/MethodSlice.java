package org.spongepowered.asm.mixin.injection.code;

import java.util.NoSuchElementException;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.IInjectionPointContext;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.LinkedList;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.lib.tree.MethodNode;
import com.google.common.base.Strings;
import org.spongepowered.asm.mixin.injection.throwables.InvalidSliceException;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.apache.logging.log4j.Logger;

public final class MethodSlice
{
    private static final Logger logger;
    private final ISliceContext owner;
    private final String id;
    private final InjectionPoint from;
    private final InjectionPoint to;
    private final String name;
    
    private MethodSlice(final ISliceContext owner, final String s, final InjectionPoint from, final InjectionPoint to) {
        super();
        if (from == null && to == null) {
            throw new InvalidSliceException(owner, String.format("%s is redundant. No 'from' or 'to' value specified", this));
        }
        this.owner = owner;
        this.id = Strings.nullToEmpty(s);
        this.from = from;
        this.to = to;
        this.name = getSliceName(s);
    }
    
    public String getId() {
        return this.id;
    }
    
    public ReadOnlyInsnList getSlice(final MethodNode methodNode) {
        final int n = methodNode.instructions.size() - 1;
        final int find = this.find(methodNode, this.from, 0, 0, this.name + "(from)");
        final int find2 = this.find(methodNode, this.to, n, find, this.name + "(to)");
        if (find > find2) {
            throw new InvalidSliceException(this.owner, String.format("%s is negative size. Range(%d -> %d)", this.describe(), find, find2));
        }
        if (find < 0 || find2 < 0 || find > n || find2 > n) {
            throw new InjectionError("Unexpected critical error in " + this + ": out of bounds start=" + find + " end=" + find2 + " lim=" + n);
        }
        if (find2 == n) {
            return new ReadOnlyInsnList(methodNode.instructions);
        }
        return new InsnListSlice(methodNode.instructions, find, find2);
    }
    
    private int find(final MethodNode methodNode, final InjectionPoint injectionPoint, final int n, final int n2, final String s) {
        if (injectionPoint == null) {
            return n;
        }
        final LinkedList<AbstractInsnNode> list = new LinkedList<AbstractInsnNode>();
        injectionPoint.find(methodNode.desc, new ReadOnlyInsnList(methodNode.instructions), list);
        final InjectionPoint.Selector selector = injectionPoint.getSelector();
        if (list.size() != 1 && selector == InjectionPoint.Selector.ONE) {
            throw new InvalidSliceException(this.owner, String.format("%s requires 1 result but found %d", this.describe(s), list.size()));
        }
        if (this.owner.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            MethodSlice.logger.warn("{} did not match any instructions", new Object[] { this.describe(s) });
        }
        return n2;
    }
    
    @Override
    public String toString() {
        return this.describe();
    }
    
    private String describe() {
        return this.describe(this.name);
    }
    
    private String describe(final String s) {
        return describeSlice(s, this.owner);
    }
    
    private static String describeSlice(final String s, final ISliceContext sliceContext) {
        final String simpleName = Bytecode.getSimpleName(sliceContext.getAnnotation());
        final MethodNode method = sliceContext.getMethod();
        return String.format("%s->%s(%s)::%s%s", sliceContext.getContext(), simpleName, s, method.name, method.desc);
    }
    
    private static String getSliceName(final String s) {
        return String.format("@Slice[%s]", Strings.nullToEmpty(s));
    }
    
    public static MethodSlice parse(final ISliceContext sliceContext, final Slice slice) {
        final String id = slice.id();
        final At from = slice.from();
        final At to = slice.to();
        return new MethodSlice(sliceContext, id, (from != null) ? InjectionPoint.parse(sliceContext, from) : null, (to != null) ? InjectionPoint.parse(sliceContext, to) : null);
    }
    
    public static MethodSlice parse(final ISliceContext sliceContext, final AnnotationNode annotationNode) {
        final String s = Annotations.<String>getValue(annotationNode, "id");
        final AnnotationNode annotationNode2 = Annotations.<AnnotationNode>getValue(annotationNode, "from");
        final AnnotationNode annotationNode3 = Annotations.<AnnotationNode>getValue(annotationNode, "to");
        return new MethodSlice(sliceContext, s, (annotationNode2 != null) ? InjectionPoint.parse(sliceContext, annotationNode2) : null, (annotationNode3 != null) ? InjectionPoint.parse(sliceContext, annotationNode3) : null);
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
