package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import java.io.Serializable;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.AbstractAppender;

static class MixinAppender extends AbstractAppender
{
    protected MixinAppender(final String s, final Filter filter, final Layout<? extends Serializable> layout) {
        super(s, filter, (Layout)layout);
    }
    
    public void append(final LogEvent logEvent) {
        if (logEvent.getLevel() == Level.DEBUG && "Validating minecraft".equals(logEvent.getMessage().getFormat())) {
            MixinEnvironment.gotoPhase(Phase.INIT);
        }
    }
}
