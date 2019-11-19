package org.reflections;

import com.google.common.collect.Iterables;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;
import java.lang.reflect.Member;
import com.google.common.base.Predicate;

static final class ReflectionUtils$11 implements Predicate<Member> {
    final /* synthetic */ Class val$annotationClass;
    
    ReflectionUtils$11(final Class val$annotationClass) {
        this.val$annotationClass = val$annotationClass;
        super();
    }
    
    @Override
    public boolean apply(@Nullable final Member member) {
        return member != null && Iterables.<Object>any((Iterable<Object>)ReflectionUtils.access$400(ReflectionUtils.access$300(member)), (Predicate<? super Object>)new Predicate<Class<? extends Annotation>>() {
            final /* synthetic */ ReflectionUtils$11 this$0;
            
            ReflectionUtils$11$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Class<? extends Annotation> clazz) {
                return clazz.equals(this.this$0.val$annotationClass);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Class<? extends Annotation>)o);
            }
        });
    }
    
    @Override
    public /* bridge */ boolean apply(@Nullable final Object o) {
        return this.apply((Member)o);
    }
}