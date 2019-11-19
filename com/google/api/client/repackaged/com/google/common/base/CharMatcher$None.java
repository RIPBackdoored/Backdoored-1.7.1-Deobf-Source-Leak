package com.google.api.client.repackaged.com.google.common.base;

private static final class None extends NamedFastMatcher
{
    static final None INSTANCE;
    
    private None() {
        super("CharMatcher.none()");
    }
    
    @Override
    public boolean matches(final char c) {
        return false;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return -1;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence, final int n) {
        Preconditions.checkPositionIndex(n, charSequence.length());
        return -1;
    }
    
    @Override
    public int lastIndexIn(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return -1;
    }
    
    @Override
    public boolean matchesAllOf(final CharSequence charSequence) {
        return charSequence.length() == 0;
    }
    
    @Override
    public boolean matchesNoneOf(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return true;
    }
    
    @Override
    public String removeFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final char c) {
        return charSequence.toString();
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final CharSequence charSequence2) {
        Preconditions.<CharSequence>checkNotNull(charSequence2);
        return charSequence.toString();
    }
    
    @Override
    public String collapseFrom(final CharSequence charSequence, final char c) {
        return charSequence.toString();
    }
    
    @Override
    public String trimFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String trimLeadingFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String trimTrailingFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public int countIn(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return 0;
    }
    
    @Override
    public CharMatcher and(final CharMatcher charMatcher) {
        Preconditions.<CharMatcher>checkNotNull(charMatcher);
        return this;
    }
    
    @Override
    public CharMatcher or(final CharMatcher charMatcher) {
        return Preconditions.<CharMatcher>checkNotNull(charMatcher);
    }
    
    @Override
    public CharMatcher negate() {
        return CharMatcher.any();
    }
    
    static {
        INSTANCE = new None();
    }
}
