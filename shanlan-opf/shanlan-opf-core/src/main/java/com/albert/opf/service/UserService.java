/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.exception.RequestCheckingException;
import com.albert.opf.common.model.domain.Service;
import com.shanlan.common.domain.User;
//import org.openkoala.auth.application.vo.UserVO;

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
	 * 验证用户并获取调用服务的URL
	 * 
	 * @param service
	 * @param v
	 * @return
	 * @throws RequestCheckingException
	 */
	Service getServiceByServiceNameAndVersion(String service, String v)
			throws OPFBaseException;


    User login(String userName,String password) throws OPFBaseException;


    boolean register(User user);
}
