package com.shanlan.photo.core.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;
import java.io.Serializable;

/**
 * Auto Generated Entity
 * 
 * @author Koala 
 * 
 */
@Entity
@Table(name="re_photo_user_join")
public class RePhotoUserJoin extends KoalaLegacyEntity {

 private static final long serialVersionUID = 1L;
 
/**
*
* 主键
*
**/
      
       @Id
       @Column(name="id")
	   //@GeneratedValue(strategy = GenerationType.AUTO)
       private int id;
   
   

    @Column(name="upo_id")
  private int upoId;
  

    @Column(name="user_name")
  private String userName;
  

    @Column(name="photo_id")
  private int photoId;
  

    @Column(name="photo_path")
  private String photoPath;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public int getUpoId() {
		return upoId;
  }
    public void setUpoId(int upoId) {
		this.upoId = upoId;
  }
  
  
    
    public String getUserName() {
		return userName;
  }
    public void setUserName(String userName) {
		this.userName = userName;
  }
  
  
    
    public int getPhotoId() {
		return photoId;
  }
    public void setPhotoId(int photoId) {
		this.photoId = photoId;
  }
  
  
    
    public String getPhotoPath() {
		return photoPath;
  }
    public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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