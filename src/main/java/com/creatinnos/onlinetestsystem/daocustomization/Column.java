package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	ColumnType columnType();

	String columnValue() default "";

	int columnLength() default 0;
}
