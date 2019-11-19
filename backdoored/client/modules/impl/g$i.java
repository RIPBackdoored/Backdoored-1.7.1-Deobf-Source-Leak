package r.k.d.m;

import r.k.d.c.y;
import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface g$i {
    @Nonnull
    String name();
    
    @Nonnull
    String description();
    
    @Nonnull
    y category();
    
    boolean defaultOn() default false;
    
    boolean defaultIsVisible() default true;
    
    String defaultBind() default "NONE";
}
