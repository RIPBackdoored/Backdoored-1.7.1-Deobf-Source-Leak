package org.reflections;

import java.util.regex.Pattern;
import com.google.common.base.Predicate;

class Reflections$3 implements Predicate<String> {
    final /* synthetic */ Pattern val$pattern;
    final /* synthetic */ Reflections this$0;
    
    Reflections$3(final Reflections this$0, final Pattern val$pattern) {
        this.this$0 = this$0;
        this.val$pattern = val$pattern;
        super();
    }
    
    @Override
    public boolean apply(final String s) {
        return this.val$pattern.matcher(s).matches();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((String)o);
    }
}