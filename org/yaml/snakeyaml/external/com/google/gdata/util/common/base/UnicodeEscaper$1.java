package org.yaml.snakeyaml.external.com.google.gdata.util.common.base;

import java.io.IOException;

class UnicodeEscaper$1 implements Appendable {
    int pendingHighSurrogate = -1;
    char[] decodedChars = new char[2];
    final /* synthetic */ Appendable val$out;
    final /* synthetic */ UnicodeEscaper this$0;
    
    UnicodeEscaper$1(final UnicodeEscaper this$0, final Appendable val$out) {
        this.this$0 = this$0;
        this.val$out = val$out;
        super();
    }
    
    @Override
    public Appendable append(final CharSequence charSequence) throws IOException {
        return this.append(charSequence, 0, charSequence.length());
    }
    
    @Override
    public Appendable append(final CharSequence charSequence, final int n, final int n2) throws IOException {
        int start = n;
        if (start < n2) {
            int n3 = start;
            if (this.pendingHighSurrogate != -1) {
                final char char1 = charSequence.charAt(start++);
                if (!Character.isLowSurrogate(char1)) {
                    throw new IllegalArgumentException("Expected low surrogate character but got " + char1);
                }
                final char[] escape = this.this$0.escape(Character.toCodePoint((char)this.pendingHighSurrogate, char1));
                if (escape != null) {
                    this.outputChars(escape, escape.length);
                    ++n3;
                }
                else {
                    this.val$out.append((char)this.pendingHighSurrogate);
                }
                this.pendingHighSurrogate = -1;
            }
            while (true) {
                final int nextEscapeIndex = this.this$0.nextEscapeIndex(charSequence, start, n2);
                if (nextEscapeIndex > n3) {
                    this.val$out.append(charSequence, n3, nextEscapeIndex);
                }
                if (nextEscapeIndex == n2) {
                    break;
                }
                final int codePoint = UnicodeEscaper.codePointAt(charSequence, nextEscapeIndex, n2);
                if (codePoint < 0) {
                    this.pendingHighSurrogate = -codePoint;
                    break;
                }
                final char[] escape2 = this.this$0.escape(codePoint);
                if (escape2 != null) {
                    this.outputChars(escape2, escape2.length);
                }
                else {
                    this.outputChars(this.decodedChars, Character.toChars(codePoint, this.decodedChars, 0));
                }
                start = (n3 = nextEscapeIndex + (Character.isSupplementaryCodePoint(codePoint) ? 2 : 1));
            }
        }
        return this;
    }
    
    @Override
    public Appendable append(final char pendingHighSurrogate) throws IOException {
        if (this.pendingHighSurrogate != -1) {
            if (!Character.isLowSurrogate(pendingHighSurrogate)) {
                throw new IllegalArgumentException("Expected low surrogate character but got '" + pendingHighSurrogate + "' with value " + (int)pendingHighSurrogate);
            }
            final char[] escape = this.this$0.escape(Character.toCodePoint((char)this.pendingHighSurrogate, pendingHighSurrogate));
            if (escape != null) {
                this.outputChars(escape, escape.length);
            }
            else {
                this.val$out.append((char)this.pendingHighSurrogate);
                this.val$out.append(pendingHighSurrogate);
            }
            this.pendingHighSurrogate = -1;
        }
        else if (Character.isHighSurrogate(pendingHighSurrogate)) {
            this.pendingHighSurrogate = pendingHighSurrogate;
        }
        else {
            if (Character.isLowSurrogate(pendingHighSurrogate)) {
                throw new IllegalArgumentException("Unexpected low surrogate character '" + pendingHighSurrogate + "' with value " + (int)pendingHighSurrogate);
            }
            final char[] escape2 = this.this$0.escape(pendingHighSurrogate);
            if (escape2 != null) {
                this.outputChars(escape2, escape2.length);
            }
            else {
                this.val$out.append(pendingHighSurrogate);
            }
        }
        return this;
    }
    
    private void outputChars(final char[] array, final int n) throws IOException {
        for (int i = 0; i < n; ++i) {
            this.val$out.append(array[i]);
        }
    }
}