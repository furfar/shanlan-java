package com.shanlan.trade.application.dto;

import java.io.Serializable;

public class TradePhotoCollectionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7461220192799501655L;

	private Integer id;

	
	private String updatedAt;

	
	private String updatedAtEnd;

	private Integer photoCount;

	
	private String createdAt;

	
	private String createdAtEnd;

	private String description;

	private String name;

	private String owner;

	private Integer goodsId;

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	public String getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAtEnd(String updatedAtEnd) {
		this.updatedAtEnd = updatedAtEnd;
	}

	
	public String getUpdatedAtEnd() {
		return this.updatedAtEnd;
	}

	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}

	public Integer getPhotoCount() {
		return this.photoCount;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	
	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAtEnd(String createdAtEnd) {
		this.createdAtEnd = createdAtEnd;
	}

	
	public String getCreatedAtEnd() {
		return this.createdAtEnd;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
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
		TradePhotoCollectionDTO other = (TradePhotoCollectionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}