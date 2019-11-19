package com.google.cloud.storage;

import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.google.api.services.storage.model.Bucket;

static class RawDeleteRule extends DeleteRule
{
    private static final long serialVersionUID = -7166938278642301933L;
    private transient Bucket.Lifecycle.Rule rule;
    
    RawDeleteRule(final Bucket.Lifecycle.Rule rule) {
        super(Type.UNKNOWN);
        this.rule = rule;
    }
    
    @Override
    void populateCondition(final Bucket.Lifecycle.Rule.Condition condition) {
        throw new UnsupportedOperationException();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.rule.toString());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.rule = new JacksonFactory().<Bucket.Lifecycle.Rule>fromString(objectInputStream.readUTF(), Bucket.Lifecycle.Rule.class);
    }
    
    @Override
    Bucket.Lifecycle.Rule toPb() {
        return this.rule;
    }
}
