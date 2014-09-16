/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.common.model.domain;

import java.io.Serializable;

/**
 * @ClassName:Service
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 下午9:14:43
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5878081575378956010L;
	private String serviceName;
	private String version;
	private String serviceURL;
	private int serviceType;
    private boolean isLocal;
	private int enable;

	/**
	 * @param serviceName
	 * @param serviceURI
	 * @param serviceType
	 */
	public Service(String serviceName, String version, String serviceURI,
			Integer serviceType, int enable) {
		this.serviceName = serviceName;
		this.version = version;
		this.serviceURL = serviceURI;
		this.serviceType = serviceType;
		this.enable = enable;
	}

	public Service() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceURL
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * @param serviceURL
	 *            the serviceURI to set
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the serviceType
	 */
	public int getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType
	 *            the serviceType to set
	 */
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the enable
	 */
	public int getEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(int enable) {
		this.enable = enable;
	}

    public boolean getIsLocal(){
        return isLocal;
    }
    public void setIsLocal(boolean isLocal){
        this.isLocal=isLocal;
    }
}
