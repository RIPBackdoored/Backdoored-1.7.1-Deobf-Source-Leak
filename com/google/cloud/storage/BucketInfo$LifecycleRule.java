package com.google.cloud.storage;

import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.base.Functions;
import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.Bucket;
import java.util.Objects;
import java.io.Serializable;

public static class LifecycleRule implements Serializable
{
    private static final long serialVersionUID = -5739807320148748613L;
    private final LifecycleAction lifecycleAction;
    private final LifecycleCondition lifecycleCondition;
    
    public LifecycleRule(final LifecycleAction lifecycleAction, final LifecycleCondition lifecycleCondition) {
        super();
        if (lifecycleCondition.getIsLive() == null && lifecycleCondition.getAge() == null && lifecycleCondition.getCreatedBefore() == null && lifecycleCondition.getMatchesStorageClass() == null && lifecycleCondition.getNumberOfNewerVersions() == null) {
            throw new IllegalArgumentException("You must specify at least one condition to use object lifecycle management. Please see https://cloud.google.com/storage/docs/lifecycle for details.");
        }
        this.lifecycleAction = lifecycleAction;
        this.lifecycleCondition = lifecycleCondition;
    }
    
    public LifecycleAction getAction() {
        return this.lifecycleAction;
    }
    
    public LifecycleCondition getCondition() {
        return this.lifecycleCondition;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.lifecycleAction, this.lifecycleCondition);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && Objects.equals(this.toPb(), ((LifecycleRule)o).toPb()));
    }
    
    Bucket.Lifecycle.Rule toPb() {
        final Bucket.Lifecycle.Rule rule = new Bucket.Lifecycle.Rule();
        final Bucket.Lifecycle.Rule.Action setType = new Bucket.Lifecycle.Rule.Action().setType(this.lifecycleAction.getActionType());
        if (this.lifecycleAction.getActionType().equals("SetStorageClass")) {
            setType.setStorageClass(((SetStorageClassLifecycleAction)this.lifecycleAction).getStorageClass().toString());
        }
        rule.setAction(setType);
        rule.setCondition(new Bucket.Lifecycle.Rule.Condition().setAge(this.lifecycleCondition.getAge()).setCreatedBefore((this.lifecycleCondition.getCreatedBefore() == null) ? null : new DateTime(true, this.lifecycleCondition.getCreatedBefore().getValue(), 0)).setIsLive(this.lifecycleCondition.getIsLive()).setNumNewerVersions(this.lifecycleCondition.getNumberOfNewerVersions()).setMatchesStorageClass((List)((this.lifecycleCondition.getMatchesStorageClass() == null) ? null : Lists.<StorageClass, Object>transform(this.lifecycleCondition.getMatchesStorageClass(), (Function<? super StorageClass, ?>)Functions.toStringFunction()))));
        return rule;
    }
    
    static LifecycleRule fromPb(final Bucket.Lifecycle.Rule rule) {
        final Bucket.Lifecycle.Rule.Action action = rule.getAction();
        final String type = action.getType();
        LifecycleAction lifecycleAction = null;
        switch (type) {
            case "Delete":
                lifecycleAction = LifecycleAction.newDeleteAction();
                break;
            case "SetStorageClass":
                lifecycleAction = LifecycleAction.newSetStorageClassAction(StorageClass.valueOf(action.getStorageClass()));
                break;
            default:
                throw new UnsupportedOperationException("The specified lifecycle action " + action.getType() + " is not currently supported");
        }
        final Bucket.Lifecycle.Rule.Condition condition = rule.getCondition();
        return new LifecycleRule(lifecycleAction, LifecycleCondition.newBuilder().setAge(condition.getAge()).setCreatedBefore(condition.getCreatedBefore()).setIsLive(condition.getIsLive()).setNumberOfNewerVersions(condition.getNumNewerVersions()).setMatchesStorageClass((List<StorageClass>)((condition.getMatchesStorageClass() == null) ? null : Lists.<Object, Object>transform((List<Object>)condition.getMatchesStorageClass(), (Function<? super Object, ?>)new Function<String, StorageClass>() {
            BucketInfo$LifecycleRule$1() {
                super();
            }
            
            @Override
            public StorageClass apply(final String s) {
                return StorageClass.valueOf(s);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        }))).build());
    }
}
