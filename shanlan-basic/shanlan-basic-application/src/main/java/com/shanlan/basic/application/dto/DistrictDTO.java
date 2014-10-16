package com.shanlan.basic.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class DistrictDTO implements Serializable {

	private Integer id;

			private Boolean level;
    private String levelAsString;
	
						
	private Integer upid;
	
		
	private String name;
	
			
		
	public Boolean getLevel() {
		return level;
	}
	
	public void setLevel(Boolean level) { 
		this.level = level;
		this.levelAsString = level ? "1" : "0";
	}
	
	public String getLevelAsString() {
		return this.levelAsString;
	}
	
	public void setLevelAsString(String level) { 
		this.levelAsString = level;
		if(level == null || "".equals(level.trim()))
	       this.level = null;
	    else
	       this.level =  "1".equals(level);
	}
	
								
		

	public void setUpid(Integer upid) { 
		this.upid = upid;
	}

	public Integer getUpid() {
		return this.upid;
	}
	
			
		

	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
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
		DistrictDTO other = (DistrictDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}