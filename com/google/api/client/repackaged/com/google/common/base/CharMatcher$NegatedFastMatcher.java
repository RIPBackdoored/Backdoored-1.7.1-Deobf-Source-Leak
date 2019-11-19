package com.google.api.client.repackaged.com.google.common.base;

static class NegatedFastMatcher extends Negated
{
    NegatedFastMatcher(final CharMatcher charMatcher) {
        super(charMatcher);
    }
    
    @Override
    public final CharMatcher precomputed() {
        return this;
    }
}
