/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;


/**
 * @ClassName:InvokeTimesAndFrequencyInterceptor
 * @Description: TODO
 * @Author Albert
 * @Date:2013-4-4 下午12:45:54
 * 
 * @Remarks:
 * @Version:V1.1
 */
public interface InvokeTimesAndFrequencyService {

	/**
	 * 计算服务被调用的次数
	 * 
	 * @param key
	 */
	void caculateInvokeTimes(String key);

	/**
	 * 判断某个应用的服务调用次数是否已经超限
	 * 
	 * @param key
	 * @return
	 */
	boolean isAppInvokeTimesExceed(String key);

	/**
	 * 判断某个应用的服务调用频率是否已经超限
	 * 
	 * @param key
	 * @return
	 */
	boolean isAppInvokeFrequencyExceed(String key);

}
