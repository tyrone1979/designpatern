package org.tyrone.beans;

import org.tyrone.beans.annotations.Bean;
import org.tyrone.beans.annotations.Instance;

@Bean(name="Og")
public class Oganic {

	@Instance
	private Human oneHuman;
	
	public String getThisHumanName() {
		return oneHuman.getName();
	}
}
