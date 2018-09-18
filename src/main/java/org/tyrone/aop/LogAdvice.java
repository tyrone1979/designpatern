package org.tyrone.aop;

import org.tyrone.beans.annotations.PointCut;

public class LogAdvice implements Advice {

	@PointCut(scope="*",position="before")
	public void beforeExecution() {
		System.out.println("started:"+System.currentTimeMillis());
	}
	
	@PointCut(scope="*",position="after")
	public void afterExecution() {
		System.out.println("finished:"+System.currentTimeMillis());
	}
	
	
}
