package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;

public static final class Role extends StringEnumValue
{
    private static final long serialVersionUID = 123037132067643600L;
    private static final ApiFunction<String, Role> CONSTRUCTOR;
    private static final StringEnumType<Role> type;
    public static final Role OWNER;
    public static final Role READER;
    public static final Role WRITER;
    
    private Role(final String s) {
        super(s);
    }
    
    public static Role valueOfStrict(final String s) {
        return (Role)Role.type.valueOfStrict(s);
    }
    
    public static Role valueOf(final String s) {
        return (Role)Role.type.valueOf(s);
    }
    
    public static Role[] values() {
        return (Role[])Role.type.values();
    }
    
    Role(final String s, final Acl$1 function) {
        this(s);
    }
    
    static {
        CONSTRUCTOR = (ApiFunction)new ApiFunction<String, Role>() {
            Acl$Role$1() {
                super();
            }
            
            public Role apply(final String s) {
                return new Role(s);
            }
            
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        };
        type = new StringEnumType((Class)Role.class, (ApiFunction)Role.CONSTRUCTOR);
        OWNER = (Role)Role.type.createAndRegister("OWNER");
        READER = (Role)Role.type.createAndRegister("READER");
        WRITER = (Role)Role.type.createAndRegister("WRITER");
    }
}
