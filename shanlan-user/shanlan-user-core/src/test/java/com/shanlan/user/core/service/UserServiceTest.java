package com.shanlan.user.core.service;

import org.openkoala.koala.util.KoalaBaseSpringTestCase;
import org.junit.Test;

import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.user.core.domain.UserBase;

/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * @author lingen
 *
 */
public class UserServiceTest extends KoalaBaseSpringTestCase {

	@Test
	public void testRegister(){
		UserBase userBase=new UserBase("sdfas", "fdsfa", "sdf@asdf.com");
		try {
			UserService.register(userBase);
		} catch (RequestParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}