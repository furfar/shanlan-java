package com.shanlan.user.core.domain;

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
@Table(name="model")
public class Model extends KoalaLegacyEntity {

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
   
   

    @Column(name="user_name")
  private String userName;
  

    @Column(name="height")
  private short height;
  

    @Column(name="weight")
  private short weight;
  

    @Column(name="chest")
  private short chest;
  

    @Column(name="waistline")
  private short waistline;
  

    @Column(name="hip")
  private short hip;
  

    @Column(name="other")
  private String other;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public String getUserName() {
		return userName;
  }
    public void setUserName(String userName) {
		this.userName = userName;
  }
  
  
    
    public short getHeight() {
		return height;
  }
    public void setHeight(short height) {
		this.height = height;
  }
  
  
    
    public short getWeight() {
		return weight;
  }
    public void setWeight(short weight) {
		this.weight = weight;
  }
  
  
    
    public short getChest() {
		return chest;
  }
    public void setChest(short chest) {
		this.chest = chest;
  }
  
  
    
    public short getWaistline() {
		return waistline;
  }
    public void setWaistline(short waistline) {
		this.waistline = waistline;
  }
  
  
    
    public short getHip() {
		return hip;
  }
    public void setHip(short hip) {
		this.hip = hip;
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