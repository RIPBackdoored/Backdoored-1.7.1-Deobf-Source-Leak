package com.google.api.client.googleapis.auth.oauth2;

class SystemEnvironmentProvider
{
    static final SystemEnvironmentProvider INSTANCE;
    
    SystemEnvironmentProvider() {
        super();
    }
    
    String getEnv(final String s) {
        return System.getenv(s);
    }
    
    static {
        INSTANCE = new SystemEnvironmentProvider();
    }
}
