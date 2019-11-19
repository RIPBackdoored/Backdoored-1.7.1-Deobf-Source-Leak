package com.google.cloud.storage.spi.v1;

import java.util.Objects;
import com.google.api.services.storage.model.StorageObject;

public static class RewriteResponse
{
    public final RewriteRequest rewriteRequest;
    public final StorageObject result;
    public final long blobSize;
    public final boolean isDone;
    public final String rewriteToken;
    public final long totalBytesRewritten;
    
    public RewriteResponse(final RewriteRequest rewriteRequest, final StorageObject result, final long blobSize, final boolean isDone, final String rewriteToken, final long totalBytesRewritten) {
        super();
        this.rewriteRequest = rewriteRequest;
        this.result = result;
        this.blobSize = blobSize;
        this.isDone = isDone;
        this.rewriteToken = rewriteToken;
        this.totalBytesRewritten = totalBytesRewritten;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof RewriteResponse)) {
            return false;
        }
        final RewriteResponse rewriteResponse = (RewriteResponse)o;
        return Objects.equals(this.rewriteRequest, rewriteResponse.rewriteRequest) && Objects.equals(this.result, rewriteResponse.result) && Objects.equals(this.rewriteToken, rewriteResponse.rewriteToken) && this.blobSize == rewriteResponse.blobSize && Objects.equals(this.isDone, rewriteResponse.isDone) && this.totalBytesRewritten == rewriteResponse.totalBytesRewritten;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.rewriteRequest, this.result, this.blobSize, this.isDone, this.rewriteToken, this.totalBytesRewritten);
    }
}
