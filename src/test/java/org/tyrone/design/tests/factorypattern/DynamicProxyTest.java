package org.tyrone.design.tests.factorypattern;



import org.junit.Test;
import org.tyrone.aop.LogAdvice;
import org.tyrone.aop.Proxy;
import org.tyrone.beans.Human;
import org.tyrone.beans.Oganic;
import org.tyrone.patterns.factorypattern.BeanFactory;
import org.tyrone.patterns.factorypattern.ConcreteBeanFactory;

public class DynamicProxyTest {
	
	@Test
	public void cglibTest() {
		Oganic man=(Oganic) Proxy.newInstance(Oganic.class,new LogAdvice());
		man.setThisHumanName("Tom");
		System.out.println(man.getThisHumanName());
		
	}

}
