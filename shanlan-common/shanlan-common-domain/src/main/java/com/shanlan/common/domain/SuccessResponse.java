package com.shanlan.common.domain;

import com.shanlan.common.constant.ConstantString;

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

	private String businessResult;

	/**
	 * @param flag
	 */
	public SuccessResponse() {
		super.flag = ConstantString.FLAG_SUCCESS;
	}

	/**
	 * @param businessResult
	 */
	public SuccessResponse(String businessResult) {
		super.flag = ConstantString.FLAG_SUCCESS;
		this.businessResult = businessResult;
	}

	/**
	 * @return the businessResult
	 */
	public String getBusinessResult() {
		return businessResult;
	}

	/**
	 * @param businessResult the businessResult to set
	 */
	public void setBusinessResult(String businessResult) {
		this.businessResult = businessResult;
	}



}
