/**
 *
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.shanlan.user.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shanlan.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * @ClassName:UserDetail
 * @Description: 用户基本信息
 * @Author Albert
 * @Date:2013-4-7 下午12:10:04
 * @Remarks:Ø
 * @Version:V1.1
 */
@Entity
@Table(name = "user_introduction")
public class UserIntroduction extends KoalaLegacyEntity {

	private static final long serialVersionUID = 7697595059618556524L;
	// private static final Logger logger = LoggerFactory
	// .getLogger(UserIntroduction.class);
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "sequence")
	private Integer sequence;

	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;

	/**
     *
     */
	public UserIntroduction() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		if (StringUtils.isBlank(createdAt)) {
			this.createdAt = DateUtil.getNow(DateUtil.format1);
		} else {
			this.createdAt = createdAt;
		}
	}

	public String getUpdateddAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		if (StringUtils.isBlank(updatedAt)) {
			this.updatedAt = null;
		} else {
			this.updatedAt = updatedAt;
		}
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "UserIntroduction [id=" + id + ", userName=" + userName
				+ ", title=" + title + ", content=" + content + ", sequence="
				+ sequence + ", createdAt=" + createdAt + ", updateddAt="
				+ updatedAt + "]";
	}

	public static List<UserIntroduction> findByUserName(String userName) {
		List<UserIntroduction> userIntroductions = new ArrayList<UserIntroduction>();
		if (StringUtils.isNotBlank(userName)) {
			String propName = "userName";
			userIntroductions = findByProperty(UserIntroduction.class,
					propName, userName);
		}
		return userIntroductions;
	}

}
