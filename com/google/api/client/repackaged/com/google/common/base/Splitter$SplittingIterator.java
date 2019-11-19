package com.google.api.client.repackaged.com.google.common.base;

private abstract static class SplittingIterator extends AbstractIterator<String>
{
    final CharSequence toSplit;
    final CharMatcher trimmer;
    final boolean omitEmptyStrings;
    int offset;
    int limit;
    
    abstract int separatorStart(final int p0);
    
    abstract int separatorEnd(final int p0);
    
    protected SplittingIterator(final Splitter splitter, final CharSequence toSplit) {
        super();
        this.offset = 0;
        this.trimmer = Splitter.access$200(splitter);
        this.omitEmptyStrings = Splitter.access$300(splitter);
        this.limit = Splitter.access$400(splitter);
        this.toSplit = toSplit;
    }
    
    @Override
    protected String computeNext() {
        int n = this.offset;
        while (this.offset != -1) {
            int n2 = n;
            final int separatorStart = this.separatorStart(this.offset);
            int n3;
            if (separatorStart == -1) {
                n3 = this.toSplit.length();
                this.offset = -1;
            }
            else {
                n3 = separatorStart;
                this.offset = this.separatorEnd(separatorStart);
            }
            if (this.offset == n) {
                ++this.offset;
                if (this.offset < this.toSplit.length()) {
                    continue;
                }
                this.offset = -1;
            }
            else {
                while (n2 < n3 && this.trimmer.matches(this.toSplit.charAt(n2))) {
                    ++n2;
                }
                while (n3 > n2 && this.trimmer.matches(this.toSplit.charAt(n3 - 1))) {
                    --n3;
                }
                if (!this.omitEmptyStrings || n2 != n3) {
                    if (this.limit == 1) {
                        n3 = this.toSplit.length();
                        this.offset = -1;
                        while (n3 > n2 && this.trimmer.matches(this.toSplit.charAt(n3 - 1))) {
                            --n3;
                        }
                    }
                    else {
                        --this.limit;
                    }
                    return this.toSplit.subSequence(n2, n3).toString();
                }
                n = this.offset;
            }
        }
        return this.endOfData();
    }
    
    @Override
    protected /* bridge */ Object computeNext() {
        return this.computeNext();
    }
}
