package com.sun.jna;

private static class NativeMappedArray extends Memory implements PostCallRead
{
    private final NativeMapped[] original;
    
    public NativeMappedArray(final NativeMapped[] original) {
        super(Native.getNativeSize(original.getClass(), original));
        this.setValue(0L, this.original = original, this.original.getClass());
    }
    
    @Override
    public void read() {
        this.getValue(0L, this.original.getClass(), this.original);
    }
}
