package com.google.cloud.storage;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.PageImpl;

private static class BlobPageFetcher implements PageImpl.NextPageFetcher<Blob>
{
    private static final long serialVersionUID = 81807334445874098L;
    private final Map<StorageRpc.Option, ?> requestOptions;
    private final StorageOptions serviceOptions;
    private final String bucket;
    
    BlobPageFetcher(final String bucket, final StorageOptions serviceOptions, final String s, final Map<StorageRpc.Option, ?> map) {
        super();
        this.requestOptions = (Map<StorageRpc.Option, ?>)PageImpl.nextRequestOptions((Object)StorageRpc.Option.PAGE_TOKEN, s, (Map)map);
        this.serviceOptions = serviceOptions;
        this.bucket = bucket;
    }
    
    public Page<Blob> getNextPage() {
        return (Page<Blob>)StorageImpl.access$200(this.bucket, this.serviceOptions, this.requestOptions);
    }
}
