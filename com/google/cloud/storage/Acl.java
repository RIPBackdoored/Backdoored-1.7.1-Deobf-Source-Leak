package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;
import java.util.Objects;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.api.services.storage.model.BucketAccessControl;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.common.base.Function;
import java.io.Serializable;

public final class Acl implements Serializable
{
    private static final long serialVersionUID = 7516713233557576082L;
    static final Function<ObjectAccessControl, Acl> FROM_OBJECT_PB_FUNCTION;
    static final Function<BucketAccessControl, Acl> FROM_BUCKET_PB_FUNCTION;
    private final Entity entity;
    private final Role role;
    private final String id;
    private final String etag;
    
    private Acl(final Builder builder) {
        super();
        this.entity = Preconditions.<Entity>checkNotNull(builder.entity);
        this.role = Preconditions.<Role>checkNotNull(builder.role);
        this.id = builder.id;
        this.etag = builder.etag;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public Role getRole() {
        return this.role;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public Builder toBuilder() {
        return new Builder(this);
    }
    
    public static Acl of(final Entity entity, final Role role) {
        return newBuilder(entity, role).build();
    }
    
    public static Builder newBuilder(final Entity entity, final Role role) {
        return new Builder(entity, role);
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("entity", this.entity).add("role", this.role).add("etag", this.etag).add("id", this.id).toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.entity, this.role);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Acl acl = (Acl)o;
        return Objects.equals(this.entity, acl.entity) && Objects.equals(this.role, acl.role) && Objects.equals(this.etag, acl.etag) && Objects.equals(this.id, acl.id);
    }
    
    BucketAccessControl toBucketPb() {
        final BucketAccessControl bucketAccessControl = new BucketAccessControl();
        bucketAccessControl.setEntity(this.getEntity().toString());
        bucketAccessControl.setRole(this.getRole().toString());
        bucketAccessControl.setId(this.getId());
        bucketAccessControl.setEtag(this.getEtag());
        return bucketAccessControl;
    }
    
    ObjectAccessControl toObjectPb() {
        final ObjectAccessControl objectAccessControl = new ObjectAccessControl();
        objectAccessControl.setEntity(this.getEntity().toPb());
        objectAccessControl.setRole(this.getRole().name());
        objectAccessControl.setId(this.getId());
        objectAccessControl.setEtag(this.getEtag());
        return objectAccessControl;
    }
    
    static Acl fromPb(final ObjectAccessControl objectAccessControl) {
        return newBuilder(Entity.fromPb(objectAccessControl.getEntity()), Role.valueOf(objectAccessControl.getRole())).setEtag(objectAccessControl.getEtag()).setId(objectAccessControl.getId()).build();
    }
    
    static Acl fromPb(final BucketAccessControl bucketAccessControl) {
        return newBuilder(Entity.fromPb(bucketAccessControl.getEntity()), Role.valueOf(bucketAccessControl.getRole())).setEtag(bucketAccessControl.getEtag()).setId(bucketAccessControl.getId()).build();
    }
    
    static /* synthetic */ Entity access$100(final Acl acl) {
        return acl.entity;
    }
    
    static /* synthetic */ Role access$200(final Acl acl) {
        return acl.role;
    }
    
    static /* synthetic */ String access$300(final Acl acl) {
        return acl.id;
    }
    
    static /* synthetic */ String access$400(final Acl acl) {
        return acl.etag;
    }
    
    Acl(final Builder builder, final Acl$1 function) {
        this(builder);
    }
    
    static {
        FROM_OBJECT_PB_FUNCTION = new Function<ObjectAccessControl, Acl>() {
            Acl$1() {
                super();
            }
            
            @Override
            public Acl apply(final ObjectAccessControl objectAccessControl) {
                return Acl.fromPb(objectAccessControl);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((ObjectAccessControl)o);
            }
        };
        FROM_BUCKET_PB_FUNCTION = new Function<BucketAccessControl, Acl>() {
            Acl$2() {
                super();
            }
            
            @Override
            public Acl apply(final BucketAccessControl bucketAccessControl) {
                return Acl.fromPb(bucketAccessControl);
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((BucketAccessControl)o);
            }
        };
    }
}
