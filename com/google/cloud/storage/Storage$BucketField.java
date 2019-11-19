package com.google.cloud.storage;

import com.google.common.collect.ImmutableList;
import java.util.List;
import com.google.cloud.FieldSelector;

public enum BucketField implements FieldSelector
{
    ID("id"), 
    SELF_LINK("selfLink"), 
    NAME("name"), 
    TIME_CREATED("timeCreated"), 
    METAGENERATION("metageneration"), 
    ACL("acl"), 
    DEFAULT_OBJECT_ACL("defaultObjectAcl"), 
    OWNER("owner"), 
    LABELS("labels"), 
    LOCATION("location"), 
    LOCATION_TYPE("locationType"), 
    WEBSITE("website"), 
    VERSIONING("versioning"), 
    CORS("cors"), 
    LIFECYCLE("lifecycle"), 
    STORAGE_CLASS("storageClass"), 
    ETAG("etag"), 
    ENCRYPTION("encryption"), 
    BILLING("billing"), 
    DEFAULT_EVENT_BASED_HOLD("defaultEventBasedHold"), 
    RETENTION_POLICY("retentionPolicy"), 
    IAMCONFIGURATION("iamConfiguration");
    
    static final List<? extends FieldSelector> REQUIRED_FIELDS;
    private final String selector;
    private static final /* synthetic */ BucketField[] $VALUES;
    
    public static BucketField[] values() {
        return BucketField.$VALUES.clone();
    }
    
    public static BucketField valueOf(final String s) {
        return Enum.<BucketField>valueOf(BucketField.class, s);
    }
    
    private BucketField(final String selector) {
        this.selector = selector;
    }
    
    public String getSelector() {
        return this.selector;
    }
    
    static {
        $VALUES = new BucketField[] { BucketField.ID, BucketField.SELF_LINK, BucketField.NAME, BucketField.TIME_CREATED, BucketField.METAGENERATION, BucketField.ACL, BucketField.DEFAULT_OBJECT_ACL, BucketField.OWNER, BucketField.LABELS, BucketField.LOCATION, BucketField.LOCATION_TYPE, BucketField.WEBSITE, BucketField.VERSIONING, BucketField.CORS, BucketField.LIFECYCLE, BucketField.STORAGE_CLASS, BucketField.ETAG, BucketField.ENCRYPTION, BucketField.BILLING, BucketField.DEFAULT_EVENT_BASED_HOLD, BucketField.RETENTION_POLICY, BucketField.IAMCONFIGURATION };
        REQUIRED_FIELDS = ImmutableList.<BucketField>of(BucketField.NAME);
    }
}
