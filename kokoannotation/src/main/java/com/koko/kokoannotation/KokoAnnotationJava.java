package com.koko.kokoannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huanggang on 2022/9/5
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface KokoAnnotationJava {
}
