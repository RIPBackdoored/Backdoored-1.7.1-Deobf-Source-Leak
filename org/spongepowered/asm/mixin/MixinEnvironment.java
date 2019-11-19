package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.util.JavaVersion;
import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import com.google.common.collect.Sets;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.service.ITransformer;
import java.util.Iterator;
import java.util.Collections;
import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.service.MixinService;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import org.spongepowered.asm.service.ILegacyClassTransformer;
import org.spongepowered.asm.obfuscation.RemapperChain;
import java.util.Map;
import java.util.List;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.util.perf.Profiler;
import org.apache.logging.log4j.Logger;
import java.util.Set;
import org.spongepowered.asm.util.ITokenProvider;

public final class MixinEnvironment implements ITokenProvider
{
    private static final Set<String> excludeTransformers;
    private static MixinEnvironment currentEnvironment;
    private static Phase currentPhase;
    private static CompatibilityLevel compatibility;
    private static boolean showHeader;
    private static final Logger logger;
    private static final Profiler profiler;
    private final IMixinService service;
    private final Phase phase;
    private final String configsKey;
    private final boolean[] options;
    private final Set<String> tokenProviderClasses;
    private final List<TokenProviderWrapper> tokenProviders;
    private final Map<String, Integer> internalTokens;
    private final RemapperChain remappers;
    private Side side;
    private List<ILegacyClassTransformer> transformers;
    private String obfuscationContext;
    
    MixinEnvironment(final Phase phase) {
        super();
        this.tokenProviderClasses = new HashSet<String>();
        this.tokenProviders = new ArrayList<TokenProviderWrapper>();
        this.internalTokens = new HashMap<String, Integer>();
        this.remappers = new RemapperChain();
        this.obfuscationContext = null;
        this.service = MixinService.getService();
        this.phase = phase;
        this.configsKey = "mixin.configs." + this.phase.name.toLowerCase();
        final String version = this.getVersion();
        if (version == null || !"0.7.11".equals(version)) {
            throw new MixinException("Environment conflict, mismatched versions or you didn't call MixinBootstrap.init()");
        }
        this.service.checkEnv(this);
        this.options = new boolean[Option.values().length];
        for (final Option option : Option.values()) {
            this.options[option.ordinal()] = option.getBooleanValue();
        }
        if (MixinEnvironment.showHeader) {
            MixinEnvironment.showHeader = false;
            this.printHeader(version);
        }
    }
    
    private void printHeader(final Object o) {
        final String codeSource = this.getCodeSource();
        final String name = this.service.getName();
        final Side side = this.getSide();
        MixinEnvironment.logger.info("SpongePowered MIXIN Subsystem Version={} Source={} Service={} Env={}", new Object[] { o, codeSource, name, side });
        final boolean option = this.getOption(Option.DEBUG_VERBOSE);
        if (this.getOption(Option.DEBUG_EXPORT) || this.getOption(Option.DEBUG_PROFILER)) {
            final PrettyPrinter prettyPrinter = new PrettyPrinter(32);
            prettyPrinter.add("SpongePowered MIXIN%s", option ? " (Verbose debugging enabled)" : "").centre().hr();
            prettyPrinter.kv("Code source", (Object)codeSource);
            prettyPrinter.kv("Internal Version", o);
            prettyPrinter.kv("Java 8 Supported", CompatibilityLevel.JAVA_8.isSupported()).hr();
            prettyPrinter.kv("Service Name", (Object)name);
            prettyPrinter.kv("Service Class", (Object)this.service.getClass().getName()).hr();
            for (final Option option2 : Option.values()) {
                final StringBuilder sb = new StringBuilder();
                for (int j = 0; j < option2.depth; ++j) {
                    sb.append("- ");
                }
                prettyPrinter.kv(option2.property, "%s<%s>", sb, option2);
            }
            prettyPrinter.hr().kv("Detected Side", side);
            prettyPrinter.print(System.err);
        }
    }
    
    private String getCodeSource() {
        try {
            return this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        }
        catch (Throwable t) {
            return "Unknown";
        }
    }
    
    public Phase getPhase() {
        return this.phase;
    }
    
    @Deprecated
    public List<String> getMixinConfigs() {
        List<String> list = GlobalProperties.<List<String>>get(this.configsKey);
        if (list == null) {
            list = new ArrayList<String>();
            GlobalProperties.put(this.configsKey, list);
        }
        return list;
    }
    
    @Deprecated
    public MixinEnvironment addConfiguration(final String s) {
        MixinEnvironment.logger.warn("MixinEnvironment::addConfiguration is deprecated and will be removed. Use Mixins::addConfiguration instead!");
        Mixins.addConfiguration(s, this);
        return this;
    }
    
    void registerConfig(final String s) {
        final List<String> mixinConfigs = this.getMixinConfigs();
        if (!mixinConfigs.contains(s)) {
            mixinConfigs.add(s);
        }
    }
    
    @Deprecated
    public MixinEnvironment registerErrorHandlerClass(final String s) {
        Mixins.registerErrorHandlerClass(s);
        return this;
    }
    
    public MixinEnvironment registerTokenProviderClass(final String s) {
        if (!this.tokenProviderClasses.contains(s)) {
            try {
                this.registerTokenProvider((IEnvironmentTokenProvider)this.service.getClassProvider().findClass(s, true).newInstance());
            }
            catch (Throwable t) {
                MixinEnvironment.logger.error("Error instantiating " + s, t);
            }
        }
        return this;
    }
    
    public MixinEnvironment registerTokenProvider(final IEnvironmentTokenProvider environmentTokenProvider) {
        if (environmentTokenProvider != null && !this.tokenProviderClasses.contains(environmentTokenProvider.getClass().getName())) {
            final String name = environmentTokenProvider.getClass().getName();
            final TokenProviderWrapper tokenProviderWrapper = new TokenProviderWrapper(environmentTokenProvider, this);
            MixinEnvironment.logger.info("Adding new token provider {} to {}", new Object[] { name, this });
            this.tokenProviders.add(tokenProviderWrapper);
            this.tokenProviderClasses.add(name);
            Collections.<TokenProviderWrapper>sort(this.tokenProviders);
        }
        return this;
    }
    
    @Override
    public Integer getToken(String upperCase) {
        upperCase = upperCase.toUpperCase();
        final Iterator<TokenProviderWrapper> iterator = this.tokenProviders.iterator();
        while (iterator.hasNext()) {
            final Integer token = iterator.next().getToken(upperCase);
            if (token != null) {
                return token;
            }
        }
        return this.internalTokens.get(upperCase);
    }
    
    @Deprecated
    public Set<String> getErrorHandlerClasses() {
        return Mixins.getErrorHandlerClasses();
    }
    
    public Object getActiveTransformer() {
        return GlobalProperties.<Object>get("mixin.transformer");
    }
    
    public void setActiveTransformer(final ITransformer transformer) {
        if (transformer != null) {
            GlobalProperties.put("mixin.transformer", transformer);
        }
    }
    
    public MixinEnvironment setSide(final Side side) {
        if (side != null && this.getSide() == Side.UNKNOWN && side != Side.UNKNOWN) {
            this.side = side;
        }
        return this;
    }
    
    public Side getSide() {
        if (this.side == null) {
            for (final Side side : Side.values()) {
                if (side.detect()) {
                    this.side = side;
                    break;
                }
            }
        }
        return (this.side != null) ? this.side : Side.UNKNOWN;
    }
    
    public String getVersion() {
        return GlobalProperties.<String>get("mixin.initialised");
    }
    
    public boolean getOption(final Option option) {
        return this.options[option.ordinal()];
    }
    
    public void setOption(final Option option, final boolean b) {
        this.options[option.ordinal()] = b;
    }
    
    public String getOptionValue(final Option option) {
        return option.getStringValue();
    }
    
    public <E extends Enum<E>> E getOption(final Option option, final E e) {
        return option.<E>getEnumValue(e);
    }
    
    public void setObfuscationContext(final String obfuscationContext) {
        this.obfuscationContext = obfuscationContext;
    }
    
    public String getObfuscationContext() {
        return this.obfuscationContext;
    }
    
    public String getRefmapObfuscationContext() {
        final String stringValue = Option.OBFUSCATION_TYPE.getStringValue();
        if (stringValue != null) {
            return stringValue;
        }
        return this.obfuscationContext;
    }
    
    public RemapperChain getRemappers() {
        return this.remappers;
    }
    
    public void audit() {
        final Object activeTransformer = this.getActiveTransformer();
        if (activeTransformer instanceof MixinTransformer) {
            ((MixinTransformer)activeTransformer).audit(this);
        }
    }
    
    public List<ILegacyClassTransformer> getTransformers() {
        if (this.transformers == null) {
            this.buildTransformerDelegationList();
        }
        return Collections.<ILegacyClassTransformer>unmodifiableList((List<? extends ILegacyClassTransformer>)this.transformers);
    }
    
    public void addTransformerExclusion(final String s) {
        MixinEnvironment.excludeTransformers.add(s);
        this.transformers = null;
    }
    
    private void buildTransformerDelegationList() {
        MixinEnvironment.logger.debug("Rebuilding transformer delegation list:");
        this.transformers = new ArrayList<ILegacyClassTransformer>();
        for (final ITransformer transformer : this.service.getTransformers()) {
            if (!(transformer instanceof ILegacyClassTransformer)) {
                continue;
            }
            final ILegacyClassTransformer legacyClassTransformer = (ILegacyClassTransformer)transformer;
            final String name = legacyClassTransformer.getName();
            boolean b = true;
            final Iterator<String> iterator2 = MixinEnvironment.excludeTransformers.iterator();
            while (iterator2.hasNext()) {
                if (name.contains(iterator2.next())) {
                    b = false;
                    break;
                }
            }
            if (b && !legacyClassTransformer.isDelegationExcluded()) {
                MixinEnvironment.logger.debug("  Adding:    {}", new Object[] { name });
                this.transformers.add(legacyClassTransformer);
            }
            else {
                MixinEnvironment.logger.debug("  Excluding: {}", new Object[] { name });
            }
        }
        MixinEnvironment.logger.debug("Transformer delegation list created with {} entries", new Object[] { this.transformers.size() });
    }
    
    @Override
    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.phase);
    }
    
    private static Phase getCurrentPhase() {
        if (MixinEnvironment.currentPhase == Phase.NOT_INITIALISED) {
            init(Phase.PREINIT);
        }
        return MixinEnvironment.currentPhase;
    }
    
    public static void init(final Phase currentPhase) {
        if (MixinEnvironment.currentPhase == Phase.NOT_INITIALISED) {
            MixinEnvironment.currentPhase = currentPhase;
            getProfiler().setActive(getEnvironment(currentPhase).getOption(Option.DEBUG_PROFILER));
            MixinLogWatcher.begin();
        }
    }
    
    public static MixinEnvironment getEnvironment(final Phase phase) {
        if (phase == null) {
            return Phase.DEFAULT.getEnvironment();
        }
        return phase.getEnvironment();
    }
    
    public static MixinEnvironment getDefaultEnvironment() {
        return getEnvironment(Phase.DEFAULT);
    }
    
    public static MixinEnvironment getCurrentEnvironment() {
        if (MixinEnvironment.currentEnvironment == null) {
            MixinEnvironment.currentEnvironment = getEnvironment(getCurrentPhase());
        }
        return MixinEnvironment.currentEnvironment;
    }
    
    public static CompatibilityLevel getCompatibilityLevel() {
        return MixinEnvironment.compatibility;
    }
    
    @Deprecated
    public static void setCompatibilityLevel(final CompatibilityLevel compatibility) throws IllegalArgumentException {
        if (!"org.spongepowered.asm.mixin.transformer.MixinConfig".equals(Thread.currentThread().getStackTrace()[2].getClassName())) {
            MixinEnvironment.logger.warn("MixinEnvironment::setCompatibilityLevel is deprecated and will be removed. Set level via config instead!");
        }
        if (compatibility != MixinEnvironment.compatibility && compatibility.isAtLeast(MixinEnvironment.compatibility)) {
            if (!compatibility.isSupported()) {
                throw new IllegalArgumentException("The requested compatibility level " + compatibility + " could not be set. Level is not supported");
            }
            MixinEnvironment.compatibility = compatibility;
            MixinEnvironment.logger.info("Compatibility level set to {}", new Object[] { compatibility });
        }
    }
    
    public static Profiler getProfiler() {
        return MixinEnvironment.profiler;
    }
    
    static void gotoPhase(final Phase currentPhase) {
        if (currentPhase == null || currentPhase.ordinal < 0) {
            throw new IllegalArgumentException("Cannot go to the specified phase, phase is null or invalid");
        }
        if (currentPhase.ordinal > getCurrentPhase().ordinal) {
            MixinService.getService().beginPhase();
        }
        if (currentPhase == Phase.DEFAULT) {
            MixinLogWatcher.end();
        }
        MixinEnvironment.currentPhase = currentPhase;
        MixinEnvironment.currentEnvironment = getEnvironment(getCurrentPhase());
    }
    
    static {
        excludeTransformers = Sets.newHashSet((Object[])new String[] { "net.minecraftforge.fml.common.asm.transformers.EventSubscriptionTransformer", "cpw.mods.fml.common.asm.transformers.EventSubscriptionTransformer", "net.minecraftforge.fml.common.asm.transformers.TerminalTransformer", "cpw.mods.fml.common.asm.transformers.TerminalTransformer" });
        MixinEnvironment.currentPhase = Phase.NOT_INITIALISED;
        MixinEnvironment.compatibility = Option.DEFAULT_COMPATIBILITY_LEVEL.<CompatibilityLevel>getEnumValue(CompatibilityLevel.JAVA_6);
        MixinEnvironment.showHeader = true;
        logger = LogManager.getLogger("mixin");
        profiler = new Profiler();
    }
}
