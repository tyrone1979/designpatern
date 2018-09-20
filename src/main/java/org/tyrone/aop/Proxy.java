package org.tyrone.aop;


import java.lang.reflect.Field;
import java.util.Map;

import org.tyrone.beans.annotations.Instance;
import org.tyrone.beans.utils.BeanInstanceUtil;

import net.sf.cglib.proxy.Enhancer;

public class Proxy {
	
	
	public static Object newInstance(Class<?> beanClass,Advice advice) {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanClass);		
		enhancer.setCallback(new Pointcut(advice));
		Object proxy= enhancer.create();
		Map<String,Class> fieldswithAnno=BeanInstanceUtil.getAnnotatedField(beanClass);
		Field[] fields=proxy.getClass().getSuperclass().getDeclaredFields();
		for(Field field: fields) {
			String name=field.getName();
			Class anno=fieldswithAnno.get(name);
			if(anno!=null)
				if(anno == Instance.class) {
					try {
						field.setAccessible(true);
						Object obj=field.getType().newInstance();
						field.set(proxy, obj);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		return proxy;

	}
}
