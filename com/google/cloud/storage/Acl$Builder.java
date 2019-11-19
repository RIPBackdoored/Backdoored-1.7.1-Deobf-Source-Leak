package com.google.cloud.storage;

public static class Builder
{
    private Entity entity;
    private Role role;
    private String id;
    private String etag;
    
    private Builder(final Entity entity, final Role role) {
        super();
        this.entity = entity;
        this.role = role;
    }
    
    private Builder(final Acl acl) {
        super();
        this.entity = Acl.access$100(acl);
        this.role = Acl.access$200(acl);
        this.id = Acl.access$300(acl);
        this.etag = Acl.access$400(acl);
    }
    
    public Builder setEntity(final Entity entity) {
        this.entity = entity;
        return this;
    }
    
    public Builder setRole(final Role role) {
        this.role = role;
        return this;
    }
    
    Builder setId(final String id) {
        this.id = id;
        return this;
    }
    
    Builder setEtag(final String etag) {
        this.etag = etag;
        return this;
    }
    
    public Acl build() {
        return new Acl(this, null);
    }
    
    static /* synthetic */ Entity access$700(final Builder builder) {
        return builder.entity;
    }
    
    static /* synthetic */ Role access$800(final Builder builder) {
        return builder.role;
    }
    
    static /* synthetic */ String access$900(final Builder builder) {
        return builder.id;
    }
    
    static /* synthetic */ String access$1000(final Builder builder) {
        return builder.etag;
    }
    
    Builder(final Acl acl, final Acl$1 function) {
        this(acl);
    }
    
    Builder(final Entity entity, final Role role, final Acl$1 function) {
        this(entity, role);
    }
}
