package com.google.api.client.repackaged.com.google.common.base;

import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.ServiceConfigurationError;
import javax.annotation.Nullable;
import java.util.Locale;
import java.lang.ref.WeakReference;
import java.util.logging.Logger;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class Platform
{
    private static final Logger logger;
    private static final PatternCompiler patternCompiler;
    
    private Platform() {
        super();
    }
    
    static long systemNanoTime() {
        return System.nanoTime();
    }
    
    static CharMatcher precomputeCharMatcher(final CharMatcher charMatcher) {
        return charMatcher.precomputedInternal();
    }
    
    static <T extends Enum<T>> Optional<T> getEnumIfPresent(final Class<T> clazz, final String s) {
        final WeakReference<? extends Enum<?>> weakReference = Enums.<T>getEnumConstants(clazz).get(s);
        return (weakReference == null) ? Optional.<T>absent() : Optional.<T>of(clazz.cast(weakReference.get()));
    }
    
    static String formatCompact4Digits(final double n) {
        return String.format(Locale.ROOT, "%.4g", n);
    }
    
    static boolean stringIsNullOrEmpty(@Nullable final String s) {
        return s == null || s.isEmpty();
    }
    
    static CommonPattern compilePattern(final String s) {
        Preconditions.<String>checkNotNull(s);
        return Platform.patternCompiler.compile(s);
    }
    
    static boolean usingJdkPatternCompiler() {
        return Platform.patternCompiler instanceof JdkPatternCompiler;
    }
    
    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }
    
    private static void logPatternCompilerError(final ServiceConfigurationError serviceConfigurationError) {
        Platform.logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", serviceConfigurationError);
    }
    
    static {
        logger = Logger.getLogger(Platform.class.getName());
        patternCompiler = loadPatternCompiler();
    }
}
