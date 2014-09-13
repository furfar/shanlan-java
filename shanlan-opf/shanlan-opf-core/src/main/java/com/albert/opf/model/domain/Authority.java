/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.model.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName:Authority 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-4-7 下午5:51:36
 *
 * @Remarks:
 * @Version:V1.1
 */
public class Authority implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7602488089000078088L;
	private String authority;

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
}
