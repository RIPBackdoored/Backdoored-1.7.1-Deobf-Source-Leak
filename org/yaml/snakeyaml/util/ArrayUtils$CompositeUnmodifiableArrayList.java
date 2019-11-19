package org.yaml.snakeyaml.util;

import java.util.AbstractList;

private static class CompositeUnmodifiableArrayList<E> extends AbstractList<E>
{
    private final E[] array1;
    private final E[] array2;
    
    CompositeUnmodifiableArrayList(final E[] array1, final E[] array2) {
        super();
        this.array1 = array1;
        this.array2 = array2;
    }
    
    @Override
    public E get(final int n) {
        E e;
        if (n < this.array1.length) {
            e = this.array1[n];
        }
        else {
            if (n - this.array1.length >= this.array2.length) {
                throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size());
            }
            e = this.array2[n - this.array1.length];
        }
        return e;
    }
    
    @Override
    public int size() {
        return this.array1.length + this.array2.length;
    }
}
