/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import com.albert.opf.common.model.domain.response.SuccessResponse;

/**
 * @ClassName:InvokeService 
 * @Description: 	
 * @Author Albert
 * @Date:2013-1-21 上午8:36:44
 *
 * @Remarks:
 * @Version:V1.1
 */
public interface InvokeService {
	/**调用远程服务
	 * @param serviceURL		远程服务的URL
	 * @param param				传入该服务所需的参数
	 * @return
	 */
	SuccessResponse invokeRemoteService(String serviceURL,String param);
	

	/**调用本地服务
	 * @param param		传入该服务的参数
	 * @return
	 * @throws Exception 
	 */
	SuccessResponse invokeLocalService(String service,String param) throws Exception;
}
