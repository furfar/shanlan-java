/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.shanlan.common.exception.sub.business;

/**
 * @ClassName:RequestAuthenticationException 
 * @Description: 身份验证异常		
 * @Author Albert
 * @Date:2013-1-20 下午7:15:07
 *
 * @Remarks:
 * @Version:V1.1
 */
public class RequestAuthenticationException extends RequestCheckingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633852296712491459L;

	/**
	 * 身份验证异常
	 */
	public RequestAuthenticationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param throwable
	 */
	public RequestAuthenticationException(String msg, Throwable throwable) {
		super(msg, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public RequestAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public RequestAuthenticationException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
