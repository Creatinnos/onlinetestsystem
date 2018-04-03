package com.creatinnos.onlinetestsystem.daocustomization;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public class InvokeSetterGetter {

/*	public static void main(String[] args) {
		 Create object of Actor. 
		Actor objActor = new Actor();

		InvokeSetterGetter objInvokeSetterGetter = new InvokeSetterGetter();
		 Call invokeSetter method 
		objInvokeSetterGetter.invokeSetter(objActor, "Name", "Benedict Cumberbatch");
		 Call invokeGetter method 
		objInvokeSetterGetter.invokeGetter(objActor, "Name");
	}
*/
	static Logger log = Logger.getLogger(InvokeSetterGetter.class.getName());
	public static void invokeSetter(Object obj, String variableName, Object variableValue){
		/* variableValue is Object because value can be an Object, Integer, String, etc... */
		try {
			/**
			 * Get object of PropertyDescriptor using variable name and class
			 * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
			 */
			PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
			/* Set field/variable value using getWriteMethod() */
			objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IntrospectionException e) {
			/* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
			log.error(e.getLocalizedMessage());
		}
	}
	

	public static Object invokeGetter(Object obj, String variableName){
		try {
			/**
			 * Get object of PropertyDescriptor using variable name and class
			 * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
			 */
			PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
			/**
			 * Get field/variable value using getReadMethod()
			 * variableValue is Object because value can be an Object, Integer, String, etc...
			 */
			Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
			/* Print value of variable */
			return variableValue;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IntrospectionException e) {
			/* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
			log.error(e.getLocalizedMessage());
		}
		return null;
	}
}