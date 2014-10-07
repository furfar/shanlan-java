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
public class SuccessResponseDTO {

	public static final int FLAG_FAILURE = 1;
	public static final int FLAG_SUCCESS = 0;

	private String businessResult;
	/**
	 * 标记，0表示成功，1表示失败
	 */
	protected Integer flag;

	/**
	 * @param flag
	 */
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
