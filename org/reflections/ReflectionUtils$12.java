package org.reflections;

import com.google.common.collect.Iterables;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import com.google.common.base.Predicate;

static final class ReflectionUtils$12 implements Predicate<Member> {
    final /* synthetic */ Annotation val$annotation;
    
    ReflectionUtils$12(final Annotation val$annotation) {
        this.val$annotation = val$annotation;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Member member) {
        return member != null && Iterables.<Object>any((Iterable<Object>)ReflectionUtils.access$300(member), (Predicate<? super Object>)new Predicate<Annotation>() {
            final /* synthetic */ ReflectionUtils$12 this$0;
            
            ReflectionUtils$12$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Annotation annotation) {
                return ReflectionUtils.access$100(this.this$0.val$annotation, annotation);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Annotation)o);
            }
        });
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}