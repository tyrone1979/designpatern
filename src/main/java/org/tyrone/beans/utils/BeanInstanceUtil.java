package org.tyrone.beans.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.tyrone.aop.LogAdvice;
import org.tyrone.aop.Proxy;
import org.tyrone.beans.annotations.Bean;
import org.tyrone.beans.annotations.Default;
import org.tyrone.beans.annotations.Instance;

/**
 * An utility class to instance a bean with default value
 * 
 * @author leizhao
 *
 */
public class BeanInstanceUtil {

	/**
	 * Give a class name, check if it is a bean. If it is, return the bean name else
	 * return null
	 * 
	 * @param candidate class name
	 * @return bean name
	 */
	public static String getBeanName(Class<?> candidate) {
		Annotation annotate = candidate.getAnnotation(Bean.class);
		if (annotate != null) {
			return ((Bean) annotate).name();
		}
		return null;
	}

	/**
	 * Instance a bean by given a bean class with default value annotated.
	 * 
	 * @param bean
	 * @return an instance
	 */
	public static Object instanceBean(Class<?> bean) {
		Object instance;
		try {
			instance = bean.newInstance();
			instanceBeanWithBean(instance);
			setDefaultVluae(instance);
			return instance;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static Map<String,Class> getAnnotatedField(Class<?> bean) {
		Field[] fields = bean.getDeclaredFields();
		Map<String,Class> fieldswithAnno=new HashMap<String,Class>();
		for (Field field : fields) {
			if (field.getAnnotation(Instance.class) != null) {
				fieldswithAnno.put(field.getName(), Instance.class);
			}
		}
		return fieldswithAnno;
		
		
	}

	private static void instanceBeanWithBean(Object parent) {
		Field[] fields = parent.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Instance.class) != null) {
				// instance this field
					//Object child = Proxy.newInstance(field.getType(), new LogAdvice());
					try {
						Object child=field.getType().newInstance();
						field.setAccessible(true);
						field.set(parent, child);
						instanceBeanWithBean(child);
						setDefaultVluae(child);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}
	}

	private static void setDefaultVluae(Object instance) {
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			Default defaultvalue = field.getAnnotation(Default.class);
			if (defaultvalue != null) {
				String value = defaultvalue.value();
				field.setAccessible(true);
				try {
					field.set(instance, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
