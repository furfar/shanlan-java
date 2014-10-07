package com.shanlan.photo.application.dto;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class PhotoDTO implements Serializable {

	private Integer id;

		
	private String title;
	
		
	private String other;
	
		
	private String filePath;
	
		
	private String fileName;
	
		
	private Date uploadedAt;
	
		
	private String creator;
	
						
	private Integer size;
	
			
		

	public void setTitle(String title) { 
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	
			
		

	public void setOther(String other) { 
		this.other = other;
	}

	public String getOther() {
		return this.other;
	}
	
			
		

	public void setFilePath(String filePath) { 
		this.filePath = filePath;
	}

	public String getFilePath() {
		return this.filePath;
	}
	
			
		

	public void setFileName(String fileName) { 
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}
	
			
		

	public void setUploadedAt(Date uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public Date getUploadedAt() {
		return this.uploadedAt;
	}
	
			
		

	public void setCreator(String creator) { 
		this.creator = creator;
	}

	public String getCreator() {
		return this.creator;
	}
	
								
		

	public void setSize(Integer size) { 
		this.size = size;
	}

	public Integer getSize() {
		return this.size;
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
		PhotoDTO other = (PhotoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}