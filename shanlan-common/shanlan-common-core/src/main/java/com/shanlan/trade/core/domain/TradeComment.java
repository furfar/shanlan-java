package com.shanlan.trade.core.domin;

import java.util.Date;

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
@Table(name = "trade_comment")
public class TradeComment extends KoalaLegacyEntity {

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

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "rater")
	private String rater;

	@Column(name = "ratee")
	private String ratee;

	@Column(name = "score")
	private Integer score;

	@Column(name = "content")
	private String content;

	@Column(name = "explanation")
	private String explanation;

	@Column(name = "type")
	private String type;

	@Column(name = "crated_at")
	private Date cratedAt;

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getRater() {
		return rater;
	}

	public void setRater(String rater) {
		this.rater = rater;
	}

	public String getRatee() {
		return ratee;
	}

	public void setRatee(String ratee) {
		this.ratee = ratee;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCratedAt() {
		return cratedAt;
	}

	public void setCratedAt(Date cratedAt) {
		this.cratedAt = cratedAt;
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