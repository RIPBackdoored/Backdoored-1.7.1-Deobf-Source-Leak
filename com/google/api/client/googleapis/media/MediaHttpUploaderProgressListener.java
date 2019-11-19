package com.google.api.client.googleapis.media;

import java.io.IOException;

public interface MediaHttpUploaderProgressListener
{
    void progressChanged(final MediaHttpUploader p0) throws IOException;
}
