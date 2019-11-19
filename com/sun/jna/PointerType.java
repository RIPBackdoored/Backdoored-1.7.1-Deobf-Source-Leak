package com.sun.jna;

public abstract class PointerType implements NativeMapped
{
    private Pointer pointer;
    
    protected PointerType() {
        super();
        this.pointer = Pointer.NULL;
    }
    
    protected PointerType(final Pointer pointer) {
        super();
        this.pointer = pointer;
    }
    
    @Override
    public Class<?> nativeType() {
        return Pointer.class;
    }
    
    @Override
    public Object toNative() {
        return this.getPointer();
    }
    
    public Pointer getPointer() {
        return this.pointer;
    }
    
    public void setPointer(final Pointer pointer) {
        this.pointer = pointer;
    }
    
    @Override
    public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
        if (o == null) {
            return null;
        }
        try {
            final PointerType pointerType = (PointerType)this.getClass().newInstance();
            pointerType.pointer = (Pointer)o;
            return pointerType;
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Can't instantiate " + this.getClass());
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Not allowed to instantiate " + this.getClass());
        }
    }
    
    @Override
    public int hashCode() {
        return (this.pointer != null) ? this.pointer.hashCode() : 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PointerType)) {
            return false;
        }
        final Pointer pointer = ((PointerType)o).getPointer();
        if (this.pointer == null) {
            return pointer == null;
        }
        return this.pointer.equals(pointer);
    }
    
    @Override
    public String toString() {
        return (this.pointer == null) ? "NULL" : (this.pointer.toString() + " (" + super.toString() + ")");
    }
}
