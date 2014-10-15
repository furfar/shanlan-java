package com.shanlan.opf.application.dto;

import com.shanlan.common.constant.ConstantNumber;

/**
 * @ClassName:SuccessResponseDTO
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-21 上午9:49:57
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class SuccessResponseDTO extends BaseResponseDTO {

	private String data;

	public SuccessResponseDTO() {
		this.code = ConstantNumber.FLAG_SUCCESS;
	}

	/**
	 * @param data
	 */
	public SuccessResponseDTO(String data) {
		this.code = ConstantNumber.FLAG_SUCCESS;
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String businessResult) {
		this.data = businessResult;
	}

}
