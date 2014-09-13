/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:OPDaoFAuthenticationProvider
 * @Description: TODO
 * @Author Albert
 * @Date:2013-2-3 下午9:31:05
 * 
 * @Remarks:
 * @Version:V1.1
 */
// @Component
public class OPFDaoAuthenticationProvider extends
		OPFAbstractAuthenticationProvider {

	@Autowired
	private DaoFacade daoFacade;
	
	private static final Logger logger = Logger.getLogger(OPFDaoAuthenticationProvider.class);

	@Override
	protected Service getServiceByServiceNameAndVersionFromDB(
			String serviceName, String version) {

		List<Service> services = daoFacade.serviceDao
				.getServicesByServiceNameAndVersion(serviceName, version);

		if (services.size() == 0) {
			logger.debug("Query returned no results for service '"
					+ serviceName + "'");

		}

		Service service = services.get(0);

		return service;
	}

}
