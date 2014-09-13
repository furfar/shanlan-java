/**
 * 
 */
package com.albert.opf.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lei.panglei
 * 
 */
public class SpringObjectFactory {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";

	public static ApplicationContext context;

	private SpringObjectFactory() {
	}

	public synchronized static Object getBean(String name) {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					APPLICATION_CONTEXT_XML);
		}
		return context.getBean(name);
	}

	public synchronized static void setContext(
			ApplicationContext webApplicationContext) {
		context = webApplicationContext;
	}
}
