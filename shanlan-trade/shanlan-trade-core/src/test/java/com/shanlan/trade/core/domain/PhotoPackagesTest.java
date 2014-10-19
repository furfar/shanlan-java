package com.shanlan.trade.core.domain;

import javax.persistence.EntityManagerFactory;

import org.dayatang.domain.EntityRepository;
import org.dayatang.domain.InstanceFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import java.util.List;

/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * 
 * @author lingen
 *
 */
@Ignore
public class PhotoPackagesTest extends KoalaBaseSpringTestCase {

	@Test
	public void testList() {
		// 在这里，你可以直接使用InstanceFactory获取各种spring的bean
//		EntityRepository repository = InstanceFactory
//				.getInstance(EntityRepository.class);
//		EntityManagerFactory entityManagerFactory = InstanceFactory
//				.getInstance(EntityManagerFactory.class);

//		repository
//				.createJpqlQuery("select * from photo_packages as pp , re_photo_type as rpt where pp.photographer='wangwu3' "
//						+ "and rpt.re_type='PHOTO_PACKAGES' and rpt.type_name='亲子家庭' and rpt.re_id=pp.id;");

//		List<Service> services=repository.createJpqlQuery("select s from Service s").list();
//		
//		System.out.println(services.size());
		// entityManagerFactory
		// 同样，你可以直接调用领域类的领域方法
		// User.add("koala");



        List<PhotoPackages> photoPackagesList= PhotoPackages.list("wangwu3");
        System.out.println(photoPackagesList.size());

	}
}