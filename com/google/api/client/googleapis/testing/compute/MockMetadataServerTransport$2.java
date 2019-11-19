package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;

class MockMetadataServerTransport$2 extends MockLowLevelHttpRequest {
    final /* synthetic */ MockMetadataServerTransport this$0;
    
    MockMetadataServerTransport$2(final MockMetadataServerTransport this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public LowLevelHttpResponse execute() {
        final MockLowLevelHttpResponse mockLowLevelHttpResponse = new MockLowLevelHttpResponse();
        mockLowLevelHttpResponse.addHeader("Metadata-Flavor", "Google");
        return mockLowLevelHttpResponse;
    }
}