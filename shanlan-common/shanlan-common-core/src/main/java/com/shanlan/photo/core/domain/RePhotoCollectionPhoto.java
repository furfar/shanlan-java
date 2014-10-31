package com.shanlan.photo.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "re_photo_collection_photo")
public class RePhotoCollectionPhoto extends KoalaLegacyEntity {

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

	@Column(name = "upo_id")
	private int upoId;

	@Column(name = "pc_id")
	private int pcId;


    public RePhotoCollectionPhoto(){

    }

    public RePhotoCollectionPhoto(Integer upoId,Integer pcId){
        this.upoId=upoId;
        this.pcId=pcId;
    }

	public void setId(int id) {
		this.id = id;
	}

	public int getUpoId() {
		return upoId;
	}

	public void setUpoId(int upoId) {
		this.upoId = upoId;
	}

	public int getPcId() {
		return pcId;
	}

	public void setPcId(int pcId) {
		this.pcId = pcId;
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

	public static List<RePhotoCollectionPhoto> findByPhotoCollectionId(
			Integer photoCollectionId) {
		List<RePhotoCollectionPhoto> rePhotoCollectionPhotos = new ArrayList<RePhotoCollectionPhoto>();
		if (photoCollectionId != null && photoCollectionId > 0) {
			rePhotoCollectionPhotos = findByProperty(
					RePhotoCollectionPhoto.class, "pcId", photoCollectionId);
		}
		return rePhotoCollectionPhotos;
	}




}