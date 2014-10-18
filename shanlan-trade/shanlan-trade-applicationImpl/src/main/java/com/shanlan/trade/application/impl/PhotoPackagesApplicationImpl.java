
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

import com.shanlan.trade.application.PhotoPackagesApplication;
import com.shanlan.trade.application.dto.PhotoPackagesDTO;
import com.shanlan.trade.core.domin.PhotoPackages;

@Named
@Transactional
public class PhotoPackagesApplicationImpl implements PhotoPackagesApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PhotoPackagesDTO getPhotoPackages(Integer id) {
		PhotoPackages photoPackages = PhotoPackages.load(PhotoPackages.class, id);
		PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		photoPackagesDTO.setId((Integer)photoPackages.getId());
		return photoPackagesDTO;
	}
	
	public PhotoPackagesDTO savePhotoPackages(PhotoPackagesDTO photoPackagesDTO) {
		PhotoPackages photoPackages = new PhotoPackages();
		try {
        	BeanUtils.copyProperties(photoPackages, photoPackagesDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		photoPackages.save();
		photoPackagesDTO.setId((Integer)photoPackages.getId());
		return photoPackagesDTO;
	}
	
	public void updatePhotoPackages(PhotoPackagesDTO photoPackagesDTO) {
		PhotoPackages photoPackages = PhotoPackages.get(PhotoPackages.class, photoPackagesDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(photoPackages, photoPackagesDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePhotoPackages(Integer id) {
		this.removePhotoPackagess(new Integer[] { id });
	}
	
	public void removePhotoPackagess(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			PhotoPackages photoPackages = PhotoPackages.load(PhotoPackages.class, ids[i]);
			photoPackages.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoPackagesDTO> findAllPhotoPackages() {
		List<PhotoPackagesDTO> list = new ArrayList<PhotoPackagesDTO>();
		List<PhotoPackages> all = PhotoPackages.findAll(PhotoPackages.class);
		for (PhotoPackages photoPackages : all) {
			PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(photoPackagesDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<PhotoPackagesDTO> pageQueryPhotoPackages(PhotoPackagesDTO queryVo, int currentPage, int pageSize) {
		List<PhotoPackagesDTO> result = new ArrayList<PhotoPackagesDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _photoPackages from PhotoPackages _photoPackages   where 1=1 ");
	
	   	if (queryVo.getGoodsId() != null) {
	   		jpql.append(" and _photoPackages.goodsId=?");
	   		conditionVals.add(queryVo.getGoodsId());
	   	}	
	
	
	   	if (queryVo.getPhotographer() != null && !"".equals(queryVo.getPhotographer())) {
	   		jpql.append(" and _photoPackages.photographer like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPhotographer()));
	   	}		
	   	if (queryVo.getDays() != null) {
	   		jpql.append(" and _photoPackages.days=?");
	   		conditionVals.add(queryVo.getDays());
	   	}	
	
	   	if (queryVo.getPhotoCounts() != null) {
	   		jpql.append(" and _photoPackages.photoCounts=?");
	   		conditionVals.add(queryVo.getPhotoCounts());
	   	}	
	
	   	if (queryVo.getAlterCounts() != null) {
	   		jpql.append(" and _photoPackages.alterCounts=?");
	   		conditionVals.add(queryVo.getAlterCounts());
	   	}	
	
	   	if (queryVo.getPlaceId() != null) {
	   		jpql.append(" and _photoPackages.placeId=?");
	   		conditionVals.add(queryVo.getPlaceId());
	   	}	
	
	
	   	if (queryVo.getNote() != null && !"".equals(queryVo.getNote())) {
	   		jpql.append(" and _photoPackages.note like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNote()));
	   	}		
	
	   	if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
	   		jpql.append(" and _photoPackages.other like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
	   	}		
        Page<PhotoPackages> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (PhotoPackages photoPackages : pages.getData()) {
            PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                                                          result.add(photoPackagesDTO);
        }
        return new Page<PhotoPackagesDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}