package com.shanlan.trade.application.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

public class TradePhotoCollectionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7461220192799501655L;

	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAtEnd;

	private Integer photoQuantity;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAtEnd;

	private String description;

	private String name;

	private String owner;

	private Integer orderId;

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

	public void setPhotoQuantity(Integer photoQuantity) {
		this.photoQuantity = photoQuantity;
	}

	public Integer getPhotoQuantity() {
		return this.photoQuantity;
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

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return this.orderId;
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