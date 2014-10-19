package com.shanlan.trade.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1834204467417643097L;

	private Integer id;

	private Integer receiveAddreId;

	private String other;

	private String receiver;

	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date createdAtEnd;

	private String buyer;

	private Set orderItems;

	private String seller;

	public void setReceiveAddreId(Integer receiveAddreId) {
		this.receiveAddreId = receiveAddreId;
	}

	public Integer getReceiveAddreId() {
		return this.receiveAddreId;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOther() {
		return this.other;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
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

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyer() {
		return this.buyer;
	}

	public void setOrderItems(Set orderItems) {
		this.orderItems = orderItems;
	}

	public Set getOrderItems() {
		return this.orderItems;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSeller() {
		return this.seller;
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
		OrderDTO other = (OrderDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}