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
@Table(name="photo_collection")
public class PhotoCollection extends KoalaLegacyEntity {

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
   
   

    @Column(name="name")
  private String name;
  

    @Column(name="creator")
  private String creator;
  

    @Column(name="created_at")
  private Timestamp createdAt;
  

    @Column(name="updated_at")
  private Timestamp updatedAt;
  

    @Column(name="photo_quantity")
  private int photoQuantity;
  

    @Column(name="other")
  private String other;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public String getName() {
		return name;
  }
    public void setName(String name) {
		this.name = name;
  }
  
  
    
    public String getCreator() {
		return creator;
  }
    public void setCreator(String creator) {
		this.creator = creator;
  }
  
  
    
    public Timestamp getCreatedAt() {
		return createdAt;
  }
    public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
  }
  
  
    
    public Timestamp getUpdatedAt() {
		return updatedAt;
  }
    public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
  }
  
  
    
    public int getPhotoQuantity() {
		return photoQuantity;
  }
    public void setPhotoQuantity(int photoQuantity) {
		this.photoQuantity = photoQuantity;
  }
  
  
    
    public String getOther() {
		return other;
  }
    public void setOther(String other) {
		this.other = other;
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