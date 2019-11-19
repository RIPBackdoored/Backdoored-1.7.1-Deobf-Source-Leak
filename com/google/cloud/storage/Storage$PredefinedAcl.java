package com.google.cloud.storage;

public enum PredefinedAcl
{
    AUTHENTICATED_READ("authenticatedRead"), 
    ALL_AUTHENTICATED_USERS("allAuthenticatedUsers"), 
    PRIVATE("private"), 
    PROJECT_PRIVATE("projectPrivate"), 
    PUBLIC_READ("publicRead"), 
    PUBLIC_READ_WRITE("publicReadWrite"), 
    BUCKET_OWNER_READ("bucketOwnerRead"), 
    BUCKET_OWNER_FULL_CONTROL("bucketOwnerFullControl");
    
    private final String entry;
    private static final /* synthetic */ PredefinedAcl[] $VALUES;
    
    public static PredefinedAcl[] values() {
        return PredefinedAcl.$VALUES.clone();
    }
    
    public static PredefinedAcl valueOf(final String s) {
        return Enum.<PredefinedAcl>valueOf(PredefinedAcl.class, s);
    }
    
    private PredefinedAcl(final String entry) {
        this.entry = entry;
    }
    
    String getEntry() {
        return this.entry;
    }
    
    static {
        $VALUES = new PredefinedAcl[] { PredefinedAcl.AUTHENTICATED_READ, PredefinedAcl.ALL_AUTHENTICATED_USERS, PredefinedAcl.PRIVATE, PredefinedAcl.PROJECT_PRIVATE, PredefinedAcl.PUBLIC_READ, PredefinedAcl.PUBLIC_READ_WRITE, PredefinedAcl.BUCKET_OWNER_READ, PredefinedAcl.BUCKET_OWNER_FULL_CONTROL };
    }
}
