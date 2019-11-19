package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.util.SignaturePrinter;
import java.util.HashMap;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.lib.Type;
import java.util.Collections;
import java.util.Set;

public class LocalVariableDiscriminator
{
    private final boolean argsOnly;
    private final int ordinal;
    private final int index;
    private final Set<String> names;
    private final boolean print;
    
    public LocalVariableDiscriminator(final boolean argsOnly, final int ordinal, final int index, final Set<String> set, final boolean print) {
        super();
        this.argsOnly = argsOnly;
        this.ordinal = ordinal;
        this.index = index;
        this.names = Collections.<String>unmodifiableSet((Set<? extends String>)set);
        this.print = print;
    }
    
    public boolean isArgsOnly() {
        return this.argsOnly;
    }
    
    public int getOrdinal() {
        return this.ordinal;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public Set<String> getNames() {
        return this.names;
    }
    
    public boolean hasNames() {
        return !this.names.isEmpty();
    }
    
    public boolean printLVT() {
        return this.print;
    }
    
    protected boolean isImplicit(final Context context) {
        return this.ordinal < 0 && this.index < context.baseArgIndex && this.names.isEmpty();
    }
    
    public int findLocal(final Type type, final boolean b, final Target target, final AbstractInsnNode abstractInsnNode) {
        try {
            return this.findLocal(new Context(type, b, target, abstractInsnNode));
        }
        catch (InvalidImplicitDiscriminatorException ex) {
            return -2;
        }
    }
    
    public int findLocal(final Context context) {
        if (this.isImplicit(context)) {
            return this.findImplicitLocal(context);
        }
        return this.findExplicitLocal(context);
    }
    
    private int findImplicitLocal(final Context context) {
        int n = 0;
        int n2 = 0;
        for (int i = context.baseArgIndex; i < context.locals.length; ++i) {
            final Context.Local local = context.locals[i];
            if (local != null) {
                if (local.type.equals(context.returnType)) {
                    ++n2;
                    n = i;
                }
            }
        }
        if (n2 == 1) {
            return n;
        }
        throw new InvalidImplicitDiscriminatorException("Found " + n2 + " candidate variables but exactly 1 is required.");
    }
    
    private int findExplicitLocal(final Context context) {
        for (int i = context.baseArgIndex; i < context.locals.length; ++i) {
            final Context.Local local = context.locals[i];
            if (local != null) {
                if (local.type.equals(context.returnType)) {
                    if (this.ordinal > -1) {
                        if (this.ordinal == local.ord) {
                            return i;
                        }
                    }
                    else if (this.index >= context.baseArgIndex) {
                        if (this.index == i) {
                            return i;
                        }
                    }
                    else if (this.names.contains(local.name)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static LocalVariableDiscriminator parse(final AnnotationNode annotationNode) {
        final boolean booleanValue = Annotations.<Boolean>getValue(annotationNode, "argsOnly", Boolean.FALSE);
        final int intValue = Annotations.<Integer>getValue(annotationNode, "ordinal", -1);
        final int intValue2 = Annotations.<Integer>getValue(annotationNode, "index", -1);
        final boolean booleanValue2 = Annotations.<Boolean>getValue(annotationNode, "print", Boolean.FALSE);
        final HashSet<Object> set = new HashSet<Object>();
        final List<? extends String> list = Annotations.<List<? extends String>>getValue(annotationNode, "name", (List<? extends String>)null);
        if (list != null) {
            set.addAll(list);
        }
        return new LocalVariableDiscriminator(booleanValue, intValue, intValue2, (Set<String>)set, booleanValue2);
    }
}
