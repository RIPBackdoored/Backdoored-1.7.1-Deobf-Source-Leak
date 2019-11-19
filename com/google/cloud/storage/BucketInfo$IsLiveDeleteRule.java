package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;

@Deprecated
public static class IsLiveDeleteRule extends DeleteRule
{
    private static final long serialVersionUID = -3502994563121313364L;
    private final boolean isLive;
    
    public IsLiveDeleteRule(final boolean isLive) {
        super(Type.IS_LIVE);
        this.isLive = isLive;
    }
    
    public boolean isLive() {
        return this.isLive;
    }
    
    @Override
    void populateCondition(final Bucket.Lifecycle.Rule.Condition condition) {
        condition.setIsLive(Boolean.valueOf(this.isLive));
    }
}
