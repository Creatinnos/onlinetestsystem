package com.creatinnos.onlinetestsystem.bo;
 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
 
public class JdkProxyDemo {
 
    interface If {
        void originalMethod(String s);
        String getOriginalMethod();
    }
 

 
    static class Handler implements InvocationHandler {
        private final Object original;
 
        public Handler(Object object) {
            this.original = object;
        }
 
        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
            System.out.println("BEFORE");
            method.invoke(original, args);
            System.out.println("AFTER");
            return null;
        }
    }
 
    public static void main(String[] args){
        Handler handler = new Handler(new Object());
        If f = (If) Proxy.newProxyInstance(If.class.getClassLoader(),
                new Class[] { If.class },
                handler);
        f.originalMethod("Hallo");
        
        If f2 = (If) Proxy.newProxyInstance(If.class.getClassLoader(),
                new Class[] { If.class },
                handler);
        f2.originalMethod("Halllllo");
        System.out.println(f.getOriginalMethod());
    }
 
}