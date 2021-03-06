package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.webtoken.JsonWebSignature;
import java.io.IOException;
import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpRequest;
import java.util.HashMap;
import java.util.Map;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.testing.http.MockHttpTransport;

@Beta
public class MockTokenServerTransport extends MockHttpTransport
{
    static final String EXPECTED_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    static final JsonFactory JSON_FACTORY;
    final String tokenServerUrl;
    Map<String, String> serviceAccounts;
    Map<String, String> clients;
    Map<String, String> refreshTokens;
    
    public MockTokenServerTransport() {
        this("https://accounts.google.com/o/oauth2/token");
    }
    
    public MockTokenServerTransport(final String tokenServerUrl) {
        super();
        this.serviceAccounts = new HashMap<String, String>();
        this.clients = new HashMap<String, String>();
        this.refreshTokens = new HashMap<String, String>();
        this.tokenServerUrl = tokenServerUrl;
    }
    
    public void addServiceAccount(final String s, final String s2) {
        this.serviceAccounts.put(s, s2);
    }
    
    public void addClient(final String s, final String s2) {
        this.clients.put(s, s2);
    }
    
    public void addRefreshToken(final String s, final String s2) {
        this.refreshTokens.put(s, s2);
    }
    
    @Override
    public LowLevelHttpRequest buildRequest(final String s, final String s2) throws IOException {
        if (s2.equals(this.tokenServerUrl)) {
            return new MockLowLevelHttpRequest(s2) {
                final /* synthetic */ MockTokenServerTransport this$0;
                
                MockTokenServerTransport$1(final String s) {
                    this.this$0 = this$0;
                    super(s);
                }
                
                @Override
                public LowLevelHttpResponse execute() throws IOException {
                    final Map<String, String> query = TestUtils.parseQuery(this.getContentAsString());
                    final String s = query.get("client_id");
                    String s5;
                    if (s != null) {
                        if (!this.this$0.clients.containsKey(s)) {
                            throw new IOException("Client ID not found.");
                        }
                        final String s2 = query.get("client_secret");
                        final String s3 = this.this$0.clients.get(s);
                        if (s2 == null || !s2.equals(s3)) {
                            throw new IOException("Client secret not found.");
                        }
                        final String s4 = query.get("refresh_token");
                        if (!this.this$0.refreshTokens.containsKey(s4)) {
                            throw new IOException("Refresh Token not found.");
                        }
                        s5 = this.this$0.refreshTokens.get(s4);
                    }
                    else {
                        if (!query.containsKey("grant_type")) {
                            throw new IOException("Unknown token type.");
                        }
                        if (!"urn:ietf:params:oauth:grant-type:jwt-bearer".equals(query.get("grant_type"))) {
                            throw new IOException("Unexpected Grant Type.");
                        }
                        final JsonWebSignature parse = JsonWebSignature.parse(MockTokenServerTransport.JSON_FACTORY, query.get("assertion"));
                        final String issuer = parse.getPayload().getIssuer();
                        if (!this.this$0.serviceAccounts.containsKey(issuer)) {
                            throw new IOException("Service Account Email not found as issuer.");
                        }
                        s5 = this.this$0.serviceAccounts.get(issuer);
                        final String s6 = (String)parse.getPayload().get("scope");
                        if (s6 == null || s6.length() == 0) {
                            throw new IOException("Scopes not found.");
                        }
                    }
                    final GenericJson genericJson = new GenericJson();
                    genericJson.setFactory(MockTokenServerTransport.JSON_FACTORY);
                    genericJson.put("access_token", s5);
                    genericJson.put("expires_in", 3600000);
                    genericJson.put("token_type", "Bearer");
                    return new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent(genericJson.toPrettyString());
                }
            };
        }
        return super.buildRequest(s, s2);
    }
    
    static {
        JSON_FACTORY = new JacksonFactory();
    }
}
