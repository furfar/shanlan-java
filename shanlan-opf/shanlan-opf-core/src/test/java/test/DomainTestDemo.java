package test;

import java.util.List;

import org.dayatang.domain.EntityRepository;
import org.dayatang.domain.InstanceFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import com.shanlan.opf.core.domain.Service;



/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * @author lingen
 *
 */
@Ignore
public class DomainTestDemo extends KoalaBaseSpringTestCase {

	@Test
	public void test(){
		//在这里，你可以直接使用InstanceFactory获取各种spring的bean
		EntityRepository repository = InstanceFactory.getInstance(EntityRepository.class);
		
		List<Service> services=repository.createJpqlQuery("select s from Service s").list();
//		List<Service> services=repository.createNamedQuery("findEmployeesByAgeRange").list();
		
		System.out.println(services.size());
		//同样，你可以直接调用领域类的领域方法
		//UserDetail.addUser("koala");
		
	}
	
}