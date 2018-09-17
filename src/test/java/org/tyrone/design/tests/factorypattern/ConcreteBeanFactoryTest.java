package org.tyrone.design.tests.factorypattern;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tyrone.beans.Human;
import org.tyrone.beans.Oganic;
import org.tyrone.patterns.factorypattern.BeanFactory;
import org.tyrone.patterns.factorypattern.ConcreteBeanFactory;

public class ConcreteBeanFactoryTest {

	@Test
	public void testFactory() {
		BeanFactory factory=new ConcreteBeanFactory();
		Human oneHuman=(Human)factory.getBean("Human");
		assertEquals("xiaoming", oneHuman.getName());
		assertEquals("Female",oneHuman.getGendor());
		System.out.println(oneHuman.toString());
	}
	
	@Test
	public void testInstance() {
		BeanFactory factory=new ConcreteBeanFactory();
		Oganic og=(Oganic)factory.getBean("Og");		
		assertEquals("xiaoming", og.getThisHumanName());
	}
	

}
