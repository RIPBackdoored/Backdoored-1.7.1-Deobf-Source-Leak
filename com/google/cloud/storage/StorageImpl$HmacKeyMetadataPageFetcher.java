package com.google.cloud.storage;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.spi.v1.StorageRpc;
import java.util.Map;
import com.google.cloud.PageImpl;

private static class HmacKeyMetadataPageFetcher implements PageImpl.NextPageFetcher<HmacKey.HmacKeyMetadata>
{
    private static final long serialVersionUID = 308012320541700881L;
    private final StorageOptions serviceOptions;
    private final Map<StorageRpc.Option, ?> options;
    
    HmacKeyMetadataPageFetcher(final StorageOptions serviceOptions, final Map<StorageRpc.Option, ?> options) {
        super();
        this.serviceOptions = serviceOptions;
        this.options = options;
    }
    
    public Page<HmacKey.HmacKeyMetadata> getNextPage() {
        return (Page<HmacKey.HmacKeyMetadata>)StorageImpl.access$300(this.serviceOptions, this.options);
    }
}
