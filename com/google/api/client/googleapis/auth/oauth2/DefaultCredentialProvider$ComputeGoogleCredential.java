package com.google.api.client.googleapis.auth.oauth2;

import java.io.InputStream;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import java.io.IOException;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;

private static class ComputeGoogleCredential extends GoogleCredential
{
    private static final String TOKEN_SERVER_ENCODED_URL;
    
    ComputeGoogleCredential(final HttpTransport transport, final JsonFactory jsonFactory) {
        super(new Builder().setTransport(transport).setJsonFactory(jsonFactory).setTokenServerEncodedUrl(ComputeGoogleCredential.TOKEN_SERVER_ENCODED_URL));
    }
    
    @Override
    protected TokenResponse executeRefreshToken() throws IOException {
        final HttpRequest buildGetRequest = this.getTransport().createRequestFactory().buildGetRequest(new GenericUrl(this.getTokenServerEncodedUrl()));
        final JsonObjectParser parser = new JsonObjectParser(this.getJsonFactory());
        buildGetRequest.setParser(parser);
        buildGetRequest.getHeaders().set("Metadata-Flavor", "Google");
        buildGetRequest.setThrowExceptionOnExecuteError(false);
        final HttpResponse execute = buildGetRequest.execute();
        final int statusCode = execute.getStatusCode();
        if (statusCode == 200) {
            final InputStream content = execute.getContent();
            if (content == null) {
                throw new IOException("Empty content from metadata token server request.");
            }
            return parser.<TokenResponse>parseAndClose(content, execute.getContentCharset(), TokenResponse.class);
        }
        else {
            if (statusCode == 404) {
                throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified.", statusCode));
            }
            throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", statusCode, execute.parseAsString()));
        }
    }
    
    static {
        TOKEN_SERVER_ENCODED_URL = String.valueOf(OAuth2Utils.getMetadataServerUrl()).concat("/computeMetadata/v1/instance/service-accounts/default/token");
    }
}
