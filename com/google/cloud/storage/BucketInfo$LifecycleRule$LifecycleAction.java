package com.google.cloud.storage;

import java.io.Serializable;

public abstract static class LifecycleAction implements Serializable
{
    private static final long serialVersionUID = 5801228724709173284L;
    
    public LifecycleAction() {
        super();
    }
    
    public abstract String getActionType();
    
    public static DeleteLifecycleAction newDeleteAction() {
        return new DeleteLifecycleAction();
    }
    
    public static SetStorageClassLifecycleAction newSetStorageClassAction(final StorageClass storageClass) {
        return new SetStorageClassLifecycleAction(storageClass);
    }
}
