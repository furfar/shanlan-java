/**
 * 
 */
package com.shanlan.common.exception.business;

import java.util.Set;

import com.shanlan.common.exception.sub.SubException;

/**
 * @author Albert
 * 
 */
public class ParameterInvalidException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3230963066351537104L;
	
	/**
	 * 
	 */
	public ParameterInvalidException() {
		super.code = BusinessExceptionCodeConstants.B_PARAMTER_INVALID;
	}
	
	/**
	 * @param message
	 */
	public ParameterInvalidException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param subExceptions
	 */
	public ParameterInvalidException(Set<SubException> subExceptions) {
		super(subExceptions);
		super.code = BusinessExceptionCodeConstants.B_PARAMTER_INVALID;
	}
	
	/**
	 * @param code
	 * @param subExceptions
	 */
	public ParameterInvalidException(String errorCode,
			Set<SubException> subExceptions) {
		super(errorCode, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param code
	 * @param message
	 */
	public ParameterInvalidException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param code
	 * @param message
	 * @param subExceptions
	 */
	public ParameterInvalidException(String errorCode, String errorMessage,
			Set<SubException> subExceptions) {
		super(errorCode, errorMessage, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param code
	 * @param message
	 * @param solution
	 */
	public ParameterInvalidException(String errorCode, String errorMessage,
			String solution) {
		super(errorCode, errorMessage, solution);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param code
	 * @param message
	 * @param solution
	 * @param subExceptions
	 */
	public ParameterInvalidException(String errorCode, String errorMessage,
			String solution, Set<SubException> subExceptions) {
		super(errorCode, errorMessage, solution, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cause
	 */
	public ParameterInvalidException(Throwable cause) {
		super(cause);
		super.code = BusinessExceptionCodeConstants.B_PARAMTER_INVALID;
	}
	
	/**
	 * @param cause
	 * @param subExceptions
	 */
	public ParameterInvalidException(Throwable cause,
			Set<SubException> subExceptions) {
		super(cause, subExceptions);
		super.code = BusinessExceptionCodeConstants.B_PARAMTER_INVALID;
	}
	
	/**
	 * @param cause
	 * @param code
	 * @param subExceptions
	 */
	public ParameterInvalidException(Throwable cause, String errorCode,
			Set<SubException> subExceptions) {
		super(cause, errorCode, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cause
	 * @param code
	 * @param message
	 * @param subExceptions
	 */
	public ParameterInvalidException(Throwable cause, String errorCode,
			String errorMessage, Set<SubException> subExceptions) {
		super(cause, errorCode, errorMessage, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cause
	 * @param code
	 * @param message
	 * @param solution
	 * @param subExceptions
	 */
	public ParameterInvalidException(Throwable cause, String errorCode,
			String errorMessage, String solution,
			Set<SubException> subExceptions) {
		super(cause, errorCode, errorMessage, solution, subExceptions);
		// TODO Auto-generated constructor stub
	}
	
}
