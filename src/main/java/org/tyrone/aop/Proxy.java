package org.tyrone.aop;

import org.tyrone.beans.utils.BeanInstanceUtil;
import net.sf.cglib.proxy.Enhancer;

public class Proxy {
	
	
	public static Object newInstance(Class<?> beanClass,Advice advice) {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanClass);		
		enhancer.setCallback(new Pointcut(advice));
		Object proxy= enhancer.create();
		//get annotated field and instance it.
		proxy=BeanInstanceUtil.instanceBeanWithBean(proxy.getClass().getSuperclass(),proxy);
		return proxy;

	}
}
