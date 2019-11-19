package com.google.cloud.storage;

import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.Bucket;
import java.util.Objects;
import java.io.Serializable;

@Deprecated
public abstract static class DeleteRule implements Serializable
{
    private static final long serialVersionUID = 3137971668395933033L;
    private static final String SUPPORTED_ACTION = "Delete";
    private final Type type;
    
    DeleteRule(final Type type) {
        super();
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.type);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && Objects.equals(this.toPb(), ((DeleteRule)o).toPb()));
    }
    
    Bucket.Lifecycle.Rule toPb() {
        final Bucket.Lifecycle.Rule rule = new Bucket.Lifecycle.Rule();
        rule.setAction(new Bucket.Lifecycle.Rule.Action().setType("Delete"));
        final Bucket.Lifecycle.Rule.Condition condition = new Bucket.Lifecycle.Rule.Condition();
        this.populateCondition(condition);
        rule.setCondition(condition);
        return rule;
    }
    
    abstract void populateCondition(final Bucket.Lifecycle.Rule.Condition p0);
    
    static DeleteRule fromPb(final Bucket.Lifecycle.Rule rule) {
        if (rule.getAction() != null && "Delete".endsWith(rule.getAction().getType())) {
            final Bucket.Lifecycle.Rule.Condition condition = rule.getCondition();
            final Integer age = condition.getAge();
            if (age != null) {
                return new AgeDeleteRule(age);
            }
            final DateTime createdBefore = condition.getCreatedBefore();
            if (createdBefore != null) {
                return new CreatedBeforeDeleteRule(createdBefore.getValue());
            }
            final Integer numNewerVersions = condition.getNumNewerVersions();
            if (numNewerVersions != null) {
                return new NumNewerVersionsDeleteRule(numNewerVersions);
            }
            final Boolean isLive = condition.getIsLive();
            if (isLive != null) {
                return new IsLiveDeleteRule(isLive);
            }
        }
        return new RawDeleteRule(rule);
    }
}
