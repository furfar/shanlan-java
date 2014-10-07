package com.shanlan.opf.web.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shanlan.common.exception.sub.business.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.ReflectionUtils;
import com.shanlan.opf.core.domain.ErrorResponse;
import com.shanlan.opf.core.domain.Request;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:BaseCoontroller
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-18 上午11:27:57
 * 
 * @Remarks:
 * @Version:V1.1
 */
public abstract class AbstractBaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(AbstractBaseController.class);

	/**
	 * 解析并检查传入的请求参数
	 * 
	 * @param request
	 * @return
	 * @throws RequestParameterException
	 */
	protected Request parseRequestParameter(String request)
			throws RequestParameterException {

		Request requestObj = new Request();

		try {

			requestObj = JSONObject.parseObject(request, Request.class);

		} catch (JSONException e) {
			throw new RequestFormatException(e.getMessage());
		}

		List<Field> fields = ReflectionUtils
				.getSelfAndDirectParentDeclaredFields(requestObj);

		for (Field field : fields) {// 通过“反射”检查所有的字段是否成功完成映射

			field.setAccessible(true); // 要想访问private字段，需要先将其Accessible属性设置为true
			Object fieldValue = null;
			try {
				fieldValue = field.get(requestObj);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			}
			if (fieldValue == null) { // 如果字段的值为null，说明JSON到Java对象的映射过程出现异常
				String fieldName = field.getName();

				throw new RequestMappingException("json key '" + fieldName
						+ "' does not exist, plesse check it.");
			}

		}

		return requestObj;

	}

	protected ModelAndView handleException(Exception e) {

        ModelAndView resultMav = new ModelAndView("jsonView");

		if (e instanceof RequestFormatException) {
			ErrorResponse errorResponse = new ErrorResponse("101",
					e.getMessage());

            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;

		}

		if (e instanceof RequestMappingException) {

			ErrorResponse errorResponse = new ErrorResponse("201",
					e.getMessage());

            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;

		}

		if (e instanceof RequestAuthenticationException) {

			ErrorResponse errorResponse = new ErrorResponse("301",
					e.getMessage());

            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;
		}

		if (e instanceof RequestAuthorizationException) {

			ErrorResponse errorResponse = new ErrorResponse("401",
					e.getMessage());

            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;
		}

        if (e instanceof RequestParameterException){
            ErrorResponse errorResponse = new ErrorResponse("501",
                    e.getMessage());
            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;
        }

		if (e instanceof ServiceDisableException) {
			ErrorResponse errorResponse = new ErrorResponse("601",
					e.getMessage());

            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;
		}

        if (e instanceof RequestCheckingException){
            ErrorResponse errorResponse = new ErrorResponse("701",
                    e.getMessage());
            resultMav.addObject(ErrorResponse.ERROR_CODE_STRING,errorResponse.getCode());
            resultMav.addObject(ErrorResponse.ERROR_MESSAGE_STRING,
                    errorResponse.getMessage());
            return resultMav;
        }
		return resultMav;

	}

	/**
	 * 判断是否是HTTPS访问
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isHTTPSAccess(Request request) {

		if (request != null
				&& (!StringUtils.isEmpty(request.getSecret()) && StringUtils
						.isEmpty(request.getSign()))) {// 如果secret不为空,sign为空则是HTTPS访问
			return true;
		}

		return false;
	}

}
