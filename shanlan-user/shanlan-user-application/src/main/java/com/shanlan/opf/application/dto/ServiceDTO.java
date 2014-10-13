package com.shanlan.opf.application.dto;

import java.io.Serializable;

public class ServiceDTO implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1769603050336568829L;

	private Integer id;

	private String serviceName;

	private Integer enable;
//	private String enableAsString;

	private String serviceVersion;

	private String type;

	private String serviceGroup;

	private Integer isLocal;

//	private String isLocalAsString;

	private String url;

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
//		this.enableAsString = enable ? "1" : "0";
	}

//	public String getEnableAsString() {
//		return this.enableAsString;
//	}
//
//	public String getIsLocalAsString() {
//		return this.isLocalAsString;
//	}

//	public void setEnableAsString(Integer enable) {
//		this.enableAsString = enable;
//		if (enable == null || "".equals(enable.trim()))
//			this.enable = null;
//		else
//			this.enable = "1".equals(enable);
//	}

//	public void setIsLocalAsString(String isLocal) {
//		this.isLocalAsString = isLocal;
//		if (isLocal == null || "".equals(isLocal.trim()))
//			this.isLocal = null;
//		else
//			this.isLocal = "1".equals(isLocal);
//	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getServiceVersion() {
		return this.serviceVersion;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public String getServiceGroup() {
		return this.serviceGroup;
	}

	public void setIsLocal(Integer isLocal) {
		this.isLocal = isLocal;
//		this.isLocalAsString = isLocal ? "1" : "0";
	}

	public Integer getIsLocal() {
		return this.isLocal;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
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
		ServiceDTO other = (ServiceDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


    @Override
    public String toString() {
        return "ServiceDTO{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", enable=" + enable +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", type='" + type + '\'' +
                ", group='" + serviceGroup + '\'' +
                ", isLocal=" + isLocal +
                ", url='" + url + '\'' +
                '}';
    }
}