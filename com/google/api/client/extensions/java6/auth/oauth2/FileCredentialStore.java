package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.json.JsonGenerator;
import java.io.OutputStream;
import com.google.api.client.util.Charsets;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;
import com.google.api.client.util.Preconditions;
import java.util.concurrent.locks.ReentrantLock;
import java.io.File;
import java.util.concurrent.locks.Lock;
import com.google.api.client.json.JsonFactory;
import java.util.logging.Logger;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.CredentialStore;

@Deprecated
@Beta
public class FileCredentialStore implements CredentialStore
{
    private static final Logger LOGGER;
    private final JsonFactory jsonFactory;
    private final Lock lock;
    private FilePersistedCredentials credentials;
    private final File file;
    private static final boolean IS_WINDOWS;
    
    public FileCredentialStore(final File file, final JsonFactory jsonFactory) throws IOException {
        super();
        this.lock = new ReentrantLock();
        this.credentials = new FilePersistedCredentials();
        this.file = Preconditions.<File>checkNotNull(file);
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        final File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            final String value = String.valueOf(String.valueOf(parentFile));
            throw new IOException(new StringBuilder(35 + value.length()).append("unable to create parent directory: ").append(value).toString());
        }
        if (this.isSymbolicLink(file)) {
            final String value2 = String.valueOf(String.valueOf(file));
            throw new IOException(new StringBuilder(31 + value2.length()).append("unable to use a symbolic link: ").append(value2).toString());
        }
        if (!file.createNewFile()) {
            this.loadCredentials(file);
        }
        else {
            if (!file.setReadable(false, false) || !file.setWritable(false, false) || !file.setExecutable(false, false)) {
                final Logger logger = FileCredentialStore.LOGGER;
                final String value3 = String.valueOf(String.valueOf(file));
                logger.warning(new StringBuilder(49 + value3.length()).append("unable to change file permissions for everybody: ").append(value3).toString());
            }
            if (!file.setReadable(true) || !file.setWritable(true)) {
                final String value4 = String.valueOf(String.valueOf(file));
                throw new IOException(new StringBuilder(32 + value4.length()).append("unable to set file permissions: ").append(value4).toString());
            }
            this.save();
        }
    }
    
    protected boolean isSymbolicLink(final File file) throws IOException {
        if (FileCredentialStore.IS_WINDOWS) {
            return false;
        }
        File file2 = file;
        if (file.getParent() != null) {
            file2 = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file2.getCanonicalFile().equals(file2.getAbsoluteFile());
    }
    
    public void store(final String s, final Credential credential) throws IOException {
        this.lock.lock();
        try {
            this.credentials.store(s, credential);
            this.save();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void delete(final String s, final Credential credential) throws IOException {
        this.lock.lock();
        try {
            this.credentials.delete(s);
            this.save();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public boolean load(final String s, final Credential credential) {
        this.lock.lock();
        try {
            return this.credentials.load(s, credential);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private void loadCredentials(final File file) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        try {
            this.credentials = this.jsonFactory.<FilePersistedCredentials>fromInputStream(fileInputStream, FilePersistedCredentials.class);
        }
        finally {
            fileInputStream.close();
        }
    }
    
    private void save() throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        try {
            final JsonGenerator jsonGenerator = this.jsonFactory.createJsonGenerator(fileOutputStream, Charsets.UTF_8);
            jsonGenerator.serialize(this.credentials);
            jsonGenerator.close();
        }
        finally {
            fileOutputStream.close();
        }
    }
    
    public final void migrateTo(final FileDataStoreFactory fileDataStoreFactory) throws IOException {
        this.migrateTo(StoredCredential.getDefaultDataStore(fileDataStoreFactory));
    }
    
    public final void migrateTo(final DataStore<StoredCredential> dataStore) throws IOException {
        this.credentials.migrateTo(dataStore);
    }
    
    static {
        LOGGER = Logger.getLogger(FileCredentialStore.class.getName());
        IS_WINDOWS = (File.separatorChar == '\\');
    }
}
