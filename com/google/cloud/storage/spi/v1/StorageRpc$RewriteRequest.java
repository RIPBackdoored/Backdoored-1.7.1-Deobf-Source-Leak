package com.google.cloud.storage.spi.v1;

import java.util.Objects;
import java.util.Map;
import com.google.api.services.storage.model.StorageObject;

public static class RewriteRequest
{
    public final StorageObject source;
    public final Map<Option, ?> sourceOptions;
    public final boolean overrideInfo;
    public final StorageObject target;
    public final Map<Option, ?> targetOptions;
    public final Long megabytesRewrittenPerCall;
    
    public RewriteRequest(final StorageObject source, final Map<Option, ?> sourceOptions, final boolean overrideInfo, final StorageObject target, final Map<Option, ?> targetOptions, final Long megabytesRewrittenPerCall) {
        super();
        this.source = source;
        this.sourceOptions = sourceOptions;
        this.overrideInfo = overrideInfo;
        this.target = target;
        this.targetOptions = targetOptions;
        this.megabytesRewrittenPerCall = megabytesRewrittenPerCall;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof RewriteRequest)) {
            return false;
        }
        final RewriteRequest rewriteRequest = (RewriteRequest)o;
        return Objects.equals(this.source, rewriteRequest.source) && Objects.equals(this.sourceOptions, rewriteRequest.sourceOptions) && Objects.equals(this.overrideInfo, rewriteRequest.overrideInfo) && Objects.equals(this.target, rewriteRequest.target) && Objects.equals(this.targetOptions, rewriteRequest.targetOptions) && Objects.equals(this.megabytesRewrittenPerCall, rewriteRequest.megabytesRewrittenPerCall);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.sourceOptions, this.overrideInfo, this.target, this.targetOptions, this.megabytesRewrittenPerCall);
    }
}
