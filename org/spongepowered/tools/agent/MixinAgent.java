package org.spongepowered.tools.agent;

import org.spongepowered.asm.service.IMixinService;
import java.lang.instrument.ClassDefinition;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import java.util.Iterator;
import java.lang.instrument.ClassFileTransformer;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import java.util.List;
import java.lang.instrument.Instrumentation;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;

public class MixinAgent implements IHotSwap
{
    public static final byte[] ERROR_BYTECODE;
    static final MixinAgentClassLoader classLoader;
    static final Logger logger;
    static Instrumentation instrumentation;
    private static List<MixinAgent> agents;
    final MixinTransformer classTransformer;
    
    public MixinAgent(final MixinTransformer classTransformer) {
        super();
        this.classTransformer = classTransformer;
        MixinAgent.agents.add(this);
        if (MixinAgent.instrumentation != null) {
            this.initTransformer();
        }
    }
    
    private void initTransformer() {
        MixinAgent.instrumentation.addTransformer(new Transformer(), true);
    }
    
    @Override
    public void registerMixinClass(final String s) {
        MixinAgent.classLoader.addMixinClass(s);
    }
    
    @Override
    public void registerTargetClass(final String s, final byte[] array) {
        MixinAgent.classLoader.addTargetClass(s, array);
    }
    
    public static void init(final Instrumentation instrumentation) {
        MixinAgent.instrumentation = instrumentation;
        if (!MixinAgent.instrumentation.isRedefineClassesSupported()) {
            MixinAgent.logger.error("The instrumentation doesn't support re-definition of classes");
        }
        final Iterator<MixinAgent> iterator = MixinAgent.agents.iterator();
        while (iterator.hasNext()) {
            iterator.next().initTransformer();
        }
    }
    
    public static void premain(final String s, final Instrumentation instrumentation) {
        System.setProperty("mixin.hotSwap", "true");
        init(instrumentation);
    }
    
    public static void agentmain(final String s, final Instrumentation instrumentation) {
        init(instrumentation);
    }
    
    static {
        ERROR_BYTECODE = new byte[] { 1 };
        classLoader = new MixinAgentClassLoader();
        logger = LogManager.getLogger("mixin.agent");
        MixinAgent.instrumentation = null;
        MixinAgent.agents = new ArrayList<MixinAgent>();
    }
}
