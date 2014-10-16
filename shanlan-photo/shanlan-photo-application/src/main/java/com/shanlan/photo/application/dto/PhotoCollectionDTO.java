package com.shanlan.photo.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */

public class PhotoCollectionDTO {

	/**
	 * 
	 * 主键
	 * 
	 **/

	private int id;

	private String name;

	private String creator;

	private String createdAt;

	private String updatedAt;

	private int photoQuantity;

	private String other;

	private List<PhotoDTO> photoDTOList;

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPhotoQuantity() {
		return photoQuantity;
	}

	public void setPhotoQuantity(int photoQuantity) {
		this.photoQuantity = photoQuantity;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getId() {
		return id;
	}

	public List<PhotoDTO> getPhotoDTOList() {
		if (photoDTOList == null) {
			return new ArrayList<PhotoDTO>();
		} else {
			return photoDTOList;
		}
	}

	public void setPhotoDTOList(List<PhotoDTO> photoDTOList) {
		this.photoDTOList = photoDTOList;
	}

}