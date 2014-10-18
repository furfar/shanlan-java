package com.shanlan.trade.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class PhotoPackagesDTO implements Serializable {

	private Integer id;

						
	private Integer goodsId;
	
		
	private String other;
	
						
	private Short days;
	
						
	private Integer placeId;
	
		
	private String photographer;
	
		
	private String note;
	
						
	private Short alterCounts;
	
						
	private Short photoCounts;
	
								
		

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
	
								
		

	public void setDays(Short days) { 
		this.days = days;
	}

	public Short getDays() {
		return this.days;
	}
	
								
		

	public void setPlaceId(Integer placeId) { 
		this.placeId = placeId;
	}

	public Integer getPlaceId() {
		return this.placeId;
	}
	
			
		

	public void setPhotographer(String photographer) { 
		this.photographer = photographer;
	}

	public String getPhotographer() {
		return this.photographer;
	}
	
			
		

	public void setNote(String note) { 
		this.note = note;
	}

	public String getNote() {
		return this.note;
	}
	
								
		

	public void setAlterCounts(Short alterCounts) { 
		this.alterCounts = alterCounts;
	}

	public Short getAlterCounts() {
		return this.alterCounts;
	}
	
								
		

	public void setPhotoCounts(Short photoCounts) { 
		this.photoCounts = photoCounts;
	}

	public Short getPhotoCounts() {
		return this.photoCounts;
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