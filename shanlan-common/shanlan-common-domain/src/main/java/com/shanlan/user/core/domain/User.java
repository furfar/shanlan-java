/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.shanlan.user.core.domain;


/**
 * @ClassName:User
 * @Description: TODO
 * @Author Albert
 * @Date:2013-4-7 下午12:10:04
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class User {
	
	private String userName;
	
	private String password;

	private String nickName;
	
	private String city;
	
	private String email;
	
	private boolean isValid;
	

	/**
	 * 
	 */
	public User() {
	}

	

	public User(String userAccount, String userPassword, String nickName,
			String email,String city, boolean isValid) {
		super();
		this.userName = userAccount;
		this.password = userPassword;
		this.nickName = nickName;
		this.email = email;
		this.city=city;
		this.isValid = isValid;
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userAccount) {
		this.userName = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String userPassword) {
		this.password = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", nickName=" + nickName + ", city=" + city + ", email="
				+ email + ", isValid=" + isValid + "]";
	}



	
	

}
