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
@Table(name="photo")
public class Photo extends KoalaLegacyEntity {

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
   
   

    @Column(name="file_path")
  private String filePath;
  

    @Column(name="size")
  private int size;
  

    @Column(name="md5")
  private String md;
  

    @Column(name="like_count")
  private int likeCount;
  

    @Column(name="other")
  private String other;
  

      
         
       public void setId(int id) {
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
  
  
    
    public String getMd() {
		return md;
  }
    public void setMd(String md) {
		this.md = md;
  }
  
  
    
    public int getLikeCount() {
		return likeCount;
  }
    public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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