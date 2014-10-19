package com.shanlan.trade.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "trade_order")
public class TradeOrder extends KoalaLegacyEntity {

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

	@Column(name = "buyer")
	private String buyer;

	@Column(name = "seller")
	private String seller;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "status")
	private String status;

	@Column(name = "receiver")
	private String receiver;

	// 这里配置关系，并且确定关系维护端和被维护端。mappBy表示关系被维护端，只有关系端有权去更新外键。
	//这里还有注意OneToMany默认的加载方式是赖加载。当看到设置关系中最后一个单词是Many，那么该加载默认为懒加载
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE },fetch= FetchType.EAGER, mappedBy = "tradeOrder")
	private Set<TradeOrderItem> tradeOrderItems;

	/**
	 * 收件人地址ID
	 */
	@Column(name = "receive_addre_id")
	private Integer receiveAddreId;

	@Column(name = "other")
	private String other;

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiveAddreId() {
		return receiveAddreId;
	}

	public void setReceiveAddreId(Integer receiveAddreId) {
		this.receiveAddreId = receiveAddreId;
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


	public Set<TradeOrderItem> getTradeOrderItems() {
		return tradeOrderItems;
	}

	public void setTradeOrderItems(Set<TradeOrderItem> items) {
		this.tradeOrderItems = items;
	}

	public void addOrderItem(TradeOrderItem tradeOrderItem) {
		tradeOrderItem.setOrder(this);// 用关系维护端来维护关系
		this.tradeOrderItems.add(tradeOrderItem);
	}

}