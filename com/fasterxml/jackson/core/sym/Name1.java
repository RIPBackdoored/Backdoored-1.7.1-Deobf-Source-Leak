package com.fasterxml.jackson.core.sym;

public final class Name1 extends Name
{
    private static final Name1 EMPTY;
    private final int q;
    
    Name1(final String s, final int n, final int q) {
        super(s, n);
        this.q = q;
    }
    
    public static Name1 getEmptyName() {
        return Name1.EMPTY;
    }
    
    @Override
    public boolean equals(final int n) {
        return n == this.q;
    }
    
    @Override
    public boolean equals(final int n, final int n2) {
        return n == this.q;
    }
    
    @Override
    public boolean equals(final int n, final int n2, final int n3) {
        return false;
    }
    
    @Override
    public boolean equals(final int[] array, final int n) {
        return n == 1 && array[0] == this.q;
    }
    
    static {
        EMPTY = new Name1("", 0, 0);
    }
}
