package com.shanlan.trade.application.dto;

import java.io.Serializable;

public class ReTradePhotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7402155230250338305L;

	private Integer id;

	private Integer upoId;

	private Integer tpcId;

	public void setUpoId(Integer upoId) {
		this.upoId = upoId;
	}

	public Integer getUpoId() {
		return this.upoId;
	}

	public void setTpcId(Integer tpcId) {
		this.tpcId = tpcId;
	}

	public Integer getTpcId() {
		return this.tpcId;
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
		ReTradePhotoDTO other = (ReTradePhotoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}