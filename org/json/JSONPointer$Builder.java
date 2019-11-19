package org.json;

import java.util.ArrayList;
import java.util.List;

public static class Builder
{
    private final List<String> refTokens;
    
    public Builder() {
        super();
        this.refTokens = new ArrayList<String>();
    }
    
    public JSONPointer build() {
        return new JSONPointer(this.refTokens);
    }
    
    public Builder append(final String s) {
        if (s == null) {
            throw new NullPointerException("token cannot be null");
        }
        this.refTokens.add(s);
        return this;
    }
    
    public Builder append(final int n) {
        this.refTokens.add(String.valueOf(n));
        return this;
    }
}
