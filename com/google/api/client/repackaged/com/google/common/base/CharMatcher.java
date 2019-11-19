package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
public abstract class CharMatcher implements Predicate<Character>
{
    @Deprecated
    public static final CharMatcher WHITESPACE;
    @Deprecated
    public static final CharMatcher BREAKING_WHITESPACE;
    @Deprecated
    public static final CharMatcher ASCII;
    @Deprecated
    public static final CharMatcher DIGIT;
    @Deprecated
    public static final CharMatcher JAVA_DIGIT;
    @Deprecated
    public static final CharMatcher JAVA_LETTER;
    @Deprecated
    public static final CharMatcher JAVA_LETTER_OR_DIGIT;
    @Deprecated
    public static final CharMatcher JAVA_UPPER_CASE;
    @Deprecated
    public static final CharMatcher JAVA_LOWER_CASE;
    @Deprecated
    public static final CharMatcher JAVA_ISO_CONTROL;
    @Deprecated
    public static final CharMatcher INVISIBLE;
    @Deprecated
    public static final CharMatcher SINGLE_WIDTH;
    @Deprecated
    public static final CharMatcher ANY;
    @Deprecated
    public static final CharMatcher NONE;
    private static final int DISTINCT_CHARS = 65536;
    
    public static CharMatcher any() {
        return Any.INSTANCE;
    }
    
    public static CharMatcher none() {
        return None.INSTANCE;
    }
    
    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }
    
    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }
    
    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }
    
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }
    
    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }
    
    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }
    
    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }
    
    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }
    
    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }
    
    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }
    
    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }
    
    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }
    
    public static CharMatcher is(final char c) {
        return new Is(c);
    }
    
    public static CharMatcher isNot(final char c) {
        return new IsNot(c);
    }
    
    public static CharMatcher anyOf(final CharSequence charSequence) {
        switch (charSequence.length()) {
            case 0:
                return none();
            case 1:
                return is(charSequence.charAt(0));
            case 2:
                return isEither(charSequence.charAt(0), charSequence.charAt(1));
            default:
                return new AnyOf(charSequence);
        }
    }
    
    public static CharMatcher noneOf(final CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }
    
    public static CharMatcher inRange(final char c, final char c2) {
        return new InRange(c, c2);
    }
    
    public static CharMatcher forPredicate(final Predicate<? super Character> predicate) {
        return (predicate instanceof CharMatcher) ? ((CharMatcher)predicate) : new ForPredicate(predicate);
    }
    
    protected CharMatcher() {
        super();
    }
    
    public abstract boolean matches(final char p0);
    
    public CharMatcher negate() {
        return new Negated(this);
    }
    
    public CharMatcher and(final CharMatcher charMatcher) {
        return new And(this, charMatcher);
    }
    
    public CharMatcher or(final CharMatcher charMatcher) {
        return new Or(this, charMatcher);
    }
    
    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }
    
    @GwtIncompatible
    CharMatcher precomputedInternal() {
        final BitSet bits = new BitSet();
        this.setBits(bits);
        final int cardinality = bits.cardinality();
        if (cardinality * 2 <= 65536) {
            return precomputedPositive(cardinality, bits, this.toString());
        }
        bits.flip(0, 65536);
        final int n = 65536 - cardinality;
        final String s = ".negate()";
        final String string = this.toString();
        return new NegatedFastMatcher(precomputedPositive(n, bits, string.endsWith(s) ? string.substring(0, string.length() - s.length()) : (string + s))) {
            final /* synthetic */ String val$description;
            final /* synthetic */ CharMatcher this$0;
            
            CharMatcher$1(final CharMatcher charMatcher) {
                this.this$0 = this$0;
                super(charMatcher);
            }
            
            @Override
            public String toString() {
                return string;
            }
        };
    }
    
    @GwtIncompatible
    private static CharMatcher precomputedPositive(final int n, final BitSet set, final String s) {
        switch (n) {
            case 0:
                return none();
            case 1:
                return is((char)set.nextSetBit(0));
            case 2: {
                final char c = (char)set.nextSetBit(0);
                return isEither(c, (char)set.nextSetBit(c + '\u0001'));
            }
            default:
                return isSmall(n, set.length()) ? SmallCharMatcher.from(set, s) : new BitSetMatcher(set, s);
        }
    }
    
    @GwtIncompatible
    private static boolean isSmall(final int n, final int n2) {
        return n <= 1023 && n2 > n * 4 * 16;
    }
    
    @GwtIncompatible
    void setBits(final BitSet set) {
        for (int i = 65535; i >= 0; --i) {
            if (this.matches((char)i)) {
                set.set(i);
            }
        }
    }
    
    public boolean matchesAnyOf(final CharSequence charSequence) {
        return !this.matchesNoneOf(charSequence);
    }
    
    public boolean matchesAllOf(final CharSequence charSequence) {
        for (int i = charSequence.length() - 1; i >= 0; --i) {
            if (!this.matches(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean matchesNoneOf(final CharSequence charSequence) {
        return this.indexIn(charSequence) == -1;
    }
    
    public int indexIn(final CharSequence charSequence) {
        return this.indexIn(charSequence, 0);
    }
    
    public int indexIn(final CharSequence charSequence, final int n) {
        final int length = charSequence.length();
        Preconditions.checkPositionIndex(n, length);
        for (int i = n; i < length; ++i) {
            if (this.matches(charSequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
    
    public int lastIndexIn(final CharSequence charSequence) {
        for (int i = charSequence.length() - 1; i >= 0; --i) {
            if (this.matches(charSequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
    
    public int countIn(final CharSequence charSequence) {
        int n = 0;
        for (int i = 0; i < charSequence.length(); ++i) {
            if (this.matches(charSequence.charAt(i))) {
                ++n;
            }
        }
        return n;
    }
    
    public String removeFrom(final CharSequence charSequence) {
        final String string = charSequence.toString();
        int i = this.indexIn(string);
        if (i == -1) {
            return string;
        }
        final char[] charArray = string.toCharArray();
        int n = 1;
    Label_0029:
        while (true) {
            ++i;
            while (i != charArray.length) {
                if (this.matches(charArray[i])) {
                    ++n;
                    continue Label_0029;
                }
                charArray[i - n] = charArray[i];
                ++i;
            }
            break;
        }
        return new String(charArray, 0, i - n);
    }
    
    public String retainFrom(final CharSequence charSequence) {
        return this.negate().removeFrom(charSequence);
    }
    
    public String replaceFrom(final CharSequence charSequence, final char c) {
        final String string = charSequence.toString();
        final int indexIn = this.indexIn(string);
        if (indexIn == -1) {
            return string;
        }
        final char[] charArray = string.toCharArray();
        charArray[indexIn] = c;
        for (int i = indexIn + 1; i < charArray.length; ++i) {
            if (this.matches(charArray[i])) {
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }
    
    public String replaceFrom(final CharSequence charSequence, final CharSequence charSequence2) {
        charSequence2.length();
        return this.removeFrom(charSequence);
    }
    
    public String trimFrom(final CharSequence charSequence) {
        int length;
        int n;
        for (length = charSequence.length(), n = 0; n < length && this.matches(charSequence.charAt(n)); ++n) {}
        int n2;
        for (n2 = length - 1; n2 > n && this.matches(charSequence.charAt(n2)); --n2) {}
        return charSequence.subSequence(n, n2 + 1).toString();
    }
    
    public String trimLeadingFrom(final CharSequence charSequence) {
        for (int length = charSequence.length(), i = 0; i < length; ++i) {
            if (!this.matches(charSequence.charAt(i))) {
                return charSequence.subSequence(i, length).toString();
            }
        }
        return "";
    }
    
    public String trimTrailingFrom(final CharSequence charSequence) {
        for (int i = charSequence.length() - 1; i >= 0; --i) {
            if (!this.matches(charSequence.charAt(i))) {
                return charSequence.subSequence(0, i + 1).toString();
            }
        }
        return "";
    }
    
    public String collapseFrom(final CharSequence charSequence, final char c) {
        for (int length = charSequence.length(), i = 0; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (this.matches(char1)) {
                if (char1 != c || (i != length - 1 && this.matches(charSequence.charAt(i + 1)))) {
                    return this.finishCollapseFrom(charSequence, i + 1, length, c, new StringBuilder(length).append(charSequence, 0, i).append(c), true);
                }
                ++i;
            }
        }
        return charSequence.toString();
    }
    
    public String trimAndCollapseFrom(final CharSequence charSequence, final char c) {
        final int length = charSequence.length();
        int n = 0;
        int n2 = length - 1;
        while (n < length && this.matches(charSequence.charAt(n))) {
            ++n;
        }
        while (n2 > n && this.matches(charSequence.charAt(n2))) {
            --n2;
        }
        return (n2 == length - 1) ? this.collapseFrom(charSequence, c) : this.finishCollapseFrom(charSequence, n, n2 + 1, c, new StringBuilder(n2 + 1 - n), false);
    }
    
    private String finishCollapseFrom(final CharSequence charSequence, final int n, final int n2, final char c, final StringBuilder sb, final boolean b) {
        for (int i = n; i < n2; ++i) {
            final char char1 = charSequence.charAt(i);
            if (this.matches(char1)) {
                sb.append(c);
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    @Deprecated
    @Override
    public boolean apply(final Character c) {
        return this.matches(c);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    private static String showCharacter(char c) {
        final String s = "0123456789ABCDEF";
        final char[] array = { '\\', 'u', '\0', '\0', '\0', '\0' };
        for (int i = 0; i < 4; ++i) {
            array[5 - i] = s.charAt(c & '\u000f');
            c >>= 4;
        }
        return String.copyValueOf(array);
    }
    
    private static IsEither isEither(final char c, final char c2) {
        return new IsEither(c, c2);
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((Character)o);
    }
    
    static /* synthetic */ String access$100(final char c) {
        return showCharacter(c);
    }
    
    static {
        WHITESPACE = whitespace();
        BREAKING_WHITESPACE = breakingWhitespace();
        ASCII = ascii();
        DIGIT = digit();
        JAVA_DIGIT = javaDigit();
        JAVA_LETTER = javaLetter();
        JAVA_LETTER_OR_DIGIT = javaLetterOrDigit();
        JAVA_UPPER_CASE = javaUpperCase();
        JAVA_LOWER_CASE = javaLowerCase();
        JAVA_ISO_CONTROL = javaIsoControl();
        INVISIBLE = invisible();
        SINGLE_WIDTH = singleWidth();
        ANY = any();
        NONE = none();
    }
}
