/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.model.domain.request.Request;
import com.albert.opf.common.model.domain.response.ErrorResponse;
import com.albert.opf.common.model.domain.response.SuccessResponse;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.JsonUtil;

/**
 * @ClassName:OPFClient
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-18 下午4:20:15
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class OPFClientHTTPS {

	private static final Logger logger = Logger.getLogger(OPFClientHTTPS.class);

	public static void main(String[] args) {

		Request request = new Request();
		request.setKey("app1");
		request.setSecret("secret1");
		request.setService("user.get");
		request.setParam("{\"username\":\"app1\",\"password\":\"secrect1\"}");

		SuccessResponse successResponse = null;
		try {
			successResponse = getResponse(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(successResponse.getBusinessResult());

	}

	public static SuccessResponse getResponse(Request request) throws Exception {

		SuccessResponse successResponse = new SuccessResponse();

		if (request != null) {
			HttpClient httpClient = new DefaultHttpClient();

			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			FileInputStream instream = new FileInputStream(new File(
					"tomcat.keystore"));
			try {
				// 加载keyStore tomcat.keystore
				trustStore.load(instream, "123456".toCharArray());
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}

			// 创建Socket工厂,将trustStore注入
			SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);

			// 创建Scheme
			Scheme sch = new Scheme("https", 8443, socketFactory);

			// 注册Scheme
			httpClient.getConnectionManager().getSchemeRegistry().register(sch);

			// 创建httppost
			HttpPost httppost = new HttpPost(OPFConstants.OPF_URI_HTTPS);

			// 将请求参数转换成JSON字符串
			String paramString = JsonUtil.toJson(request);

			// 创建参数队列
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(OPFConstants.REQUEST,
					paramString));
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
					String responseString = EntityUtils.toString(entity,
							OPFConstants.UTF8);
					if (logger.isDebugEnabled()) {
						logger.debug("Response content: " + responseString);
					}
					try {
						successResponse = JSONObject.parseObject(
								responseString, SuccessResponse.class);
					} catch (JSONException e) {
						ErrorResponse errorResponse = JSONObject.parseObject(
								responseString, ErrorResponse.class);
						throw new OPFBaseException(errorResponse.getMessage());
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
		}
		return successResponse;
	}

}
