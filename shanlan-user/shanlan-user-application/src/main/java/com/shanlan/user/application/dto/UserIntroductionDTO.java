package com.shanlan.user.application.dto;

import java.sql.Timestamp;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class UserIntroductionDTO implements Serializable {

	private Integer id;

		
	private Timestamp updatedAt;
	
		
	private String content;
	
		
	private String title;
	
						
	private Short sequence;
	
		
	private Timestamp createdAt;
	
		
	private String userName;
	
			
		

	public void setUpdatedAt(Timestamp updatedAt) { 
		this.updatedAt = updatedAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}
	
			
		

	public void setContent(String content) { 
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
	
			
		

	public void setTitle(String title) { 
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	
								
		

	public void setSequence(Short sequence) { 
		this.sequence = sequence;
	}

	public Short getSequence() {
		return this.sequence;
	}
	
			
		

	public void setCreatedAt(Timestamp createdAt) { 
		this.createdAt = createdAt;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}
	
			
		

	public void setUserName(String userName) { 
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
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
		UserIntroductionDTO other = (UserIntroductionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}