package com.restapi.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(RUNTIME)
@Target({METHOD})
public @interface FrameworkAnnotation {

    String[] author() default "default author";
    String[] category() default "default category";
}