/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.interceptor;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.albert.opf.service.InvokeTimesAndFrequencyService;

/**
 * @ClassName:InvokeTimesAndFrequencyInterceptor
 * @Description: TODO
 * @Author Albert
 * @Date:2013-4-4 下午12:45:54
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class InvokeTimesAndFrequencyInterceptor implements AfterReturningAdvice {

	@Autowired
	private InvokeTimesAndFrequencyService invokeTimesAndFrequency;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang
	 * .Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {

		String key = (String) arg2[0];

		invokeTimesAndFrequency.caculateInvokeTimes(key);

		invokeTimesAndFrequency.isAppInvokeFrequencyExceed(key);

		invokeTimesAndFrequency.isAppInvokeTimesExceed(key);
	}

}
