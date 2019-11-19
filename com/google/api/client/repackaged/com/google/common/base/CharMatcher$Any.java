package com.google.api.client.repackaged.com.google.common.base;

import java.util.Arrays;

private static final class Any extends NamedFastMatcher
{
    static final Any INSTANCE;
    
    private Any() {
        super("CharMatcher.any()");
    }
    
    @Override
    public boolean matches(final char c) {
        return true;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence) {
        return (charSequence.length() == 0) ? -1 : 0;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence, final int n) {
        final int length = charSequence.length();
        Preconditions.checkPositionIndex(n, length);
        return (n == length) ? -1 : n;
    }
    
    @Override
    public int lastIndexIn(final CharSequence charSequence) {
        return charSequence.length() - 1;
    }
    
    @Override
    public boolean matchesAllOf(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return true;
    }
    
    @Override
    public boolean matchesNoneOf(final CharSequence charSequence) {
        return charSequence.length() == 0;
    }
    
    @Override
    public String removeFrom(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return "";
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final char c) {
        final char[] array = new char[charSequence.length()];
        Arrays.fill(array, c);
        return new String(array);
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final CharSequence charSequence2) {
        final StringBuilder sb = new StringBuilder(charSequence.length() * charSequence2.length());
        for (int i = 0; i < charSequence.length(); ++i) {
            sb.append(charSequence2);
        }
        return sb.toString();
    }
    
    @Override
    public String collapseFrom(final CharSequence charSequence, final char c) {
        return (charSequence.length() == 0) ? "" : String.valueOf(c);
    }
    
    @Override
    public String trimFrom(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return "";
    }
    
    @Override
    public int countIn(final CharSequence charSequence) {
        return charSequence.length();
    }
    
    @Override
    public CharMatcher and(final CharMatcher charMatcher) {
        return Preconditions.<CharMatcher>checkNotNull(charMatcher);
    }
    
    @Override
    public CharMatcher or(final CharMatcher charMatcher) {
        Preconditions.<CharMatcher>checkNotNull(charMatcher);
        return this;
    }
    
    @Override
    public CharMatcher negate() {
        return CharMatcher.none();
    }
    
    static {
        INSTANCE = new Any();
    }
}
