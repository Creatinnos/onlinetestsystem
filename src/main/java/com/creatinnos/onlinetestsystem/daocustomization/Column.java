package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	boolean isPrimaryKey() default false;

	String columnName();
	Class<?> value() default String.class; 
	ColumnType columnType();
	String columnValue() default "";
	Class<?> enumValue() default Enum.class;

	int columnLength() default 0;
}
