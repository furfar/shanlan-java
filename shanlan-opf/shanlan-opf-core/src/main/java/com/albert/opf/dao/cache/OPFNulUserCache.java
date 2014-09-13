/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.dao.cache;

import org.springframework.security.core.userdetails.UserDetails;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:OPFNulUserCache 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-1-20 下午9:22:58
 *
 * @Remarks:
 * @Version:V1.1
 */
public class OPFNulUserCache implements OPFCache {

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserCache#getUserFromCache(java.lang.String)
	 */
	@Override
	public UserDetails getUserFromCache(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserCache#putUserInCache(org.springframework.security.core.userdetails.UserDetails)
	 */
	@Override
	public void putUserInCache(UserDetails user) {


	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserCache#removeUserFromCache(java.lang.String)
	 */
	@Override
	public void removeUserFromCache(String username) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.albert.opf.service.dao.cache.OPFCache#getServiceByServiceNameFromCahce(java.lang.String)
	 */
	@Override
	public Service getServiceFromCahce(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.albert.opf.dao.cache.OPFCache#putServiceInCache(com.albert.opf.model.domain.Service)
	 */
	@Override
	public void putServiceInCache(Service service) {
		// TODO Auto-generated method stub
		
	}

}
