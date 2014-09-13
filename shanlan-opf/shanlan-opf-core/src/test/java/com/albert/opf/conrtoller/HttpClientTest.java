/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.conrtoller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName:HttpClientTest
 * @Description: TODO
 * @Author Albert
 * @Date:2013-4-3 下午4:07:44
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class HttpClientTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {

		//创建默认的httpClient实例.
		HttpClient httpclient = new DefaultHttpClient();
		
		// 创建httppost
		HttpPost httppost = new HttpPost(
				"http://localhost:8088/phoneservice/getBrandByModel");
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("type", "house"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("--------------------------------------");
				System.out.println("Response content: "
						+ EntityUtils.toString(entity, "UTF-8"));
				System.out.println("--------------------------------------");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
		}

		// HttpClient client = new HttpClient();
		// // 设置代理服务器地址和端口
		// //
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		// // HttpMethod method = new GetMethod(
		// // "http://localhost:8088/phoneservice/getBrandByModel");
		// // 使用POST方法
		// HttpMethod postMethod = new PostMethod(
		// "http://localhost:8088/phoneservice/getBrandByModel");
		//
		// NameValuePair simcard = new NameValuePair("model", "c8812");
		// ((PostMethod) postMethod)
		// .setRequestBody(new NameValuePair[] { simcard });
		//
		// try {
		// // client.executeMethod(method);
		// client.executeMethod(postMethod);
		// } catch (HttpException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// String response = null;
		// // 打印服务器返回的状态
		// // System.out.println(method.getStatusLine());
		// System.out.println(postMethod.getStatusLine());
		// // 打印返回的信息
		// try {
		// // System.out.println(method.getResponseBodyAsString());
		// response = postMethod.getResponseBodyAsString();
		// System.out.println(response);
		//
		//
		// SuccessResponse successResponse = JSONObject.parseObject(response,
		// SuccessResponse.class);
		//
		// System.out.println(successResponse.getBusinessResult());
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// // 释放连接
		// // method.releaseConnection();
		// postMethod.releaseConnection();
	}

}
