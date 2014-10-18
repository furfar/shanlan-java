
package com.shanlan.trade.application.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.trade.application.OrderItemApplication;
import com.shanlan.trade.application.dto.OrderDTO;
import com.shanlan.trade.application.dto.OrderItemDTO;
import com.shanlan.trade.core.domin.Order;
import com.shanlan.trade.core.domin.OrderItem;

@Named
@Transactional
public class OrderItemApplicationImpl implements OrderItemApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OrderItemDTO getOrderItem(Integer id) {
		OrderItem orderItem = OrderItem.load(OrderItem.class, id);
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(orderItemDTO, orderItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		orderItemDTO.setId((Integer)orderItem.getId());
		return orderItemDTO;
	}
	
	public OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {
		OrderItem orderItem = new OrderItem();
		try {
        	BeanUtils.copyProperties(orderItem, orderItemDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		orderItem.save();
		orderItemDTO.setId((Integer)orderItem.getId());
		return orderItemDTO;
	}
	
	public void updateOrderItem(OrderItemDTO orderItemDTO) {
		OrderItem orderItem = OrderItem.get(OrderItem.class, orderItemDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(orderItem, orderItemDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeOrderItem(Integer id) {
		this.removeOrderItems(new Integer[] { id });
	}
	
	public void removeOrderItems(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			OrderItem orderItem = OrderItem.load(OrderItem.class, ids[i]);
			orderItem.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OrderItemDTO> findAllOrderItem() {
		List<OrderItemDTO> list = new ArrayList<OrderItemDTO>();
		List<OrderItem> all = OrderItem.findAll(OrderItem.class);
		for (OrderItem orderItem : all) {
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(orderItemDTO, orderItem);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(orderItemDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<OrderItemDTO> pageQueryOrderItem(OrderItemDTO queryVo, int currentPage, int pageSize) {
		List<OrderItemDTO> result = new ArrayList<OrderItemDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _orderItem from OrderItem _orderItem   left join _orderItem.order  where 1=1 ");
	
	
	   	if (queryVo.getGoodsId() != null) {
	   		jpql.append(" and _orderItem.goodsId=?");
	   		conditionVals.add(queryVo.getGoodsId());
	   	}	
	
	   	if (queryVo.getQuantity() != null) {
	   		jpql.append(" and _orderItem.quantity=?");
	   		conditionVals.add(queryVo.getQuantity());
	   	}	
	
	   	if (queryVo.getPrice() != null) {
	   		jpql.append(" and _orderItem.price=?");
	   		conditionVals.add(queryVo.getPrice());
	   	}	
	
	
        Page<OrderItem> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (OrderItem orderItem : pages.getData()) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(orderItemDTO, orderItem);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                              result.add(orderItemDTO);
        }
        return new Page<OrderItemDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OrderDTO findOrderByOrderItem(Integer id) {
		String jpql = "select e from OrderItem o right join o.order e where o.id=?";
		Order result = (Order) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[] { id }).singleResult();
		OrderDTO  dto = new OrderDTO();
		if (result != null) {
			try {
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	
}
