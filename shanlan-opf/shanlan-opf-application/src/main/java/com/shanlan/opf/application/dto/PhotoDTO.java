package com.shanlan.opf.application.dto;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */

public class PhotoDTO {

	private int id;

	private String filePath;

	private int size;

	private int likeCount;

	private String other;

	public PhotoDTO() {

	}

	public PhotoDTO(int id, String filePath) {
		super();
		this.id = id;
		this.filePath = filePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}