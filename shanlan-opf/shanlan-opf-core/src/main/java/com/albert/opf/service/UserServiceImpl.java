/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.exception.RequestAuthenticationException;
import com.albert.opf.common.exception.RequestAuthorizationException;
import com.albert.opf.common.exception.RequestCheckingException;
import com.albert.opf.common.exception.ServiceDisableException;
import com.albert.opf.common.model.domain.Service;
import com.albert.opf.common.model.domain.request.Request;
import com.albert.opf.common.model.domain.response.ErrorResponse;
import com.albert.opf.common.model.domain.response.SuccessResponse;
import com.albert.opf.common.utils.SignUtils;
import com.albert.opf.dao.OPFAbstractAuthenticationProvider;
import com.albert.opf.model.domain.User;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName:CheckingService
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 3:14:41
 * 
 * @Remarks:
 * @Version:V1.1
 */
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	// private RestTemplate restTemplate;

	private HttpClient httpClient;

	private UserCache userCache;

	// @Autowired
	private OPFAbstractAuthenticationProvider opfDaoAuthenticationProvider;

	@Override
	public Service getServiceByServiceNameAndVersion(String serviceName,
			String version) throws OPFBaseException{

		Service service = opfDaoAuthenticationProvider
				.getServiceByServiceNameAndVersion(serviceName, version);

		if (service != null) {

			if (service.getEnable() != 1) {// 如果服务不可用
				throw new ServiceDisableException("the service '"+ serviceName + "' is disable");
			}

		} else {
			throw new RequestCheckingException("the service '" + serviceName
					+ "' is not exist");
		}
		return service;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.albert.opf.service.CheckingService#checkUser(com.albert.opf.model
	 * .domain.request.BaseRequest)
	 */
	@Override
	public boolean checkUser(Request request) throws RequestCheckingException {

		Assert.notNull(request, "baseRequest cannot be null");

		String password = getPasswordByUsername(request.getKey());

		if (password != null) {

			Map<String, String> paramValues = SignUtils.getParamMap(request);

			String serverSign = SignUtils.sign(paramValues, password);

			if (serverSign.equals(request.getSign())) {
				return true;
			}

		}

		return true;
	}

	/**
	 * @param opfDaoAuthenticationProvider
	 *            the opfDaoAuthenticationProvider to set
	 */
	public void setOpfDaoAuthenticationProvider(
			OPFAbstractAuthenticationProvider opfDaoAuthenticationProvider) {
		this.opfDaoAuthenticationProvider = opfDaoAuthenticationProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.albert.opf.service.UserService#checkUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean checkUser(String username, String password,
			String serviceName, String version) throws RequestCheckingException {

		boolean result = false;

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				username, password);

		Authentication authenResult = null;
		try {

			authenResult = opfDaoAuthenticationProvider
					.authenticate(authentication);

		} catch (AuthenticationException e) {
			throw new RequestAuthenticationException();
		} finally {
		}

		if (authenResult != null && !StringUtils.isEmpty(serviceName)) {

			if (logger.isInfoEnabled()) {
				logger.info(username + " authentication success ");
			}

			Collection<? extends GrantedAuthority> authorities = authenResult
					.getAuthorities();

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
					serviceName + "_" + version);

			if (authorities.contains(authority)) {// 如果该用户的服务（权限）列表中含有需要调用的这个服务

				if (logger.isInfoEnabled()) {
					logger.info(username + " can call " + serviceName);
				}

				result = true;

			} else {

				throw new RequestAuthorizationException(
						"you do not have permission to call the '"
								+ serviceName
								+ "' service, or service name error. ");
			}

		} else {
			throw new RequestAuthenticationException();
		}

		return result;

	}

	private String getPasswordByUsername(String username)
			throws RequestCheckingException {
		String password = null;

		UserDetails userDetailsCache = userCache.getUserFromCache(username);

		if (userDetailsCache == null) {

			SuccessResponse successResponse = new SuccessResponse();
			String responseString = null;
			User user=new User();

			HttpPost httppost = new HttpPost(
					OPFConstants.OPF_MANAGER_URI_USER_GET);
			// 创建参数队列
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair("param", username));

			UrlEncodedFormEntity uefEntity;
			try {
				uefEntity = new UrlEncodedFormEntity(postParams,
						OPFConstants.UTF8);
				httppost.setEntity(uefEntity);
				if (logger.isDebugEnabled()) {
					logger.debug("executing request " + httppost.getURI());
				}
				HttpResponse response = httpClient.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseString = EntityUtils.toString(entity,
							OPFConstants.UTF8);
					if (logger.isDebugEnabled()) {
						logger.debug("Response content: " + responseString);
					}
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

			if (responseString != null) {
				try {
					successResponse = JSONObject.parseObject(responseString,
							SuccessResponse.class);
				} catch (JSONException e) {
					ErrorResponse errorResponse = JSONObject.parseObject(
							responseString, ErrorResponse.class);
					throw new RequestCheckingException(
							errorResponse.getErr_info());
				}

				// User user = JsonUtil.foJson(
				// successResponse.getBusinessResult(),
				// new TypeReference<User>() {
				// });

				user = JSONObject.parseObject(
						successResponse.getBusinessResult(), User.class);

				password = user.getPassword();
			}

			// SuccessResponse successResponse = restTemplate.postForObject(
			// OPFConstants.OPF_MANAGER_URI_USER_GET, null,
			// SuccessResponse.class, params);
			//
			// if (successResponse != null) {
			// UserDetails userDetails = JsonUtil.foJson(
			// successResponse.getBusinessResult(),
			// new TypeReference<UserDetails>() {
			// });
			// password = userDetails.getPassword();
			//
			userCache.putUserInCache(user);

		} else {
			password = userDetailsCache.getPassword();
		}

		return password;
	}

	/**
	 * @param restTemplate
	 *            the restTemplate to set
	 */
	// public void setRestTemplate(RestTemplate restTemplate) {
	// this.restTemplate = restTemplate;
	// }

	/**
	 * @param userCache
	 *            the userCache to set
	 */
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	/**
	 * @param httpClient
	 *            the httpClient to set
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

}
