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
@Table(name="user_blog")
public class UserBlog extends KoalaLegacyEntity {

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
   
   

    @Column(name="creator")
  private String creator;
  

    @Column(name="title")
  private String title;
  

    @Column(name="content")
  private String content;
  

    @Column(name="created_at")
  private Timestamp createdAt;
  

    @Column(name="updated_at")
  private Timestamp updatedAt;
  

    @Column(name="view_times")
  private int viewTimes;
  

    @Column(name="status")
  private Boolean status;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public String getCreator() {
		return creator;
  }
    public void setCreator(String creator) {
		this.creator = creator;
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
  
  
    
    public int getViewTimes() {
		return viewTimes;
  }
    public void setViewTimes(int viewTimes) {
		this.viewTimes = viewTimes;
  }
  
  
    
    public Boolean getStatus() {
		return status;
  }
    public void setStatus(Boolean status) {
		this.status = status;
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