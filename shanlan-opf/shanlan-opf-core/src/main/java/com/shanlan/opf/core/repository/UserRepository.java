package com.shanlan.opf.core.repository;

import java.util.List;

import com.shanlan.user.core.domain.User;

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

	List<User> getUserByUserName(String userName);

    boolean add(User user);

    List<User> getUserByEmail(String email);
}
