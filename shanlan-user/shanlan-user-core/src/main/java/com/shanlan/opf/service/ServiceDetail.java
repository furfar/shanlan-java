package com.shanlan.opf.service;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "service_detail")
public class ServiceDetail extends KoalaLegacyEntity {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键
	 *
	 **/

	@Id
	@Column(name = "id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "service_id")
	private int serviceId;

	@Column(name = "scenario")
	private String scenario;

	@Column(name = "business_param")
	private String businessParam;

	@Column(name = "reponse")
	private String reponse;

	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "response_sample")
	private String responseSample;

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getBusinessParam() {
		return businessParam;
	}

	public void setBusinessParam(String businessParam) {
		this.businessParam = businessParam;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getResponseSample() {
		return responseSample;
	}

	public void setResponseSample(String responseSample) {
		this.responseSample = responseSample;
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