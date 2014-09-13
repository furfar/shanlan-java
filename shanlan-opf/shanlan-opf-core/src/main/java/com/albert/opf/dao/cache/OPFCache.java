/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.dao.cache;

import org.springframework.security.core.userdetails.UserCache;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:OPFCache 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-1-20 下午8:46:16
 *
 * @Remarks:
 * @Version:V1.1
 */
public interface OPFCache extends UserCache{

	/**
	 * @param serviceName
	 * @return
	 */
	Service getServiceFromCahce(String serviceName);

	/**
	 * @param service
	 */
	void putServiceInCache(Service service);

}
