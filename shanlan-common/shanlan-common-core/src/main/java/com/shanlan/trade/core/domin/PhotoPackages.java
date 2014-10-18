package com.shanlan.trade.core.domin;

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
@Table(name = "photo_packages")
public class PhotoPackages extends KoalaLegacyEntity {

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

	@Column(name = "goods_id")
	private int goodsId;

	@Column(name = "photographer")
	private String photographer;

	@Column(name = "days")
	private short days;

	@Column(name = "photo_counts")
	private short photoCounts;

	@Column(name = "alter_counts")
	private short alterCounts;

	@Column(name = "place_id")
	private int placeId;

	@Column(name = "note")
	private String note;

	@Column(name = "other")
	private String other;

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getPhotographer() {
		return photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	public short getDays() {
		return days;
	}

	public void setDays(short days) {
		this.days = days;
	}

	public short getPhotoCounts() {
		return photoCounts;
	}

	public void setPhotoCounts(short photoCounts) {
		this.photoCounts = photoCounts;
	}

	public short getAlterCounts() {
		return alterCounts;
	}

	public void setAlterCounts(short alterCounts) {
		this.alterCounts = alterCounts;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

}