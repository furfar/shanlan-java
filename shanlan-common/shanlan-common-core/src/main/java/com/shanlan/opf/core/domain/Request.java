package com.shanlan.opf.core.domain;


/**
 * @ClassName:Request 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-1-19 下午5:02:31
 *
 * @Remarks:
 * @Version:V1.1
 */
public class Request extends BaseRequest {

	/**
	 * 业务级参数
	 */
	private String param;

	/**
	 * 与key对应的密钥secret（可以理解为子账号的密码）
	 */
	private String secret="";
	
	
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
	
	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	
	
	
}
