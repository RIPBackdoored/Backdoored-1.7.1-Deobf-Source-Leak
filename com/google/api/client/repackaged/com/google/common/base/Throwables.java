package com.google.api.client.repackaged.com.google.common.base;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractList;
import java.util.Arrays;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
public final class Throwables
{
    @GwtIncompatible
    private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
    @GwtIncompatible
    @VisibleForTesting
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    @Nullable
    @GwtIncompatible
    private static final Object jla;
    @Nullable
    @GwtIncompatible
    private static final Method getStackTraceElementMethod;
    @Nullable
    @GwtIncompatible
    private static final Method getStackTraceDepthMethod;
    
    private Throwables() {
        super();
    }
    
    @GwtIncompatible
    public static <X extends Throwable> void throwIfInstanceOf(final Throwable t, final Class<X> clazz) throws X, Throwable {
        Preconditions.<Throwable>checkNotNull(t);
        if (clazz.isInstance(t)) {
            throw clazz.cast(t);
        }
    }
    
    @Deprecated
    @GwtIncompatible
    public static <X extends Throwable> void propagateIfInstanceOf(@Nullable final Throwable t, final Class<X> clazz) throws X, Throwable {
        if (t != null) {
            Throwables.<Throwable>throwIfInstanceOf(t, (Class<Throwable>)clazz);
        }
    }
    
    public static void throwIfUnchecked(final Throwable t) {
        Preconditions.<Throwable>checkNotNull(t);
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
    }
    
    @Deprecated
    @GwtIncompatible
    public static void propagateIfPossible(@Nullable final Throwable t) {
        if (t != null) {
            throwIfUnchecked(t);
        }
    }
    
    @GwtIncompatible
    public static <X extends Throwable> void propagateIfPossible(@Nullable final Throwable t, final Class<X> clazz) throws X, Throwable {
        Throwables.<Throwable>propagateIfInstanceOf(t, (Class<Throwable>)clazz);
        propagateIfPossible(t);
    }
    
    @GwtIncompatible
    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(@Nullable final Throwable t, final Class<X1> clazz, final Class<X2> clazz2) throws X1, X2, Throwable {
        Preconditions.<Class<X2>>checkNotNull(clazz2);
        Throwables.<X1>propagateIfInstanceOf(t, clazz);
        Throwables.<X2>propagateIfPossible(t, clazz2);
    }
    
    @Deprecated
    @CanIgnoreReturnValue
    @GwtIncompatible
    public static RuntimeException propagate(final Throwable t) {
        throwIfUnchecked(t);
        throw new RuntimeException(t);
    }
    
    public static Throwable getRootCause(Throwable t) {
        Throwable cause;
        while ((cause = t.getCause()) != null) {
            t = cause;
        }
        return t;
    }
    
    @Beta
    public static List<Throwable> getCausalChain(Throwable cause) {
        Preconditions.<Throwable>checkNotNull(cause);
        final ArrayList<Throwable> list = new ArrayList<Throwable>(4);
        while (cause != null) {
            list.add(cause);
            cause = cause.getCause();
        }
        return (List<Throwable>)Collections.<Object>unmodifiableList((List<?>)list);
    }
    
    @GwtIncompatible
    public static String getStackTraceAsString(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    @Beta
    @GwtIncompatible
    public static List<StackTraceElement> lazyStackTrace(final Throwable t) {
        return lazyStackTraceIsLazy() ? jlaStackTrace(t) : Collections.<StackTraceElement>unmodifiableList((List<? extends StackTraceElement>)Arrays.<? extends T>asList((T[])t.getStackTrace()));
    }
    
    @Beta
    @GwtIncompatible
    public static boolean lazyStackTraceIsLazy() {
        return Throwables.getStackTraceElementMethod != null & Throwables.getStackTraceDepthMethod != null;
    }
    
    @GwtIncompatible
    private static List<StackTraceElement> jlaStackTrace(final Throwable t) {
        Preconditions.<Throwable>checkNotNull(t);
        return new AbstractList<StackTraceElement>() {
            final /* synthetic */ Throwable val$t;
            
            Throwables$1() {
                super();
            }
            
            @Override
            public StackTraceElement get(final int n) {
                return (StackTraceElement)invokeAccessibleNonThrowingMethod(Throwables.getStackTraceElementMethod, Throwables.jla, new Object[] { t, n });
            }
            
            @Override
            public int size() {
                return (int)invokeAccessibleNonThrowingMethod(Throwables.getStackTraceDepthMethod, Throwables.jla, new Object[] { t });
            }
            
            @Override
            public /* bridge */ Object get(final int n) {
                return this.get(n);
            }
        };
    }
    
    @GwtIncompatible
    private static Object invokeAccessibleNonThrowingMethod(final Method method, final Object o, final Object... array) {
        try {
            return method.invoke(o, array);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvocationTargetException ex2) {
            throw propagate(ex2.getCause());
        }
    }
    
    @Nullable
    @GwtIncompatible
    private static Object getJLA() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    @Nullable
    @GwtIncompatible
    private static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }
    
    @Nullable
    @GwtIncompatible
    private static Method getSizeMethod() {
        return getJlaMethod("getStackTraceDepth", Throwable.class);
    }
    
    @Nullable
    @GwtIncompatible
    private static Method getJlaMethod(final String s, final Class<?>... array) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(s, array);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    static /* synthetic */ Method access$000() {
        return Throwables.getStackTraceElementMethod;
    }
    
    static /* synthetic */ Object access$100() {
        return Throwables.jla;
    }
    
    static /* synthetic */ Object access$200(final Method method, final Object o, final Object[] array) {
        return invokeAccessibleNonThrowingMethod(method, o, array);
    }
    
    static /* synthetic */ Method access$300() {
        return Throwables.getStackTraceDepthMethod;
    }
    
    static {
        jla = getJLA();
        getStackTraceElementMethod = ((Throwables.jla == null) ? null : getGetMethod());
        getStackTraceDepthMethod = ((Throwables.jla == null) ? null : getSizeMethod());
    }
}
