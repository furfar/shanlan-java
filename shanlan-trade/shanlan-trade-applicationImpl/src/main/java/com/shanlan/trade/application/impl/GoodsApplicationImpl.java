
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

import com.shanlan.trade.application.GoodsApplication;
import com.shanlan.trade.application.dto.GoodsDTO;
import com.shanlan.trade.core.domain.Goods;

@Named
@Transactional
public class GoodsApplicationImpl implements GoodsApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GoodsDTO getGoods(Integer id) {
		Goods goods = Goods.load(Goods.class, id);
		GoodsDTO goodsDTO = new GoodsDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(goodsDTO, goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goodsDTO.setId((Integer)goods.getId());
		return goodsDTO;
	}
	
	public GoodsDTO saveGoods(GoodsDTO goodsDTO) {
		Goods goods = new Goods();
		try {
        	BeanUtils.copyProperties(goods, goodsDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		goods.save();
		goodsDTO.setId((Integer)goods.getId());
		return goodsDTO;
	}
	
	public void updateGoods(GoodsDTO goodsDTO) {
		Goods goods = Goods.get(Goods.class, goodsDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(goods, goodsDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeGoods(Integer id) {
		this.removeGoodss(new Integer[] { id });
	}
	
	public void removeGoodss(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Goods goods = Goods.load(Goods.class, ids[i]);
			goods.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GoodsDTO> findAllGoods() {
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		List<Goods> all = Goods.findAll(Goods.class);
		for (Goods goods : all) {
			GoodsDTO goodsDTO = new GoodsDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(goodsDTO, goods);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(goodsDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<GoodsDTO> pageQueryGoods(GoodsDTO queryVo, int currentPage, int pageSize) {
		List<GoodsDTO> result = new ArrayList<GoodsDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _goods from Goods _goods   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _goods.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
	   		jpql.append(" and _goods.description like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
	   	}		
	   	if (queryVo.getPrice() != null) {
	   		jpql.append(" and _goods.price=?");
	   		conditionVals.add(queryVo.getPrice());
	   	}	
	
	
	   	if (queryVo.getType() != null && !"".equals(queryVo.getType())) {
	   		jpql.append(" and _goods.type like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getType()));
	   	}		
	
	   	if (queryVo.getStatus() != null && !"".equals(queryVo.getStatus())) {
	   		jpql.append(" and _goods.status like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getStatus()));
	   	}		
	
	   	if (queryVo.getCreatedAt() != null) {
	   		jpql.append(" and _goods.createdAt between ? and ? ");
	   		conditionVals.add(queryVo.getCreatedAt());
	   		conditionVals.add(queryVo.getCreatedAtEnd());
	   	}	
	
	   	if (queryVo.getUpdatedAt() != null) {
	   		jpql.append(" and _goods.updatedAt between ? and ? ");
	   		conditionVals.add(queryVo.getUpdatedAt());
	   		conditionVals.add(queryVo.getUpdatedAtEnd());
	   	}	
	
	   	if (queryVo.getValidDate() != null) {
	   		jpql.append(" and _goods.validDate between ? and ? ");
	   		conditionVals.add(queryVo.getValidDate());
	   		conditionVals.add(queryVo.getValidDateEnd());
	   	}	
	
	   	if (queryVo.getInvalidDate() != null) {
	   		jpql.append(" and _goods.invalidDate between ? and ? ");
	   		conditionVals.add(queryVo.getInvalidDate());
	   		conditionVals.add(queryVo.getInvalidDateEnd());
	   	}	
	
	   	if (queryVo.getAddress() != null && !"".equals(queryVo.getAddress())) {
	   		jpql.append(" and _goods.address like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAddress()));
	   	}		
	
	   	if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
	   		jpql.append(" and _goods.other like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
	   	}		
        Page<Goods> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (Goods goods : pages.getData()) {
            GoodsDTO goodsDTO = new GoodsDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(goodsDTO, goods);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                                                                                                       result.add(goodsDTO);
        }
        return new Page<GoodsDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
