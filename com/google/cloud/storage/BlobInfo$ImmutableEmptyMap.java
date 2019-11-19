package com.google.cloud.storage;

import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap;

public static final class ImmutableEmptyMap<K, V> extends AbstractMap<K, V>
{
    public ImmutableEmptyMap() {
        super();
    }
    
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return (Set<Map.Entry<K, V>>)ImmutableSet.<Object>of();
    }
}
