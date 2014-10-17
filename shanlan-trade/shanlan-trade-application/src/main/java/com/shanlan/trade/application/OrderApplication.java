
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.OrderDTO;

public interface OrderApplication {

	public OrderDTO getOrder(Integer id);
	
	public OrderDTO saveOrder(OrderDTO order);
	
	public void updateOrder(OrderDTO order);
	
	public void removeOrder(Integer id);
	
	public void removeOrders(Integer[] ids);
	
	public List<OrderDTO> findAllOrder();
	
	public Page<OrderDTO> pageQueryOrder(OrderDTO order, int currentPage, int pageSize);
	

}

