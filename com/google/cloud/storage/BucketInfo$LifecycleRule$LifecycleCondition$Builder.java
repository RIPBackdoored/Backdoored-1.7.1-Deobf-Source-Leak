package com.google.cloud.storage;

import java.util.List;
import com.google.api.client.util.DateTime;

public static class Builder
{
    private Integer age;
    private DateTime createdBefore;
    private Integer numberOfNewerVersions;
    private Boolean isLive;
    private List<StorageClass> matchesStorageClass;
    
    private Builder() {
        super();
    }
    
    public Builder setAge(final Integer age) {
        this.age = age;
        return this;
    }
    
    public Builder setCreatedBefore(final DateTime createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }
    
    public Builder setNumberOfNewerVersions(final Integer numberOfNewerVersions) {
        this.numberOfNewerVersions = numberOfNewerVersions;
        return this;
    }
    
    public Builder setIsLive(final Boolean isLive) {
        this.isLive = isLive;
        return this;
    }
    
    public Builder setMatchesStorageClass(final List<StorageClass> matchesStorageClass) {
        this.matchesStorageClass = matchesStorageClass;
        return this;
    }
    
    public LifecycleCondition build() {
        return new LifecycleCondition(this);
    }
    
    static /* synthetic */ Integer access$300(final Builder builder) {
        return builder.age;
    }
    
    static /* synthetic */ DateTime access$400(final Builder builder) {
        return builder.createdBefore;
    }
    
    static /* synthetic */ Integer access$500(final Builder builder) {
        return builder.numberOfNewerVersions;
    }
    
    static /* synthetic */ Boolean access$600(final Builder builder) {
        return builder.isLive;
    }
    
    static /* synthetic */ List access$700(final Builder builder) {
        return builder.matchesStorageClass;
    }
    
    Builder(final BucketInfo$1 function) {
        this();
    }
}
