package com.shanlan.trade.application.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

public class TradeCommentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8408621545940504456L;

	private Integer id;

	private String content;

	private String explanation;

	private Integer score;

	private String rater;

	private String type;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cratedAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cratedAtEnd;

	private String ratee;

	private Integer orderId;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExplanation() {
		return this.explanation;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setRater(String rater) {
		this.rater = rater;
	}

	public String getRater() {
		return this.rater;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setCratedAt(Date cratedAt) {
		this.cratedAt = cratedAt;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCratedAt() {
		return this.cratedAt;
	}

	public void setCratedAtEnd(Date cratedAtEnd) {
		this.cratedAtEnd = cratedAtEnd;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCratedAtEnd() {
		return this.cratedAtEnd;
	}

	public void setRatee(String ratee) {
		this.ratee = ratee;
	}

	public String getRatee() {
		return this.ratee;
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
		TradeCommentDTO other = (TradeCommentDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}