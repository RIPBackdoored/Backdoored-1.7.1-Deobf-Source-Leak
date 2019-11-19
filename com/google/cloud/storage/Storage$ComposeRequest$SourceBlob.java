package com.google.cloud.storage;

import java.io.Serializable;

public static class SourceBlob implements Serializable
{
    private static final long serialVersionUID = 4094962795951990439L;
    final String name;
    final Long generation;
    
    SourceBlob(final String s) {
        this(s, null);
    }
    
    SourceBlob(final String name, final Long generation) {
        super();
        this.name = name;
        this.generation = generation;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Long getGeneration() {
        return this.generation;
    }
}
