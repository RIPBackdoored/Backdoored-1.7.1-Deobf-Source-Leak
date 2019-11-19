package com.google.cloud.storage.spi.v1;

import com.google.api.client.googleapis.json.GoogleJsonError;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;

public interface RpcBatch
{
    void addDelete(final StorageObject p0, final Callback<Void> p1, final Map<StorageRpc.Option, ?> p2);
    
    void addPatch(final StorageObject p0, final Callback<StorageObject> p1, final Map<StorageRpc.Option, ?> p2);
    
    void addGet(final StorageObject p0, final Callback<StorageObject> p1, final Map<StorageRpc.Option, ?> p2);
    
    void submit();
}
