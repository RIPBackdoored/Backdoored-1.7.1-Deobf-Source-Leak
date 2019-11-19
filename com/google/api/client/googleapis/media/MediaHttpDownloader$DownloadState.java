package com.google.api.client.googleapis.media;

public enum DownloadState
{
    NOT_STARTED, 
    MEDIA_IN_PROGRESS, 
    MEDIA_COMPLETE;
    
    private static final /* synthetic */ DownloadState[] $VALUES;
    
    public static DownloadState[] values() {
        return DownloadState.$VALUES.clone();
    }
    
    public static DownloadState valueOf(final String s) {
        return Enum.<DownloadState>valueOf(DownloadState.class, s);
    }
    
    static {
        $VALUES = new DownloadState[] { DownloadState.NOT_STARTED, DownloadState.MEDIA_IN_PROGRESS, DownloadState.MEDIA_COMPLETE };
    }
}
