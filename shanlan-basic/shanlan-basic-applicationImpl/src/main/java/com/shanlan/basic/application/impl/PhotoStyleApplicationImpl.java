
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

import com.shanlan.basic.application.PhotoStyleApplication;
import com.shanlan.basic.application.dto.PhotoStyleDTO;
import com.shanlan.basic.core.domain.PhotoStyle;

@Named
@Transactional
public class PhotoStyleApplicationImpl implements PhotoStyleApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PhotoStyleDTO getPhotoStyle(Integer id) {
		PhotoStyle photoStyle = PhotoStyle.load(PhotoStyle.class, id);
		PhotoStyleDTO photoStyleDTO = new PhotoStyleDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(photoStyleDTO, photoStyle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		photoStyleDTO.setId((Integer)photoStyle.getId());
		return photoStyleDTO;
	}
	
	public PhotoStyleDTO savePhotoStyle(PhotoStyleDTO photoStyleDTO) {
		PhotoStyle photoStyle = new PhotoStyle();
		try {
        	BeanUtils.copyProperties(photoStyle, photoStyleDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		photoStyle.save();
		photoStyleDTO.setId((Integer)photoStyle.getId());
		return photoStyleDTO;
	}
	
	public void updatePhotoStyle(PhotoStyleDTO photoStyleDTO) {
		PhotoStyle photoStyle = PhotoStyle.get(PhotoStyle.class, photoStyleDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(photoStyle, photoStyleDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePhotoStyle(Integer id) {
		this.removePhotoStyles(new Integer[] { id });
	}
	
	public void removePhotoStyles(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			PhotoStyle photoStyle = PhotoStyle.load(PhotoStyle.class, ids[i]);
			photoStyle.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoStyleDTO> findAllPhotoStyle() {
		List<PhotoStyleDTO> list = new ArrayList<PhotoStyleDTO>();
		List<PhotoStyle> all = PhotoStyle.findAll(PhotoStyle.class);
		for (PhotoStyle photoStyle : all) {
			PhotoStyleDTO photoStyleDTO = new PhotoStyleDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(photoStyleDTO, photoStyle);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(photoStyleDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<PhotoStyleDTO> pageQueryPhotoStyle(PhotoStyleDTO queryVo, int currentPage, int pageSize) {
		List<PhotoStyleDTO> result = new ArrayList<PhotoStyleDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _photoStyle from PhotoStyle _photoStyle   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _photoStyle.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
	   		jpql.append(" and _photoStyle.description like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
	   	}		
        Page<PhotoStyle> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (PhotoStyle photoStyle : pages.getData()) {
            PhotoStyleDTO photoStyleDTO = new PhotoStyleDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(photoStyleDTO, photoStyle);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                result.add(photoStyleDTO);
        }
        return new Page<PhotoStyleDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
