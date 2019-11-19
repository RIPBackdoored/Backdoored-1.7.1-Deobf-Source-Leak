package com.google.api.client.util.store;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import com.google.api.client.util.Maps;
import java.io.IOException;
import com.google.api.client.util.IOUtils;
import java.io.File;
import java.io.Serializable;

static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V>
{
    private final File dataFile;
    
    FileDataStore(final FileDataStoreFactory fileDataStoreFactory, final File file, final String s) throws IOException {
        super(fileDataStoreFactory, s);
        this.dataFile = new File(file, s);
        if (IOUtils.isSymbolicLink(this.dataFile)) {
            throw new IOException("unable to use a symbolic link: " + this.dataFile);
        }
        if (this.dataFile.createNewFile()) {
            this.keyValueMap = Maps.<String, byte[]>newHashMap();
            this.save();
        }
        else {
            this.keyValueMap = IOUtils.<HashMap<String, byte[]>>deserialize(new FileInputStream(this.dataFile));
        }
    }
    
    @Override
    public void save() throws IOException {
        IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
    }
    
    @Override
    public FileDataStoreFactory getDataStoreFactory() {
        return (FileDataStoreFactory)super.getDataStoreFactory();
    }
    
    @Override
    public /* bridge */ DataStoreFactory getDataStoreFactory() {
        return this.getDataStoreFactory();
    }
}
