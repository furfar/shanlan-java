/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.common.model.domain.response;

import com.albert.opf.common.constant.OPFConstants;

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
		super.flag = OPFConstants.FLAG_SUCCESS;
	}

	/**
	 * @param businessResult
	 */
	public SuccessResponse(String businessResult) {
		super.flag = OPFConstants.FLAG_SUCCESS;
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
