/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.exception.RequestAuthenticationException;
import com.albert.opf.common.exception.RequestCheckingException;
import com.albert.opf.common.exception.ServiceDisableException;
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
	public User login(String userName, String password) throws OPFBaseException {

		List<User> users = daoFacade.getUserDao().getUserByUserName(userName);
		if (CollectionUtils.isNotEmpty(users)) {
			User user = users.get(0);
			try {
				String md5Password = SignUtils.getMD5DigestInString(password);
				String userPassword = user.getPassword();
				if (!userPassword.equals(md5Password)) {// 如果密码不匹配
					throw new RequestAuthenticationException();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return user;
		}
		return null;
	}

	@Override
	public boolean register(User user) {
		if (user != null) {
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

	public DaoFacade getDaoFacade() {
		return daoFacade;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

}
