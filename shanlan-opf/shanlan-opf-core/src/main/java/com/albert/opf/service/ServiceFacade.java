/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:ServiceFacade 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-1-22 下午11:24:11
 *
 * @Remarks:
 * @Version:V1.1
 */
@Service
public class ServiceFacade {
	
	@Autowired
	public InvokeService invokeService;
	@Autowired
	public UserService userService;
	
}
