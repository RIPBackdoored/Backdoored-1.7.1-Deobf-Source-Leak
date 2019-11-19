package com.google.cloud.storage;

import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.Bucket;

@Deprecated
public static class CreatedBeforeDeleteRule extends DeleteRule
{
    private static final long serialVersionUID = 881692650279195867L;
    private final long timeMillis;
    
    public CreatedBeforeDeleteRule(final long timeMillis) {
        super(Type.CREATE_BEFORE);
        this.timeMillis = timeMillis;
    }
    
    public long getTimeMillis() {
        return this.timeMillis;
    }
    
    @Override
    void populateCondition(final Bucket.Lifecycle.Rule.Condition condition) {
        condition.setCreatedBefore(new DateTime(true, this.timeMillis, 0));
    }
}
