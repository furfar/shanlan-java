package com.shanlan.user.application.dto;


/**
 * @ClassName:User
 * @Description: 用户基本信息
 * @Author Albert
 * @Date:2013-4-7 下午12:10:04
 * @Remarks:Ø
 * @Version:V1.1
 */

public class UserBaseDTO {

	private int id;

	private String userName;

	private String email;

    private String password;

	/**
     *
     */
	public UserBaseDTO() {
	}

	public UserBaseDTO(String userName, String password, String email) {
		super();
		this.userName = userName;
        this.password=password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
