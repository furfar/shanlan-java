package com.shanlan.common.exception.system;

public class NoSuchMessageException extends SystemException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -214061011166542675L;
	
	public NoSuchMessageException() {
		super();
	}
	
	public NoSuchMessageException(String errorMessage) {
		super(errorMessage);
	}
	
}
