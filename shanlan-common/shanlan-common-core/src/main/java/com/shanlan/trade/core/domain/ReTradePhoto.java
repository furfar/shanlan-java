package com.shanlan.trade.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "re_trade_photo")
public class ReTradePhoto extends KoalaLegacyEntity {

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

	@Column(name = "tpc_id")
	private int tpcId;

	@Column(name = "upo_id")
	private int upoId;

	public void setId(int id) {
		this.id = id;
	}

	public int getTpcId() {
		return tpcId;
	}

	public void setTpcId(int tpcId) {
		this.tpcId = tpcId;
	}

	public int getUpoId() {
		return upoId;
	}

	public void setUpoId(int upoId) {
		this.upoId = upoId;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
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

}