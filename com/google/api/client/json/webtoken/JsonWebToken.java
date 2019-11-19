package com.google.api.client.json.webtoken;

import java.util.Collections;
import java.util.List;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Preconditions;

public class JsonWebToken
{
    private final Header header;
    private final Payload payload;
    
    public JsonWebToken(final Header header, final Payload payload) {
        super();
        this.header = Preconditions.<Header>checkNotNull(header);
        this.payload = Preconditions.<Payload>checkNotNull(payload);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("header", this.header).add("payload", this.payload).toString();
    }
    
    public Header getHeader() {
        return this.header;
    }
    
    public Payload getPayload() {
        return this.payload;
    }
}
