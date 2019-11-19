package org.spongepowered.asm.mixin.extensibility;

import org.apache.logging.log4j.Level;

public interface IMixinErrorHandler
{
    ErrorAction onPrepareError(final IMixinConfig p0, final Throwable p1, final IMixinInfo p2, final ErrorAction p3);
    
    ErrorAction onApplyError(final String p0, final Throwable p1, final IMixinInfo p2, final ErrorAction p3);
}
