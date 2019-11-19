package org.reflections;

import javax.annotation.Nullable;
import java.lang.reflect.Member;
import com.google.common.base.Predicate;

static final class ReflectionUtils$9 implements Predicate<Member> {
    final /* synthetic */ Class[] val$types;
    
    ReflectionUtils$9(final Class[] val$types) {
        this.val$types = val$types;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Member member) {
        if (member != null) {
            final Class[] access$200 = ReflectionUtils.access$200(member);
            if (access$200.length == this.val$types.length) {
                for (int i = 0; i < access$200.length; ++i) {
                    if (!access$200[i].isAssignableFrom(this.val$types[i]) || (access$200[i] == Object.class && this.val$types[i] != Object.class)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}