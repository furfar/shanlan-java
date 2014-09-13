/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.manager.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName:CheckingService
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 下午3:16:10
 * 
 * @Remarks:
 * @Version:V1.1
 */
public interface UserService {

	UserDetails getUserByUsername(String username);

}
