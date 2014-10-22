package com.shanlan.user.application.dto;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class UserDetailDTO implements Serializable {

	private Integer id;

		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birthday;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birthdayEnd;
						
	private Integer activeness;


	private String other;
	
						
	private Integer cityId;
	
						
	private Integer photoCount;
	
		
	private String type;
	
	private String email;

	private String webchart;
	
		
	private String photoPath;
	
						
	private Integer tradeTimes;
	
		
	private String alipay;
	
						
	private Integer photoId;
	
		
	private String userName;
	
		
	private Integer gender;
	
		
	private String realName;
	
		
	private String qq;
	
		
	private String mobile;
	
			
		

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getBirthday() {
		return this.birthday;
	}
	
	public void setBirthdayEnd(String birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}


	public String getBirthdayEnd() {
		return this.birthdayEnd;
	}
								
		

	public void setActiveness(Integer activeness) { 
		this.activeness = activeness;
	}

	public Integer getActiveness() {
		return this.activeness;
	}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOther(String other) {
		this.other = other;
	}

	public String getOther() {
		return this.other;
	}
	
								
		

	public void setCityId(Integer cityId) { 
		this.cityId = cityId;
	}

	public Integer getCityId() {
		return this.cityId;
	}
	
								
		

	public void setPhotoCount(Integer photoCount) { 
		this.photoCount = photoCount;
	}

	public Integer getPhotoCount() {
		return this.photoCount;
	}
	
			
		

	public void setType(String type) { 
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
			
		

	public void setWebchart(String webchart) { 
		this.webchart = webchart;
	}

	public String getWebchart() {
		return this.webchart;
	}
	
			
		

	public void setPhotoPath(String photoPath) { 
		this.photoPath = photoPath;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}
	
								
		

	public void setTradeTimes(Integer tradeTimes) { 
		this.tradeTimes = tradeTimes;
	}

	public Integer getTradeTimes() {
		return this.tradeTimes;
	}
	
			
		

	public void setAlipay(String alipay) { 
		this.alipay = alipay;
	}

	public String getAlipay() {
		return this.alipay;
	}
	
								
		

	public void setPhotoId(Integer photoId) { 
		this.photoId = photoId;
	}

	public Integer getPhotoId() {
		return this.photoId;
	}
	
			
		

	public void setUserName(String userName) { 
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	
			
		

	public void setGender(Integer gender) { 
		this.gender = gender;
	}

	public Integer getGender() {
		return this.gender;
	}
	
			
		

	public void setRealName(String realName) { 
		this.realName = realName;
	}

	public String getRealName() {
		return this.realName;
	}
	
			
		

	public void setQq(String qq) { 
		this.qq = qq;
	}

	public String getQq() {
		return this.qq;
	}
	
			
		

	public void setMobile(String mobile) { 
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
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
		UserDetailDTO other = (UserDetailDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}