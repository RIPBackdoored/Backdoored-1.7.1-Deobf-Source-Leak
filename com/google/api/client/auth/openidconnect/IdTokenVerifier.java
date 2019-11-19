package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.Preconditions;
import java.util.Collections;
import java.util.Collection;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Beta;

@Beta
public class IdTokenVerifier
{
    public static final long DEFAULT_TIME_SKEW_SECONDS = 300L;
    private final Clock clock;
    private final long acceptableTimeSkewSeconds;
    private final Collection<String> issuers;
    private final Collection<String> audience;
    
    public IdTokenVerifier() {
        this(new Builder());
    }
    
    protected IdTokenVerifier(final Builder builder) {
        super();
        this.clock = builder.clock;
        this.acceptableTimeSkewSeconds = builder.acceptableTimeSkewSeconds;
        this.issuers = ((builder.issuers == null) ? null : Collections.<String>unmodifiableCollection((Collection<? extends String>)builder.issuers));
        this.audience = ((builder.audience == null) ? null : Collections.<String>unmodifiableCollection((Collection<? extends String>)builder.audience));
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public final long getAcceptableTimeSkewSeconds() {
        return this.acceptableTimeSkewSeconds;
    }
    
    public final String getIssuer() {
        if (this.issuers == null) {
            return null;
        }
        return this.issuers.iterator().next();
    }
    
    public final Collection<String> getIssuers() {
        return this.issuers;
    }
    
    public final Collection<String> getAudience() {
        return this.audience;
    }
    
    public boolean verify(final IdToken idToken) {
        return (this.issuers == null || idToken.verifyIssuer(this.issuers)) && (this.audience == null || idToken.verifyAudience(this.audience)) && idToken.verifyTime(this.clock.currentTimeMillis(), this.acceptableTimeSkewSeconds);
    }
}
