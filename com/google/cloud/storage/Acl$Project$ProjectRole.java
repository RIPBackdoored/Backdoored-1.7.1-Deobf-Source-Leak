package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;

public static final class ProjectRole extends StringEnumValue
{
    private static final long serialVersionUID = -8360324311187914382L;
    private static final ApiFunction<String, ProjectRole> CONSTRUCTOR;
    private static final StringEnumType<ProjectRole> type;
    public static final ProjectRole OWNERS;
    public static final ProjectRole EDITORS;
    public static final ProjectRole VIEWERS;
    
    private ProjectRole(final String s) {
        super(s);
    }
    
    public static ProjectRole valueOfStrict(final String s) {
        return (ProjectRole)ProjectRole.type.valueOfStrict(s);
    }
    
    public static ProjectRole valueOf(final String s) {
        return (ProjectRole)ProjectRole.type.valueOf(s);
    }
    
    public static ProjectRole[] values() {
        return (ProjectRole[])ProjectRole.type.values();
    }
    
    ProjectRole(final String s, final Acl$1 function) {
        this(s);
    }
    
    static {
        CONSTRUCTOR = (ApiFunction)new ApiFunction<String, ProjectRole>() {
            Acl$Project$ProjectRole$1() {
                super();
            }
            
            public ProjectRole apply(final String s) {
                return new ProjectRole(s);
            }
            
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        };
        type = new StringEnumType((Class)ProjectRole.class, (ApiFunction)ProjectRole.CONSTRUCTOR);
        OWNERS = (ProjectRole)ProjectRole.type.createAndRegister("OWNERS");
        EDITORS = (ProjectRole)ProjectRole.type.createAndRegister("EDITORS");
        VIEWERS = (ProjectRole)ProjectRole.type.createAndRegister("VIEWERS");
    }
}
