package com.shanlan.opf.core.domain;

/**
 * @ClassName:Request
 * @Description: 基础公共请求参数
 * @Author Albert
 * @Date:2013-1-17 下午8:35:43
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class BaseRequest {

	/**
	 * OPF分配给“第三方应用”的key用以确定其身份（可以理解为平台用户的子账号）。应用键对应一个密钥secret。
	 */
	protected String key;
	/**
	 * OPF分配给用户的accessToken，通过oauth协议登陆授权获取。某个API是否需要传入accessToken参数，由是访问的信息否需要“
	 * 非平台用户”授权来决定。
	 */
	protected String accessToken="";

	/**
	 * 请求的服务名
	 */
	protected String service;

	protected String formate = "json";
	protected String v="1.0";

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
	/**
	 * @return the formate
	 */
	public String getFormate() {
		return formate;
	}

	/**
	 * @param formate
	 *            the formate to set
	 */
	public void setFormate(String formate) {
		this.formate = formate;
	}

	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}

		
}
