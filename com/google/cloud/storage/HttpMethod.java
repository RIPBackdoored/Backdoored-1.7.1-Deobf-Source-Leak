package com.google.cloud.storage;

import com.google.cloud.StringEnumType;
import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumValue;

public final class HttpMethod extends StringEnumValue
{
    private static final long serialVersionUID = -1394461645628254471L;
    private static final ApiFunction<String, HttpMethod> CONSTRUCTOR;
    private static final StringEnumType<HttpMethod> type;
    public static final HttpMethod GET;
    public static final HttpMethod HEAD;
    public static final HttpMethod PUT;
    public static final HttpMethod POST;
    public static final HttpMethod DELETE;
    public static final HttpMethod OPTIONS;
    
    private HttpMethod(final String s) {
        super(s);
    }
    
    public static HttpMethod valueOfStrict(final String s) {
        return (HttpMethod)HttpMethod.type.valueOfStrict(s);
    }
    
    public static HttpMethod valueOf(final String s) {
        return (HttpMethod)HttpMethod.type.valueOf(s);
    }
    
    public static HttpMethod[] values() {
        return (HttpMethod[])HttpMethod.type.values();
    }
    
    HttpMethod(final String s, final HttpMethod$1 apiFunction) {
        this(s);
    }
    
    static {
        CONSTRUCTOR = (ApiFunction)new ApiFunction<String, HttpMethod>() {
            HttpMethod$1() {
                super();
            }
            
            public HttpMethod apply(final String s) {
                return new HttpMethod(s, null);
            }
            
            public /* bridge */ Object apply(final Object o) {
                return this.apply((String)o);
            }
        };
        type = new StringEnumType((Class)HttpMethod.class, (ApiFunction)HttpMethod.CONSTRUCTOR);
        GET = (HttpMethod)HttpMethod.type.createAndRegister("GET");
        HEAD = (HttpMethod)HttpMethod.type.createAndRegister("HEAD");
        PUT = (HttpMethod)HttpMethod.type.createAndRegister("PUT");
        POST = (HttpMethod)HttpMethod.type.createAndRegister("POST");
        DELETE = (HttpMethod)HttpMethod.type.createAndRegister("DELETE");
        OPTIONS = (HttpMethod)HttpMethod.type.createAndRegister("OPTIONS");
    }
}
