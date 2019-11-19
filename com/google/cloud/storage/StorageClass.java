package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;

public final class StorageClass extends StringEnumValue
{
    private static final long serialVersionUID = -6938125060419556331L;
    private static final ApiFunction<String, StorageClass> CONSTRUCTOR;
    private static final StringEnumType<StorageClass> type;
    public static final StorageClass REGIONAL;
    public static final StorageClass MULTI_REGIONAL;
    public static final StorageClass NEARLINE;
    public static final StorageClass COLDLINE;
    public static final StorageClass STANDARD;
    public static final StorageClass DURABLE_REDUCED_AVAILABILITY;
    
    private StorageClass(final String s) {
        super(s);
    }
    
    public static StorageClass valueOfStrict(final String s) {
        return (StorageClass)StorageClass.type.valueOfStrict(s);
    }
    
    public static StorageClass valueOf(final String s) {
        return (StorageClass)StorageClass.type.valueOf(s);
    }
    
    public static StorageClass[] values() {
        return (StorageClass[])StorageClass.type.values();
    }
    
    StorageClass(final String s, final StorageClass$1 apiFunction) {
        this(s);
    }
    
    static {
        CONSTRUCTOR = (ApiFunction)new ApiFunction<String, StorageClass>() {
            StorageClass$1() {
                super();
            }
            
            public StorageClass apply(final String s) {
                return new StorageClass(s, null);
            }
            
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        };
        type = new StringEnumType((Class)StorageClass.class, (ApiFunction)StorageClass.CONSTRUCTOR);
        REGIONAL = (StorageClass)StorageClass.type.createAndRegister("REGIONAL");
        MULTI_REGIONAL = (StorageClass)StorageClass.type.createAndRegister("MULTI_REGIONAL");
        NEARLINE = (StorageClass)StorageClass.type.createAndRegister("NEARLINE");
        COLDLINE = (StorageClass)StorageClass.type.createAndRegister("COLDLINE");
        STANDARD = (StorageClass)StorageClass.type.createAndRegister("STANDARD");
        DURABLE_REDUCED_AVAILABILITY = (StorageClass)StorageClass.type.createAndRegister("DURABLE_REDUCED_AVAILABILITY");
    }
}
