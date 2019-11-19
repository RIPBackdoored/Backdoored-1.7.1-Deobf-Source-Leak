package com.fasterxml.jackson.core.sym;

public final class Name3 extends Name
{
    private final int q1;
    private final int q2;
    private final int q3;
    
    Name3(final String s, final int n, final int q1, final int q2, final int q3) {
        super(s, n);
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }
    
    @Override
    public boolean equals(final int n) {
        return false;
    }
    
    @Override
    public boolean equals(final int n, final int n2) {
        return false;
    }
    
    @Override
    public boolean equals(final int n, final int n2, final int n3) {
        return this.q1 == n && this.q2 == n2 && this.q3 == n3;
    }
    
    @Override
    public boolean equals(final int[] array, final int n) {
        return n == 3 && array[0] == this.q1 && array[1] == this.q2 && array[2] == this.q3;
    }
}
