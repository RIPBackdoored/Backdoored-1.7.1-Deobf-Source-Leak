package com.sun.jna;

private static class AutoAllocated extends Memory
{
    public AutoAllocated(final int n) {
        super(n);
        super.clear();
    }
    
    @Override
    public String toString() {
        return "auto-" + super.toString();
    }
}
