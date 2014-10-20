package com.shanlan.photo.core.domain;

import com.shanlan.common.util.JPQLUtil;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "photo")
public class Photo extends KoalaLegacyEntity {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键
	 *
	 **/

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "md5")
	private String md5;

	@Column(name = "size")
	private int size;

	@Column(name = "like_count")
	private int likeCount;

	@Column(name = "other")
	private String other;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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


    public static List<Photo> list(List<Integer> ids){
        List<Photo> photos = new ArrayList<Photo>();
        String jpql = JPQLUtil.selectByIdIn(Photo.class, ids);
        if (StringUtils.isNotBlank(jpql)) {
            photos = getRepository().createJpqlQuery(jpql).list();
        }
        return photos;
    }

}