package com.shanlan.trade.application.dto;

import java.io.Serializable;


public class PhotoPackagesDTO extends GoodsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698276121085528019L;

	
		
	private String other;
	
						
	private Short days;
	
						
	private Integer placeId;
	
		
	private String photographer;
	
						
	private Short alterCount;
	
						
	private Short photoCount;
	
	private String photoType;
		

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
							
		

	public void setAlterCount(Short alterCount) {
		this.alterCount = alterCount;
	}

	public Short getAlterCount() {
		return this.alterCount;
	}

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public void setPhotoCount(Short photoCount) {
		this.photoCount = photoCount;
	}

	public Short getPhotoCount() {
		return this.photoCount;
	}



}