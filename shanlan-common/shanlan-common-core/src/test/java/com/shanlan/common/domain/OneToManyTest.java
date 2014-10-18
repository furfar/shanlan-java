package com.shanlan.common.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.dayatang.domain.InstanceFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import com.shanlan.trade.core.domin.Order;
import com.shanlan.trade.core.domin.OrderItem;

//@Ignore
public class OneToManyTest extends KoalaBaseSpringTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void addOrder() {
		EntityManagerFactory factory = InstanceFactory.getInstance(EntityManagerFactory.class);;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin(); // start transaction

		Order order = new Order();
		order.setBuyer("buyer");
		order.setSeller("seller");
		order.setReceiveAddreId(1);

		// order中包含的OrderItem项OrderItem1，OrderItem2
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setGoodsId(12);
		orderItem1.setPrice(23);
		order.addOrderItem(orderItem1); // add orderitem in order

		OrderItem orderItem2 = new OrderItem();
		orderItem1.setGoodsId(13);
		orderItem1.setPrice(33);
		
		
		order.addOrderItem(orderItem2);

		em.persist(order); // persist order object
		em.getTransaction().commit(); // commit transaction
		em.close();
		factory.close();
	}
}
