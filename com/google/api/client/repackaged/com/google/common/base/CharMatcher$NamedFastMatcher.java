package com.google.api.client.repackaged.com.google.common.base;

abstract static class NamedFastMatcher extends FastMatcher
{
    private final String description;
    
    NamedFastMatcher(final String s) {
        super();
        this.description = Preconditions.<String>checkNotNull(s);
    }
    
    @Override
    public final String toString() {
        return this.description;
    }
}
