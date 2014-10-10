package com.shanlan.opf.application.dto;


/**
 * @ClassName:User
 * @Description: 用户基本信息
 * @Author Albert
 * @Date:2013-4-7 下午12:10:04
 * @Remarks:Ø
 * @Version:V1.1
 */

public class UserBase {

	private int id;

	private String userName;

	private String nickName;

	private String email;

	private Integer isValid;

	/**
     *
     */
	public UserBase() {
	}

	public UserBase(String userAccount, String nickName, String email,
			Integer isValid) {
		super();
		this.userName = userAccount;
		this.nickName = nickName;
		this.email = email;
		this.isValid = isValid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "UserBase [id=" + id + ", userName=" + userName + ", nickName="
				+ nickName + ", email=" + email + ", isValid=" + isValid + "]";
	}
}
