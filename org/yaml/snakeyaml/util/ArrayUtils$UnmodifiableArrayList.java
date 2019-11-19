package org.yaml.snakeyaml.util;

import java.util.AbstractList;

private static class UnmodifiableArrayList<E> extends AbstractList<E>
{
    private final E[] array;
    
    UnmodifiableArrayList(final E[] array) {
        super();
        this.array = array;
    }
    
    @Override
    public E get(final int n) {
        if (n >= this.array.length) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size());
        }
        return this.array[n];
    }
    
    @Override
    public int size() {
        return this.array.length;
    }
}
