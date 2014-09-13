/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.dao.cache;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:OPFCacheImpl
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 下午9:10:12
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class OPFCacheImpl extends EhCacheBasedUserCache implements OPFCache {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OPFCacheImpl.class);

	@Override
	public Service getServiceFromCahce(String key) {

		Element element = getCache().get(key);

		if (element == null) {
			return null;
		} else {

			if (logger.isDebugEnabled()) {
				logger.debug("Cache hit: " + (element != null) + "; key: "
						+ key);
			}

			return (Service) element.getValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.albert.opf.dao.cache.OPFCache#putServiceInCache(com.albert.opf.model
	 * .domain.Service)
	 */
	@Override
	public void putServiceInCache(Service service) {
		Element element = new Element(service.getServiceName()
				+ service.getVersion(), service);

		super.getCache().put(element);

		if (logger.isDebugEnabled()) {
			logger.debug("Cache put: " + element.getKey());
		}
	}

}
