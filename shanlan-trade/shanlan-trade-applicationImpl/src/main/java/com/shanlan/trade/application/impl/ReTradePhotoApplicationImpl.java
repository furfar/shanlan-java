
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

import com.shanlan.trade.application.ReTradePhotoApplication;
import com.shanlan.trade.application.dto.ReTradePhotoDTO;
import com.shanlan.trade.core.domain.ReTradePhoto;

@Named
@Transactional
public class ReTradePhotoApplicationImpl implements ReTradePhotoApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReTradePhotoDTO getReTradePhoto(Integer id) {
		ReTradePhoto reTradePhoto = ReTradePhoto.load(ReTradePhoto.class, id);
		ReTradePhotoDTO reTradePhotoDTO = new ReTradePhotoDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(reTradePhotoDTO, reTradePhoto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reTradePhotoDTO.setId((Integer)reTradePhoto.getId());
		return reTradePhotoDTO;
	}
	
	public ReTradePhotoDTO saveReTradePhoto(ReTradePhotoDTO reTradePhotoDTO) {
		ReTradePhoto reTradePhoto = new ReTradePhoto();
		try {
        	BeanUtils.copyProperties(reTradePhoto, reTradePhotoDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		reTradePhoto.save();
		reTradePhotoDTO.setId((Integer)reTradePhoto.getId());
		return reTradePhotoDTO;
	}
	
	public void updateReTradePhoto(ReTradePhotoDTO reTradePhotoDTO) {
		ReTradePhoto reTradePhoto = ReTradePhoto.get(ReTradePhoto.class, reTradePhotoDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(reTradePhoto, reTradePhotoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeReTradePhoto(Integer id) {
		this.removeReTradePhotos(new Integer[] { id });
	}
	
	public void removeReTradePhotos(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			ReTradePhoto reTradePhoto = ReTradePhoto.load(ReTradePhoto.class, ids[i]);
			reTradePhoto.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReTradePhotoDTO> findAllReTradePhoto() {
		List<ReTradePhotoDTO> list = new ArrayList<ReTradePhotoDTO>();
		List<ReTradePhoto> all = ReTradePhoto.findAll(ReTradePhoto.class);
		for (ReTradePhoto reTradePhoto : all) {
			ReTradePhotoDTO reTradePhotoDTO = new ReTradePhotoDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(reTradePhotoDTO, reTradePhoto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(reTradePhotoDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<ReTradePhotoDTO> pageQueryReTradePhoto(ReTradePhotoDTO queryVo, int currentPage, int pageSize) {
		List<ReTradePhotoDTO> result = new ArrayList<ReTradePhotoDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _reTradePhoto from ReTradePhoto _reTradePhoto   where 1=1 ");
	
	   	if (queryVo.getTpcId() != null) {
	   		jpql.append(" and _reTradePhoto.tpcId=?");
	   		conditionVals.add(queryVo.getTpcId());
	   	}	
	
	   	if (queryVo.getUpoId() != null) {
	   		jpql.append(" and _reTradePhoto.upoId=?");
	   		conditionVals.add(queryVo.getUpoId());
	   	}	
	
        Page<ReTradePhoto> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (ReTradePhoto reTradePhoto : pages.getData()) {
            ReTradePhotoDTO reTradePhotoDTO = new ReTradePhotoDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(reTradePhotoDTO, reTradePhoto);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                result.add(reTradePhotoDTO);
        }
        return new Page<ReTradePhotoDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
