package org.tyrone.beans.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

		@SuppressWarnings("rawtypes")
		Constructor[] ctors = bean.getDeclaredConstructors();
		for (Constructor<?> ctor : ctors) {
			if (ctor.getGenericParameterTypes().length == 0) {
				try {
					Object instance = ctor.newInstance();
					instanceBeanWithBean(instance);
					setDefaultVluae(instance);
					return instance;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	private static void instanceBeanWithBean(Object parent) {
		Field[] fields = parent.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Instance.class) != null) {
				// instance this field
				Constructor[] ctors = field.getType().getDeclaredConstructors();
				for (Constructor ctor : ctors) {
					if (ctor.getGenericParameterTypes().length == 0) {
						try {
							Object child = ctor.newInstance();
							field.setAccessible(true);
							field.set(parent, child);
							instanceBeanWithBean(child);
							setDefaultVluae(child);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
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
