package com.shanlan.user.application.dto;

import java.io.Serializable;

public class UserBlogDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1534707840186577788L;

	private Integer id;

	private String updatedAt;

	private String content;

	private String title;

	private Integer viewTimes;

	private Boolean status;
	private String statusAsString;

	private String createdAt;

	private String creator;

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Integer getViewTimes() {
		return this.viewTimes;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
		this.statusAsString = status ? "1" : "0";
	}

	public String getStatusAsString() {
		return this.statusAsString;
	}

	public void setStatusAsString(String status) {
		this.statusAsString = status;
		if (status == null || "".equals(status.trim()))
			this.status = null;
		else
			this.status = "1".equals(status);
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return this.creator;
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
		UserBlogDTO other = (UserBlogDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}