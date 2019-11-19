package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import java.io.Serializable;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

static class MixinLogger
{
    static MixinAppender appender;
    
    public MixinLogger() {
        super();
        final Logger logger = (Logger)LogManager.getLogger("FML");
        MixinLogger.appender.start();
        logger.addAppender((Appender)MixinLogger.appender);
    }
    
    static {
        MixinLogger.appender = new MixinAppender("MixinLogger", null, null);
    }
}
