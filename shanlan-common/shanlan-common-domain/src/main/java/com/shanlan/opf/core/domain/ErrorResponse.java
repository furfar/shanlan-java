package com.shanlan.opf.core.domain;

import com.shanlan.common.constant.ConstantString;


/**
 * @ClassName:ErrorResponse
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-17 下午11:29:34
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class ErrorResponse extends BaseResponse {

	/**
	 * 错误编码
	 */
	private String code;

    public static String ERROR_CODE_STRING="code";

	/**
	 * 错误描述，对错误编码的描述
	 */
	private String message;

    public static String ERROR_MESSAGE_STRING="message";

	/**
	 *
	 */
	public ErrorResponse() {
		this.flag = ConstantString.FLAG_FAILURE;
	}

	public ErrorResponse(String err_code) {
		this.flag = ConstantString.FLAG_FAILURE;
		this.code = err_code;
	}

	/**
	 * @param err_code
	 * @param err_info
	 */
	public ErrorResponse(String code, String message) {
		this.flag = ConstantString.FLAG_FAILURE;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + "]";
	}

	

}
