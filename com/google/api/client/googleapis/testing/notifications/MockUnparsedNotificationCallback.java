package com.google.api.client.googleapis.testing.notifications;

import java.io.IOException;
import com.google.api.client.googleapis.notifications.UnparsedNotification;
import com.google.api.client.googleapis.notifications.StoredChannel;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.notifications.UnparsedNotificationCallback;

@Beta
public class MockUnparsedNotificationCallback implements UnparsedNotificationCallback
{
    private static final long serialVersionUID = 0L;
    private boolean wasCalled;
    
    public boolean wasCalled() {
        return this.wasCalled;
    }
    
    public MockUnparsedNotificationCallback() {
        super();
    }
    
    public void onNotification(final StoredChannel storedChannel, final UnparsedNotification unparsedNotification) throws IOException {
        this.wasCalled = true;
    }
}
