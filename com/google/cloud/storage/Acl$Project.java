package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;

public static final class Project extends Entity
{
    private static final long serialVersionUID = 7933776866530023027L;
    private final ProjectRole projectRole;
    private final String projectId;
    
    public Project(final ProjectRole projectRole, final String projectId) {
        super(Type.PROJECT, projectRole.name().toLowerCase() + "-" + projectId);
        this.projectRole = projectRole;
        this.projectId = projectId;
    }
    
    public ProjectRole getProjectRole() {
        return this.projectRole;
    }
    
    public String getProjectId() {
        return this.projectId;
    }
}
