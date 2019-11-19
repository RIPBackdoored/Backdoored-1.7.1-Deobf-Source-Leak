package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.json.GenericJson;
import java.io.IOException;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.testing.http.MockHttpTransport;

@Beta
public class MockMetadataServerTransport extends MockHttpTransport
{
    private static final String METADATA_SERVER_URL;
    private static final String METADATA_TOKEN_SERVER_URL;
    static final JsonFactory JSON_FACTORY;
    String accessToken;
    Integer tokenRequestStatusCode;
    
    public MockMetadataServerTransport(final String accessToken) {
        super();
        this.accessToken = accessToken;
    }
    
    public void setTokenRequestStatusCode(final Integer tokenRequestStatusCode) {
        this.tokenRequestStatusCode = tokenRequestStatusCode;
    }
    
    @Override
    public LowLevelHttpRequest buildRequest(final String s, final String s2) throws IOException {
        if (s2.equals(MockMetadataServerTransport.METADATA_TOKEN_SERVER_URL)) {
            return new MockLowLevelHttpRequest(s2) {
                final /* synthetic */ MockMetadataServerTransport this$0;
                
                MockMetadataServerTransport$1(final String s) {
                    this.this$0 = this$0;
                    super(s);
                }
                
                @Override
                public LowLevelHttpResponse execute() throws IOException {
                    if (this.this$0.tokenRequestStatusCode != null) {
                        return new MockLowLevelHttpResponse().setStatusCode(this.this$0.tokenRequestStatusCode).setContent("Token Fetch Error");
                    }
                    if (!"Google".equals(this.getFirstHeaderValue("Metadata-Flavor"))) {
                        throw new IOException("Metadata request header not found.");
                    }
                    final GenericJson genericJson = new GenericJson();
                    genericJson.setFactory(MockMetadataServerTransport.JSON_FACTORY);
                    genericJson.put("access_token", this.this$0.accessToken);
                    genericJson.put("expires_in", 3600000);
                    genericJson.put("token_type", "Bearer");
                    return new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent(genericJson.toPrettyString());
                }
            };
        }
        if (s2.equals(MockMetadataServerTransport.METADATA_SERVER_URL)) {
            return new MockLowLevelHttpRequest(s2) {
                final /* synthetic */ MockMetadataServerTransport this$0;
                
                MockMetadataServerTransport$2(final String s) {
                    this.this$0 = this$0;
                    super(s);
                }
                
                @Override
                public LowLevelHttpResponse execute() {
                    final MockLowLevelHttpResponse mockLowLevelHttpResponse = new MockLowLevelHttpResponse();
                    mockLowLevelHttpResponse.addHeader("Metadata-Flavor", "Google");
                    return mockLowLevelHttpResponse;
                }
            };
        }
        return super.buildRequest(s, s2);
    }
    
    static {
        METADATA_SERVER_URL = OAuth2Utils.getMetadataServerUrl();
        METADATA_TOKEN_SERVER_URL = String.valueOf(MockMetadataServerTransport.METADATA_SERVER_URL).concat("/computeMetadata/v1/instance/service-accounts/default/token");
        JSON_FACTORY = new JacksonFactory();
    }
}
