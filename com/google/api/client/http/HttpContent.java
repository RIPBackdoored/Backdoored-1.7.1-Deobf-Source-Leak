package com.google.api.client.http;

import java.io.OutputStream;
import java.io.IOException;
import com.google.api.client.util.StreamingContent;

public interface HttpContent extends StreamingContent
{
    long getLength() throws IOException;
    
    String getType();
    
    boolean retrySupported();
    
    void writeTo(final OutputStream p0) throws IOException;
}
