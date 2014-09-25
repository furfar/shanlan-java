/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.shanlan.common.constant.ConstantRegex;
import com.shanlan.common.exception.sub.business.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.albert.opf.common.model.domain.Service;
import com.albert.opf.common.utils.SignUtils;
import com.albert.opf.dao.DaoFacade;
import com.shanlan.common.domain.User;

//import org.springframework.security.core.userdetails.User;

/**
 * @ClassName:CheckingService
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 3:14:41
 * 
 * @Remarks:
 * @Version:V1.1
 */
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	private DaoFacade daoFacade;

	@Override
	public Service getServiceByServiceNameAndVersion(String serviceName,
			String version) throws OPFBaseException {

		Service service = new Service();

		List<Service> services = daoFacade.getServiceDao()
				.getServicesByServiceNameAndVersion(serviceName, version);
		if (CollectionUtils.isNotEmpty(services)) {
			service = services.get(0);
			if (service != null) {

				if (service.getEnable() != 1) {// 如果服务不可用
					throw new ServiceDisableException("the service '"
							+ serviceName + "' is disable");
				}

			} else {
				throw new RequestCheckingException("the service '"
						+ serviceName + "' is not exist");
			}
		}

		return service;
	}

	@Override
	public User login(String userAccount, String password) throws OPFBaseException {
        if (StringUtils.isNotBlank(userAccount)&& StringUtils.isNotBlank(password)){
            List<User> users=new ArrayList<User>();
            if (StringUtils.isNotBlank(userAccount) && userAccount.matches(ConstantRegex.REGEX_EMAIL)){//如果是Eamil
                users = daoFacade.getUserDao().getUserByEmail(userAccount);
            }else{
                users = daoFacade.getUserDao().getUserByUserName(userAccount);
            }
            RequestAuthenticationException requestAuthenticationException=new RequestAuthenticationException("username or password error, please check it.");
            if (CollectionUtils.isNotEmpty(users)) {
                User user = users.get(0);
                try {
                    String md5Password = SignUtils.getMD5DigestInString(password);
                    String userPassword = user.getPassword();
                    if (!userPassword.equals(md5Password)) {// 如果密码不匹配
                        throw requestAuthenticationException;
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                return user;
            }else {
                throw requestAuthenticationException;
            }
        }
        return null;
	}




	@Override
	public boolean register(User user) throws RequestParameterException{
		if (user != null) {

            if (isUserNameExist(user.getUserName())){
                throw new RequestParameterException("user name already exists");
            }else if(isEmailExist(user.getEmail())){
                throw new RequestParameterException("email already exists");
            }

			String md5Password;
			try {
				md5Password = SignUtils.getMD5DigestInString(user
						.getPassword());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return false;
			}
			user.setPassword(md5Password);
			return daoFacade.getUserDao().add(user);
		}
		return false;
	}

    /**
     * 判断用户账号（用户名或者Email）是否已经存在
     *
     * @param userName
     * @param email
     * @return
     */
    @Override
    public boolean isUserAccountExist(String userName, String email) {

        if(StringUtils.isNotBlank(userName)){
            return isUserNameExist(userName);
        }else if(StringUtils.isNotBlank(email)){
            return isEmailExist(email);
        }
        return false;
    }

    @Override
    public boolean isUserNameExist(String userName) {
        if(StringUtils.isNotBlank(userName)){
            List<User> users = daoFacade.getUserDao().getUserByUserName(userName);
            if (CollectionUtils.isNotEmpty(users)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmailExist(String email) {
        if(StringUtils.isNotBlank(email)) {
            List<User> users = daoFacade.getUserDao().getUserByEmail(email);
            if (CollectionUtils.isNotEmpty(users)) {
                return true;
            }
        }
        return false;
    }

    public DaoFacade getDaoFacade() {
		return daoFacade;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

}
