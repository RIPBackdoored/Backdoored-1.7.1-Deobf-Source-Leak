package com.google.cloud.storage;

public static final class Domain extends Entity
{
    private static final long serialVersionUID = -3033025857280447253L;
    
    public Domain(final String s) {
        super(Type.DOMAIN, s);
    }
    
    public String getDomain() {
        return this.getValue();
    }
}
