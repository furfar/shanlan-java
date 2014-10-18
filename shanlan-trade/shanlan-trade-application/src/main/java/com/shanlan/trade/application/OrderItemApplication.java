
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.OrderDTO;
import com.shanlan.trade.application.dto.OrderItemDTO;

public interface OrderItemApplication {

	public OrderItemDTO getOrderItem(Integer id);
	
	public OrderItemDTO saveOrderItem(OrderItemDTO orderItem);
	
	public void updateOrderItem(OrderItemDTO orderItem);
	
	public void removeOrderItem(Integer id);
	
	public void removeOrderItems(Integer[] ids);
	
	public List<OrderItemDTO> findAllOrderItem();
	
	public Page<OrderItemDTO> pageQueryOrderItem(OrderItemDTO orderItem, int currentPage, int pageSize);
	
	public OrderDTO findOrderByOrderItem(Integer id);


	
}

