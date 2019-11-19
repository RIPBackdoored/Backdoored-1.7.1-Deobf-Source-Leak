package com.sun.jna;

static final class Native$7 extends ThreadLocal<Memory> {
    Native$7() {
        super();
    }
    
    @Override
    protected Memory initialValue() {
        final Memory memory = new Memory(4L);
        memory.clear();
        return memory;
    }
    
    @Override
    protected /* bridge */ Object initialValue() {
        return this.initialValue();
    }
}