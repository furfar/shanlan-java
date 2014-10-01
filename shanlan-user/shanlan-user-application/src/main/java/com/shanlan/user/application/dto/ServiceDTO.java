package com.shanlan.user.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class ServiceDTO implements Serializable {

	private Integer id;

		
	private String name;
	
			private Boolean enable;
    private String enableAsString;
	
		
	private String serviceVersion;
	
		
	private String type;
	
		
	private String group;
	
						
	private Short isLocal;
	
		
	private String url;
	
			
		

	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
			
		
	public Boolean getEnable() {
		return enable;
	}
	
	public void setEnable(Boolean enable) { 
		this.enable = enable;
		this.enableAsString = enable ? "1" : "0";
	}
	
	public String getEnableAsString() {
		return this.enableAsString;
	}
	
	public void setEnableAsString(String enable) { 
		this.enableAsString = enable;
		if(enable == null || "".equals(enable.trim()))
	       this.enable = null;
	    else
	       this.enable =  "1".equals(enable);
	}
	
			
		

	public void setServiceVersion(String serviceVersion) { 
		this.serviceVersion = serviceVersion;
	}

	public String getServiceVersion() {
		return this.serviceVersion;
	}
	
			
		

	public void setType(String type) { 
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
			
		

	public void setGroup(String group) { 
		this.group = group;
	}

	public String getGroup() {
		return this.group;
	}
	
								
		

	public void setIsLocal(Short isLocal) { 
		this.isLocal = isLocal;
	}

	public Short getIsLocal() {
		return this.isLocal;
	}
	
			
		

	public void setUrl(String url) { 
		this.url = url;
	}

	public String getUrl() {
		return this.url;
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
		ServiceDTO other = (ServiceDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}