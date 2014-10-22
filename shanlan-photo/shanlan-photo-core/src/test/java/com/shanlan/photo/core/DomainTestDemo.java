package com.shanlan.photo.core;

import org.openkoala.koala.util.KoalaBaseSpringTestCase;
import org.junit.Test;

import com.shanlan.photo.core.domain.Photo;
import com.shanlan.user.core.domain.UserBase;

/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * 
 * @author lingen
 *
 */
public class DomainTestDemo extends KoalaBaseSpringTestCase {

	@Test
	public void test() {
		// 在这里，你可以直接使用InstanceFactory获取各种spring的bean
		// EntityRepository repository =
		// InstanceFactory.getInstance(EntityRepository.class);
		// 同样，你可以直接调用领域类的领域方法
		// UserDetail.addUser("koala");

		Photo photo = Photo.get(Photo.class, 1);

		System.out.println(photo.getFilePath());
	}

}