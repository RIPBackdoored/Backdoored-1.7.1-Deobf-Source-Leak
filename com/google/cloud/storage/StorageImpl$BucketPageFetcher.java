package com.google.cloud.storage;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.PageImpl;

private static class BucketPageFetcher implements PageImpl.NextPageFetcher<Bucket>
{
    private static final long serialVersionUID = 5850406828803613729L;
    private final Map<StorageRpc.Option, ?> requestOptions;
    private final StorageOptions serviceOptions;
    
    BucketPageFetcher(final StorageOptions serviceOptions, final String s, final Map<StorageRpc.Option, ?> map) {
        super();
        this.requestOptions = (Map<StorageRpc.Option, ?>)PageImpl.nextRequestOptions((Object)StorageRpc.Option.PAGE_TOKEN, s, (Map)map);
        this.serviceOptions = serviceOptions;
    }
    
    public Page<Bucket> getNextPage() {
        return (Page<Bucket>)StorageImpl.access$100(this.serviceOptions, this.requestOptions);
    }
}
