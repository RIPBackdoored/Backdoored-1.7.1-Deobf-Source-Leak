package com.google.cloud.storage.spi.v1;

import java.util.Map;

public enum Option
{
    PREDEFINED_ACL("predefinedAcl"), 
    PREDEFINED_DEFAULT_OBJECT_ACL("predefinedDefaultObjectAcl"), 
    IF_METAGENERATION_MATCH("ifMetagenerationMatch"), 
    IF_METAGENERATION_NOT_MATCH("ifMetagenerationNotMatch"), 
    IF_GENERATION_MATCH("ifGenerationMatch"), 
    IF_GENERATION_NOT_MATCH("ifGenerationNotMatch"), 
    IF_SOURCE_METAGENERATION_MATCH("ifSourceMetagenerationMatch"), 
    IF_SOURCE_METAGENERATION_NOT_MATCH("ifSourceMetagenerationNotMatch"), 
    IF_SOURCE_GENERATION_MATCH("ifSourceGenerationMatch"), 
    IF_SOURCE_GENERATION_NOT_MATCH("ifSourceGenerationNotMatch"), 
    IF_DISABLE_GZIP_CONTENT("disableGzipContent"), 
    PREFIX("prefix"), 
    PROJECT_ID("projectId"), 
    PROJECTION("projection"), 
    MAX_RESULTS("maxResults"), 
    PAGE_TOKEN("pageToken"), 
    DELIMITER("delimiter"), 
    VERSIONS("versions"), 
    FIELDS("fields"), 
    CUSTOMER_SUPPLIED_KEY("customerSuppliedKey"), 
    USER_PROJECT("userProject"), 
    KMS_KEY_NAME("kmsKeyName"), 
    SERVICE_ACCOUNT_EMAIL("serviceAccount"), 
    SHOW_DELETED_KEYS("showDeletedKeys");
    
    private final String value;
    private static final /* synthetic */ Option[] $VALUES;
    
    public static Option[] values() {
        return Option.$VALUES.clone();
    }
    
    public static Option valueOf(final String s) {
        return Enum.<Option>valueOf(Option.class, s);
    }
    
    private Option(final String value) {
        this.value = value;
    }
    
    public String value() {
        return this.value;
    }
    
     <T> T get(final Map<Option, ?> map) {
        return (T)map.get(this);
    }
    
    String getString(final Map<Option, ?> map) {
        return this.<String>get(map);
    }
    
    Long getLong(final Map<Option, ?> map) {
        return this.<Long>get(map);
    }
    
    Boolean getBoolean(final Map<Option, ?> map) {
        return this.<Boolean>get(map);
    }
    
    static {
        $VALUES = new Option[] { Option.PREDEFINED_ACL, Option.PREDEFINED_DEFAULT_OBJECT_ACL, Option.IF_METAGENERATION_MATCH, Option.IF_METAGENERATION_NOT_MATCH, Option.IF_GENERATION_MATCH, Option.IF_GENERATION_NOT_MATCH, Option.IF_SOURCE_METAGENERATION_MATCH, Option.IF_SOURCE_METAGENERATION_NOT_MATCH, Option.IF_SOURCE_GENERATION_MATCH, Option.IF_SOURCE_GENERATION_NOT_MATCH, Option.IF_DISABLE_GZIP_CONTENT, Option.PREFIX, Option.PROJECT_ID, Option.PROJECTION, Option.MAX_RESULTS, Option.PAGE_TOKEN, Option.DELIMITER, Option.VERSIONS, Option.FIELDS, Option.CUSTOMER_SUPPLIED_KEY, Option.USER_PROJECT, Option.KMS_KEY_NAME, Option.SERVICE_ACCOUNT_EMAIL, Option.SHOW_DELETED_KEYS };
    }
}
