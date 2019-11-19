package org.spongepowered.asm.mixin.extensibility;

import org.apache.logging.log4j.Level;

public enum ErrorAction
{
    NONE(Level.INFO), 
    WARN(Level.WARN), 
    ERROR(Level.FATAL);
    
    public final Level logLevel;
    private static final /* synthetic */ ErrorAction[] $VALUES;
    
    public static ErrorAction[] values() {
        return ErrorAction.$VALUES.clone();
    }
    
    public static ErrorAction valueOf(final String s) {
        return Enum.<ErrorAction>valueOf(ErrorAction.class, s);
    }
    
    private ErrorAction(final Level logLevel) {
        this.logLevel = logLevel;
    }
    
    static {
        $VALUES = new ErrorAction[] { ErrorAction.NONE, ErrorAction.WARN, ErrorAction.ERROR };
    }
}
