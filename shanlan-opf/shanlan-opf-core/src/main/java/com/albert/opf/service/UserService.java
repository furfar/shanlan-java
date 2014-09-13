/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.exception.RequestCheckingException;
import com.albert.opf.common.model.domain.Service;
import com.albert.opf.common.model.domain.request.Request;

/**
 * @ClassName:CheckingService
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 下午3:16:10
 * 
 * @Remarks:
 * @Version:V1.1
 */
public interface UserService {

	/**
	 * 检查用户的身份和权限
	 * 
	 * @param baseRequest
	 * @return
	 * @throws RequestCheckingException
	 */
	boolean checkUser(Request request) throws RequestCheckingException;

	/**
	 * @param key
	 * @param secret
	 * @return
	 */
	boolean checkUser(String key, String secret,String serviceName,String version)
			throws RequestCheckingException;

	/**
	 * 验证用户并获取调用服务的URL
	 * 
	 * @param service
	 * @param v
	 * @return
	 * @throws RequestCheckingException
	 */
	Service getServiceByServiceNameAndVersion(String service, String v)
			throws OPFBaseException;

}
