package com.google.api.client.googleapis.testing.auth.oauth2;

import java.util.Map;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.webtoken.JsonWebSignature;
import java.io.IOException;
import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;

class MockTokenServerTransport$1 extends MockLowLevelHttpRequest {
    final /* synthetic */ MockTokenServerTransport this$0;
    
    MockTokenServerTransport$1(final MockTokenServerTransport this$0, final String s) {
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
}