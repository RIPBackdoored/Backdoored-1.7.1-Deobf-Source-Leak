package org.reflections;

import javax.annotation.Nullable;
import java.lang.reflect.Member;
import com.google.common.base.Predicate;

static final class ReflectionUtils$10 implements Predicate<Member> {
    final /* synthetic */ int val$count;
    
    ReflectionUtils$10(final int val$count) {
        this.val$count = val$count;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Member member) {
        return member != null && ReflectionUtils.access$200(member).length == this.val$count;
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}