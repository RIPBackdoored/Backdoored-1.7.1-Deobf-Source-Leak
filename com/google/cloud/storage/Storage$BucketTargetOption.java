package com.google.cloud.storage;

import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BucketTargetOption extends Option
{
    private static final long serialVersionUID = -5880204616982900975L;
    
    private BucketTargetOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    private BucketTargetOption(final StorageRpc.Option option) {
        this(option, null);
    }
    
    public static BucketTargetOption predefinedAcl(final PredefinedAcl predefinedAcl) {
        return new BucketTargetOption(StorageRpc.Option.PREDEFINED_ACL, predefinedAcl.getEntry());
    }
    
    public static BucketTargetOption predefinedDefaultObjectAcl(final PredefinedAcl predefinedAcl) {
        return new BucketTargetOption(StorageRpc.Option.PREDEFINED_DEFAULT_OBJECT_ACL, predefinedAcl.getEntry());
    }
    
    public static BucketTargetOption metagenerationMatch() {
        return new BucketTargetOption(StorageRpc.Option.IF_METAGENERATION_MATCH);
    }
    
    public static BucketTargetOption metagenerationNotMatch() {
        return new BucketTargetOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH);
    }
    
    public static BucketTargetOption userProject(final String s) {
        return new BucketTargetOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BucketTargetOption projection(final String s) {
        return new BucketTargetOption(StorageRpc.Option.PROJECTION, s);
    }
}
