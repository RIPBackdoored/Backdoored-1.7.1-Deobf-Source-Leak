package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.json.GenericJson;
import java.io.IOException;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;

class MockMetadataServerTransport$1 extends MockLowLevelHttpRequest {
    final /* synthetic */ MockMetadataServerTransport this$0;
    
    MockMetadataServerTransport$1(final MockMetadataServerTransport this$0, final String s) {
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
}