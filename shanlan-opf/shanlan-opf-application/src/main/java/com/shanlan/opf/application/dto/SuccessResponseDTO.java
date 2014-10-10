package com.shanlan.opf.application.dto;


/**
 * @ClassName:SuccessResponseDTO
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-21 上午9:49:57
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class SuccessResponseDTO extends BaseResponseDTO{



	private String businessResult;


	public SuccessResponseDTO() {
		this.flag = FLAG_SUCCESS;
	}

	/**
	 * @param businessResult
	 */
	public SuccessResponseDTO(String businessResult) {
		this.flag = FLAG_SUCCESS;
		this.businessResult = businessResult;
	}

	/**
	 * @return the businessResult
	 */
	public String getBusinessResult() {
		return businessResult;
	}

	/**
	 * @param businessResult
	 *            the businessResult to set
	 */
	public void setBusinessResult(String businessResult) {
		this.businessResult = businessResult;
	}

}
