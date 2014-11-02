package com.shanlan.trade.application.dto;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class PhotoPackagesDTO implements Serializable {

	private Integer id;

						
	private Integer goodsId;
	
		
	private String other;
	
		
	private String status;
	
		
	private Integer photoCount;
	
		
	private Integer alterCount;
	
		
	private String type;
	
		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date invalidDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date invalidDateEnd;
		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAtEnd;
						
	private Date price;
	
		
	private Integer days;
	
		
	private String address;
	
						
	private Integer placeId;
	
		
	private String description;
	
		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAtEnd;
		
	private String name;
	
		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validDateEnd;
		
	private String photographer;
	
								
		

	public void setGoodsId(Integer goodsId) { 
		this.goodsId = goodsId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}
	
			
		

	public void setOther(String other) { 
		this.other = other;
	}

	public String getOther() {
		return this.other;
	}
	
			
		

	public void setStatus(String status) { 
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
			
		

	public void setPhotoCount(Integer photoCount) { 
		this.photoCount = photoCount;
	}

	public Integer getPhotoCount() {
		return this.photoCount;
	}
	
			
		

	public void setAlterCount(Integer alterCount) { 
		this.alterCount = alterCount;
	}

	public Integer getAlterCount() {
		return this.alterCount;
	}
	
			
		

	public void setType(String type) { 
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
			
		

	public void setInvalidDate(Date invalidDate) { 
		this.invalidDate = invalidDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getInvalidDate() {
		return this.invalidDate;
	}
	
	public void setInvalidDateEnd(Date invalidDateEnd) { 
		this.invalidDateEnd = invalidDateEnd;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getInvalidDateEnd() {
		return this.invalidDateEnd;
	}
			
		

	public void setUpdatedAt(Date updatedAt) { 
		this.updatedAt = updatedAt;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	
	public void setUpdatedAtEnd(Date updatedAtEnd) { 
		this.updatedAtEnd = updatedAtEnd;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getUpdatedAtEnd() {
		return this.updatedAtEnd;
	}
						
		

	public void setPrice(Date price) { 
		this.price = price;
	}

	public Date getPrice() {
		return this.price;
	}
	
			
		

	public void setDays(Integer days) { 
		this.days = days;
	}

	public Integer getDays() {
		return this.days;
	}
	
			
		

	public void setAddress(String address) { 
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
	
								
		

	public void setPlaceId(Integer placeId) { 
		this.placeId = placeId;
	}

	public Integer getPlaceId() {
		return this.placeId;
	}
	
			
		

	public void setDescription(String description) { 
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
			
		

	public void setCreatedAt(Date createdAt) { 
		this.createdAt = createdAt;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	public void setCreatedAtEnd(Date createdAtEnd) { 
		this.createdAtEnd = createdAtEnd;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedAtEnd() {
		return this.createdAtEnd;
	}
			
		

	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
			
		

	public void setValidDate(Date validDate) { 
		this.validDate = validDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getValidDate() {
		return this.validDate;
	}
	
	public void setValidDateEnd(Date validDateEnd) { 
		this.validDateEnd = validDateEnd;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getValidDateEnd() {
		return this.validDateEnd;
	}
			
		

	public void setPhotographer(String photographer) { 
		this.photographer = photographer;
	}

	public String getPhotographer() {
		return this.photographer;
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
		PhotoPackagesDTO other = (PhotoPackagesDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}