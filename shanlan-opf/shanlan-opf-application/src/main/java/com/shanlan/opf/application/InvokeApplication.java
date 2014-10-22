package com.shanlan.opf.application;

import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.RequestDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;


/**
 * @ClassName:InvokeApplication 
 * @Description: 	
 * @Author Albert
 * @Date:2013-1-21 上午8:36:44
 *
 * @Remarks:
 * @Version:V1.1
 */
public interface InvokeApplication {
	/**调用远程服务
	 * @param serviceURL		远程服务的URL
	 * @param param				传入该服务所需的参数
	 * @return
	 */
	SuccessResponseDTO invokeRemoteService(String serviceURL,String param);
	

	/**调用本地服务
	 * @param param		传入该服务的参数
	 * @return
	 * @throws Exception 
	 */
	SuccessResponseDTO invokeLocalService(String service,String param) throws Exception;


    public BaseResponseDTO invokeService(String request, String method) ;
}
