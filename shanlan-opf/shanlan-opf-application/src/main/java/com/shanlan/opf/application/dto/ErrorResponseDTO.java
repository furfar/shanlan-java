package com.shanlan.opf.application.dto;


/**
 * @ClassName:ErrorResponse
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-17 下午11:29:34
 * @Remarks:
 * @Version:V1.1
 */
public class ErrorResponseDTO extends BaseResponseDTO {

    /**
     * 错误编码
     */
    private String code;

    public static final String ERROR_CODE_STRING = "code";
    /**
     * 错误描述，对错误编码的描述
     */
    private String message;

    public static String ERROR_MESSAGE_STRING = "message";

    /**
     *
     */
    public ErrorResponseDTO() {
        this.flag = FLAG_FAILURE;
    }

    public ErrorResponseDTO(String err_code) {
        this.flag = FLAG_FAILURE;
        this.code = err_code;
    }

    /**
     * @param code
     * @param message
     */
    public ErrorResponseDTO(String code, String message) {
        this.flag = FLAG_FAILURE;
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
