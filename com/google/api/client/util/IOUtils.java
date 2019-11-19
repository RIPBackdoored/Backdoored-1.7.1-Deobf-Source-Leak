package com.google.api.client.util;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class IOUtils
{
    public IOUtils() {
        super();
    }
    
    public static void copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, true);
    }
    
    public static void copy(final InputStream inputStream, final OutputStream outputStream, final boolean b) throws IOException {
        try {
            ByteStreams.copy(inputStream, outputStream);
        }
        finally {
            if (b) {
                inputStream.close();
            }
        }
    }
    
    public static long computeLength(final StreamingContent streamingContent) throws IOException {
        final ByteCountingOutputStream byteCountingOutputStream = new ByteCountingOutputStream();
        try {
            streamingContent.writeTo(byteCountingOutputStream);
        }
        finally {
            byteCountingOutputStream.close();
        }
        return byteCountingOutputStream.count;
    }
    
    public static byte[] serialize(final Object o) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        serialize(o, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void serialize(final Object o, final OutputStream outputStream) throws IOException {
        try {
            new ObjectOutputStream(outputStream).writeObject(o);
        }
        finally {
            outputStream.close();
        }
    }
    
    public static <S extends Serializable> S deserialize(final byte[] array) throws IOException {
        if (array == null) {
            return null;
        }
        return IOUtils.<S>deserialize(new ByteArrayInputStream(array));
    }
    
    public static <S extends Serializable> S deserialize(final InputStream inputStream) throws IOException {
        try {
            return (S)new ObjectInputStream(inputStream).readObject();
        }
        catch (ClassNotFoundException ex2) {
            final IOException ex = new IOException("Failed to deserialize object");
            ex.initCause(ex2);
            throw ex;
        }
        finally {
            inputStream.close();
        }
    }
    
    public static boolean isSymbolicLink(final File file) throws IOException {
        try {
            return (boolean)Class.forName("java.nio.file.Files").getMethod("isSymbolicLink", Class.forName("java.nio.file.Path")).invoke(null, File.class.getMethod("toPath", (Class<?>[])new Class[0]).invoke(file, new Object[0]));
        }
        catch (InvocationTargetException ex) {
            final Throwable cause = ex.getCause();
            Throwables.<IOException>propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        }
        catch (ClassNotFoundException ex2) {}
        catch (IllegalArgumentException ex3) {}
        catch (SecurityException ex4) {}
        catch (IllegalAccessException ex5) {}
        catch (NoSuchMethodException ex6) {}
        if (File.separatorChar == '\\') {
            return false;
        }
        File file2 = file;
        if (file.getParent() != null) {
            file2 = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file2.getCanonicalFile().equals(file2.getAbsoluteFile());
    }
}
