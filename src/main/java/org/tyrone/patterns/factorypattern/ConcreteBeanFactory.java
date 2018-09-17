package org.tyrone.patterns.factorypattern;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tyrone.beans.utils.BeanInstanceUtil;
/**
 * A bean factory to load all declared bean under a particular package
 * 
 * @author leizhao
 *
 */
public class ConcreteBeanFactory implements BeanFactory {

	private String beanPackageName = "org.tyrone.beans";
	/**
	 * by default, load org.tyrone.beans
	 */
	public ConcreteBeanFactory() {
		this.loadAllClasses(beanPackageName);
	}

	public String getBeanPackageName() {
		return beanPackageName;
	}

	public void setBeanPackageName(String beanPackageName) {
		this.beanPackageName = beanPackageName;
	}

	private final Map<String, Object> beans = new ConcurrentHashMap<String, Object>(8);

	@Override
	public Object getBean(String name) {
		return beans.get(name);
	}

	@Override
	public Object[] getAllBeans() {
		return beans.values().toArray();
	}

	/**
	 * Scan a directory with package name
	 * load all class with @Bean
	 * @param pckgname
	 */
	public void loadAllClasses(String pckgname) {
		try {
			// Get a File object for the package
			File directory = null;
			try {
				directory = new File(Thread.currentThread().getContextClassLoader()
						.getResource(pckgname.replace('.', '/')).getFile());
			} catch (NullPointerException x) {
				System.out.println("Nullpointer");
				throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
			}
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();
				for (String file:files) {
					// we are only interested in .class files
					if (file.endsWith(".class")) {
						// removes the .class extension
						Class<?> bean=Class.forName(pckgname + '.' + file.substring(0, file.length() - 6));
						String name=BeanInstanceUtil.getBeanName(bean);
						if(name!=null) {
							Object instance =BeanInstanceUtil.instanceBean(bean);
							if(instance!=null)
								beans.put(name, instance);
						}
					}
				}
			} else {
				System.out.println("Directory does not exist");
				throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
