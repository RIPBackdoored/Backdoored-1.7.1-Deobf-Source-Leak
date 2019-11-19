package com.google.cloud.storage;

import java.util.Objects;
import java.io.Serializable;

public abstract static class Entity implements Serializable
{
    private static final long serialVersionUID = -2707407252771255840L;
    private final Type type;
    private final String value;
    
    Entity(final Type type, final String value) {
        super();
        this.type = type;
        this.value = value;
    }
    
    public Type getType() {
        return this.type;
    }
    
    protected String getValue() {
        return this.value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Entity entity = (Entity)o;
        return Objects.equals(this.type, entity.type) && Objects.equals(this.value, entity.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.value);
    }
    
    @Override
    public String toString() {
        return this.toPb();
    }
    
    String toPb() {
        return this.type.name().toLowerCase() + "-" + this.getValue();
    }
    
    static Entity fromPb(final String s) {
        if (s.startsWith("user-")) {
            return new User(s.substring(5));
        }
        if (s.equals("allUsers")) {
            return User.ofAllUsers();
        }
        if (s.equals("allAuthenticatedUsers")) {
            return User.ofAllAuthenticatedUsers();
        }
        if (s.startsWith("group-")) {
            return new Group(s.substring(6));
        }
        if (s.startsWith("domain-")) {
            return new Domain(s.substring(7));
        }
        if (s.startsWith("project-")) {
            final int index = s.indexOf(45, 8);
            return new Project(Project.ProjectRole.valueOf(s.substring(8, index).toUpperCase()), s.substring(index + 1));
        }
        return new RawEntity(s);
    }
}
