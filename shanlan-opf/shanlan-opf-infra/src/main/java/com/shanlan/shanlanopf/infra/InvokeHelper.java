package com.shanlan.shanlanopf.infra;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantString;
import com.shanlan.opf.application.dto.SuccessResponseDTO;

@Named
public class InvokeHelper {
	@Inject
	private static HttpClient httpClient;
	
	private static final Logger logger = LoggerFactory
			.getLogger(InvokeHelper.class);

	public static SuccessResponseDTO getResponse(String URI, String param) {

		SuccessResponseDTO successResponse = new SuccessResponseDTO();

		// 创建httppost
		HttpPost httppost = new HttpPost(URI);
		// 创建参数队列
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair(ConstantString.PARAM, param));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(postParams, ConstantString.STRING_ENCODING_UTF8);
			httppost.setEntity(uefEntity);
			if (logger.isDebugEnabled()) {
				logger.debug("executing request " + httppost.getURI());
			}
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {

				String responseString = EntityUtils.toString(entity,
						ConstantString.STRING_ENCODING_UTF8);

				if (logger.isDebugEnabled()) {
					logger.debug("Response content: " + responseString);
				}

				successResponse = JSONObject.parseObject(responseString,
						SuccessResponseDTO.class);
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

}
