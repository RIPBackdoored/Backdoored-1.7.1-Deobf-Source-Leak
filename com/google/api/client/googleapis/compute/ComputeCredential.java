package com.google.api.client.googleapis.compute;

import java.util.Collection;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.Credential;

@Beta
public class ComputeCredential extends Credential
{
    public static final String TOKEN_SERVER_ENCODED_URL;
    
    public ComputeCredential(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
        this(new Builder(httpTransport, jsonFactory));
    }
    
    protected ComputeCredential(final Builder builder) {
        super(builder);
    }
    
    @Override
    protected TokenResponse executeRefreshToken() throws IOException {
        final HttpRequest buildGetRequest = this.getTransport().createRequestFactory().buildGetRequest(new GenericUrl(this.getTokenServerEncodedUrl()));
        buildGetRequest.setParser(new JsonObjectParser(this.getJsonFactory()));
        buildGetRequest.getHeaders().set("Metadata-Flavor", "Google");
        return buildGetRequest.execute().<TokenResponse>parseAs(TokenResponse.class);
    }
    
    static {
        TOKEN_SERVER_ENCODED_URL = String.valueOf(OAuth2Utils.getMetadataServerUrl()).concat("/computeMetadata/v1/instance/service-accounts/default/token");
    }
}
