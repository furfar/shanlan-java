package com.shanlan.trade.core.domain;

import javax.persistence.*;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "trade_order_item")
public class TradeOrderItem extends KoalaLegacyEntity {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键
	 *
	 **/

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@Column(name = "order_id")
//	private Integer orderId;

	@Column(name = "goods_id")
	private int goodsId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private float price;

	// 这里设置JoinColum设置了外键的名字，并且orderItem是关系维护端
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "order_id")
	private TradeOrder tradeOrder;

	public void setId(Integer id) {
		this.id = id;
	}

//	public int getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existed(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}


	public TradeOrder getOrder() {
		return tradeOrder;
	}

	public void setOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

}