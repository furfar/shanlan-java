/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.client;

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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.model.domain.request.Request;
import com.albert.opf.common.model.domain.response.ErrorResponse;
import com.albert.opf.common.model.domain.response.SuccessResponse;
import com.albert.opf.common.utils.JsonUtil;
import com.albert.opf.common.utils.SignUtils;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName:OPFClient
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-18 下午4:20:15
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class OPFClientHTTP {

	private static final Logger logger = Logger.getLogger(OPFClientHTTP.class);

	public static void main(String[] args) {

		Request request = new Request();
		request.setKey("app1");
		request.setSecret("secret1");
		request.setService("phone.brand.get");
		request.setParam("c8812");

		SuccessResponse successResponse = null;
		try {
			successResponse = OPFClientHTTP.getResponse(request);
		} catch (OPFBaseException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(successResponse.getBusinessResult());
	}

	public static SuccessResponse getResponse(Request request)
			throws OPFBaseException {
		SuccessResponse successResponse = new SuccessResponse();
		if (request != null) {

			String responseString = null;

			// 计算sign
			Map<String, String> paramValues = SignUtils.getParamMap(request);
			String sign = SignUtils.sign(paramValues, request.getSecret());
			request.setSign(sign);
			// 将请求参数转换成JSON字符串
			String paramString = JsonUtil.toJson(request);
			// 创建httppost
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(OPFConstants.OPF_URI_HTTP);
//			HttpPost httppost = new HttpPost("http://localhost:8088/opf.manager/user.get");
			// 创建参数队列
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(OPFConstants.REQUEST,
					paramString));
//			postParams.add(new BasicNameValuePair("param",
//					"app1"));
			
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
					throw new OPFBaseException(errorResponse.getErr_info());
				}
			}
		}

		return successResponse;
	}
}
