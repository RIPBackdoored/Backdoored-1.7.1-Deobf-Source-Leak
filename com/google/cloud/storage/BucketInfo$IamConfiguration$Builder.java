package com.google.cloud.storage;

public static class Builder
{
    private Boolean isBucketPolicyOnlyEnabled;
    private Long bucketPolicyOnlyLockedTime;
    
    public Builder() {
        super();
    }
    
    public Builder setIsBucketPolicyOnlyEnabled(final Boolean isBucketPolicyOnlyEnabled) {
        this.isBucketPolicyOnlyEnabled = isBucketPolicyOnlyEnabled;
        return this;
    }
    
    Builder setBucketPolicyOnlyLockedTime(final Long bucketPolicyOnlyLockedTime) {
        this.bucketPolicyOnlyLockedTime = bucketPolicyOnlyLockedTime;
        return this;
    }
    
    public IamConfiguration build() {
        return new IamConfiguration(this);
    }
    
    static /* synthetic */ Boolean access$000(final Builder builder) {
        return builder.isBucketPolicyOnlyEnabled;
    }
    
    static /* synthetic */ Long access$100(final Builder builder) {
        return builder.bucketPolicyOnlyLockedTime;
    }
    
    static /* synthetic */ Boolean access$002(final Builder builder, final Boolean isBucketPolicyOnlyEnabled) {
        return builder.isBucketPolicyOnlyEnabled = isBucketPolicyOnlyEnabled;
    }
    
    static /* synthetic */ Long access$102(final Builder builder, final Long bucketPolicyOnlyLockedTime) {
        return builder.bucketPolicyOnlyLockedTime = bucketPolicyOnlyLockedTime;
    }
}
