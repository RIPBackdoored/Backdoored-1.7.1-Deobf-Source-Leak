package com.google.api.client.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public final class Lists
{
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }
    
    public static <E> ArrayList<E> newArrayListWithCapacity(final int n) {
        return new ArrayList<E>(n);
    }
    
    public static <E> ArrayList<E> newArrayList(final Iterable<? extends E> iterable) {
        return (iterable instanceof Collection) ? new ArrayList<E>((Collection<? extends E>)Collections2.<Object>cast((Iterable<Object>)iterable)) : Lists.<E>newArrayList((Iterator<? extends E>)iterable.iterator());
    }
    
    public static <E> ArrayList<E> newArrayList(final Iterator<? extends E> iterator) {
        final ArrayList<E> arrayList = Lists.<E>newArrayList();
        while (iterator.hasNext()) {
            arrayList.add((E)iterator.next());
        }
        return arrayList;
    }
    
    private Lists() {
        super();
    }
}
