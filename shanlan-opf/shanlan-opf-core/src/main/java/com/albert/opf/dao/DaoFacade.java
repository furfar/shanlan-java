/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:DAOFacade
 * @Description: TODO
 * @Author Albert
 * @Date:2013-2-3 下午8:09:32
 *
 * @Remarks:
 * @Version:V1.1
 */
@Repository
public class DaoFacade {

	private AbstractUserDetailsAuthenticationProvider daoAuthenticationProvider;

	@Autowired
	public ServiceDao serviceDao;

	/**
	 * @return the daoAuthenticationProvider
	 */
	public AbstractUserDetailsAuthenticationProvider getDaoAuthenticationProvider() {
		return daoAuthenticationProvider;
	}

	/**
	 * @param daoAuthenticationProvider the daoAuthenticationProvider to set
	 */
	public void setDaoAuthenticationProvider(
			AbstractUserDetailsAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}

}
