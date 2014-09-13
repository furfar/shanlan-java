/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.manager.service;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @ClassName:UserServiceImpl
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 3:14:41
 * 
 * @Remarks:
 * @Version:V1.1
 */
@Service
public class UserServiceImpl implements UserService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	private UserDetailsManager userDetailsManager; 

	private UserCache userCache;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.albert.opf.manager.service.UserService#getUserByUserName(java.lang
	 * .String)
	 */
	@Override
	public UserDetails getUserByUsername(String username) {

		UserDetails userDetails = null;

		userDetails = userCache.getUserFromCache(username);

		if (userDetails != null) {
			if (logger.isInfoEnabled()) {
				logger.info("getUserByUsername(String) - get userdetails from cache");
			}
			return userDetails;
		} else {
			userDetails = userDetailsManager.loadUserByUsername(username);
			userCache.putUserInCache(userDetails);
			if (logger.isInfoEnabled()) {
				logger.info("getUserByUsername(String) - get userdetails from db");
			}
			return userDetails;
		}

	}

	/**
	 * @param userDetailsManager
	 *            the userDetailsManager to set
	 */
	public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}

	/**
	 * @param userCache
	 *            the userCache to set
	 */
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

}
