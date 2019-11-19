package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.commons.ClassRemapper;
import java.io.IOException;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.lib.commons.Remapper;
import org.apache.logging.log4j.LogManager;
import java.util.UUID;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.transformers.MixinClassWriter;
import org.spongepowered.asm.lib.ClassReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;

final class InnerClassGenerator implements IClassGenerator
{
    private static final Logger logger;
    private final Map<String, String> innerClassNames;
    private final Map<String, InnerClassInfo> innerClasses;
    
    InnerClassGenerator() {
        super();
        this.innerClassNames = new HashMap<String, String>();
        this.innerClasses = new HashMap<String, InnerClassInfo>();
    }
    
    public String registerInnerClass(final MixinInfo mixinInfo, final String s, final MixinTargetContext mixinTargetContext) {
        final String format = String.format("%s%s", s, mixinTargetContext);
        String uniqueReference = this.innerClassNames.get(format);
        if (uniqueReference == null) {
            uniqueReference = getUniqueReference(s, mixinTargetContext);
            this.innerClassNames.put(format, uniqueReference);
            this.innerClasses.put(uniqueReference, new InnerClassInfo(uniqueReference, s, mixinInfo, mixinTargetContext));
            InnerClassGenerator.logger.debug("Inner class {} in {} on {} gets unique name {}", new Object[] { s, mixinInfo.getClassRef(), mixinTargetContext.getTargetClassRef(), uniqueReference });
        }
        return uniqueReference;
    }
    
    @Override
    public byte[] generate(final String s) {
        final InnerClassInfo innerClassInfo = this.innerClasses.get(s.replace('.', '/'));
        if (innerClassInfo != null) {
            return this.generate(innerClassInfo);
        }
        return null;
    }
    
    private byte[] generate(final InnerClassInfo innerClassInfo) {
        try {
            InnerClassGenerator.logger.debug("Generating mapped inner class {} (originally {})", new Object[] { innerClassInfo.getName(), innerClassInfo.getOriginalName() });
            final ClassReader classReader = new ClassReader(innerClassInfo.getClassBytes());
            final MixinClassWriter mixinClassWriter = new MixinClassWriter(classReader, 0);
            classReader.accept(new InnerClassAdapter(mixinClassWriter, innerClassInfo), 8);
            return mixinClassWriter.toByteArray();
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            InnerClassGenerator.logger.catching((Throwable)ex2);
            return null;
        }
    }
    
    private static String getUniqueReference(final String s, final MixinTargetContext mixinTargetContext) {
        String substring = s.substring(s.lastIndexOf(36) + 1);
        if (substring.matches("^[0-9]+$")) {
            substring = "Anonymous";
        }
        return String.format("%s$%s$%s", mixinTargetContext.getTargetClassRef(), substring, UUID.randomUUID().toString().replace("-", ""));
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
