package org.reflections;

import com.google.common.collect.Iterables;
import java.util.Iterator;
import com.google.common.collect.Lists;
import java.util.List;

private static class IterableChain<T> implements Iterable<T>
{
    private final List<Iterable<T>> chain;
    
    private IterableChain() {
        super();
        this.chain = (List<Iterable<T>>)Lists.<Object>newArrayList();
    }
    
    private void addAll(final Iterable<T> iterable) {
        this.chain.add(iterable);
    }
    
    @Override
    public Iterator<T> iterator() {
        return Iterables.<T>concat((Iterable<? extends Iterable<? extends T>>)this.chain).iterator();
    }
    
    IterableChain(final Store$1 supplier) {
        this();
    }
    
    static /* synthetic */ void access$100(final IterableChain iterableChain, final Iterable iterable) {
        iterableChain.addAll(iterable);
    }
}
