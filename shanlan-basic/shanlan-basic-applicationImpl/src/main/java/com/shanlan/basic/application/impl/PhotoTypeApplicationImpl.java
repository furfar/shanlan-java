
package com.shanlan.basic.application.impl;

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

import com.shanlan.basic.application.PhotoTypeApplication;
import com.shanlan.basic.application.dto.PhotoTypeDTO;
import com.shanlan.basic.core.domain.PhotoType;

@Named
@Transactional
public class PhotoTypeApplicationImpl implements PhotoTypeApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PhotoTypeDTO getPhotoType(Integer id) {
		PhotoType photoType = PhotoType.load(PhotoType.class, id);
		PhotoTypeDTO photoTypeDTO = new PhotoTypeDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(photoTypeDTO, photoType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		photoTypeDTO.setId((Integer)photoType.getId());
		return photoTypeDTO;
	}
	
	public PhotoTypeDTO savePhotoType(PhotoTypeDTO photoTypeDTO) {
		PhotoType photoType = new PhotoType();
		try {
        	BeanUtils.copyProperties(photoType, photoTypeDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		photoType.save();
		photoTypeDTO.setId((Integer)photoType.getId());
		return photoTypeDTO;
	}
	
	public void updatePhotoType(PhotoTypeDTO photoTypeDTO) {
		PhotoType photoType = PhotoType.get(PhotoType.class, photoTypeDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(photoType, photoTypeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePhotoType(Integer id) {
		this.removePhotoTypes(new Integer[] { id });
	}
	
	public void removePhotoTypes(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			PhotoType photoType = PhotoType.load(PhotoType.class, ids[i]);
			photoType.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoTypeDTO> findAllPhotoType() {
		List<PhotoTypeDTO> list = new ArrayList<PhotoTypeDTO>();
		List<PhotoType> all = PhotoType.findAll(PhotoType.class);
		for (PhotoType photoType : all) {
			PhotoTypeDTO photoTypeDTO = new PhotoTypeDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(photoTypeDTO, photoType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(photoTypeDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<PhotoTypeDTO> pageQueryPhotoType(PhotoTypeDTO queryVo, int currentPage, int pageSize) {
		List<PhotoTypeDTO> result = new ArrayList<PhotoTypeDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _photoType from PhotoType _photoType   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _photoType.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
	   		jpql.append(" and _photoType.description like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
	   	}		
        Page<PhotoType> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (PhotoType photoType : pages.getData()) {
            PhotoTypeDTO photoTypeDTO = new PhotoTypeDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(photoTypeDTO, photoType);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                result.add(photoTypeDTO);
        }
        return new Page<PhotoTypeDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
