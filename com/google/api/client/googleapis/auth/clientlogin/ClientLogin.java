package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;
import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Key;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;

@Beta
public final class ClientLogin
{
    public HttpTransport transport;
    public GenericUrl serverUrl;
    @Key("source")
    public String applicationName;
    @Key("service")
    public String authTokenType;
    @Key("Email")
    public String username;
    @Key("Passwd")
    public String password;
    @Key
    public String accountType;
    @Key("logintoken")
    public String captchaToken;
    @Key("logincaptcha")
    public String captchaAnswer;
    
    public ClientLogin() {
        super();
        this.serverUrl = new GenericUrl("https://www.google.com");
    }
    
    public Response authenticate() throws IOException {
        final GenericUrl clone = this.serverUrl.clone();
        clone.appendRawPath("/accounts/ClientLogin");
        final HttpRequest buildPostRequest = this.transport.createRequestFactory().buildPostRequest(clone, new UrlEncodedContent(this));
        buildPostRequest.setParser(AuthKeyValueParser.INSTANCE);
        buildPostRequest.setContentLoggingLimit(0);
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        final HttpResponse execute = buildPostRequest.execute();
        if (execute.isSuccessStatusCode()) {
            return execute.<Response>parseAs(Response.class);
        }
        final HttpResponseException.Builder builder = new HttpResponseException.Builder(execute.getStatusCode(), execute.getStatusMessage(), execute.getHeaders());
        final ErrorInfo errorInfo = execute.<ErrorInfo>parseAs(ErrorInfo.class);
        final String string = errorInfo.toString();
        final StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(execute);
        if (!Strings.isNullOrEmpty(string)) {
            computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(string);
            builder.setContent(string);
        }
        builder.setMessage(computeMessageBuffer.toString());
        throw new ClientLoginResponseException(builder, errorInfo);
    }
    
    public static String getAuthorizationHeaderValue(final String s) {
        final String s2 = "GoogleLogin auth=";
        final String value = String.valueOf(s);
        return (value.length() != 0) ? s2.concat(value) : new String(s2);
    }
}
