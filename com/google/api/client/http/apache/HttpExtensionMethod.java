package com.google.api.client.http.apache;

import java.net.URI;
import com.google.api.client.util.Preconditions;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

final class HttpExtensionMethod extends HttpEntityEnclosingRequestBase
{
    private final String methodName;
    
    public HttpExtensionMethod(final String s, final String s2) {
        super();
        this.methodName = Preconditions.<String>checkNotNull(s);
        this.setURI(URI.create(s2));
    }
    
    public String getMethod() {
        return this.methodName;
    }
}
