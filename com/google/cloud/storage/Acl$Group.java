package com.google.cloud.storage;

public static final class Group extends Entity
{
    private static final long serialVersionUID = -1660987136294408826L;
    
    public Group(final String s) {
        super(Type.GROUP, s);
    }
    
    public String getEmail() {
        return this.getValue();
    }
}
