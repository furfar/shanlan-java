
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

import com.shanlan.trade.application.TradePhotoCollectionApplication;
import com.shanlan.trade.application.dto.TradePhotoCollectionDTO;
import com.shanlan.trade.core.domin.TradePhotoCollection;

@Named
@Transactional
public class TradePhotoCollectionApplicationImpl implements TradePhotoCollectionApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TradePhotoCollectionDTO getTradePhotoCollection(Integer id) {
		TradePhotoCollection tradePhotoCollection = TradePhotoCollection.load(TradePhotoCollection.class, id);
		TradePhotoCollectionDTO tradePhotoCollectionDTO = new TradePhotoCollectionDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(tradePhotoCollectionDTO, tradePhotoCollection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tradePhotoCollectionDTO.setId((Integer)tradePhotoCollection.getId());
		return tradePhotoCollectionDTO;
	}
	
	public TradePhotoCollectionDTO saveTradePhotoCollection(TradePhotoCollectionDTO tradePhotoCollectionDTO) {
		TradePhotoCollection tradePhotoCollection = new TradePhotoCollection();
		try {
        	BeanUtils.copyProperties(tradePhotoCollection, tradePhotoCollectionDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		tradePhotoCollection.save();
		tradePhotoCollectionDTO.setId((Integer)tradePhotoCollection.getId());
		return tradePhotoCollectionDTO;
	}
	
	public void updateTradePhotoCollection(TradePhotoCollectionDTO tradePhotoCollectionDTO) {
		TradePhotoCollection tradePhotoCollection = TradePhotoCollection.get(TradePhotoCollection.class, tradePhotoCollectionDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(tradePhotoCollection, tradePhotoCollectionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeTradePhotoCollection(Integer id) {
		this.removeTradePhotoCollections(new Integer[] { id });
	}
	
	public void removeTradePhotoCollections(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			TradePhotoCollection tradePhotoCollection = TradePhotoCollection.load(TradePhotoCollection.class, ids[i]);
			tradePhotoCollection.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TradePhotoCollectionDTO> findAllTradePhotoCollection() {
		List<TradePhotoCollectionDTO> list = new ArrayList<TradePhotoCollectionDTO>();
		List<TradePhotoCollection> all = TradePhotoCollection.findAll(TradePhotoCollection.class);
		for (TradePhotoCollection tradePhotoCollection : all) {
			TradePhotoCollectionDTO tradePhotoCollectionDTO = new TradePhotoCollectionDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(tradePhotoCollectionDTO, tradePhotoCollection);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(tradePhotoCollectionDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<TradePhotoCollectionDTO> pageQueryTradePhotoCollection(TradePhotoCollectionDTO queryVo, int currentPage, int pageSize) {
		List<TradePhotoCollectionDTO> result = new ArrayList<TradePhotoCollectionDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _tradePhotoCollection from TradePhotoCollection _tradePhotoCollection   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _tradePhotoCollection.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
	   		jpql.append(" and _tradePhotoCollection.description like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
	   	}		
	
	   	if (queryVo.getOwner() != null && !"".equals(queryVo.getOwner())) {
	   		jpql.append(" and _tradePhotoCollection.owner like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOwner()));
	   	}		
	   	if (queryVo.getOrderId() != null) {
	   		jpql.append(" and _tradePhotoCollection.orderId=?");
	   		conditionVals.add(queryVo.getOrderId());
	   	}	
	
	   	if (queryVo.getPhotoQuantity() != null) {
	   		jpql.append(" and _tradePhotoCollection.photoQuantity=?");
	   		conditionVals.add(queryVo.getPhotoQuantity());
	   	}	
	
	
	   	if (queryVo.getCreatedAt() != null) {
	   		jpql.append(" and _tradePhotoCollection.createdAt between ? and ? ");
	   		conditionVals.add(queryVo.getCreatedAt());
	   		conditionVals.add(queryVo.getCreatedAtEnd());
	   	}	
	
	   	if (queryVo.getUpdatedAt() != null) {
	   		jpql.append(" and _tradePhotoCollection.updatedAt between ? and ? ");
	   		conditionVals.add(queryVo.getUpdatedAt());
	   		conditionVals.add(queryVo.getUpdatedAtEnd());
	   	}	
        Page<TradePhotoCollection> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (TradePhotoCollection tradePhotoCollection : pages.getData()) {
            TradePhotoCollectionDTO tradePhotoCollectionDTO = new TradePhotoCollectionDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(tradePhotoCollectionDTO, tradePhotoCollection);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                                           result.add(tradePhotoCollectionDTO);
        }
        return new Page<TradePhotoCollectionDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
