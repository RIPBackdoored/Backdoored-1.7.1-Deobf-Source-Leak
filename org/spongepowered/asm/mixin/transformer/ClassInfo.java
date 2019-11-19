package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.lib.Type;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.Collections;
import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.HashSet;
import org.spongepowered.asm.util.ClassSignature;
import java.util.Set;
import java.util.Map;
import org.spongepowered.asm.util.perf.Profiler;
import org.apache.logging.log4j.Logger;

public final class ClassInfo
{
    public static final int INCLUDE_PRIVATE = 2;
    public static final int INCLUDE_STATIC = 8;
    public static final int INCLUDE_ALL = 10;
    private static final Logger logger;
    private static final Profiler profiler;
    private static final String JAVA_LANG_OBJECT = "java/lang/Object";
    private static final Map<String, ClassInfo> cache;
    private static final ClassInfo OBJECT;
    private final String name;
    private final String superName;
    private final String outerName;
    private final boolean isProbablyStatic;
    private final Set<String> interfaces;
    private final Set<Method> methods;
    private final Set<Field> fields;
    private final Set<MixinInfo> mixins;
    private final Map<ClassInfo, ClassInfo> correspondingTypes;
    private final MixinInfo mixin;
    private final MethodMapper methodMapper;
    private final boolean isMixin;
    private final boolean isInterface;
    private final int access;
    private ClassInfo superClass;
    private ClassInfo outerClass;
    private ClassSignature signature;
    
    private ClassInfo() {
        super();
        this.mixins = new HashSet<MixinInfo>();
        this.correspondingTypes = new HashMap<ClassInfo, ClassInfo>();
        this.name = "java/lang/Object";
        this.superName = null;
        this.outerName = null;
        this.isProbablyStatic = true;
        this.methods = (Set<Method>)ImmutableSet.of((Object)new Method("getClass", "()Ljava/lang/Class;"), (Object)new Method("hashCode", "()I"), (Object)new Method("equals", "(Ljava/lang/Object;)Z"), (Object)new Method("clone", "()Ljava/lang/Object;"), (Object)new Method("toString", "()Ljava/lang/String;"), (Object)new Method("notify", "()V"), (Object[])new Method[] { new Method("notifyAll", "()V"), new Method("wait", "(J)V"), new Method("wait", "(JI)V"), new Method("wait", "()V"), new Method("finalize", "()V") });
        this.fields = Collections.<Field>emptySet();
        this.isInterface = false;
        this.interfaces = Collections.<String>emptySet();
        this.access = 1;
        this.isMixin = false;
        this.mixin = null;
        this.methodMapper = null;
    }
    
    private ClassInfo(final ClassNode classNode) {
        super();
        this.mixins = new HashSet<MixinInfo>();
        this.correspondingTypes = new HashMap<ClassInfo, ClassInfo>();
        final Profiler.Section begin = ClassInfo.profiler.begin(1, "class.meta");
        try {
            this.name = classNode.name;
            this.superName = ((classNode.superName != null) ? classNode.superName : "java/lang/Object");
            this.methods = new HashSet<Method>();
            this.fields = new HashSet<Field>();
            this.isInterface = ((classNode.access & 0x200) != 0x0);
            this.interfaces = new HashSet<String>();
            this.access = classNode.access;
            this.isMixin = (classNode instanceof MixinInfo.MixinClassNode);
            this.mixin = (this.isMixin ? ((MixinInfo.MixinClassNode)classNode).getMixin() : null);
            this.interfaces.addAll(classNode.interfaces);
            final Iterator<MethodNode> iterator = classNode.methods.iterator();
            while (iterator.hasNext()) {
                this.addMethod(iterator.next(), this.isMixin);
            }
            boolean isProbablyStatic = true;
            String outerName = classNode.outerClass;
            for (final FieldNode fieldNode : classNode.fields) {
                if ((fieldNode.access & 0x1000) != 0x0 && fieldNode.name.startsWith("this$")) {
                    isProbablyStatic = false;
                    if (outerName == null) {
                        outerName = fieldNode.desc;
                        if (outerName != null && outerName.startsWith("L")) {
                            outerName = outerName.substring(1, outerName.length() - 1);
                        }
                    }
                }
                this.fields.add(new Field(fieldNode, this.isMixin));
            }
            this.isProbablyStatic = isProbablyStatic;
            this.outerName = outerName;
            this.methodMapper = new MethodMapper(MixinEnvironment.getCurrentEnvironment(), this);
            this.signature = ClassSignature.ofLazy(classNode);
        }
        finally {
            begin.end();
        }
    }
    
    void addInterface(final String s) {
        this.interfaces.add(s);
        this.getSignature().addInterface(s);
    }
    
    void addMethod(final MethodNode methodNode) {
        this.addMethod(methodNode, true);
    }
    
    private void addMethod(final MethodNode methodNode, final boolean b) {
        if (!methodNode.name.startsWith("<")) {
            this.methods.add(new Method(methodNode, b));
        }
    }
    
    void addMixin(final MixinInfo mixinInfo) {
        if (this.isMixin) {
            throw new IllegalArgumentException("Cannot add target " + this.name + " for " + mixinInfo.getClassName() + " because the target is a mixin");
        }
        this.mixins.add(mixinInfo);
    }
    
    public Set<MixinInfo> getMixins() {
        return Collections.<MixinInfo>unmodifiableSet((Set<? extends MixinInfo>)this.mixins);
    }
    
    public boolean isMixin() {
        return this.isMixin;
    }
    
    public boolean isPublic() {
        return (this.access & 0x1) != 0x0;
    }
    
    public boolean isAbstract() {
        return (this.access & 0x400) != 0x0;
    }
    
    public boolean isSynthetic() {
        return (this.access & 0x1000) != 0x0;
    }
    
    public boolean isProbablyStatic() {
        return this.isProbablyStatic;
    }
    
    public boolean isInner() {
        return this.outerName != null;
    }
    
    public boolean isInterface() {
        return this.isInterface;
    }
    
    public Set<String> getInterfaces() {
        return Collections.<String>unmodifiableSet((Set<? extends String>)this.interfaces);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public MethodMapper getMethodMapper() {
        return this.methodMapper;
    }
    
    public int getAccess() {
        return this.access;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getClassName() {
        return this.name.replace('/', '.');
    }
    
    public String getSuperName() {
        return this.superName;
    }
    
    public ClassInfo getSuperClass() {
        if (this.superClass == null && this.superName != null) {
            this.superClass = forName(this.superName);
        }
        return this.superClass;
    }
    
    public String getOuterName() {
        return this.outerName;
    }
    
    public ClassInfo getOuterClass() {
        if (this.outerClass == null && this.outerName != null) {
            this.outerClass = forName(this.outerName);
        }
        return this.outerClass;
    }
    
    public ClassSignature getSignature() {
        return this.signature.wake();
    }
    
    List<ClassInfo> getTargets() {
        if (this.mixin != null) {
            final ArrayList<Object> list = new ArrayList<Object>();
            list.add(this);
            list.addAll(this.mixin.getTargets());
            return (List<ClassInfo>)list;
        }
        return ImmutableList.<ClassInfo>of(this);
    }
    
    public Set<Method> getMethods() {
        return Collections.<Method>unmodifiableSet((Set<? extends Method>)this.methods);
    }
    
    public Set<Method> getInterfaceMethods(final boolean b) {
        final HashSet<Method> set = new HashSet<Method>();
        ClassInfo classInfo = this.addMethodsRecursive(set, b);
        if (!this.isInterface) {
            while (classInfo != null && classInfo != ClassInfo.OBJECT) {
                classInfo = classInfo.addMethodsRecursive(set, b);
            }
        }
        final Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isAbstract()) {
                iterator.remove();
            }
        }
        return (Set<Method>)Collections.<Object>unmodifiableSet((Set<?>)set);
    }
    
    private ClassInfo addMethodsRecursive(final Set<Method> set, final boolean b) {
        if (this.isInterface) {
            for (final Method method : this.methods) {
                if (!method.isAbstract()) {
                    set.remove(method);
                }
                set.add(method);
            }
        }
        else if (!this.isMixin && b) {
            final Iterator<MixinInfo> iterator2 = this.mixins.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().getClassInfo().addMethodsRecursive(set, b);
            }
        }
        final Iterator<String> iterator3 = this.interfaces.iterator();
        while (iterator3.hasNext()) {
            forName(iterator3.next()).addMethodsRecursive(set, b);
        }
        return this.getSuperClass();
    }
    
    public boolean hasSuperClass(final String s) {
        return this.hasSuperClass(s, Traversal.NONE);
    }
    
    public boolean hasSuperClass(final String s, final Traversal traversal) {
        return "java/lang/Object".equals(s) || this.findSuperClass(s, traversal) != null;
    }
    
    public boolean hasSuperClass(final ClassInfo classInfo) {
        return this.hasSuperClass(classInfo, Traversal.NONE, false);
    }
    
    public boolean hasSuperClass(final ClassInfo classInfo, final Traversal traversal) {
        return this.hasSuperClass(classInfo, traversal, false);
    }
    
    public boolean hasSuperClass(final ClassInfo classInfo, final Traversal traversal, final boolean b) {
        return ClassInfo.OBJECT == classInfo || this.findSuperClass(classInfo.name, traversal, b) != null;
    }
    
    public ClassInfo findSuperClass(final String s) {
        return this.findSuperClass(s, Traversal.NONE);
    }
    
    public ClassInfo findSuperClass(final String s, final Traversal traversal) {
        return this.findSuperClass(s, traversal, false, new HashSet<String>());
    }
    
    public ClassInfo findSuperClass(final String s, final Traversal traversal, final boolean b) {
        if (ClassInfo.OBJECT.name.equals(s)) {
            return null;
        }
        return this.findSuperClass(s, traversal, b, new HashSet<String>());
    }
    
    private ClassInfo findSuperClass(final String s, final Traversal traversal, final boolean b, final Set<String> set) {
        final ClassInfo superClass = this.getSuperClass();
        if (superClass != null) {
            for (final ClassInfo classInfo : superClass.getTargets()) {
                if (s.equals(classInfo.getName())) {
                    return superClass;
                }
                final ClassInfo superClass2 = classInfo.findSuperClass(s, traversal.next(), b, set);
                if (superClass2 != null) {
                    return superClass2;
                }
            }
        }
        if (b) {
            final ClassInfo interface1 = this.findInterface(s);
            if (interface1 != null) {
                return interface1;
            }
        }
        if (traversal.canTraverse()) {
            for (final MixinInfo mixinInfo : this.mixins) {
                final String className = mixinInfo.getClassName();
                if (set.contains(className)) {
                    continue;
                }
                set.add(className);
                final ClassInfo classInfo2 = mixinInfo.getClassInfo();
                if (s.equals(classInfo2.getName())) {
                    return classInfo2;
                }
                final ClassInfo superClass3 = classInfo2.findSuperClass(s, Traversal.ALL, b, set);
                if (superClass3 != null) {
                    return superClass3;
                }
            }
        }
        return null;
    }
    
    private ClassInfo findInterface(final String s) {
        for (final String s2 : this.getInterfaces()) {
            final ClassInfo forName = forName(s2);
            if (s.equals(s2)) {
                return forName;
            }
            final ClassInfo interface1 = forName.findInterface(s);
            if (interface1 != null) {
                return interface1;
            }
        }
        return null;
    }
    
    ClassInfo findCorrespondingType(final ClassInfo classInfo) {
        if (classInfo == null || !classInfo.isMixin || this.isMixin) {
            return null;
        }
        ClassInfo superTypeForMixin = this.correspondingTypes.get(classInfo);
        if (superTypeForMixin == null) {
            superTypeForMixin = this.findSuperTypeForMixin(classInfo);
            this.correspondingTypes.put(classInfo, superTypeForMixin);
        }
        return superTypeForMixin;
    }
    
    private ClassInfo findSuperTypeForMixin(final ClassInfo classInfo) {
        for (ClassInfo superClass = this; superClass != null && superClass != ClassInfo.OBJECT; superClass = superClass.getSuperClass()) {
            final Iterator<MixinInfo> iterator = superClass.mixins.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getClassInfo().equals(classInfo)) {
                    return superClass;
                }
            }
        }
        return null;
    }
    
    public boolean hasMixinInHierarchy() {
        if (!this.isMixin) {
            return false;
        }
        for (ClassInfo classInfo = this.getSuperClass(); classInfo != null && classInfo != ClassInfo.OBJECT; classInfo = classInfo.getSuperClass()) {
            if (classInfo.isMixin) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasMixinTargetInHierarchy() {
        if (this.isMixin) {
            return false;
        }
        for (ClassInfo classInfo = this.getSuperClass(); classInfo != null && classInfo != ClassInfo.OBJECT; classInfo = classInfo.getSuperClass()) {
            if (classInfo.mixins.size() > 0) {
                return true;
            }
        }
        return false;
    }
    
    public Method findMethodInHierarchy(final MethodNode methodNode, final SearchType searchType) {
        return this.findMethodInHierarchy(methodNode.name, methodNode.desc, searchType, Traversal.NONE);
    }
    
    public Method findMethodInHierarchy(final MethodNode methodNode, final SearchType searchType, final int n) {
        return this.findMethodInHierarchy(methodNode.name, methodNode.desc, searchType, Traversal.NONE, n);
    }
    
    public Method findMethodInHierarchy(final MethodInsnNode methodInsnNode, final SearchType searchType) {
        return this.findMethodInHierarchy(methodInsnNode.name, methodInsnNode.desc, searchType, Traversal.NONE);
    }
    
    public Method findMethodInHierarchy(final MethodInsnNode methodInsnNode, final SearchType searchType, final int n) {
        return this.findMethodInHierarchy(methodInsnNode.name, methodInsnNode.desc, searchType, Traversal.NONE, n);
    }
    
    public Method findMethodInHierarchy(final String s, final String s2, final SearchType searchType) {
        return this.findMethodInHierarchy(s, s2, searchType, Traversal.NONE);
    }
    
    public Method findMethodInHierarchy(final String s, final String s2, final SearchType searchType, final Traversal traversal) {
        return this.findMethodInHierarchy(s, s2, searchType, traversal, 0);
    }
    
    public Method findMethodInHierarchy(final String s, final String s2, final SearchType searchType, final Traversal traversal, final int n) {
        return this.<Method>findInHierarchy(s, s2, searchType, traversal, n, Member.Type.METHOD);
    }
    
    public Field findFieldInHierarchy(final FieldNode fieldNode, final SearchType searchType) {
        return this.findFieldInHierarchy(fieldNode.name, fieldNode.desc, searchType, Traversal.NONE);
    }
    
    public Field findFieldInHierarchy(final FieldNode fieldNode, final SearchType searchType, final int n) {
        return this.findFieldInHierarchy(fieldNode.name, fieldNode.desc, searchType, Traversal.NONE, n);
    }
    
    public Field findFieldInHierarchy(final FieldInsnNode fieldInsnNode, final SearchType searchType) {
        return this.findFieldInHierarchy(fieldInsnNode.name, fieldInsnNode.desc, searchType, Traversal.NONE);
    }
    
    public Field findFieldInHierarchy(final FieldInsnNode fieldInsnNode, final SearchType searchType, final int n) {
        return this.findFieldInHierarchy(fieldInsnNode.name, fieldInsnNode.desc, searchType, Traversal.NONE, n);
    }
    
    public Field findFieldInHierarchy(final String s, final String s2, final SearchType searchType) {
        return this.findFieldInHierarchy(s, s2, searchType, Traversal.NONE);
    }
    
    public Field findFieldInHierarchy(final String s, final String s2, final SearchType searchType, final Traversal traversal) {
        return this.findFieldInHierarchy(s, s2, searchType, traversal, 0);
    }
    
    public Field findFieldInHierarchy(final String s, final String s2, final SearchType searchType, final Traversal traversal, final int n) {
        return this.<Field>findInHierarchy(s, s2, searchType, traversal, n, Member.Type.FIELD);
    }
    
    private <M extends Member> M findInHierarchy(final String s, final String s2, final SearchType searchType, final Traversal traversal, final int n, final Member.Type type) {
        if (searchType == SearchType.ALL_CLASSES) {
            final Member member = this.<Member>findMember(s, s2, n, type);
            if (member != null) {
                return (M)member;
            }
            if (traversal.canTraverse()) {
                final Iterator<MixinInfo> iterator = this.mixins.iterator();
                while (iterator.hasNext()) {
                    final Member member2 = iterator.next().getClassInfo().<Member>findMember(s, s2, n, type);
                    if (member2 != null) {
                        return this.<M>cloneMember(member2);
                    }
                }
            }
        }
        final ClassInfo superClass = this.getSuperClass();
        if (superClass != null) {
            final Iterator<ClassInfo> iterator2 = superClass.getTargets().iterator();
            while (iterator2.hasNext()) {
                final Member inHierarchy = iterator2.next().<Member>findInHierarchy(s, s2, SearchType.ALL_CLASSES, traversal.next(), n & 0xFFFFFFFD, type);
                if (inHierarchy != null) {
                    return (M)inHierarchy;
                }
            }
        }
        if (type == Member.Type.METHOD && (this.isInterface || MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces())) {
            for (final String s3 : this.interfaces) {
                final ClassInfo forName = forName(s3);
                if (forName == null) {
                    ClassInfo.logger.debug("Failed to resolve declared interface {} on {}", new Object[] { s3, this.name });
                }
                else {
                    final Member inHierarchy2 = forName.<InterfaceMethod>findInHierarchy(s, s2, SearchType.ALL_CLASSES, traversal.next(), n & 0xFFFFFFFD, type);
                    if (inHierarchy2 != null) {
                        return (M)(this.isInterface ? inHierarchy2 : new InterfaceMethod(inHierarchy2));
                    }
                    continue;
                }
            }
        }
        return null;
    }
    
    private <M extends Member> M cloneMember(final M m) {
        if (m instanceof Method) {
            return (M)new Method(m);
        }
        return (M)new Field(m);
    }
    
    public Method findMethod(final MethodNode methodNode) {
        return this.findMethod(methodNode.name, methodNode.desc, methodNode.access);
    }
    
    public Method findMethod(final MethodNode methodNode, final int n) {
        return this.findMethod(methodNode.name, methodNode.desc, n);
    }
    
    public Method findMethod(final MethodInsnNode methodInsnNode) {
        return this.findMethod(methodInsnNode.name, methodInsnNode.desc, 0);
    }
    
    public Method findMethod(final MethodInsnNode methodInsnNode, final int n) {
        return this.findMethod(methodInsnNode.name, methodInsnNode.desc, n);
    }
    
    public Method findMethod(final String s, final String s2, final int n) {
        return this.<Method>findMember(s, s2, n, Member.Type.METHOD);
    }
    
    public Field findField(final FieldNode fieldNode) {
        return this.findField(fieldNode.name, fieldNode.desc, fieldNode.access);
    }
    
    public Field findField(final FieldInsnNode fieldInsnNode, final int n) {
        return this.findField(fieldInsnNode.name, fieldInsnNode.desc, n);
    }
    
    public Field findField(final String s, final String s2, final int n) {
        return this.<Field>findMember(s, s2, n, Member.Type.FIELD);
    }
    
    private <M extends Member> M findMember(final String s, final String s2, final int n, final Member.Type type) {
        for (final Member member : (type == Member.Type.METHOD) ? this.methods : this.fields) {
            if (member.equals(s, s2) && member.matchesFlags(n)) {
                return (M)member;
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ClassInfo && ((ClassInfo)o).name.equals(this.name);
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    static ClassInfo fromClassNode(final ClassNode classNode) {
        ClassInfo classInfo = ClassInfo.cache.get(classNode.name);
        if (classInfo == null) {
            classInfo = new ClassInfo(classNode);
            ClassInfo.cache.put(classNode.name, classInfo);
        }
        return classInfo;
    }
    
    public static ClassInfo forName(String replace) {
        replace = replace.replace('.', '/');
        ClassInfo classInfo = ClassInfo.cache.get(replace);
        if (classInfo == null) {
            try {
                classInfo = new ClassInfo(MixinService.getService().getBytecodeProvider().getClassNode(replace));
            }
            catch (Exception ex) {
                ClassInfo.logger.catching(Level.TRACE, (Throwable)ex);
                ClassInfo.logger.warn("Error loading class: {} ({}: {})", new Object[] { replace, ex.getClass().getName(), ex.getMessage() });
            }
            ClassInfo.cache.put(replace, classInfo);
            ClassInfo.logger.trace("Added class metadata for {} to metadata cache", new Object[] { replace });
        }
        return classInfo;
    }
    
    public static ClassInfo forType(final Type type) {
        if (type.getSort() == 9) {
            return forType(type.getElementType());
        }
        if (type.getSort() < 9) {
            return null;
        }
        return forName(type.getClassName().replace('.', '/'));
    }
    
    public static ClassInfo getCommonSuperClass(final String s, final String s2) {
        if (s == null || s2 == null) {
            return ClassInfo.OBJECT;
        }
        return getCommonSuperClass(forName(s), forName(s2));
    }
    
    public static ClassInfo getCommonSuperClass(final Type type, final Type type2) {
        if (type == null || type2 == null || type.getSort() != 10 || type2.getSort() != 10) {
            return ClassInfo.OBJECT;
        }
        return getCommonSuperClass(forType(type), forType(type2));
    }
    
    private static ClassInfo getCommonSuperClass(final ClassInfo classInfo, final ClassInfo classInfo2) {
        return getCommonSuperClass(classInfo, classInfo2, false);
    }
    
    public static ClassInfo getCommonSuperClassOrInterface(final String s, final String s2) {
        if (s == null || s2 == null) {
            return ClassInfo.OBJECT;
        }
        return getCommonSuperClassOrInterface(forName(s), forName(s2));
    }
    
    public static ClassInfo getCommonSuperClassOrInterface(final Type type, final Type type2) {
        if (type == null || type2 == null || type.getSort() != 10 || type2.getSort() != 10) {
            return ClassInfo.OBJECT;
        }
        return getCommonSuperClassOrInterface(forType(type), forType(type2));
    }
    
    public static ClassInfo getCommonSuperClassOrInterface(final ClassInfo classInfo, final ClassInfo classInfo2) {
        return getCommonSuperClass(classInfo, classInfo2, true);
    }
    
    private static ClassInfo getCommonSuperClass(ClassInfo superClass, final ClassInfo classInfo, final boolean b) {
        if (superClass.hasSuperClass(classInfo, Traversal.NONE, b)) {
            return classInfo;
        }
        if (classInfo.hasSuperClass(superClass, Traversal.NONE, b)) {
            return superClass;
        }
        if (superClass.isInterface() || classInfo.isInterface()) {
            return ClassInfo.OBJECT;
        }
        do {
            superClass = superClass.getSuperClass();
            if (superClass == null) {
                return ClassInfo.OBJECT;
            }
        } while (!classInfo.hasSuperClass(superClass, Traversal.NONE, b));
        return superClass;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        profiler = MixinEnvironment.getProfiler();
        cache = new HashMap<String, ClassInfo>();
        OBJECT = new ClassInfo();
        ClassInfo.cache.put("java/lang/Object", ClassInfo.OBJECT);
    }
}
