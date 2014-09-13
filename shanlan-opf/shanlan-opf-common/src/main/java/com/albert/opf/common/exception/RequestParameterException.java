/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.common.exception;

/**
 * 
 * 
 * @ClassName:RequestParameterException
 * @Description: 请求参数出现异常，包括细分为：格式异常、映射异常
 * @Author Albert
 * @Date:2013-1-18 上午11:12:14
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class RequestParameterException extends OPFBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -412139754163211866L;

	/**
	 * 
	 */
	public RequestParameterException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param throwable
	 */
	public RequestParameterException(String msg, Throwable throwable) {
		super(msg, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public RequestParameterException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param throwable
	 */
	public RequestParameterException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
