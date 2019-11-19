package org.reflections;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import java.util.concurrent.ConcurrentHashMap;
import com.google.common.base.Supplier;
import java.util.Set;
import java.util.HashMap;
import com.google.common.collect.Multimap;
import java.util.Map;

public class Store
{
    private transient boolean concurrent;
    private final Map<String, Multimap<String, String>> storeMap;
    
    protected Store() {
        super();
        this.storeMap = new HashMap<String, Multimap<String, String>>();
        this.concurrent = false;
    }
    
    public Store(final Configuration configuration) {
        super();
        this.storeMap = new HashMap<String, Multimap<String, String>>();
        this.concurrent = (configuration.getExecutorService() != null);
    }
    
    public Set<String> keySet() {
        return this.storeMap.keySet();
    }
    
    public Multimap<String, String> getOrCreate(final String s) {
        Object o = this.storeMap.get(s);
        if (o == null) {
            final SetMultimap setMultimap = Multimaps.newSetMultimap((Map)new HashMap(), (Supplier)new Supplier<Set<String>>() {
                final /* synthetic */ Store this$0;
                
                Store$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Set<String> get() {
                    return (Set<String>)Sets.newSetFromMap((Map)new ConcurrentHashMap());
                }
                
                @Override
                public /* bridge */ Object get() {
                    return this.get();
                }
            });
            o = (this.concurrent ? Multimaps.synchronizedSetMultimap(setMultimap) : setMultimap);
            this.storeMap.put(s, (Multimap<String, String>)o);
        }
        return (Multimap<String, String>)o;
    }
    
    public Multimap<String, String> get(final String s) {
        final Multimap<String, String> multimap = this.storeMap.get(s);
        if (multimap == null) {
            throw new ReflectionsException("Scanner " + s + " was not configured");
        }
        return multimap;
    }
    
    public Iterable<String> get(final String s, final String... array) {
        return this.get(s, Arrays.<String>asList(array));
    }
    
    public Iterable<String> get(final String s, final Iterable<String> iterable) {
        final Multimap<String, String> value = this.get(s);
        final IterableChain<Object> iterableChain = (IterableChain<Object>)new IterableChain<String>();
        final Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            iterableChain.addAll(value.get(iterator.next()));
        }
        return (Iterable<String>)iterableChain;
    }
    
    private Iterable<String> getAllIncluding(final String s, final Iterable<String> iterable, final IterableChain<String> iterableChain) {
        ((IterableChain<Object>)iterableChain).addAll(iterable);
        final Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            final Iterable<String> value = this.get(s, iterator.next());
            if (value.iterator().hasNext()) {
                this.getAllIncluding(s, value, iterableChain);
            }
        }
        return iterableChain;
    }
    
    public Iterable<String> getAll(final String s, final String s2) {
        return this.getAllIncluding(s, this.get(s, s2), new IterableChain<String>());
    }
    
    public Iterable<String> getAll(final String s, final Iterable<String> iterable) {
        return this.getAllIncluding(s, this.get(s, iterable), new IterableChain<String>());
    }
}
