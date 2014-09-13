/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.dao;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.albert.opf.common.model.domain.Service;
import com.albert.opf.dao.cache.OPFCache;
import com.albert.opf.dao.cache.OPFNulUserCache;

/**
 * @ClassName:OPFAuthenticationProviderImpl
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 下午9:04:34
 * 
 * @Remarks:
 * @Version:V1.1
 */
@Component
public abstract class OPFAbstractAuthenticationProvider extends
		DaoAuthenticationProvider {

	private OPFCache opfUserCache = new OPFNulUserCache();

	public Service getServiceByServiceNameAndVersion(String serviceName,
			String version) {

		Assert.notNull(serviceName, "serviceName cann't be null.");

		Service service = this.opfUserCache.getServiceFromCahce(serviceName
				+ version);

		if (true || service == null) {

			service = this.getServiceByServiceNameAndVersionFromDB(serviceName,
					version);

			this.opfUserCache.putServiceInCache(service);
		}

		return service;
	}

	protected abstract Service getServiceByServiceNameAndVersionFromDB(
			String serviceName, String version);

	/**
	 * @return the opfUserCache
	 */
	public OPFCache getOpfUserCache() {
		return opfUserCache;
	}

	/**
	 * @param opfUserCache
	 *            the opfUserCache to set
	 */
	public void setOpfUserCache(OPFCache opfUserCache) {
		this.opfUserCache = opfUserCache;
	}

}
