package com.shanlan.opf.core.repository;

import java.util.List;

import com.shanlan.user.core.domain.UserBase;

/**
 * @ClassName:ServiceDao 
 * @Description: TODO		
 * @Author Albert
 * @Date:2013-2-4 下午9:48:04
 *
 * @Remarks:
 * @Version:V1.1
 */
public interface UserRepository {

	List<UserBase> getUserByUserName(String userName);

    boolean add(UserBase user);

    List<UserBase> getUserByEmail(String email);
}
