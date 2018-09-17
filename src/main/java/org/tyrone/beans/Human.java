package org.tyrone.beans;

import org.tyrone.beans.annotations.Bean;
import org.tyrone.beans.annotations.Default;

@Bean(name="Human")
public class Human {
	
	@Default(value="xiaoming")
	private String name;
	
	@Default(value="Female")
	private String gendor;

	public String getGendor() {
		return gendor;
	}

	public void setGendor(String gendor) {
		this.gendor = gendor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Human [name=" + name + ", gendor=" + gendor + "]";
	}

}
