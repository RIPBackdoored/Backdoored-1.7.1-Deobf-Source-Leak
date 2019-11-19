package com.sun.jna.ptr;

import com.sun.jna.Pointer;
import com.sun.jna.Memory;
import com.sun.jna.PointerType;

public abstract class ByReference extends PointerType
{
    protected ByReference(final int n) {
        super();
        this.setPointer(new Memory(n));
    }
}
