package org.spongepowered.asm.mixin.injection.code;

import java.util.Collections;
import java.util.HashSet;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import java.util.TreeMap;
import java.util.Collection;
import org.spongepowered.asm.mixin.MixinEnvironment;
import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import java.util.Iterator;
import java.util.ArrayList;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import java.util.List;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.apache.logging.log4j.Logger;

public abstract class Injector
{
    protected static final Logger logger;
    protected InjectionInfo info;
    protected final ClassNode classNode;
    protected final MethodNode methodNode;
    protected final Type[] methodArgs;
    protected final Type returnType;
    protected final boolean isStatic;
    
    public Injector(final InjectionInfo info) {
        this(info.getClassNode(), info.getMethod());
        this.info = info;
    }
    
    private Injector(final ClassNode classNode, final MethodNode methodNode) {
        super();
        this.classNode = classNode;
        this.methodNode = methodNode;
        this.methodArgs = Type.getArgumentTypes(this.methodNode.desc);
        this.returnType = Type.getReturnType(this.methodNode.desc);
        this.isStatic = Bytecode.methodIsStatic(this.methodNode);
    }
    
    @Override
    public String toString() {
        return String.format("%s::%s", this.classNode.name, this.methodNode.name);
    }
    
    public final List<InjectionNodes.InjectionNode> find(final InjectorTarget injectorTarget, final List<InjectionPoint> list) {
        this.sanityCheck(injectorTarget.getTarget(), list);
        final ArrayList<InjectionNodes.InjectionNode> list2 = new ArrayList<InjectionNodes.InjectionNode>();
        for (final TargetNode targetNode : this.findTargetNodes(injectorTarget, list)) {
            this.addTargetNode(injectorTarget.getTarget(), list2, targetNode.insn, targetNode.nominators);
        }
        return list2;
    }
    
    protected void addTargetNode(final Target target, final List<InjectionNodes.InjectionNode> list, final AbstractInsnNode abstractInsnNode, final Set<InjectionPoint> set) {
        list.add(target.addInjectionNode(abstractInsnNode));
    }
    
    public final void inject(final Target target, final List<InjectionNodes.InjectionNode> list) {
        for (final InjectionNodes.InjectionNode injectionNode : list) {
            if (injectionNode.isRemoved()) {
                if (!this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                    continue;
                }
                Injector.logger.warn("Target node for {} was removed by a previous injector in {}", new Object[] { this.info, target });
            }
            else {
                this.inject(target, injectionNode);
            }
        }
        final Iterator<InjectionNodes.InjectionNode> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            this.postInject(target, iterator2.next());
        }
    }
    
    private Collection<TargetNode> findTargetNodes(final InjectorTarget injectorTarget, final List<InjectionPoint> list) {
        final IMixinContext context = this.info.getContext();
        final MethodNode method = injectorTarget.getMethod();
        final TreeMap<Object, TargetNode> treeMap = (TreeMap<Object, TargetNode>)new TreeMap<Object, Object>();
        final ArrayList<AbstractInsnNode> list2 = new ArrayList<AbstractInsnNode>(32);
        for (final InjectionPoint injectionPoint : list) {
            list2.clear();
            if (injectorTarget.isMerged() && !context.getClassName().equals(injectorTarget.getMergedBy()) && !injectionPoint.checkPriority(injectorTarget.getMergedPriority(), context.getPriority())) {
                throw new InvalidInjectionException(this.info, String.format("%s on %s with priority %d cannot inject into %s merged by %s with priority %d", injectionPoint, this, context.getPriority(), injectorTarget, injectorTarget.getMergedBy(), injectorTarget.getMergedPriority()));
            }
            if (!this.findTargetNodes(method, injectionPoint, injectorTarget.getSlice(injectionPoint), list2)) {
                continue;
            }
            for (final AbstractInsnNode abstractInsnNode : list2) {
                final Integer value = method.instructions.indexOf(abstractInsnNode);
                TargetNode targetNode = treeMap.get(value);
                if (targetNode == null) {
                    targetNode = new TargetNode(abstractInsnNode);
                    treeMap.put(value, targetNode);
                }
                targetNode.nominators.add(injectionPoint);
            }
        }
        return treeMap.values();
    }
    
    protected boolean findTargetNodes(final MethodNode methodNode, final InjectionPoint injectionPoint, final InsnList list, final Collection<AbstractInsnNode> collection) {
        return injectionPoint.find(methodNode.desc, list, collection);
    }
    
    protected void sanityCheck(final Target target, final List<InjectionPoint> list) {
        if (target.classNode != this.classNode) {
            throw new InvalidInjectionException(this.info, "Target class does not match injector class in " + this);
        }
    }
    
    protected abstract void inject(final Target p0, final InjectionNodes.InjectionNode p1);
    
    protected void postInject(final Target target, final InjectionNodes.InjectionNode injectionNode) {
    }
    
    protected AbstractInsnNode invokeHandler(final InsnList list) {
        return this.invokeHandler(list, this.methodNode);
    }
    
    protected AbstractInsnNode invokeHandler(final InsnList list, final MethodNode methodNode) {
        final boolean b = (methodNode.access & 0x2) != 0x0;
        final MethodInsnNode methodInsnNode = new MethodInsnNode(this.isStatic ? 184 : (b ? 183 : 182), this.classNode.name, methodNode.name, methodNode.desc, false);
        list.add(methodInsnNode);
        this.info.addCallbackInvocation(methodNode);
        return methodInsnNode;
    }
    
    protected void throwException(final InsnList list, final String s, final String s2) {
        list.add(new TypeInsnNode(187, s));
        list.add(new InsnNode(89));
        list.add(new LdcInsnNode(s2));
        list.add(new MethodInsnNode(183, s, "<init>", "(Ljava/lang/String;)V", false));
        list.add(new InsnNode(191));
    }
    
    public static boolean canCoerce(final Type type, final Type type2) {
        if (type.getSort() == 10 && type2.getSort() == 10) {
            return canCoerce(ClassInfo.forType(type), ClassInfo.forType(type2));
        }
        return canCoerce(type.getDescriptor(), type2.getDescriptor());
    }
    
    public static boolean canCoerce(final String s, final String s2) {
        return s.length() <= 1 && s2.length() <= 1 && canCoerce(s.charAt(0), s2.charAt(0));
    }
    
    public static boolean canCoerce(final char c, final char c2) {
        return c2 == 'I' && "IBSCZ".indexOf(c) > -1;
    }
    
    private static boolean canCoerce(final ClassInfo classInfo, final ClassInfo classInfo2) {
        return classInfo != null && classInfo2 != null && (classInfo2 == classInfo || classInfo2.hasSuperClass(classInfo));
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
