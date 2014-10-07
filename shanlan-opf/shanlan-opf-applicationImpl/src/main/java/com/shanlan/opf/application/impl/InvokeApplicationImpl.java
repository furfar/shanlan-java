package com.shanlan.opf.application.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.shanlanopf.infra.InvokeHelper;

/**
 * @ClassName:InvokeApplicationImpl
 * @Description: 服务调用实现类
 * @Author Albert
 * @Date:2013-1-21 上午8:39:11
 * 
 * @Remarks:
 * @Version:V1.1
 */
@Service
public class InvokeApplicationImpl implements InvokeApplication {

	private static final Logger logger = Logger
			.getLogger(InvokeApplicationImpl.class);
	
	@Inject
	private InvokeHelper invokeHelper;
	
//	private UserService userService;

	@Override
	public SuccessResponseDTO invokeRemoteService(String serviceURI, String param) {

		return InvokeHelper.getResponse(serviceURI, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.albert.opf.service.InvokeService#invokeLocalService(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SuccessResponseDTO invokeLocalService(String service, String param)
            throws OPFBaseException {

		// String[] serviceSplit = service.split(ConstantPunctuation.PERIOD);
		// String className = serviceSplit[0];
		// String methodName = serviceSplit[1];
		// Class clazz = Class.forName(CURRENT_PACKAGE_PATH + className);
		// Class[] parameterTypes = null;
		// Method method = clazz.getMethod(methodName, parameterTypes);
		// String responseString = (String) method.invoke(null, null);

//		Map<String, String> paramMap = JsonUtil.foJson(param,
//				new TypeReference<Map<String, String>>() {
//				});

//		if (service.equals("User.login")) {
//			User existUser = userService.login(paramMap.get("userName"),
//					paramMap.get("password"));
//			return new SuccessResponseDTO(JsonUtil.toJson(existUser));
//		} else if (service.equals("User.register")) {
//			User user = new User(paramMap.get("userName"),
//					paramMap.get("password"), paramMap.get("nickName"),
//					paramMap.get("email"), paramMap.get("city"),
//					Boolean.parseBoolean(paramMap.get("isValid")));
//			boolean result = userService.register(user);

//			return new SuccessResponseDTO(JsonUtil.toJson(true));
//		}
		return new SuccessResponseDTO();

	}


}
