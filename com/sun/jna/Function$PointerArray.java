package com.sun.jna;

private static class PointerArray extends Memory implements PostCallRead
{
    private final Pointer[] original;
    
    public PointerArray(final Pointer[] original) {
        super(Pointer.SIZE * (original.length + 1));
        this.original = original;
        for (int i = 0; i < original.length; ++i) {
            this.setPointer(i * Pointer.SIZE, original[i]);
        }
        this.setPointer(Pointer.SIZE * original.length, null);
    }
    
    @Override
    public void read() {
        this.read(0L, this.original, 0, this.original.length);
    }
}
