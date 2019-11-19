package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpResponseException;

@Beta
public class ClientLoginResponseException extends HttpResponseException
{
    private static final long serialVersionUID = 4974317674023010928L;
    private final transient ClientLogin.ErrorInfo details;
    
    ClientLoginResponseException(final Builder builder, final ClientLogin.ErrorInfo details) {
        super(builder);
        this.details = details;
    }
    
    public final ClientLogin.ErrorInfo getDetails() {
        return this.details;
    }
}
