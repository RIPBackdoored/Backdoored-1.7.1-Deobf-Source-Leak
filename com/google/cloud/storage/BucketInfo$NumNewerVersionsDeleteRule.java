package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;

@Deprecated
public static class NumNewerVersionsDeleteRule extends DeleteRule
{
    private static final long serialVersionUID = -1955554976528303894L;
    private final int numNewerVersions;
    
    public NumNewerVersionsDeleteRule(final int numNewerVersions) {
        super(Type.NUM_NEWER_VERSIONS);
        this.numNewerVersions = numNewerVersions;
    }
    
    public int getNumNewerVersions() {
        return this.numNewerVersions;
    }
    
    @Override
    void populateCondition(final Bucket.Lifecycle.Rule.Condition condition) {
        condition.setNumNewerVersions(Integer.valueOf(this.numNewerVersions));
    }
}
