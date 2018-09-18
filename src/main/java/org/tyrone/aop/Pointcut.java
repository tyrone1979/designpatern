package org.tyrone.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


import org.tyrone.beans.annotations.PointCut;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Pointcut implements MethodInterceptor {

	private Advice advice;
	
	public Pointcut(Advice advice) {
		this.advice=advice;
	}
	
	
	private String getScope(Method method) {
			PointCut pc=method.getAnnotation(PointCut.class);
			return pc.scope();
	}
	
	private String getPosition(Method method) {
		PointCut pc=method.getAnnotation(PointCut.class);
		return pc.position();
	}
	
	
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		Method[] methods=advice.getClass().getDeclaredMethods();
		Method beforeMethod=null;
		Method afterMethod=null;
		
		for(Method me: methods) {
			String scope=getScope(me);
			if(scope.equals("*")) {
				//all methods
				String position=getPosition(me);
				if(position.equals("before")) {
					beforeMethod=me;
				}else if(position.equals("after")) {
					afterMethod=me;
				}
			}
		}
		if(beforeMethod!=null)
			beforeMethod.invoke(advice, null);
		Object re =proxy.invokeSuper(obj, args);
		if(afterMethod!=null)
			afterMethod.invoke(advice, null);
		return re;
	}

}
