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
public class BaseRequestHTTPS extends BaseRequest{

	
	/**
	 * 与key对应的密钥secret（可以理解为子账号的密码）
	 */
	private String secret="";
	
	
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	
}
