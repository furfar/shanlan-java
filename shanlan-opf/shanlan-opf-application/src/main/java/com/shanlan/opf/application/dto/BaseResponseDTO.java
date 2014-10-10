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

    public static final int FLAG_FAILURE = 0;
    public static final int FLAG_SUCCESS = 1;

	/**
	 * 标记，0表示成功，1表示失败
	 */
	protected Integer flag;
	

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}



}
