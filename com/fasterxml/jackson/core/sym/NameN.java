package com.fasterxml.jackson.core.sym;

import java.util.Arrays;

public final class NameN extends Name
{
    private final int q1;
    private final int q2;
    private final int q3;
    private final int q4;
    private final int qlen;
    private final int[] q;
    
    NameN(final String s, final int n, final int q1, final int q2, final int q3, final int q4, final int[] q5, final int qlen) {
        super(s, n);
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q = q5;
        this.qlen = qlen;
    }
    
    public static NameN construct(final String s, final int n, final int[] array, final int n2) {
        if (n2 < 4) {
            throw new IllegalArgumentException();
        }
        final int n3 = array[0];
        final int n4 = array[1];
        final int n5 = array[2];
        final int n6 = array[3];
        int[] copyOfRange;
        if (n2 - 4 > 0) {
            copyOfRange = Arrays.copyOfRange(array, 4, n2);
        }
        else {
            copyOfRange = null;
        }
        return new NameN(s, n, n3, n4, n5, n6, copyOfRange, n2);
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
        return false;
    }
    
    @Override
    public boolean equals(final int[] array, final int n) {
        if (n != this.qlen) {
            return false;
        }
        if (array[0] != this.q1) {
            return false;
        }
        if (array[1] != this.q2) {
            return false;
        }
        if (array[2] != this.q3) {
            return false;
        }
        if (array[3] != this.q4) {
            return false;
        }
        switch (n) {
            default:
                return this._equals2(array);
            case 8:
                if (array[7] != this.q[3]) {
                    return false;
                }
            case 7:
                if (array[6] != this.q[2]) {
                    return false;
                }
            case 6:
                if (array[5] != this.q[1]) {
                    return false;
                }
            case 5:
                if (array[4] != this.q[0]) {
                    return false;
                }
                return true;
            case 4:
                return true;
        }
    }
    
    private final boolean _equals2(final int[] array) {
        for (int n = this.qlen - 4, i = 0; i < n; ++i) {
            if (array[i + 4] != this.q[i]) {
                return false;
            }
        }
        return true;
    }
}
