package org.tyrone.aop;


import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

public class Proxy {

	
	public void keepfield(Field[] fields) {
		for(Field field:fields) {
			
		}
		
	}
	
	public static Object newInstance(Class<?> beanClass,Advice advice) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanClass);
		
		enhancer.setCallback(new Pointcut(advice));
		return enhancer.create();

	}
}
