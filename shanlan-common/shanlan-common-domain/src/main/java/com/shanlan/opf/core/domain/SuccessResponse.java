package com.shanlan.opf.core.domain;

import com.shanlan.common.constant.ConstantNumber;

/**
 * @ClassName:SuccessResponse
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-21 上午9:49:57
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class SuccessResponse extends BaseResponse {

	private String data;

	/**
	 * @param flag
	 */
	public SuccessResponse() {
		super.flag = ConstantNumber.FLAG_SUCCESS;
	}

	/**
	 * @param data
	 */
	public SuccessResponse(String data) {
		super.flag = ConstantNumber.FLAG_SUCCESS;
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public String getBusinessResult() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setBusinessResult(String businessResult) {
		this.data = businessResult;
	}

}
