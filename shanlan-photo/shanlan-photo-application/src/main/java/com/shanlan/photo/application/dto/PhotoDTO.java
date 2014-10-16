package com.shanlan.photo.application.dto;

import java.io.Serializable;

public class PhotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716137194179265829L;

	private Integer id;

	private String other;

	private Integer likeCount;

	private String filePath;

	private String md5;

	private Integer size;

	public PhotoDTO(int photoId, String photoPath) {
		this.id = photoId;
		this.filePath = photoPath;
	}

	public PhotoDTO() {
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOther() {
		return this.other;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getLikeCount() {
		return this.likeCount;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getSize() {
		return this.size;
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
		PhotoDTO other = (PhotoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}