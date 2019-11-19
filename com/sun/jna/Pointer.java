package com.sun.jna;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.lang.reflect.Array;
import java.nio.Buffer;

public class Pointer
{
    public static final int SIZE;
    public static final Pointer NULL;
    protected long peer;
    
    public static final Pointer createConstant(final long n) {
        return new Opaque(n);
    }
    
    public static final Pointer createConstant(final int n) {
        return new Opaque((long)n & -1L);
    }
    
    Pointer() {
        super();
    }
    
    public Pointer(final long peer) {
        super();
        this.peer = peer;
    }
    
    public Pointer share(final long n) {
        return this.share(n, 0L);
    }
    
    public Pointer share(final long n, final long n2) {
        if (n == 0L) {
            return this;
        }
        return new Pointer(this.peer + n);
    }
    
    public void clear(final long n) {
        this.setMemory(0L, n, (byte)0);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o != null && o instanceof Pointer && ((Pointer)o).peer == this.peer);
    }
    
    @Override
    public int hashCode() {
        return (int)((this.peer >>> 32) + (this.peer & -1L));
    }
    
    public long indexOf(final long n, final byte b) {
        return Native.indexOf(this, this.peer, n, b);
    }
    
    public void read(final long n, final byte[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final short[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final char[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final int[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final long[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final float[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final double[] array, final int n2, final int n3) {
        Native.read(this, this.peer, n, array, n2, n3);
    }
    
    public void read(final long n, final Pointer[] array, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            final Pointer pointer = this.getPointer(n + i * Pointer.SIZE);
            final Pointer pointer2 = array[i + n2];
            if (pointer2 == null || pointer == null || pointer.peer != pointer2.peer) {
                array[i + n2] = pointer;
            }
        }
    }
    
    public void write(final long n, final byte[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final short[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final char[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final int[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final long[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final float[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final double[] array, final int n2, final int n3) {
        Native.write(this, this.peer, n, array, n2, n3);
    }
    
    public void write(final long n, final Pointer[] array, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            this.setPointer(n + i * Pointer.SIZE, array[n2 + i]);
        }
    }
    
    Object getValue(final long n, final Class<?> clazz, final Object o) {
        Object o2 = null;
        if (Structure.class.isAssignableFrom(clazz)) {
            Structure updateStructureByReference = (Structure)o;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                updateStructureByReference = Structure.updateStructureByReference(clazz, updateStructureByReference, this.getPointer(n));
            }
            else {
                updateStructureByReference.useMemory(this, (int)n, true);
                updateStructureByReference.read();
            }
            o2 = updateStructureByReference;
        }
        else if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            o2 = Function.valueOf(this.getInt(n) != 0);
        }
        else if (clazz == Byte.TYPE || clazz == Byte.class) {
            o2 = this.getByte(n);
        }
        else if (clazz == Short.TYPE || clazz == Short.class) {
            o2 = this.getShort(n);
        }
        else if (clazz == Character.TYPE || clazz == Character.class) {
            o2 = this.getChar(n);
        }
        else if (clazz == Integer.TYPE || clazz == Integer.class) {
            o2 = this.getInt(n);
        }
        else if (clazz == Long.TYPE || clazz == Long.class) {
            o2 = this.getLong(n);
        }
        else if (clazz == Float.TYPE || clazz == Float.class) {
            o2 = this.getFloat(n);
        }
        else if (clazz == Double.TYPE || clazz == Double.class) {
            o2 = this.getDouble(n);
        }
        else if (Pointer.class.isAssignableFrom(clazz)) {
            final Pointer pointer = this.getPointer(n);
            if (pointer != null) {
                final Pointer pointer2 = (o instanceof Pointer) ? ((Pointer)o) : null;
                if (pointer2 == null || pointer.peer != pointer2.peer) {
                    o2 = pointer;
                }
                else {
                    o2 = pointer2;
                }
            }
        }
        else if (clazz == String.class) {
            final Pointer pointer3 = this.getPointer(n);
            o2 = ((pointer3 != null) ? pointer3.getString(0L) : null);
        }
        else if (clazz == WString.class) {
            final Pointer pointer4 = this.getPointer(n);
            o2 = ((pointer4 != null) ? new WString(pointer4.getWideString(0L)) : null);
        }
        else if (Callback.class.isAssignableFrom(clazz)) {
            final Pointer pointer5 = this.getPointer(n);
            if (pointer5 == null) {
                o2 = null;
            }
            else {
                Callback callback = (Callback)o;
                if (!pointer5.equals(CallbackReference.getFunctionPointer(callback))) {
                    callback = CallbackReference.getCallback(clazz, pointer5);
                }
                o2 = callback;
            }
        }
        else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz)) {
            final Pointer pointer6 = this.getPointer(n);
            if (pointer6 == null) {
                o2 = null;
            }
            else {
                final Pointer pointer7 = (o == null) ? null : Native.getDirectBufferPointer((Buffer)o);
                if (pointer7 == null || !pointer7.equals(pointer6)) {
                    throw new IllegalStateException("Can't autogenerate a direct buffer on memory read");
                }
                o2 = o;
            }
        }
        else if (NativeMapped.class.isAssignableFrom(clazz)) {
            final NativeMapped nativeMapped = (NativeMapped)o;
            if (nativeMapped != null) {
                o2 = nativeMapped.fromNative(this.getValue(n, nativeMapped.nativeType(), null), new FromNativeContext(clazz));
                if (nativeMapped.equals(o2)) {
                    o2 = nativeMapped;
                }
            }
            else {
                final NativeMappedConverter instance = NativeMappedConverter.getInstance(clazz);
                o2 = instance.fromNative(this.getValue(n, instance.nativeType(), null), new FromNativeContext(clazz));
            }
        }
        else {
            if (!clazz.isArray()) {
                throw new IllegalArgumentException("Reading \"" + clazz + "\" from memory is not supported");
            }
            o2 = o;
            if (o2 == null) {
                throw new IllegalStateException("Need an initialized array");
            }
            this.readArray(n, o2, clazz.getComponentType());
        }
        return o2;
    }
    
    private void readArray(final long n, final Object o, final Class<?> clazz) {
        final int length = Array.getLength(o);
        if (clazz == Byte.TYPE) {
            this.read(n, (byte[])o, 0, length);
        }
        else if (clazz == Short.TYPE) {
            this.read(n, (short[])o, 0, length);
        }
        else if (clazz == Character.TYPE) {
            this.read(n, (char[])o, 0, length);
        }
        else if (clazz == Integer.TYPE) {
            this.read(n, (int[])o, 0, length);
        }
        else if (clazz == Long.TYPE) {
            this.read(n, (long[])o, 0, length);
        }
        else if (clazz == Float.TYPE) {
            this.read(n, (float[])o, 0, length);
        }
        else if (clazz == Double.TYPE) {
            this.read(n, (double[])o, 0, length);
        }
        else if (Pointer.class.isAssignableFrom(clazz)) {
            this.read(n, (Pointer[])o, 0, length);
        }
        else if (Structure.class.isAssignableFrom(clazz)) {
            final Structure[] array = (Structure[])o;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                final Pointer[] pointerArray = this.getPointerArray(n, array.length);
                for (int i = 0; i < array.length; ++i) {
                    array[i] = Structure.updateStructureByReference(clazz, array[i], pointerArray[i]);
                }
            }
            else {
                Structure instance = array[0];
                if (instance == null) {
                    instance = Structure.newInstance(clazz, this.share(n));
                    instance.conditionalAutoRead();
                    array[0] = instance;
                }
                else {
                    instance.useMemory(this, (int)n, true);
                    instance.read();
                }
                final Structure[] array2 = instance.toArray(array.length);
                for (int j = 1; j < array.length; ++j) {
                    if (array[j] == null) {
                        array[j] = array2[j];
                    }
                    else {
                        array[j].useMemory(this, (int)(n + j * array[j].size()), true);
                        array[j].read();
                    }
                }
            }
        }
        else {
            if (!NativeMapped.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Reading array of " + clazz + " from memory not supported");
            }
            final NativeMapped[] array3 = (NativeMapped[])o;
            final NativeMappedConverter instance2 = NativeMappedConverter.getInstance(clazz);
            final int n2 = Native.getNativeSize(o.getClass(), o) / array3.length;
            for (int k = 0; k < array3.length; ++k) {
                array3[k] = (NativeMapped)instance2.fromNative(this.getValue(n + n2 * k, instance2.nativeType(), array3[k]), new FromNativeContext(clazz));
            }
        }
    }
    
    public byte getByte(final long n) {
        return Native.getByte(this, this.peer, n);
    }
    
    public char getChar(final long n) {
        return Native.getChar(this, this.peer, n);
    }
    
    public short getShort(final long n) {
        return Native.getShort(this, this.peer, n);
    }
    
    public int getInt(final long n) {
        return Native.getInt(this, this.peer, n);
    }
    
    public long getLong(final long n) {
        return Native.getLong(this, this.peer, n);
    }
    
    public NativeLong getNativeLong(final long n) {
        return new NativeLong((NativeLong.SIZE == 8) ? this.getLong(n) : this.getInt(n));
    }
    
    public float getFloat(final long n) {
        return Native.getFloat(this, this.peer, n);
    }
    
    public double getDouble(final long n) {
        return Native.getDouble(this, this.peer, n);
    }
    
    public Pointer getPointer(final long n) {
        return Native.getPointer(this.peer + n);
    }
    
    public ByteBuffer getByteBuffer(final long n, final long n2) {
        return Native.getDirectByteBuffer(this, this.peer, n, n2).order(ByteOrder.nativeOrder());
    }
    
    @Deprecated
    public String getString(final long n, final boolean b) {
        return b ? this.getWideString(n) : this.getString(n);
    }
    
    public String getWideString(final long n) {
        return Native.getWideString(this, this.peer, n);
    }
    
    public String getString(final long n) {
        return this.getString(n, Native.getDefaultStringEncoding());
    }
    
    public String getString(final long n, final String s) {
        return Native.getString(this, n, s);
    }
    
    public byte[] getByteArray(final long n, final int n2) {
        final byte[] array = new byte[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public char[] getCharArray(final long n, final int n2) {
        final char[] array = new char[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public short[] getShortArray(final long n, final int n2) {
        final short[] array = new short[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public int[] getIntArray(final long n, final int n2) {
        final int[] array = new int[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public long[] getLongArray(final long n, final int n2) {
        final long[] array = new long[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public float[] getFloatArray(final long n, final int n2) {
        final float[] array = new float[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public double[] getDoubleArray(final long n, final int n2) {
        final double[] array = new double[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public Pointer[] getPointerArray(final long n) {
        final ArrayList<Pointer> list = new ArrayList<Pointer>();
        int n2 = 0;
        for (Pointer pointer = this.getPointer(n); pointer != null; pointer = this.getPointer(n + n2)) {
            list.add(pointer);
            n2 += Pointer.SIZE;
        }
        return list.<Pointer>toArray(new Pointer[list.size()]);
    }
    
    public Pointer[] getPointerArray(final long n, final int n2) {
        final Pointer[] array = new Pointer[n2];
        this.read(n, array, 0, n2);
        return array;
    }
    
    public String[] getStringArray(final long n) {
        return this.getStringArray(n, -1, Native.getDefaultStringEncoding());
    }
    
    public String[] getStringArray(final long n, final String s) {
        return this.getStringArray(n, -1, s);
    }
    
    public String[] getStringArray(final long n, final int n2) {
        return this.getStringArray(n, n2, Native.getDefaultStringEncoding());
    }
    
    @Deprecated
    public String[] getStringArray(final long n, final boolean b) {
        return this.getStringArray(n, -1, b);
    }
    
    public String[] getWideStringArray(final long n) {
        return this.getWideStringArray(n, -1);
    }
    
    public String[] getWideStringArray(final long n, final int n2) {
        return this.getStringArray(n, n2, "--WIDE-STRING--");
    }
    
    @Deprecated
    public String[] getStringArray(final long n, final int n2, final boolean b) {
        return this.getStringArray(n, n2, b ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }
    
    public String[] getStringArray(final long n, final int n2, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        int n3 = 0;
        if (n2 != -1) {
            Pointer pointer = this.getPointer(n + n3);
            int n4 = 0;
            while (n4++ < n2) {
                list.add((pointer == null) ? null : ("--WIDE-STRING--".equals(s) ? pointer.getWideString(0L) : pointer.getString(0L, s)));
                if (n4 < n2) {
                    n3 += Pointer.SIZE;
                    pointer = this.getPointer(n + n3);
                }
            }
        }
        else {
            Pointer pointer2;
            while ((pointer2 = this.getPointer(n + n3)) != null) {
                list.add((pointer2 == null) ? null : ("--WIDE-STRING--".equals(s) ? pointer2.getWideString(0L) : pointer2.getString(0L, s)));
                n3 += Pointer.SIZE;
            }
        }
        return list.<String>toArray(new String[list.size()]);
    }
    
    void setValue(final long n, final Object o, final Class<?> clazz) {
        if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            this.setInt(n, Boolean.TRUE.equals(o) ? -1 : 0);
        }
        else if (clazz == Byte.TYPE || clazz == Byte.class) {
            this.setByte(n, (byte)((o == null) ? 0 : ((byte)o)));
        }
        else if (clazz == Short.TYPE || clazz == Short.class) {
            this.setShort(n, (short)((o == null) ? 0 : ((short)o)));
        }
        else if (clazz == Character.TYPE || clazz == Character.class) {
            this.setChar(n, (o == null) ? '\0' : ((char)o));
        }
        else if (clazz == Integer.TYPE || clazz == Integer.class) {
            this.setInt(n, (o == null) ? 0 : ((int)o));
        }
        else if (clazz == Long.TYPE || clazz == Long.class) {
            this.setLong(n, (o == null) ? 0L : ((long)o));
        }
        else if (clazz == Float.TYPE || clazz == Float.class) {
            this.setFloat(n, (o == null) ? 0.0f : ((float)o));
        }
        else if (clazz == Double.TYPE || clazz == Double.class) {
            this.setDouble(n, (o == null) ? 0.0 : ((double)o));
        }
        else if (clazz == Pointer.class) {
            this.setPointer(n, (Pointer)o);
        }
        else if (clazz == String.class) {
            this.setPointer(n, (Pointer)o);
        }
        else if (clazz == WString.class) {
            this.setPointer(n, (Pointer)o);
        }
        else if (Structure.class.isAssignableFrom(clazz)) {
            final Structure structure = (Structure)o;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                this.setPointer(n, (structure == null) ? null : structure.getPointer());
                if (structure != null) {
                    structure.autoWrite();
                }
            }
            else {
                structure.useMemory(this, (int)n, true);
                structure.write();
            }
        }
        else if (Callback.class.isAssignableFrom(clazz)) {
            this.setPointer(n, CallbackReference.getFunctionPointer((Callback)o));
        }
        else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz)) {
            this.setPointer(n, (o == null) ? null : Native.getDirectBufferPointer((Buffer)o));
        }
        else if (NativeMapped.class.isAssignableFrom(clazz)) {
            final NativeMappedConverter instance = NativeMappedConverter.getInstance(clazz);
            this.setValue(n, instance.toNative(o, new ToNativeContext()), instance.nativeType());
        }
        else {
            if (!clazz.isArray()) {
                throw new IllegalArgumentException("Writing " + clazz + " to memory is not supported");
            }
            this.writeArray(n, o, clazz.getComponentType());
        }
    }
    
    private void writeArray(final long n, final Object o, final Class<?> clazz) {
        if (clazz == Byte.TYPE) {
            final byte[] array = (byte[])o;
            this.write(n, array, 0, array.length);
        }
        else if (clazz == Short.TYPE) {
            final short[] array2 = (short[])o;
            this.write(n, array2, 0, array2.length);
        }
        else if (clazz == Character.TYPE) {
            final char[] array3 = (char[])o;
            this.write(n, array3, 0, array3.length);
        }
        else if (clazz == Integer.TYPE) {
            final int[] array4 = (int[])o;
            this.write(n, array4, 0, array4.length);
        }
        else if (clazz == Long.TYPE) {
            final long[] array5 = (long[])o;
            this.write(n, array5, 0, array5.length);
        }
        else if (clazz == Float.TYPE) {
            final float[] array6 = (float[])o;
            this.write(n, array6, 0, array6.length);
        }
        else if (clazz == Double.TYPE) {
            final double[] array7 = (double[])o;
            this.write(n, array7, 0, array7.length);
        }
        else if (Pointer.class.isAssignableFrom(clazz)) {
            final Pointer[] array8 = (Pointer[])o;
            this.write(n, array8, 0, array8.length);
        }
        else if (Structure.class.isAssignableFrom(clazz)) {
            final Structure[] array9 = (Structure[])o;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                final Pointer[] array10 = new Pointer[array9.length];
                for (int i = 0; i < array9.length; ++i) {
                    if (array9[i] == null) {
                        array10[i] = null;
                    }
                    else {
                        array10[i] = array9[i].getPointer();
                        array9[i].write();
                    }
                }
                this.write(n, array10, 0, array10.length);
            }
            else {
                Structure instance = array9[0];
                if (instance == null) {
                    instance = Structure.newInstance(clazz, this.share(n));
                    array9[0] = instance;
                }
                else {
                    instance.useMemory(this, (int)n, true);
                }
                instance.write();
                final Structure[] array11 = instance.toArray(array9.length);
                for (int j = 1; j < array9.length; ++j) {
                    if (array9[j] == null) {
                        array9[j] = array11[j];
                    }
                    else {
                        array9[j].useMemory(this, (int)(n + j * array9[j].size()), true);
                    }
                    array9[j].write();
                }
            }
        }
        else {
            if (!NativeMapped.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Writing array of " + clazz + " to memory not supported");
            }
            final NativeMapped[] array12 = (NativeMapped[])o;
            final NativeMappedConverter instance2 = NativeMappedConverter.getInstance(clazz);
            final Class<?> nativeType = instance2.nativeType();
            final int n2 = Native.getNativeSize(o.getClass(), o) / array12.length;
            for (int k = 0; k < array12.length; ++k) {
                this.setValue(n + k * n2, instance2.toNative(array12[k], new ToNativeContext()), nativeType);
            }
        }
    }
    
    public void setMemory(final long n, final long n2, final byte b) {
        Native.setMemory(this, this.peer, n, n2, b);
    }
    
    public void setByte(final long n, final byte b) {
        Native.setByte(this, this.peer, n, b);
    }
    
    public void setShort(final long n, final short n2) {
        Native.setShort(this, this.peer, n, n2);
    }
    
    public void setChar(final long n, final char c) {
        Native.setChar(this, this.peer, n, c);
    }
    
    public void setInt(final long n, final int n2) {
        Native.setInt(this, this.peer, n, n2);
    }
    
    public void setLong(final long n, final long n2) {
        Native.setLong(this, this.peer, n, n2);
    }
    
    public void setNativeLong(final long n, final NativeLong nativeLong) {
        if (NativeLong.SIZE == 8) {
            this.setLong(n, nativeLong.longValue());
        }
        else {
            this.setInt(n, nativeLong.intValue());
        }
    }
    
    public void setFloat(final long n, final float n2) {
        Native.setFloat(this, this.peer, n, n2);
    }
    
    public void setDouble(final long n, final double n2) {
        Native.setDouble(this, this.peer, n, n2);
    }
    
    public void setPointer(final long n, final Pointer pointer) {
        Native.setPointer(this, this.peer, n, (pointer != null) ? pointer.peer : 0L);
    }
    
    @Deprecated
    public void setString(final long n, final String s, final boolean b) {
        if (b) {
            this.setWideString(n, s);
        }
        else {
            this.setString(n, s);
        }
    }
    
    public void setWideString(final long n, final String s) {
        Native.setWideString(this, this.peer, n, s);
    }
    
    public void setString(final long n, final WString wString) {
        this.setWideString(n, (wString == null) ? null : wString.toString());
    }
    
    public void setString(final long n, final String s) {
        this.setString(n, s, Native.getDefaultStringEncoding());
    }
    
    public void setString(final long n, final String s, final String s2) {
        final byte[] bytes = Native.getBytes(s, s2);
        this.write(n, bytes, 0, bytes.length);
        this.setByte(n + bytes.length, (byte)0);
    }
    
    public String dump(final long n, final int n2) {
        final StringWriter stringWriter = new StringWriter("memory dump".length() + 2 + n2 * 2 + n2 / 4 * 4);
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("memory dump");
        for (int i = 0; i < n2; ++i) {
            final byte byte1 = this.getByte(n + i);
            if (i % 4 == 0) {
                printWriter.print("[");
            }
            if (byte1 >= 0 && byte1 < 16) {
                printWriter.print("0");
            }
            printWriter.print(Integer.toHexString(byte1 & 0xFF));
            if (i % 4 == 3 && i < n2 - 1) {
                printWriter.println("]");
            }
        }
        if (stringWriter.getBuffer().charAt(stringWriter.getBuffer().length() - 2) != ']') {
            printWriter.println("]");
        }
        return stringWriter.toString();
    }
    
    @Override
    public String toString() {
        return "native@0x" + Long.toHexString(this.peer);
    }
    
    public static long nativeValue(final Pointer pointer) {
        return (pointer == null) ? 0L : pointer.peer;
    }
    
    public static void nativeValue(final Pointer pointer, final long peer) {
        pointer.peer = peer;
    }
    
    static {
        if ((SIZE = Native.POINTER_SIZE) == 0) {
            throw new Error("Native library not initialized");
        }
        NULL = null;
    }
}
