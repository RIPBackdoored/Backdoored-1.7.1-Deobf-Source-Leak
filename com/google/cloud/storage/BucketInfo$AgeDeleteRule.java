package com.google.cloud.storage;

import com.google.api.services.storage.model.Bucket;

@Deprecated
public static class AgeDeleteRule extends DeleteRule
{
    private static final long serialVersionUID = 5697166940712116380L;
    private final int daysToLive;
    
    public AgeDeleteRule(final int daysToLive) {
        super(Type.AGE);
        this.daysToLive = daysToLive;
    }
    
    public int getDaysToLive() {
        return this.daysToLive;
    }
    
    @Override
    void populateCondition(final Bucket.Lifecycle.Rule.Condition condition) {
        condition.setAge(Integer.valueOf(this.daysToLive));
    }
}
