package com.shanlan.opf.application.dto;



public class ServiceDetailDTO extends ServiceDTO {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8652415670482509497L;


	private String scenario;
	
		
	private String response;
	
		
	private String responseSample;
	
						
	private Integer serviceId;
	
		
	private String errorCode;
	
		
	private String businessParam;
	
			
		

	public void setScenario(String scenario) { 
		this.scenario = scenario;
	}

	public String getScenario() {
		return this.scenario;
	}
	
			
		

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return this.response;
	}
	
			
		

	public void setResponseSample(String responseSample) { 
		this.responseSample = responseSample;
	}

	public String getResponseSample() {
		return this.responseSample;
	}
	
								
		

	public void setServiceId(Integer serviceId) { 
		this.serviceId = serviceId;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}
	
			
		

	public void setErrorCode(String errorCode) { 
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
	
			
		

	public void setBusinessParam(String businessParam) { 
		this.businessParam = businessParam;
	}

	public String getBusinessParam() {
		return this.businessParam;
	}

}