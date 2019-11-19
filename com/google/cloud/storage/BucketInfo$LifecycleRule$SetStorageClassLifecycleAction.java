package com.google.cloud.storage;

public static class SetStorageClassLifecycleAction extends LifecycleAction
{
    public static final String TYPE = "SetStorageClass";
    private static final long serialVersionUID = -62615467186000899L;
    private final StorageClass storageClass;
    
    private SetStorageClassLifecycleAction(final StorageClass storageClass) {
        super();
        this.storageClass = storageClass;
    }
    
    @Override
    public String getActionType() {
        return "SetStorageClass";
    }
    
    StorageClass getStorageClass() {
        return this.storageClass;
    }
    
    SetStorageClassLifecycleAction(final StorageClass storageClass, final BucketInfo$1 function) {
        this(storageClass);
    }
}
