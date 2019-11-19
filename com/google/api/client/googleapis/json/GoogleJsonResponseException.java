package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import java.io.IOException;
import com.google.api.client.json.JsonToken;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpResponseException;

public class GoogleJsonResponseException extends HttpResponseException
{
    private static final long serialVersionUID = 409811126989994864L;
    private final transient GoogleJsonError details;
    
    public GoogleJsonResponseException(final Builder builder, final GoogleJsonError details) {
        super(builder);
        this.details = details;
    }
    
    public final GoogleJsonError getDetails() {
        return this.details;
    }
    
    public static GoogleJsonResponseException from(final JsonFactory jsonFactory, final HttpResponse httpResponse) {
        final Builder builder = new Builder(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
        Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        GoogleJsonError googleJsonError = null;
        String content = null;
        try {
            if (!httpResponse.isSuccessStatusCode() && HttpMediaType.equalsIgnoreParameters("application/json; charset=UTF-8", httpResponse.getContentType()) && httpResponse.getContent() != null) {
                JsonParser jsonParser = null;
                try {
                    jsonParser = jsonFactory.createJsonParser(httpResponse.getContent());
                    JsonToken jsonToken = jsonParser.getCurrentToken();
                    if (jsonToken == null) {
                        jsonToken = jsonParser.nextToken();
                    }
                    if (jsonToken != null) {
                        jsonParser.skipToKey("error");
                        if (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                            googleJsonError = jsonParser.<GoogleJsonError>parseAndClose(GoogleJsonError.class);
                            content = googleJsonError.toPrettyString();
                        }
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally {
                    if (jsonParser == null) {
                        httpResponse.ignore();
                    }
                    else if (googleJsonError == null) {
                        jsonParser.close();
                    }
                }
            }
            else {
                content = httpResponse.parseAsString();
            }
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        final StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
        if (!Strings.isNullOrEmpty(content)) {
            computeMessageBuffer.append(StringUtils.LINE_SEPARATOR).append(content);
            builder.setContent(content);
        }
        builder.setMessage(computeMessageBuffer.toString());
        return new GoogleJsonResponseException(builder, googleJsonError);
    }
    
    public static HttpResponse execute(final JsonFactory jsonFactory, final HttpRequest httpRequest) throws GoogleJsonResponseException, IOException {
        Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        final boolean throwExceptionOnExecuteError = httpRequest.getThrowExceptionOnExecuteError();
        if (throwExceptionOnExecuteError) {
            httpRequest.setThrowExceptionOnExecuteError(false);
        }
        final HttpResponse execute = httpRequest.execute();
        httpRequest.setThrowExceptionOnExecuteError(throwExceptionOnExecuteError);
        if (!throwExceptionOnExecuteError || execute.isSuccessStatusCode()) {
            return execute;
        }
        throw from(jsonFactory, execute);
    }
}
