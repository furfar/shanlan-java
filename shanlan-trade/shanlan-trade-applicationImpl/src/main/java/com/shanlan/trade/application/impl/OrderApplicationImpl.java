
package com.shanlan.trade.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.trade.application.OrderApplication;
import com.shanlan.trade.application.dto.OrderDTO;
import com.shanlan.trade.core.domain.TradeOrder;

@Named
@Transactional
public class OrderApplicationImpl implements OrderApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OrderDTO getOrder(Integer id) {
		TradeOrder tradeOrder = TradeOrder.load(TradeOrder.class, id);
		OrderDTO orderDTO = new OrderDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(orderDTO, tradeOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		orderDTO.setId((Integer)tradeOrder.getId());
		return orderDTO;
	}
	
	public OrderDTO saveOrder(OrderDTO orderDTO) {
		TradeOrder tradeOrder = new TradeOrder();
		try {
        	BeanUtils.copyProperties(tradeOrder, orderDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		tradeOrder.save();
		orderDTO.setId((Integer)tradeOrder.getId());
		return orderDTO;
	}
	
	public void updateOrder(OrderDTO orderDTO) {
		TradeOrder tradeOrder = TradeOrder.get(TradeOrder.class, orderDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(tradeOrder, orderDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeOrder(Integer id) {
		this.removeOrders(new Integer[] { id });
	}
	
	public void removeOrders(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			TradeOrder tradeOrder = TradeOrder.load(TradeOrder.class, ids[i]);
			tradeOrder.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OrderDTO> findAllOrder() {
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		List<TradeOrder> all = TradeOrder.findAll(TradeOrder.class);
		for (TradeOrder tradeOrder : all) {
			OrderDTO orderDTO = new OrderDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(orderDTO, tradeOrder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(orderDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<OrderDTO> pageQueryOrder(OrderDTO queryVo, int currentPage, int pageSize) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _order from TradeOrder _order   where 1=1 ");
	
	
	   	if (queryVo.getBuyer() != null && !"".equals(queryVo.getBuyer())) {
	   		jpql.append(" and _order.buyer like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getBuyer()));
	   	}		
	
	   	if (queryVo.getSeller() != null && !"".equals(queryVo.getSeller())) {
	   		jpql.append(" and _order.seller like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getSeller()));
	   	}		
	
	   	if (queryVo.getCreatedAt() != null) {
	   		jpql.append(" and _order.createdAt between ? and ? ");
	   		conditionVals.add(queryVo.getCreatedAt());
	   		conditionVals.add(queryVo.getCreatedAtEnd());
	   	}	
	
	   	if (queryVo.getStatus() != null && !"".equals(queryVo.getStatus())) {
	   		jpql.append(" and _order.status like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getStatus()));
	   	}		
	
	   	if (queryVo.getReceiver() != null && !"".equals(queryVo.getReceiver())) {
	   		jpql.append(" and _order.receiver like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getReceiver()));
	   	}		
	
	   	if (queryVo.getReceiveAddreId() != null) {
	   		jpql.append(" and _order.receiveAddreId=?");
	   		conditionVals.add(queryVo.getReceiveAddreId());
	   	}	
	
	
	   	if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
	   		jpql.append(" and _order.other like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
	   	}		
        Page<TradeOrder> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (TradeOrder tradeOrder : pages.getData()) {
            OrderDTO orderDTO = new OrderDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(orderDTO, tradeOrder);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                                                          result.add(orderDTO);
        }
        return new Page<OrderDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
