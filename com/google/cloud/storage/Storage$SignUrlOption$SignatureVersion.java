package com.google.cloud.storage;

enum SignatureVersion
{
    V2, 
    V4;
    
    private static final /* synthetic */ SignatureVersion[] $VALUES;
    
    public static SignatureVersion[] values() {
        return SignatureVersion.$VALUES.clone();
    }
    
    public static SignatureVersion valueOf(final String s) {
        return Enum.<SignatureVersion>valueOf(SignatureVersion.class, s);
    }
    
    static {
        $VALUES = new SignatureVersion[] { SignatureVersion.V2, SignatureVersion.V4 };
    }
}
