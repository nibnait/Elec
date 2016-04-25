package com.dcfun.elec.utils.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 */
//被这个注解修饰的注解，利用反射，将其他的注解读取出来
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationLimit {
	String mid();//权限的code
	String pid();//父级权限的code
}
