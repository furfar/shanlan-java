package com.shanlan.opf.infra;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.common.util.SignUtils;
import com.shanlan.opf.core.domain.ErrorResponse;
import com.shanlan.opf.core.domain.Request;
import com.shanlan.opf.core.domain.SuccessResponse;
import com.shanlan.opf.infra.helper.InvokeHelper;

/**
 * @ClassName:OPFClient
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-18 下午4:20:15
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class OpfClientHTTP {

	private static final Logger logger = LoggerFactory
			.getLogger(OpfClientHTTP.class);

	public static void main(String[] args) {

		Request request = new Request();
		request.setKey("app1");
		request.setSecret("secret1");
		request.setService("UserDetail.login");
		request.setParam("{\"userName\":\"zhangsan\",\"password\":\"888888\"}");

		// UserDetail user = new UserDetail("wangwu3", "888888", "王五", "wangwu@126.com",
		// "BeiJing", true);
		// request.setService("UserDetail.register");
		// request.setParam(JsonUtil.toJson(user));

		SuccessResponse successResponse = null;
		try {
			successResponse = OpfClientHTTP.getResponse(request);
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
			Map<String, String> paramValues = InvokeHelper.getParamMap(request);
			String sign = SignUtils.sign(paramValues, request.getSecret());
			request.setSign(sign);
			// 将请求参数转换成JSON字符串
			String paramString = JsonUtil.toJson(request);
			// 创建httppost
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(ConstantString.OPF_URI_HTTP);
			// HttpPost httppost = new
			// HttpPost("http://localhost:8088/opf.manager/user.get");
			// 创建参数队列
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(ConstantString.REQUEST,
					paramString));
			// postParams.add(new BasicNameValuePair("param",
			// "app1"));

			UrlEncodedFormEntity uefEntity;
			try {
				uefEntity = new UrlEncodedFormEntity(postParams,
						ConstantString.STRING_ENCODING_UTF8);
				httppost.setEntity(uefEntity);
				if (logger.isDebugEnabled()) {
					logger.debug("executing request " + httppost.getURI());
				}
				HttpResponse response = httpClient.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseString = EntityUtils.toString(entity,
							ConstantString.STRING_ENCODING_UTF8);
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
					throw new OPFBaseException(errorResponse.getMessage());
				}
			}
		}

		return successResponse;
	}
}
