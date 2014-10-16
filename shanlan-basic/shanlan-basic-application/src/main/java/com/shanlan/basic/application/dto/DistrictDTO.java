package com.shanlan.basic.application.dto;

import java.io.Serializable;

public class DistrictDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4778989665095394160L;

	private Integer id;

	private Integer level;

	private Integer upid;

	private String name;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public Integer getUpid() {
		return this.upid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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
		DistrictDTO other = (DistrictDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}