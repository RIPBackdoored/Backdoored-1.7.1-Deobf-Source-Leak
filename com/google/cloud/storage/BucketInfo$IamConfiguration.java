package com.google.cloud.storage;

import com.google.api.client.util.DateTime;
import com.google.api.services.storage.model.Bucket;
import java.util.Objects;
import java.io.Serializable;

public static class IamConfiguration implements Serializable
{
    private static final long serialVersionUID = -8671736104909424616L;
    private Boolean isBucketPolicyOnlyEnabled;
    private Long bucketPolicyOnlyLockedTime;
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && Objects.equals(this.toPb(), ((IamConfiguration)o).toPb()));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.isBucketPolicyOnlyEnabled, this.bucketPolicyOnlyLockedTime);
    }
    
    private IamConfiguration(final Builder builder) {
        super();
        this.isBucketPolicyOnlyEnabled = builder.isBucketPolicyOnlyEnabled;
        this.bucketPolicyOnlyLockedTime = builder.bucketPolicyOnlyLockedTime;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public Builder toBuilder() {
        final Builder builder = new Builder();
        builder.isBucketPolicyOnlyEnabled = this.isBucketPolicyOnlyEnabled;
        builder.bucketPolicyOnlyLockedTime = this.bucketPolicyOnlyLockedTime;
        return builder;
    }
    
    public Boolean isBucketPolicyOnlyEnabled() {
        return this.isBucketPolicyOnlyEnabled;
    }
    
    public Long getBucketPolicyOnlyLockedTime() {
        return this.bucketPolicyOnlyLockedTime;
    }
    
    Bucket.IamConfiguration toPb() {
        final Bucket.IamConfiguration iamConfiguration = new Bucket.IamConfiguration();
        final Bucket.IamConfiguration.BucketPolicyOnly bucketPolicyOnly = new Bucket.IamConfiguration.BucketPolicyOnly();
        bucketPolicyOnly.setEnabled(this.isBucketPolicyOnlyEnabled);
        bucketPolicyOnly.setLockedTime((this.bucketPolicyOnlyLockedTime == null) ? null : new DateTime(this.bucketPolicyOnlyLockedTime));
        iamConfiguration.setBucketPolicyOnly(bucketPolicyOnly);
        return iamConfiguration;
    }
    
    static IamConfiguration fromPb(final Bucket.IamConfiguration iamConfiguration) {
        final Bucket.IamConfiguration.BucketPolicyOnly bucketPolicyOnly = iamConfiguration.getBucketPolicyOnly();
        final DateTime lockedTime = bucketPolicyOnly.getLockedTime();
        return newBuilder().setIsBucketPolicyOnlyEnabled(bucketPolicyOnly.getEnabled()).setBucketPolicyOnlyLockedTime((lockedTime == null) ? null : Long.valueOf(lockedTime.getValue())).build();
    }
    
    IamConfiguration(final Builder builder, final BucketInfo$1 function) {
        this(builder);
    }
}
