package com.google.api.client.googleapis.notifications;

import java.util.UUID;

public final class NotificationUtils
{
    public static String randomUuidString() {
        return UUID.randomUUID().toString();
    }
    
    private NotificationUtils() {
        super();
    }
}
