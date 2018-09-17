package org.tyrone.patterns.factorypattern;

/**
 * This is a interface for Factory
 * @author leizhao
 *
 */
public interface BeanFactory {

	public Object getBean(String name);
	
	public Object[] getAllBeans();
	
	
}
