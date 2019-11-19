package com.fasterxml.jackson.core.sym;

public final class Name2 extends Name
{
    private final int q1;
    private final int q2;
    
    Name2(final String s, final int n, final int q1, final int q2) {
        super(s, n);
        this.q1 = q1;
        this.q2 = q2;
    }
    
    @Override
    public boolean equals(final int n) {
        return false;
    }
    
    @Override
    public boolean equals(final int n, final int n2) {
        return n == this.q1 && n2 == this.q2;
    }
    
    @Override
    public boolean equals(final int n, final int n2, final int n3) {
        return false;
    }
    
    @Override
    public boolean equals(final int[] array, final int n) {
        return n == 2 && array[0] == this.q1 && array[1] == this.q2;
    }
}
