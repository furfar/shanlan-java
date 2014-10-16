package com.shanlan.user.core.domain;

import java.io.Serializable;

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
@Table(name = "photographer")
public class Photographer extends KoalaLegacyEntity {

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

	@Column(name = "user_name")
	private String userName;

	@Column(name = "evaluation_rank")
	private int evaluationRank;

	@Column(name = "evaluation_times")
	private int evaluationTimes;

	@Column(name = "photo_styles")
	private String photoStyles;

	@Column(name = "photo_types")
	private String photoTypes;

	@Column(name = "service_status")
	private Boolean serviceStatus;

	@Column(name = "other")
	private String other;

	@Column(name = "verify_type")
	private String verifyType;

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getEvaluationRank() {
		return evaluationRank;
	}

	public void setEvaluationRank(int evaluationRank) {
		this.evaluationRank = evaluationRank;
	}

	public int getEvaluationTimes() {
		return evaluationTimes;
	}

	public void setEvaluationTimes(int evaluationTimes) {
		this.evaluationTimes = evaluationTimes;
	}

	public String getPhotoStyles() {
		return photoStyles;
	}

	public void setPhotoStyles(String photoStyles) {
		this.photoStyles = photoStyles;
	}

	public String getPhotoTypes() {
		return photoTypes;
	}

	public void setPhotoTypes(String photoTypes) {
		this.photoTypes = photoTypes;
	}

	public Boolean getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
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