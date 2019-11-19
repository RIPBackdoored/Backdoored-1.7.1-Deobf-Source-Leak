package com.google.api.client.auth.openidconnect;

import java.util.Collections;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Beta;

@Beta
public static class Builder
{
    Clock clock;
    long acceptableTimeSkewSeconds;
    Collection<String> issuers;
    Collection<String> audience;
    
    public Builder() {
        super();
        this.clock = Clock.SYSTEM;
        this.acceptableTimeSkewSeconds = 300L;
    }
    
    public IdTokenVerifier build() {
        return new IdTokenVerifier(this);
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public Builder setClock(final Clock clock) {
        this.clock = Preconditions.<Clock>checkNotNull(clock);
        return this;
    }
    
    public final String getIssuer() {
        if (this.issuers == null) {
            return null;
        }
        return this.issuers.iterator().next();
    }
    
    public Builder setIssuer(final String s) {
        if (s == null) {
            return this.setIssuers(null);
        }
        return this.setIssuers(Collections.<String>singleton(s));
    }
    
    public final Collection<String> getIssuers() {
        return this.issuers;
    }
    
    public Builder setIssuers(final Collection<String> issuers) {
        Preconditions.checkArgument(issuers == null || !issuers.isEmpty(), (Object)"Issuers must not be empty");
        this.issuers = issuers;
        return this;
    }
    
    public final Collection<String> getAudience() {
        return this.audience;
    }
    
    public Builder setAudience(final Collection<String> audience) {
        this.audience = audience;
        return this;
    }
    
    public final long getAcceptableTimeSkewSeconds() {
        return this.acceptableTimeSkewSeconds;
    }
    
    public Builder setAcceptableTimeSkewSeconds(final long acceptableTimeSkewSeconds) {
        Preconditions.checkArgument(acceptableTimeSkewSeconds >= 0L);
        this.acceptableTimeSkewSeconds = acceptableTimeSkewSeconds;
        return this;
    }
}
