package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap<String, String>
{
    private static final long serialVersionUID = 1L;
    private static final int MAX_ENTRIES = 180;
    public static final InternCache instance;
    private final Object lock;
    
    private InternCache() {
        super(180, 0.8f, 4);
        this.lock = new Object();
    }
    
    public String intern(final String s) {
        final String s2 = ((ConcurrentHashMap<K, String>)this).get(s);
        if (s2 != null) {
            return s2;
        }
        if (this.size() >= 180) {
            synchronized (this.lock) {
                if (this.size() >= 180) {
                    this.clear();
                }
            }
        }
        final String intern = s.intern();
        this.put(intern, intern);
        return intern;
    }
    
    static {
        instance = new InternCache();
    }
}
