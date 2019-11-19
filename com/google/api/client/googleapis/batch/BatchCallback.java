package com.google.api.client.googleapis.batch;

import java.io.IOException;
import com.google.api.client.http.HttpHeaders;

public interface BatchCallback<T, E>
{
    void onSuccess(final T p0, final HttpHeaders p1) throws IOException;
    
    void onFailure(final E p0, final HttpHeaders p1) throws IOException;
}
