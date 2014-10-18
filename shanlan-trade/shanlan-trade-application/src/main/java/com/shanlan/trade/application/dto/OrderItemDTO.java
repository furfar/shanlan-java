package com.shanlan.trade.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class OrderItemDTO implements Serializable {

	private Integer id;

						
	private Integer goodsId;
	
						
	private Integer price;
	
						
	private Integer quantity;
	
		
	private Integer orderId;
	
								
		

	public void setGoodsId(Integer goodsId) { 
		this.goodsId = goodsId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}
	
						
		

	public void setPrice(Integer price) { 
		this.price = price;
	}

	public Integer getPrice() {
		return this.price;
	}
	
								
		

	public void setQuantity(Integer quantity) { 
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return this.quantity;
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
		OrderItemDTO other = (OrderItemDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}