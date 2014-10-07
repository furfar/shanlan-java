package com.shanlan.common.domain;

/**
 * @ClassName:Request
 * @Description: 基础公共请求参数
 * @Author Albert
 * @Date:2013-1-17 下午8:35:43
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class BaseRequestHTTP extends BaseRequest{

	/**
	 * API输入参数签名结果，服务平台通过它验证请求数据的合法性。
	 */
	protected String sign="";
	
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	
}
