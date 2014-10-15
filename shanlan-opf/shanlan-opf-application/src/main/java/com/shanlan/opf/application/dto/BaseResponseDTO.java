package com.shanlan.opf.application.dto;

/**
 * @ClassName:BaseResponse
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-17 下午11:19:35
 *
 * @Remarks:
 * @Version:V1.1
 */
public class BaseResponseDTO {

	/**
	 * 标记，0表示成功，1表示失败
	 */
	protected int code;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int flag) {
		this.code = flag;
	}

}
