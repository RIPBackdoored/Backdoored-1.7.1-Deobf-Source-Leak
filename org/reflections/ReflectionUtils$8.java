package org.reflections;

import java.util.Arrays;
import javax.annotation.Nullable;
import java.lang.reflect.Member;
import com.google.common.base.Predicate;

static final class ReflectionUtils$8 implements Predicate<Member> {
    final /* synthetic */ Class[] val$types;
    
    ReflectionUtils$8(final Class[] val$types) {
        this.val$types = val$types;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Member member) {
        return Arrays.equals(ReflectionUtils.access$200(member), this.val$types);
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}