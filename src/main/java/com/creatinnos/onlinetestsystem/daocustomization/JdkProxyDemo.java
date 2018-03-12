package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class JdkProxyDemo{
	interface If {
		static final String NAME = "";
		static final String CHECK = "";

		void setName(String name);

		public String getName();
	}

	static class Handler implements InvocationHandler {
		private final Class<?> original;

		public Handler(Class<?> class1) {
			this.original = class1;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException {
			Object result = proxy;
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				String name = methodName
						.substring(methodName.indexOf("get") + 3);
				Field nameField=null;
				try {
					nameField = result.getClass().getField(
							name.toUpperCase());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return nameField.get(result);
			} else if (methodName.startsWith("set")) {
				String name = methodName
						.substring(methodName.indexOf("set") + 3);
				try {
					Field nameField = result.getClass().getField(
							name.toUpperCase());
					nameField.setAccessible(true);
					int modifiers = nameField.getModifiers();
					Field modifierField = nameField.getClass()
							.getDeclaredField("modifiers");
					modifiers = modifiers & ~Modifier.FINAL;
					modifierField.setAccessible(true);
					modifierField.setInt(nameField, modifiers);
					nameField.set(original, args[0]);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return original;
			} else if (methodName.startsWith("is")) {
				String name = methodName
						.substring(methodName.indexOf("is") + 2);
				System.out.println(name);
				return null;
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T create1(Class<T> boInterfaceClass) {
		Handler handler = new Handler(If.class);
	      
		T s=(T)Proxy.newProxyInstance(boInterfaceClass.getClassLoader(),
				new Class[] { boInterfaceClass }, handler);
		
		System.out.println(s.getClass().getInterfaces()[0].getCanonicalName());
		return (T)Proxy.newProxyInstance(boInterfaceClass.getClassLoader(),
					new Class[] { boInterfaceClass }, handler);
	   }
	
	public static void main(String[] args) {
		/*Handler handler = new Handler(If.class);
		If f = (If) Proxy.newProxyInstance(If.class.getClassLoader(),
				new Class[] { If.class }, handler);*/
		If f = create1(If.class);
		f.setName("Hallo");
		System.out.println("ddd"+f.getName());
		Class<?>[] classes = f.getClass().getInterfaces();
		for(Class cl:classes)
		{
			System.out.println(cl.getCanonicalName());
		}
		System.out.println(Proxy.getInvocationHandler(f).getClass().getInterfaces().getClass());
		

	}
	
	

}