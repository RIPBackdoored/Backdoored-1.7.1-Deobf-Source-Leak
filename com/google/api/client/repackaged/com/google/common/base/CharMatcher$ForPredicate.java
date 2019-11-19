package com.google.api.client.repackaged.com.google.common.base;

private static final class ForPredicate extends CharMatcher
{
    private final Predicate<? super Character> predicate;
    
    ForPredicate(final Predicate<? super Character> predicate) {
        super();
        this.predicate = Preconditions.<Predicate<? super Character>>checkNotNull(predicate);
    }
    
    @Override
    public boolean matches(final char c) {
        return this.predicate.apply(c);
    }
    
    @Override
    public boolean apply(final Character c) {
        return this.predicate.apply(Preconditions.<Character>checkNotNull(c));
    }
    
    @Override
    public String toString() {
        return "CharMatcher.forPredicate(" + this.predicate + ")";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((Character)o);
    }
}
