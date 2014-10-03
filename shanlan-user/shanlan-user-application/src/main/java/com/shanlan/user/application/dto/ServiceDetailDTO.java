package com.shanlan.user.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class ServiceDetailDTO implements Serializable {

	private Integer id;

		
	private String scenario;
	
		
	private String reponse;
	
		
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
	
			
		

	public void setReponse(String reponse) { 
		this.reponse = reponse;
	}

	public String getReponse() {
		return this.reponse;
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
	

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceDetailDTO other = (ServiceDetailDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}