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
@Table(name="photo_packages")
public class PhotoPackages extends KoalaLegacyEntity {

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
  

    @Column(name="description")
  private String description;
  

    @Column(name="photographer")
  private String photographer;
  

    @Column(name="price")
  private float price;
  

    @Column(name="days")
  private short days;
  

    @Column(name="photo_counts")
  private short photoCounts;
  

    @Column(name="alter_counts")
  private short alterCounts;
  

    @Column(name="place_id")
  private int placeId;
  

    @Column(name="status")
  private String status;
  

    @Column(name="created_at")
  private Timestamp createdAt;
  

    @Column(name="updated_at")
  private Timestamp updatedAt;
  

    @Column(name="invalid_date")
  private Date invalidDate;
  

    @Column(name="valid_date")
  private Date validDate;
  

    @Column(name="other")
  private String other;
  

    @Column(name="note")
  private String note;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public String getName() {
		return name;
  }
    public void setName(String name) {
		this.name = name;
  }
  
  
    
    public String getDescription() {
		return description;
  }
    public void setDescription(String description) {
		this.description = description;
  }
  
  
    
    public String getPhotographer() {
		return photographer;
  }
    public void setPhotographer(String photographer) {
		this.photographer = photographer;
  }
  
  
    
    public float getPrice() {
		return price;
  }
    public void setPrice(float price) {
		this.price = price;
  }
  
  
    
    public short getDays() {
		return days;
  }
    public void setDays(short days) {
		this.days = days;
  }
  
  
    
    public short getPhotoCounts() {
		return photoCounts;
  }
    public void setPhotoCounts(short photoCounts) {
		this.photoCounts = photoCounts;
  }
  
  
    
    public short getAlterCounts() {
		return alterCounts;
  }
    public void setAlterCounts(short alterCounts) {
		this.alterCounts = alterCounts;
  }
  
  
    
    public int getPlaceId() {
		return placeId;
  }
    public void setPlaceId(int placeId) {
		this.placeId = placeId;
  }
  
  
    
    public String getStatus() {
		return status;
  }
    public void setStatus(String status) {
		this.status = status;
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
  
  
    
    public Date getInvalidDate() {
		return invalidDate;
  }
    public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
  }
  
  
    
    public Date getValidDate() {
		return validDate;
  }
    public void setValidDate(Date validDate) {
		this.validDate = validDate;
  }
  
  
    
    public String getOther() {
		return other;
  }
    public void setOther(String other) {
		this.other = other;
  }
  
  
    
    public String getNote() {
		return note;
  }
    public void setNote(String note) {
		this.note = note;
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