package com.sun.jna;

import java.util.WeakHashMap;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

static class FFIType extends Structure
{
    private static final Map<Object, Object> typeInfoMap;
    private static final int FFI_TYPE_STRUCT = 13;
    public size_t size;
    public short alignment;
    public short type;
    public Pointer elements;
    
    private FFIType(final Structure structure) {
        super();
        this.type = 13;
        Structure.access$1900(structure, true);
        Pointer[] array;
        if (structure instanceof Union) {
            final StructField typeInfoField = ((Union)structure).typeInfoField();
            array = new Pointer[] { get(structure.getFieldValue(typeInfoField.field), typeInfoField.type), null };
        }
        else {
            array = new Pointer[structure.fields().size() + 1];
            int n = 0;
            final Iterator<StructField> iterator = structure.fields().values().iterator();
            while (iterator.hasNext()) {
                array[n++] = structure.getFieldTypeInfo(iterator.next());
            }
        }
        this.init(array);
    }
    
    private FFIType(final Object o, final Class<?> clazz) {
        super();
        this.type = 13;
        final int length = Array.getLength(o);
        final Pointer[] array = new Pointer[length + 1];
        final Pointer value = get(null, clazz.getComponentType());
        for (int i = 0; i < length; ++i) {
            array[i] = value;
        }
        this.init(array);
    }
    
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.<String>asList("size", "alignment", "type", "elements");
    }
    
    private void init(final Pointer[] array) {
        (this.elements = new Memory(Pointer.SIZE * array.length)).write(0L, array, 0, array.length);
        this.write();
    }
    
    static Pointer get(final Object o) {
        if (o == null) {
            return FFITypes.ffi_type_pointer;
        }
        if (o instanceof Class) {
            return get(null, (Class<?>)o);
        }
        return get(o, o.getClass());
    }
    
    private static Pointer get(Object instance, Class<?> nativeType) {
        final TypeMapper typeMapper = Native.getTypeMapper(nativeType);
        if (typeMapper != null) {
            final ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(nativeType);
            if (toNativeConverter != null) {
                nativeType = toNativeConverter.nativeType();
            }
        }
        synchronized (FFIType.typeInfoMap) {
            final Object value = FFIType.typeInfoMap.get(nativeType);
            if (value instanceof Pointer) {
                return (Pointer)value;
            }
            if (value instanceof FFIType) {
                return ((FFIType)value).getPointer();
            }
            if ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(nativeType)) || Callback.class.isAssignableFrom(nativeType)) {
                FFIType.typeInfoMap.put(nativeType, FFITypes.ffi_type_pointer);
                return FFITypes.ffi_type_pointer;
            }
            if (Structure.class.isAssignableFrom(nativeType)) {
                if (instance == null) {
                    instance = Structure.newInstance(nativeType, Structure.access$2000());
                }
                if (ByReference.class.isAssignableFrom(nativeType)) {
                    FFIType.typeInfoMap.put(nativeType, FFITypes.ffi_type_pointer);
                    return FFITypes.ffi_type_pointer;
                }
                final FFIType ffiType = new FFIType((Structure)instance);
                FFIType.typeInfoMap.put(nativeType, ffiType);
                return ffiType.getPointer();
            }
            else {
                if (NativeMapped.class.isAssignableFrom(nativeType)) {
                    final NativeMappedConverter instance2 = NativeMappedConverter.getInstance(nativeType);
                    return get(instance2.toNative(instance, new ToNativeContext()), instance2.nativeType());
                }
                if (nativeType.isArray()) {
                    final FFIType ffiType2 = new FFIType(instance, nativeType);
                    FFIType.typeInfoMap.put(instance, ffiType2);
                    return ffiType2.getPointer();
                }
                throw new IllegalArgumentException("Unsupported type " + nativeType);
            }
        }
    }
    
    static /* synthetic */ Pointer access$800(final Object o, final Class clazz) {
        return get(o, clazz);
    }
    
    static {
        typeInfoMap = new WeakHashMap<Object, Object>();
        if (Native.POINTER_SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        if (FFITypes.ffi_type_void == null) {
            throw new Error("FFI types not initialized");
        }
        FFIType.typeInfoMap.put(Void.TYPE, FFITypes.ffi_type_void);
        FFIType.typeInfoMap.put(Void.class, FFITypes.ffi_type_void);
        FFIType.typeInfoMap.put(Float.TYPE, FFITypes.ffi_type_float);
        FFIType.typeInfoMap.put(Float.class, FFITypes.ffi_type_float);
        FFIType.typeInfoMap.put(Double.TYPE, FFITypes.ffi_type_double);
        FFIType.typeInfoMap.put(Double.class, FFITypes.ffi_type_double);
        FFIType.typeInfoMap.put(Long.TYPE, FFITypes.ffi_type_sint64);
        FFIType.typeInfoMap.put(Long.class, FFITypes.ffi_type_sint64);
        FFIType.typeInfoMap.put(Integer.TYPE, FFITypes.ffi_type_sint32);
        FFIType.typeInfoMap.put(Integer.class, FFITypes.ffi_type_sint32);
        FFIType.typeInfoMap.put(Short.TYPE, FFITypes.ffi_type_sint16);
        FFIType.typeInfoMap.put(Short.class, FFITypes.ffi_type_sint16);
        final Pointer pointer = (Native.WCHAR_SIZE == 2) ? FFITypes.ffi_type_uint16 : FFITypes.ffi_type_uint32;
        FFIType.typeInfoMap.put(Character.TYPE, pointer);
        FFIType.typeInfoMap.put(Character.class, pointer);
        FFIType.typeInfoMap.put(Byte.TYPE, FFITypes.ffi_type_sint8);
        FFIType.typeInfoMap.put(Byte.class, FFITypes.ffi_type_sint8);
        FFIType.typeInfoMap.put(Pointer.class, FFITypes.ffi_type_pointer);
        FFIType.typeInfoMap.put(String.class, FFITypes.ffi_type_pointer);
        FFIType.typeInfoMap.put(WString.class, FFITypes.ffi_type_pointer);
        FFIType.typeInfoMap.put(Boolean.TYPE, FFITypes.ffi_type_uint32);
        FFIType.typeInfoMap.put(Boolean.class, FFITypes.ffi_type_uint32);
    }
}
