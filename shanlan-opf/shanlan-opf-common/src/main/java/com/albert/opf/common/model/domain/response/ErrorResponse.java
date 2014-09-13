/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.common.model.domain.response;

import com.albert.opf.common.constant.OPFConstants;

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
	private Integer err_code;

	/**
	 * 错误描述，对错误编码的描述
	 */
	private String err_info;

	/**
	 *
	 */
	public ErrorResponse() {
		this.flag = OPFConstants.FLAG_FAILURE;
	}

	public ErrorResponse(Integer err_code) {
		this.flag = OPFConstants.FLAG_FAILURE;
		this.err_code = err_code;
	}

	/**
	 * @param err_code
	 * @param err_info
	 */
	public ErrorResponse(Integer err_code, String err_info) {
		this.flag = OPFConstants.FLAG_FAILURE;
		this.err_code = err_code;
		this.err_info = err_info;
	}

	/**
	 * @return the err_code
	 */
	public Integer getErr_code() {
		return err_code;
	}

	/**
	 * @param err_code
	 *            the err_code to set
	 */
	public void setErr_code(Integer err_code) {
		this.err_code = err_code;
	}

	/**
	 * @return the err_info
	 */
	public String getErr_info() {
		return err_info;
	}

	/**
	 * @param err_info
	 *            the err_info to set
	 */
	public void setErr_info(String err_info) {
		this.err_info = err_info;
	}

}
