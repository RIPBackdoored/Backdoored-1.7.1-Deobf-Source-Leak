package org.yaml.snakeyaml.util;

import java.util.ArrayList;

public class ArrayStack<T>
{
    private ArrayList<T> stack;
    
    public ArrayStack(final int initSize) {
        super();
        this.stack = new ArrayList<T>(initSize);
    }
    
    public void push(final T obj) {
        this.stack.add(obj);
    }
    
    public T pop() {
        return this.stack.remove(this.stack.size() - 1);
    }
    
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
