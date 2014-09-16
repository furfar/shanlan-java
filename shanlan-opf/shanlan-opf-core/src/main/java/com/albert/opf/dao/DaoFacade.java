/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.dao;


/**
 * @ClassName:DAOFacade
 * @Description: TODO
 * @Author Albert
 * @Date:2013-2-3 下午8:09:32
 *
 * @Remarks:
 * @Version:V1.1
 */
public class DaoFacade {

	private ServiceDao serviceDao;
	
	private UserDao userDao;

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
}
