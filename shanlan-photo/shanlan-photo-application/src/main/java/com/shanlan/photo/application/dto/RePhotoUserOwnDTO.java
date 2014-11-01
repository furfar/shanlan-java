package com.shanlan.photo.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class RePhotoUserOwnDTO implements Serializable {

	private Integer id;

		
	private String updatedAt;
	
		
	private String title;
	
		
	private Integer visibility;
	
						
	private Integer photoId;
	
		
	private String createdAt;
	
		
	private String description;
	
		
	private String userName;
	
		
	private String photoPath;
	
			
		

	public void setUpdatedAt(String updatedAt) { 
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}
	
			
		

	public void setTitle(String title) { 
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	
			
		

	public void setVisibility(Integer visibility) { 
		this.visibility = visibility;
	}

	public Integer getVisibility() {
		return this.visibility;
	}
	
								
		

	public void setPhotoId(Integer photoId) { 
		this.photoId = photoId;
	}

	public Integer getPhotoId() {
		return this.photoId;
	}
	
			
		

	public void setCreatedAt(String createdAt) { 
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}
	
			
		

	public void setDescription(String description) { 
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
			
		

	public void setUserName(String userName) { 
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	
			
		

	public void setPhotoPath(String photoPath) { 
		this.photoPath = photoPath;
	}

	public String getPhotoPath() {
		return this.photoPath;
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
		RePhotoUserOwnDTO other = (RePhotoUserOwnDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}