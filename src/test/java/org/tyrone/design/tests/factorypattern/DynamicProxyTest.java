package org.tyrone.design.tests.factorypattern;



import org.junit.Test;
import org.tyrone.aop.LogAdvice;
import org.tyrone.aop.Proxy;
import org.tyrone.beans.Human;

public class DynamicProxyTest {
	
	@Test
	public void cglibTest() {
		Human man=(Human) Proxy.newInstance(Human.class,new LogAdvice());
		System.out.println(man.toString());
		
	}

}
