/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.dao;

import java.util.List;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:ServiceDao 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-2-4 下午9:48:04
 *
 * @Remarks:
 * @Version:V1.1
 */
public interface ServiceDao {

	/**
	 * @param serviceName
	 * @return
	 */
	List<Service> getServicesByServiceNameAndVersion(String serviceName,String version);

}
