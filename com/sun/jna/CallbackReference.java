package com.sun.jna;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.sun.jna.win32.DLLCallback;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.ref.Reference;
import java.util.Map;
import java.lang.ref.WeakReference;

public class CallbackReference extends WeakReference<Callback>
{
    static final Map<Callback, CallbackReference> callbackMap;
    static final Map<Callback, CallbackReference> directCallbackMap;
    static final Map<Pointer, Reference<Callback>> pointerCallbackMap;
    static final Map<Object, Object> allocations;
    private static final Map<CallbackReference, Reference<CallbackReference>> allocatedMemory;
    private static final Method PROXY_CALLBACK_METHOD;
    private static final Map<Callback, CallbackThreadInitializer> initializers;
    Pointer cbstruct;
    Pointer trampoline;
    CallbackProxy proxy;
    Method method;
    int callingConvention;
    
    static CallbackThreadInitializer setCallbackThreadInitializer(final Callback callback, final CallbackThreadInitializer callbackThreadInitializer) {
        synchronized (CallbackReference.initializers) {
            if (callbackThreadInitializer != null) {
                return CallbackReference.initializers.put(callback, callbackThreadInitializer);
            }
            return CallbackReference.initializers.remove(callback);
        }
    }
    
    private static ThreadGroup initializeThread(Callback callback, final AttachOptions attachOptions) {
        CallbackThreadInitializer callbackThreadInitializer = null;
        if (callback instanceof DefaultCallbackProxy) {
            callback = ((DefaultCallbackProxy)callback).getCallback();
        }
        synchronized (CallbackReference.initializers) {
            callbackThreadInitializer = CallbackReference.initializers.get(callback);
        }
        ThreadGroup threadGroup = null;
        if (callbackThreadInitializer != null) {
            threadGroup = callbackThreadInitializer.getThreadGroup(callback);
            attachOptions.name = callbackThreadInitializer.getName(callback);
            attachOptions.daemon = callbackThreadInitializer.isDaemon(callback);
            attachOptions.detach = callbackThreadInitializer.detach(callback);
            attachOptions.write();
        }
        return threadGroup;
    }
    
    public static Callback getCallback(final Class<?> clazz, final Pointer pointer) {
        return getCallback(clazz, pointer, false);
    }
    
    private static Callback getCallback(final Class<?> clazz, final Pointer pointer, final boolean b) {
        if (pointer == null) {
            return null;
        }
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        final Map<Callback, CallbackReference> map = b ? CallbackReference.directCallbackMap : CallbackReference.callbackMap;
        synchronized (CallbackReference.pointerCallbackMap) {
            final Reference<Callback> reference = CallbackReference.pointerCallbackMap.get(pointer);
            if (reference == null) {
                final int n = AltCallingConvention.class.isAssignableFrom(clazz) ? 63 : 0;
                final HashMap<String, Method> hashMap = new HashMap<String, Method>((Map<? extends String, ? extends Method>)Native.getLibraryOptions(clazz));
                hashMap.put("invoking-method", getCallbackMethod(clazz));
                final Callback callback = (Callback)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new NativeFunctionHandler(pointer, n, hashMap));
                map.remove(callback);
                CallbackReference.pointerCallbackMap.put(pointer, new WeakReference<Callback>(callback));
                return callback;
            }
            final Callback callback2 = reference.get();
            if (callback2 != null && !clazz.isAssignableFrom(callback2.getClass())) {
                throw new IllegalStateException("Pointer " + pointer + " already mapped to " + callback2 + ".\nNative code may be re-using a default function pointer, in which case you may need to use a common Callback class wherever the function pointer is reused.");
            }
            return callback2;
        }
    }
    
    private CallbackReference(final Callback callback, final int callingConvention, boolean b) {
        super(callback);
        final TypeMapper typeMapper = Native.getTypeMapper(callback.getClass());
        this.callingConvention = callingConvention;
        final boolean ppc = Platform.isPPC();
        if (b) {
            final Method callbackMethod = getCallbackMethod(callback);
            final Class<?>[] parameterTypes = callbackMethod.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (ppc && (parameterTypes[i] == Float.TYPE || parameterTypes[i] == Double.TYPE)) {
                    b = false;
                    break;
                }
                if (typeMapper != null && typeMapper.getFromNativeConverter(parameterTypes[i]) != null) {
                    b = false;
                    break;
                }
            }
            if (typeMapper != null && typeMapper.getToNativeConverter(callbackMethod.getReturnType()) != null) {
                b = false;
            }
        }
        final String stringEncoding = Native.getStringEncoding(callback.getClass());
        long n2;
        if (b) {
            this.method = getCallbackMethod(callback);
            final Class<?>[] parameterTypes2 = this.method.getParameterTypes();
            final Class<?> returnType = this.method.getReturnType();
            int n = 1;
            if (callback instanceof DLLCallback) {
                n |= 0x2;
            }
            n2 = Native.createNativeCallback(callback, this.method, parameterTypes2, returnType, callingConvention, n, stringEncoding);
        }
        else {
            if (callback instanceof CallbackProxy) {
                this.proxy = (CallbackProxy)callback;
            }
            else {
                this.proxy = new DefaultCallbackProxy(getCallbackMethod(callback), typeMapper, stringEncoding);
            }
            final Class<?>[] parameterTypes3 = this.proxy.getParameterTypes();
            Class<?> clazz = this.proxy.getReturnType();
            if (typeMapper != null) {
                for (int j = 0; j < parameterTypes3.length; ++j) {
                    final FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter(parameterTypes3[j]);
                    if (fromNativeConverter != null) {
                        parameterTypes3[j] = fromNativeConverter.nativeType();
                    }
                }
                final ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(clazz);
                if (toNativeConverter != null) {
                    clazz = toNativeConverter.nativeType();
                }
            }
            for (int k = 0; k < parameterTypes3.length; ++k) {
                parameterTypes3[k] = this.getNativeType(parameterTypes3[k]);
                if (!isAllowableNativeType(parameterTypes3[k])) {
                    throw new IllegalArgumentException("Callback argument " + parameterTypes3[k] + " requires custom type conversion");
                }
            }
            final Class<?> nativeType = this.getNativeType(clazz);
            if (!isAllowableNativeType(nativeType)) {
                throw new IllegalArgumentException("Callback return type " + nativeType + " requires custom type conversion");
            }
            n2 = Native.createNativeCallback(this.proxy, CallbackReference.PROXY_CALLBACK_METHOD, parameterTypes3, nativeType, callingConvention, (callback instanceof DLLCallback) ? 2 : 0, stringEncoding);
        }
        this.cbstruct = ((n2 != 0L) ? new Pointer(n2) : null);
        CallbackReference.allocatedMemory.put(this, new WeakReference<CallbackReference>(this));
    }
    
    private Class<?> getNativeType(final Class<?> clazz) {
        if (Structure.class.isAssignableFrom(clazz)) {
            Structure.validate(clazz);
            if (!Structure.ByValue.class.isAssignableFrom(clazz)) {
                return Pointer.class;
            }
        }
        else {
            if (NativeMapped.class.isAssignableFrom(clazz)) {
                return NativeMappedConverter.getInstance(clazz).nativeType();
            }
            if (clazz == String.class || clazz == WString.class || clazz == String[].class || clazz == WString[].class || Callback.class.isAssignableFrom(clazz)) {
                return Pointer.class;
            }
        }
        return clazz;
    }
    
    private static Method checkMethod(final Method method) {
        if (method.getParameterTypes().length > 256) {
            throw new UnsupportedOperationException("Method signature exceeds the maximum parameter count: " + method);
        }
        return method;
    }
    
    static Class<?> findCallbackClass(final Class<?> clazz) {
        if (!Callback.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " is not derived from com.sun.jna.Callback");
        }
        if (clazz.isInterface()) {
            return clazz;
        }
        final Class<?>[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (Callback.class.isAssignableFrom(interfaces[i])) {
                try {
                    getCallbackMethod(interfaces[i]);
                    return interfaces[i];
                }
                catch (IllegalArgumentException ex) {
                    break;
                }
            }
        }
        if (Callback.class.isAssignableFrom(clazz.getSuperclass())) {
            return findCallbackClass(clazz.getSuperclass());
        }
        return clazz;
    }
    
    private static Method getCallbackMethod(final Callback callback) {
        return getCallbackMethod(findCallbackClass(callback.getClass()));
    }
    
    private static Method getCallbackMethod(final Class<?> clazz) {
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        final Method[] methods = clazz.getMethods();
        final HashSet set = new HashSet<Object>(Arrays.<Method>asList(declaredMethods));
        set.retainAll(Arrays.<Method>asList(methods));
        final Iterator<Method> iterator = (Iterator<Method>)set.iterator();
        while (iterator.hasNext()) {
            if (Callback.FORBIDDEN_NAMES.contains(iterator.next().getName())) {
                iterator.remove();
            }
        }
        final Method[] array = set.<Method>toArray(new Method[set.size()]);
        if (array.length == 1) {
            return checkMethod(array[0]);
        }
        for (int i = 0; i < array.length; ++i) {
            final Method method = array[i];
            if ("callback".equals(method.getName())) {
                return checkMethod(method);
            }
        }
        throw new IllegalArgumentException("Callback must implement a single public method, or one public method named 'callback'");
    }
    
    private void setCallbackOptions(final int n) {
        this.cbstruct.setInt(Pointer.SIZE, n);
    }
    
    public Pointer getTrampoline() {
        if (this.trampoline == null) {
            this.trampoline = this.cbstruct.getPointer(0L);
        }
        return this.trampoline;
    }
    
    @Override
    protected void finalize() {
        this.dispose();
    }
    
    protected synchronized void dispose() {
        if (this.cbstruct != null) {
            try {
                Native.freeNativeCallback(this.cbstruct.peer);
            }
            finally {
                this.cbstruct.peer = 0L;
                this.cbstruct = null;
                CallbackReference.allocatedMemory.remove(this);
            }
        }
    }
    
    static void disposeAll() {
        final Iterator<CallbackReference> iterator = new LinkedList<CallbackReference>(CallbackReference.allocatedMemory.keySet()).iterator();
        while (iterator.hasNext()) {
            iterator.next().dispose();
        }
    }
    
    private Callback getCallback() {
        return this.get();
    }
    
    private static Pointer getNativeFunctionPointer(final Callback callback) {
        if (Proxy.isProxyClass(callback.getClass())) {
            final InvocationHandler invocationHandler = Proxy.getInvocationHandler(callback);
            if (invocationHandler instanceof NativeFunctionHandler) {
                return ((NativeFunctionHandler)invocationHandler).getPointer();
            }
        }
        return null;
    }
    
    public static Pointer getFunctionPointer(final Callback callback) {
        return getFunctionPointer(callback, false);
    }
    
    private static Pointer getFunctionPointer(final Callback callback, final boolean b) {
        if (callback == null) {
            return null;
        }
        final Pointer nativeFunctionPointer;
        if ((nativeFunctionPointer = getNativeFunctionPointer(callback)) != null) {
            return nativeFunctionPointer;
        }
        final Map<String, Object> libraryOptions = Native.getLibraryOptions(callback.getClass());
        final int n = (callback instanceof AltCallingConvention) ? 63 : ((libraryOptions != null && libraryOptions.containsKey("calling-convention")) ? libraryOptions.get("calling-convention") : 0);
        final Map<Callback, CallbackReference> map = b ? CallbackReference.directCallbackMap : CallbackReference.callbackMap;
        synchronized (CallbackReference.pointerCallbackMap) {
            CallbackReference callbackReference = map.get(callback);
            if (callbackReference == null) {
                callbackReference = new CallbackReference(callback, n, b);
                map.put(callback, callbackReference);
                CallbackReference.pointerCallbackMap.put(callbackReference.getTrampoline(), new WeakReference<Callback>(callback));
                if (CallbackReference.initializers.containsKey(callback)) {
                    callbackReference.setCallbackOptions(1);
                }
            }
            return callbackReference.getTrampoline();
        }
    }
    
    private static boolean isAllowableNativeType(final Class<?> clazz) {
        return clazz == Void.TYPE || clazz == Void.class || clazz == Boolean.TYPE || clazz == Boolean.class || clazz == Byte.TYPE || clazz == Byte.class || clazz == Short.TYPE || clazz == Short.class || clazz == Character.TYPE || clazz == Character.class || clazz == Integer.TYPE || clazz == Integer.class || clazz == Long.TYPE || clazz == Long.class || clazz == Float.TYPE || clazz == Float.class || clazz == Double.TYPE || clazz == Double.class || (Structure.ByValue.class.isAssignableFrom(clazz) && Structure.class.isAssignableFrom(clazz)) || Pointer.class.isAssignableFrom(clazz);
    }
    
    private static Pointer getNativeString(final Object o, final boolean b) {
        if (o != null) {
            final NativeString nativeString = new NativeString(o.toString(), b);
            CallbackReference.allocations.put(o, nativeString);
            return nativeString.getPointer();
        }
        return null;
    }
    
    static /* synthetic */ Callback access$000(final CallbackReference callbackReference) {
        return callbackReference.getCallback();
    }
    
    static /* synthetic */ Pointer access$100(final Object o, final boolean b) {
        return getNativeString(o, b);
    }
    
    static {
        callbackMap = new WeakHashMap<Callback, CallbackReference>();
        directCallbackMap = new WeakHashMap<Callback, CallbackReference>();
        pointerCallbackMap = new WeakHashMap<Pointer, Reference<Callback>>();
        allocations = new WeakHashMap<Object, Object>();
        allocatedMemory = Collections.<CallbackReference, Reference<CallbackReference>>synchronizedMap(new WeakHashMap<CallbackReference, Reference<CallbackReference>>());
        try {
            PROXY_CALLBACK_METHOD = CallbackProxy.class.getMethod("callback", Object[].class);
        }
        catch (Exception ex) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
        initializers = new WeakHashMap<Callback, CallbackThreadInitializer>();
    }
}
