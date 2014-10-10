package com.shanlan.trade.core.domin;

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
@Table(name="order")
public class Order extends KoalaLegacyEntity {

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
   
   

    @Column(name="buyer")
  private String buyer;
  

    @Column(name="seller")
  private String seller;
  

    @Column(name="created_at")
  private Timestamp createdAt;
  

    @Column(name="status")
  private String status;
  

    @Column(name="receiver")
  private String receiver;
  

    @Column(name="receive_addre_id")
  private int receiveAddreId;
  

    @Column(name="other")
  private String other;
  

      
         
       public void setId(int id) {
		  this.id = id;
       }
   
   

  
    
    public String getBuyer() {
		return buyer;
  }
    public void setBuyer(String buyer) {
		this.buyer = buyer;
  }
  
  
    
    public String getSeller() {
		return seller;
  }
    public void setSeller(String seller) {
		this.seller = seller;
  }
  
  
    
    public Timestamp getCreatedAt() {
		return createdAt;
  }
    public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
  }
  
  
    
    public String getStatus() {
		return status;
  }
    public void setStatus(String status) {
		this.status = status;
  }
  
  
    
    public String getReceiver() {
		return receiver;
  }
    public void setReceiver(String receiver) {
		this.receiver = receiver;
  }
  
  
    
    public int getReceiveAddreId() {
		return receiveAddreId;
  }
    public void setReceiveAddreId(int receiveAddreId) {
		this.receiveAddreId = receiveAddreId;
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