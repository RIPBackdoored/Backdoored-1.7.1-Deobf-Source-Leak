package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;
import org.spongepowered.asm.lib.tree.InnerClassNode;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassReader;
import java.util.HashSet;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.service.MixinService;
import java.io.IOException;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.Pseudo;
import java.util.Iterator;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTargetAlreadyLoadedException;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import java.util.Collection;
import org.spongepowered.asm.lib.Type;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Mixin;
import java.util.Collections;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.base.Functions;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import java.util.List;
import org.spongepowered.asm.util.perf.Profiler;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

class MixinInfo implements Comparable<MixinInfo>, IMixinInfo
{
    private static final IMixinService classLoaderUtil;
    static int mixinOrder;
    private final transient Logger logger;
    private final transient Profiler profiler;
    private final transient MixinConfig parent;
    private final String name;
    private final String className;
    private final int priority;
    private final boolean virtual;
    private final List<ClassInfo> targetClasses;
    private final List<String> targetClassNames;
    private final transient int order;
    private final transient IMixinService service;
    private final transient IMixinConfigPlugin plugin;
    private final transient MixinEnvironment.Phase phase;
    private final transient ClassInfo info;
    private final transient SubType type;
    private final transient boolean strict;
    private transient State pendingState;
    private transient State state;
    
    MixinInfo(final IMixinService service, final MixinConfig parent, final String name, final boolean b, final IMixinConfigPlugin plugin, final boolean b2) {
        super();
        this.logger = LogManager.getLogger("mixin");
        this.profiler = MixinEnvironment.getProfiler();
        this.order = MixinInfo.mixinOrder++;
        this.service = service;
        this.parent = parent;
        this.name = name;
        this.className = parent.getMixinPackage() + name;
        this.plugin = plugin;
        this.phase = parent.getEnvironment().getPhase();
        this.strict = parent.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_TARGETS);
        try {
            this.pendingState = new State(this.loadMixinClass(this.className, b));
            this.info = this.pendingState.getClassInfo();
            this.type = SubType.getTypeFor(this);
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException(this, ex2);
        }
        if (!this.type.isLoadable()) {
            MixinInfo.classLoaderUtil.registerInvalidClass(this.className);
        }
        try {
            this.priority = this.readPriority(this.pendingState.getClassNode());
            this.virtual = this.readPseudo(this.pendingState.getClassNode());
            this.targetClasses = this.readTargetClasses(this.pendingState.getClassNode(), b2);
            this.targetClassNames = Collections.<String>unmodifiableList(Lists.<ClassInfo, ? extends String>transform(this.targetClasses, (Function<? super ClassInfo, ? extends String>)Functions.toStringFunction()));
        }
        catch (InvalidMixinException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new InvalidMixinException(this, ex4);
        }
    }
    
    void validate() {
        if (this.pendingState == null) {
            throw new IllegalStateException("No pending validation state for " + this);
        }
        try {
            this.pendingState.validate(this.type, this.targetClasses);
            this.state = this.pendingState;
        }
        finally {
            this.pendingState = null;
        }
    }
    
    protected List<ClassInfo> readTargetClasses(final MixinClassNode mixinClassNode, final boolean b) {
        if (mixinClassNode == null) {
            return Collections.<ClassInfo>emptyList();
        }
        final AnnotationNode invisible = Annotations.getInvisible(mixinClassNode, Mixin.class);
        if (invisible == null) {
            throw new InvalidMixinException(this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final ArrayList<ClassInfo> list = new ArrayList<ClassInfo>();
        final List<Object> fromList = Annotations.<List<Object>>getValue(invisible, "value");
        final List<Object> fromList2 = Annotations.<List<Object>>getValue(invisible, "targets");
        if (fromList != null) {
            this.readTargets(list, (Collection<String>)Lists.<Object, Object>transform(fromList, (Function<? super Object, ?>)new Function<Type, String>() {
                final /* synthetic */ MixinInfo this$0;
                
                MixinInfo$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public String apply(final Type type) {
                    return type.getClassName();
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((Type)o);
                }
            }), b, false);
        }
        if (fromList2 != null) {
            this.readTargets(list, (Collection<String>)Lists.<Object, Object>transform(fromList2, (Function<? super Object, ?>)new Function<String, String>() {
                final /* synthetic */ MixinInfo this$0;
                
                MixinInfo$2() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public String apply(final String s) {
                    return this.this$0.getParent().remapClassName(this.this$0.getClassRef(), s);
                }
                
                @Override
                public /* bridge */ Object apply(final Object o) {
                    return this.apply((String)o);
                }
            }), b, true);
        }
        return list;
    }
    
    private void readTargets(final Collection<ClassInfo> collection, final Collection<String> collection2, final boolean b, final boolean b2) {
        final Iterator<String> iterator = collection2.iterator();
        while (iterator.hasNext()) {
            final String replace = iterator.next().replace('/', '.');
            if (MixinInfo.classLoaderUtil.isClassLoaded(replace) && !this.isReloading()) {
                final String format = String.format("Critical problem: %s target %s was already transformed.", this, replace);
                if (this.parent.isRequired()) {
                    throw new MixinTargetAlreadyLoadedException(this, format, replace);
                }
                this.logger.error(format);
            }
            if (this.shouldApplyMixin(b, replace)) {
                final ClassInfo target = this.getTarget(replace, b2);
                if (target == null || collection.contains(target)) {
                    continue;
                }
                collection.add(target);
                target.addMixin(this);
            }
        }
    }
    
    private boolean shouldApplyMixin(final boolean b, final String s) {
        final Profiler.Section begin = this.profiler.begin("plugin");
        final boolean b2 = this.plugin == null || this.plugin.shouldApplyMixin(s, this.className);
        begin.end();
        return b2;
    }
    
    private ClassInfo getTarget(final String s, final boolean b) throws InvalidMixinException {
        final ClassInfo forName = ClassInfo.forName(s);
        if (forName == null) {
            if (this.isVirtual()) {
                this.logger.debug("Skipping virtual target {} for {}", new Object[] { s, this });
            }
            else {
                this.handleTargetError(String.format("@Mixin target %s was not found %s", s, this));
            }
            return null;
        }
        this.type.validateTarget(s, forName);
        if (b && forName.isPublic() && !this.isVirtual()) {
            this.handleTargetError(String.format("@Mixin target %s is public in %s and should be specified in value", s, this));
        }
        return forName;
    }
    
    private void handleTargetError(final String s) {
        if (this.strict) {
            this.logger.error(s);
            throw new InvalidMixinException(this, s);
        }
        this.logger.warn(s);
    }
    
    protected int readPriority(final ClassNode classNode) {
        if (classNode == null) {
            return this.parent.getDefaultMixinPriority();
        }
        final AnnotationNode invisible = Annotations.getInvisible(classNode, Mixin.class);
        if (invisible == null) {
            throw new InvalidMixinException(this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final Integer n = Annotations.<Integer>getValue(invisible, "priority");
        return (n == null) ? this.parent.getDefaultMixinPriority() : n;
    }
    
    protected boolean readPseudo(final ClassNode classNode) {
        return Annotations.getInvisible(classNode, Pseudo.class) != null;
    }
    
    private boolean isReloading() {
        return this.pendingState instanceof Reloaded;
    }
    
    private State getState() {
        return (this.state != null) ? this.state : this.pendingState;
    }
    
    ClassInfo getClassInfo() {
        return this.info;
    }
    
    @Override
    public IMixinConfig getConfig() {
        return this.parent;
    }
    
    MixinConfig getParent() {
        return this.parent;
    }
    
    @Override
    public int getPriority() {
        return this.priority;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getClassName() {
        return this.className;
    }
    
    @Override
    public String getClassRef() {
        return this.getClassInfo().getName();
    }
    
    @Override
    public byte[] getClassBytes() {
        return this.getState().getClassBytes();
    }
    
    @Override
    public boolean isDetachedSuper() {
        return this.getState().isDetachedSuper();
    }
    
    public boolean isUnique() {
        return this.getState().isUnique();
    }
    
    public boolean isVirtual() {
        return this.virtual;
    }
    
    public boolean isAccessor() {
        return this.type instanceof SubType.Accessor;
    }
    
    public boolean isLoadable() {
        return this.type.isLoadable();
    }
    
    public Level getLoggingLevel() {
        return this.parent.getLoggingLevel();
    }
    
    @Override
    public MixinEnvironment.Phase getPhase() {
        return this.phase;
    }
    
    @Override
    public MixinClassNode getClassNode(final int n) {
        return this.getState().createClassNode(n);
    }
    
    @Override
    public List<String> getTargetClasses() {
        return this.targetClassNames;
    }
    
    List<InterfaceInfo> getSoftImplements() {
        return Collections.<InterfaceInfo>unmodifiableList(this.getState().getSoftImplements());
    }
    
    Set<String> getSyntheticInnerClasses() {
        return Collections.<String>unmodifiableSet((Set<? extends String>)this.getState().getSyntheticInnerClasses());
    }
    
    Set<String> getInnerClasses() {
        return Collections.<String>unmodifiableSet((Set<? extends String>)this.getState().getInnerClasses());
    }
    
    List<ClassInfo> getTargets() {
        return Collections.<ClassInfo>unmodifiableList((List<? extends ClassInfo>)this.targetClasses);
    }
    
    Set<String> getInterfaces() {
        return this.getState().getInterfaces();
    }
    
    MixinTargetContext createContextFor(final TargetClassContext targetClassContext) {
        final MixinClassNode classNode = this.getClassNode(8);
        final Profiler.Section begin = this.profiler.begin("pre");
        final MixinTargetContext context = this.type.createPreProcessor(classNode).prepare().createContextFor(targetClassContext);
        begin.end();
        return context;
    }
    
    private byte[] loadMixinClass(final String s, final boolean b) throws ClassNotFoundException {
        byte[] classBytes;
        try {
            if (b) {
                final String classRestrictions = this.service.getClassRestrictions(s);
                if (classRestrictions.length() > 0) {
                    this.logger.error("Classloader restrictions [{}] encountered loading {}, name: {}", new Object[] { classRestrictions, this, s });
                }
            }
            classBytes = this.service.getBytecodeProvider().getClassBytes(s, b);
        }
        catch (ClassNotFoundException ex2) {
            throw new ClassNotFoundException(String.format("The specified mixin '%s' was not found", s));
        }
        catch (IOException ex) {
            this.logger.warn("Failed to load mixin {}, the specified mixin will not be applied", new Object[] { s });
            throw new InvalidMixinException(this, "An error was encountered whilst loading the mixin class", ex);
        }
        return classBytes;
    }
    
    void reloadMixin(final byte[] array) {
        if (this.pendingState != null) {
            throw new IllegalStateException("Cannot reload mixin while it is initialising");
        }
        this.pendingState = new Reloaded(this.state, array);
        this.validate();
    }
    
    @Override
    public int compareTo(final MixinInfo mixinInfo) {
        if (mixinInfo == null) {
            return 0;
        }
        if (mixinInfo.priority == this.priority) {
            return this.order - mixinInfo.order;
        }
        return this.priority - mixinInfo.priority;
    }
    
    public void preApply(final String s, final ClassNode classNode) {
        if (this.plugin != null) {
            final Profiler.Section begin = this.profiler.begin("plugin");
            this.plugin.preApply(s, classNode, this.className, this);
            begin.end();
        }
    }
    
    public void postApply(final String s, final ClassNode classNode) {
        if (this.plugin != null) {
            final Profiler.Section begin = this.profiler.begin("plugin");
            this.plugin.postApply(s, classNode, this.className, this);
            begin.end();
        }
        this.parent.postApply(s, classNode);
    }
    
    @Override
    public String toString() {
        return String.format("%s:%s", this.parent.getName(), this.name);
    }
    
    @Override
    public /* bridge */ int compareTo(final Object o) {
        return this.compareTo((MixinInfo)o);
    }
    
    @Override
    public /* bridge */ ClassNode getClassNode(final int n) {
        return this.getClassNode(n);
    }
    
    static {
        classLoaderUtil = MixinService.getService();
        MixinInfo.mixinOrder = 0;
    }
}
