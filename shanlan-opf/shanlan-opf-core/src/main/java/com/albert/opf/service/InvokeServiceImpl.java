/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.model.domain.response.SuccessResponse;
import com.shanlan.common.domain.User;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.JsonUtil;

/**
 * @ClassName:InvokeServiceImpl
 * @Description: 服务调用实现类
 * @Author Albert
 * @Date:2013-1-21 上午8:39:11
 * 
 * @Remarks:
 * @Version:V1.1
 */
@Service
public class InvokeServiceImpl implements InvokeService {

	private static final Logger logger = Logger
			.getLogger(InvokeServiceImpl.class);

	private HttpClient httpClient;

	// private static final String CURRENT_PACKAGE_PATH =
	// "com.albert.ofp.service";

	private UserService userService;

	@Override
	public SuccessResponse invokeRemoteService(String serviceURI, String param) {

		return getResponse(serviceURI, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.albert.opf.service.InvokeService#invokeLocalService(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SuccessResponse invokeLocalService(String service, String param)
			throws OPFBaseException {

		// String[] serviceSplit = service.split(ConstantPunctuation.PERIOD);
		// String className = serviceSplit[0];
		// String methodName = serviceSplit[1];
		// Class clazz = Class.forName(CURRENT_PACKAGE_PATH + className);
		// Class[] parameterTypes = null;
		// Method method = clazz.getMethod(methodName, parameterTypes);
		// String responseString = (String) method.invoke(null, null);

		Map<String, String> paramMap = JsonUtil.foJson(param,
				new TypeReference<Map<String, String>>() {
				});

		if (service.equals("User.login")) {
			User existUser = userService.login(paramMap.get("userName"),
					paramMap.get("password"));
			return new SuccessResponse(JsonUtil.toJson(existUser));
		} else if (service.equals("User.register")) {
			User user = new User(paramMap.get("userName"),
					paramMap.get("password"), paramMap.get("nickName"),
					paramMap.get("email"), paramMap.get("city"),
					Boolean.parseBoolean(paramMap.get("isValid")));
			boolean result = userService.register(user);

			return new SuccessResponse(JsonUtil.toJson(result));
		}
		return new SuccessResponse();

	}

	private SuccessResponse getResponse(String URI, String param) {

		SuccessResponse successResponse = new SuccessResponse();

		// 创建httppost
		HttpPost httppost = new HttpPost(URI);
		// 创建参数队列
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair(OPFConstants.PARAM, param));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(postParams, OPFConstants.UTF8);
			httppost.setEntity(uefEntity);
			if (logger.isDebugEnabled()) {
				logger.debug("executing request " + httppost.getURI());
			}
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {

				String responseString = EntityUtils.toString(entity,
						OPFConstants.UTF8);

				if (logger.isDebugEnabled()) {
					logger.debug("Response content: " + responseString);
				}

				successResponse = JSONObject.parseObject(responseString,
						SuccessResponse.class);
			}
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			httpClient.getConnectionManager().shutdown();
		}

		return successResponse;
	}

	/**
	 * @param httpClient
	 *            the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
