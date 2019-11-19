package com.google.api.client.auth.oauth2;

import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import java.io.IOException;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpResponseException;

public class TokenResponseException extends HttpResponseException
{
    private static final long serialVersionUID = 4020689092957439244L;
    private final transient TokenErrorResponse details;
    
    TokenResponseException(final Builder builder, final TokenErrorResponse details) {
        super(builder);
        this.details = details;
    }
    
    public final TokenErrorResponse getDetails() {
        return this.details;
    }
    
    public static TokenResponseException from(final JsonFactory jsonFactory, final HttpResponse httpResponse) {
        final Builder builder = new Builder(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
        Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        TokenErrorResponse tokenErrorResponse = null;
        String content = null;
        final String contentType = httpResponse.getContentType();
        try {
            if (!httpResponse.isSuccessStatusCode() && contentType != null && httpResponse.getContent() != null && HttpMediaType.equalsIgnoreParameters("application/json; charset=UTF-8", contentType)) {
                tokenErrorResponse = new JsonObjectParser(jsonFactory).<TokenErrorResponse>parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), TokenErrorResponse.class);
                content = tokenErrorResponse.toPrettyString();
            }
            else {
                content = httpResponse.parseAsString();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        final StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
        if (!Strings.isNullOrEmpty(content)) {
            computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(content);
            builder.setContent(content);
        }
        builder.setMessage(computeMessageBuffer.toString());
        return new TokenResponseException(builder, tokenErrorResponse);
    }
}
