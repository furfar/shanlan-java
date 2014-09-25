/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import com.albert.opf.common.model.domain.Service;
import com.shanlan.common.domain.User;
import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
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


    User login(String userName,String password) throws OPFBaseException, OPFBaseException;


    boolean register(User user) throws RequestParameterException;

    /**
     * 判断用户账号（用户名或者Email）是否已经存在
     * @param userName
     * @param email
     * @return
     */
    boolean isUserAccountExist(String userName,String email);


    boolean isUserNameExist(String userName);

    boolean isEmailExist(String email);
}
