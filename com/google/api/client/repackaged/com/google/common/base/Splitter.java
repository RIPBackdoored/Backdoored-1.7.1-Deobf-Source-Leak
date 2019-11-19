package com.google.api.client.repackaged.com.google.common.base;

import java.util.LinkedHashMap;
import java.util.Map;
import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.regex.Pattern;
import java.util.Iterator;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
public final class Splitter
{
    private final CharMatcher trimmer;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final int limit;
    
    private Splitter(final Strategy strategy) {
        this(strategy, false, CharMatcher.none(), 0);
    }
    
    private Splitter(final Strategy strategy, final boolean omitEmptyStrings, final CharMatcher trimmer, final int limit) {
        super();
        this.strategy = strategy;
        this.omitEmptyStrings = omitEmptyStrings;
        this.trimmer = trimmer;
        this.limit = limit;
    }
    
    public static Splitter on(final char c) {
        return on(CharMatcher.is(c));
    }
    
    public static Splitter on(final CharMatcher charMatcher) {
        Preconditions.<CharMatcher>checkNotNull(charMatcher);
        return new Splitter(new Strategy() {
            final /* synthetic */ CharMatcher val$separatorMatcher;
            
            Splitter$1() {
                super();
            }
            
            @Override
            public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    final /* synthetic */ Splitter$1 this$0;
                    
                    Splitter$1$1(final Splitter splitter, final CharSequence charSequence) {
                        this.this$0 = this$0;
                        super(splitter, charSequence);
                    }
                    
                    @Override
                    int separatorStart(final int n) {
                        return charMatcher.indexIn(this.toSplit, n);
                    }
                    
                    @Override
                    int separatorEnd(final int n) {
                        return n + 1;
                    }
                };
            }
            
            @Override
            public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return this.iterator(splitter, charSequence);
            }
        });
    }
    
    public static Splitter on(final String s) {
        Preconditions.checkArgument(s.length() != 0, (Object)"The separator may not be the empty string.");
        if (s.length() == 1) {
            return on(s.charAt(0));
        }
        return new Splitter(new Strategy() {
            final /* synthetic */ String val$separator;
            
            Splitter$2() {
                super();
            }
            
            @Override
            public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    final /* synthetic */ Splitter$2 this$0;
                    
                    Splitter$2$1(final Splitter splitter, final CharSequence charSequence) {
                        this.this$0 = this$0;
                        super(splitter, charSequence);
                    }
                    
                    public int separatorStart(final int n) {
                        final int length = s.length();
                        int i = n;
                        final int n2 = this.toSplit.length() - length;
                    Label_0026:
                        while (i <= n2) {
                            for (int j = 0; j < length; ++j) {
                                if (this.toSplit.charAt(j + i) != s.charAt(j)) {
                                    ++i;
                                    continue Label_0026;
                                }
                            }
                            return i;
                        }
                        return -1;
                    }
                    
                    public int separatorEnd(final int n) {
                        return n + s.length();
                    }
                };
            }
            
            @Override
            public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return this.iterator(splitter, charSequence);
            }
        });
    }
    
    @GwtIncompatible
    public static Splitter on(final Pattern pattern) {
        return on(new JdkPattern(pattern));
    }
    
    private static Splitter on(final CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.matcher("").matches(), "The pattern may not match the empty string: %s", commonPattern);
        return new Splitter(new Strategy() {
            final /* synthetic */ CommonPattern val$separatorPattern;
            
            Splitter$3() {
                super();
            }
            
            @Override
            public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    final /* synthetic */ CommonMatcher val$matcher = commonPattern.matcher(charSequence);
                    final /* synthetic */ Splitter$3 this$0;
                    
                    Splitter$3$1(final Splitter splitter, final CharSequence charSequence) {
                        this.this$0 = this$0;
                        super(splitter, charSequence);
                    }
                    
                    public int separatorStart(final int n) {
                        return this.val$matcher.find(n) ? this.val$matcher.start() : -1;
                    }
                    
                    public int separatorEnd(final int n) {
                        return this.val$matcher.end();
                    }
                };
            }
            
            @Override
            public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return this.iterator(splitter, charSequence);
            }
        });
    }
    
    @GwtIncompatible
    public static Splitter onPattern(final String s) {
        return on(Platform.compilePattern(s));
    }
    
    public static Splitter fixedLength(final int n) {
        Preconditions.checkArgument(n > 0, (Object)"The length may not be less than 1");
        return new Splitter(new Strategy() {
            final /* synthetic */ int val$length;
            
            Splitter$4() {
                super();
            }
            
            @Override
            public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    final /* synthetic */ Splitter$4 this$0;
                    
                    Splitter$4$1(final Splitter splitter, final CharSequence charSequence) {
                        this.this$0 = this$0;
                        super(splitter, charSequence);
                    }
                    
                    public int separatorStart(final int n) {
                        final int n2 = n + n;
                        return (n2 < this.toSplit.length()) ? n2 : -1;
                    }
                    
                    public int separatorEnd(final int n) {
                        return n;
                    }
                };
            }
            
            @Override
            public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
                return this.iterator(splitter, charSequence);
            }
        });
    }
    
    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }
    
    public Splitter limit(final int n) {
        Preconditions.checkArgument(n > 0, "must be greater than zero: %s", n);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, n);
    }
    
    public Splitter trimResults() {
        return this.trimResults(CharMatcher.whitespace());
    }
    
    public Splitter trimResults(final CharMatcher charMatcher) {
        Preconditions.<CharMatcher>checkNotNull(charMatcher);
        return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
    }
    
    public Iterable<String> split(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        return new Iterable<String>() {
            final /* synthetic */ CharSequence val$sequence;
            final /* synthetic */ Splitter this$0;
            
            Splitter$5() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(charSequence);
            }
            
            @Override
            public String toString() {
                return Joiner.on(", ").appendTo(new StringBuilder().append('['), (Iterable<?>)this).append(']').toString();
            }
        };
    }
    
    private Iterator<String> splittingIterator(final CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }
    
    @Beta
    public List<String> splitToList(final CharSequence charSequence) {
        Preconditions.<CharSequence>checkNotNull(charSequence);
        final Iterator<String> splittingIterator = this.splittingIterator(charSequence);
        final ArrayList<String> list = new ArrayList<String>();
        while (splittingIterator.hasNext()) {
            list.add(splittingIterator.next());
        }
        return (List<String>)Collections.<Object>unmodifiableList((List<?>)list);
    }
    
    @Beta
    public MapSplitter withKeyValueSeparator(final String s) {
        return this.withKeyValueSeparator(on(s));
    }
    
    @Beta
    public MapSplitter withKeyValueSeparator(final char c) {
        return this.withKeyValueSeparator(on(c));
    }
    
    @Beta
    public MapSplitter withKeyValueSeparator(final Splitter splitter) {
        return new MapSplitter(this, splitter);
    }
    
    static /* synthetic */ Iterator access$000(final Splitter splitter, final CharSequence charSequence) {
        return splitter.splittingIterator(charSequence);
    }
    
    static /* synthetic */ CharMatcher access$200(final Splitter splitter) {
        return splitter.trimmer;
    }
    
    static /* synthetic */ boolean access$300(final Splitter splitter) {
        return splitter.omitEmptyStrings;
    }
    
    static /* synthetic */ int access$400(final Splitter splitter) {
        return splitter.limit;
    }
}
