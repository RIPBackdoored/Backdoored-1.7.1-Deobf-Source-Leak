package com.google.api.client.util.store;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import com.google.api.client.util.Maps;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import com.google.api.client.util.Throwables;
import java.io.Serializable;
import java.io.IOException;
import com.google.api.client.util.IOUtils;
import java.io.File;
import java.util.logging.Logger;

public class FileDataStoreFactory extends AbstractDataStoreFactory
{
    private static final Logger LOGGER;
    private final File dataDirectory;
    
    public FileDataStoreFactory(File canonicalFile) throws IOException {
        super();
        canonicalFile = canonicalFile.getCanonicalFile();
        this.dataDirectory = canonicalFile;
        if (IOUtils.isSymbolicLink(canonicalFile)) {
            throw new IOException("unable to use a symbolic link: " + canonicalFile);
        }
        if (!canonicalFile.exists() && !canonicalFile.mkdirs()) {
            throw new IOException("unable to create directory: " + canonicalFile);
        }
        setPermissionsToOwnerOnly(canonicalFile);
    }
    
    public final File getDataDirectory() {
        return this.dataDirectory;
    }
    
    @Override
    protected <V extends Serializable> DataStore<V> createDataStore(final String s) throws IOException {
        return new FileDataStore<V>(this, this.dataDirectory, s);
    }
    
    static void setPermissionsToOwnerOnly(final File file) throws IOException {
        try {
            final Method method = File.class.getMethod("setReadable", Boolean.TYPE, Boolean.TYPE);
            final Method method2 = File.class.getMethod("setWritable", Boolean.TYPE, Boolean.TYPE);
            final Method method3 = File.class.getMethod("setExecutable", Boolean.TYPE, Boolean.TYPE);
            if (!(boolean)method.invoke(file, false, false) || !(boolean)method2.invoke(file, false, false) || !(boolean)method3.invoke(file, false, false)) {
                FileDataStoreFactory.LOGGER.warning("unable to change permissions for everybody: " + file);
            }
            if (!(boolean)method.invoke(file, true, true) || !(boolean)method2.invoke(file, true, true) || !(boolean)method3.invoke(file, true, true)) {
                FileDataStoreFactory.LOGGER.warning("unable to change permissions for owner: " + file);
            }
        }
        catch (InvocationTargetException ex) {
            final Throwable cause = ex.getCause();
            Throwables.<IOException>propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        }
        catch (NoSuchMethodException ex2) {
            FileDataStoreFactory.LOGGER.warning("Unable to set permissions for " + file + ", likely because you are running a version of Java prior to 1.6");
        }
        catch (SecurityException ex3) {}
        catch (IllegalAccessException ex4) {}
        catch (IllegalArgumentException ex5) {}
    }
    
    static {
        LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    }
}
