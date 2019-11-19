package com.google.cloud.storage;

import java.util.List;
import com.google.api.client.util.DateTime;
import java.io.Serializable;

public static class LifecycleCondition implements Serializable
{
    private static final long serialVersionUID = -6482314338394768785L;
    private final Integer age;
    private final DateTime createdBefore;
    private final Integer numberOfNewerVersions;
    private final Boolean isLive;
    private final List<StorageClass> matchesStorageClass;
    
    private LifecycleCondition(final Builder builder) {
        super();
        this.age = builder.age;
        this.createdBefore = builder.createdBefore;
        this.numberOfNewerVersions = builder.numberOfNewerVersions;
        this.isLive = builder.isLive;
        this.matchesStorageClass = builder.matchesStorageClass;
    }
    
    public Builder toBuilder() {
        return newBuilder().setAge(this.age).setCreatedBefore(this.createdBefore).setNumberOfNewerVersions(this.numberOfNewerVersions).setIsLive(this.isLive).setMatchesStorageClass(this.matchesStorageClass);
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public Integer getAge() {
        return this.age;
    }
    
    public DateTime getCreatedBefore() {
        return this.createdBefore;
    }
    
    public Integer getNumberOfNewerVersions() {
        return this.numberOfNewerVersions;
    }
    
    public Boolean getIsLive() {
        return this.isLive;
    }
    
    public List<StorageClass> getMatchesStorageClass() {
        return this.matchesStorageClass;
    }
    
    LifecycleCondition(final Builder builder, final BucketInfo$1 function) {
        this(builder);
    }
}
