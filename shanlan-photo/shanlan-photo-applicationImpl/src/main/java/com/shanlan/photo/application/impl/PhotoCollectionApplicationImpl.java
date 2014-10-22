
package com.shanlan.photo.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.photo.application.PhotoCollectionApplication;
import com.shanlan.photo.application.dto.PhotoCollectionDTO;
import com.shanlan.photo.core.domain.PhotoCollection;

@Named
@Transactional
public class PhotoCollectionApplicationImpl implements PhotoCollectionApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PhotoCollectionDTO getPhotoCollection(Integer id) {
		PhotoCollection photoCollection = PhotoCollection.load(PhotoCollection.class, id);
		PhotoCollectionDTO photoCollectionDTO = new PhotoCollectionDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(photoCollectionDTO, photoCollection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		photoCollectionDTO.setId((Integer)photoCollection.getId());
		return photoCollectionDTO;
	}
	
	public PhotoCollectionDTO savePhotoCollection(PhotoCollectionDTO photoCollectionDTO) {
		PhotoCollection photoCollection = new PhotoCollection();
		try {
        	BeanUtils.copyProperties(photoCollection, photoCollectionDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		photoCollection.save();
		photoCollectionDTO.setId((Integer)photoCollection.getId());
		return photoCollectionDTO;
	}
	
	public void updatePhotoCollection(PhotoCollectionDTO photoCollectionDTO) {
		PhotoCollection photoCollection = PhotoCollection.get(PhotoCollection.class, photoCollectionDTO.getId());
        // 设置要更新的值
		try {
			BeanUtils.copyProperties(photoCollection, photoCollectionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePhotoCollection(Integer id) {
		this.removePhotoCollections(new Integer[] { id });
	}
	
	public void removePhotoCollections(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			PhotoCollection photoCollection = PhotoCollection.load(PhotoCollection.class, ids[i]);
			photoCollection.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoCollectionDTO> findAllPhotoCollection() {
		List<PhotoCollectionDTO> list = new ArrayList<PhotoCollectionDTO>();
		List<PhotoCollection> all = PhotoCollection.findAll(PhotoCollection.class);
		for (PhotoCollection photoCollection : all) {
			PhotoCollectionDTO photoCollectionDTO = new PhotoCollectionDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(photoCollectionDTO, photoCollection);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(photoCollectionDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<PhotoCollectionDTO> pageQueryPhotoCollection(PhotoCollectionDTO queryVo, int currentPage, int pageSize) {
		List<PhotoCollectionDTO> result = new ArrayList<PhotoCollectionDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _photoCollection from PhotoCollection _photoCollection   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _photoCollection.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getCreator() != null && !"".equals(queryVo.getCreator())) {
	   		jpql.append(" and _photoCollection.creator like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreator()));
	   	}		
	
	   	if (queryVo.getCreatedAt() != null && !"".equals(queryVo.getCreatedAt())) {
	   		jpql.append(" and _photoCollection.createdAt like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreatedAt()));
	   	}		
	
	   	if (queryVo.getUpdatedAt() != null && !"".equals(queryVo.getUpdatedAt())) {
	   		jpql.append(" and _photoCollection.updatedAt like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUpdatedAt()));
	   	}		
	   	if (queryVo.getPhotoCount() != null) {
	   		jpql.append(" and _photoCollection.photoCount=?");
	   		conditionVals.add(queryVo.getPhotoCount());
	   	}	
	
	
	   	if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
	   		jpql.append(" and _photoCollection.other like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
	   	}		
        Page<PhotoCollection> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (PhotoCollection photoCollection : pages.getData()) {
            PhotoCollectionDTO photoCollectionDTO = new PhotoCollectionDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(photoCollectionDTO, photoCollection);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                            result.add(photoCollectionDTO);
        }
        return new Page<PhotoCollectionDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoCollectionDTO> listPhotoCollections(String userName)
            throws Exception {

        List<PhotoCollectionDTO> photoCollectionDTOs = new ArrayList<PhotoCollectionDTO>();

        if (StringUtils.isNotBlank(userName)) {
            List<PhotoCollection> photoCollections = PhotoCollection
                    .findByCreator(userName);
            for (PhotoCollection photoCollection : photoCollections) {

                PhotoCollectionDTO photoCollectionDTO = new PhotoCollectionDTO();
                BeanUtils.copyProperties(photoCollectionDTO, photoCollection);

                List<RePhotoCollectionPhoto> rePhotoCollectionPhotos = RePhotoCollectionPhoto
                        .findByPhotoCollectionId(photoCollection.getId());
                List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();
                int count = 0;
                for (RePhotoCollectionPhoto rePhotoCollectionPhoto : rePhotoCollectionPhotos) {
                    if (count >= 4) {// 目前暂时只取前4张
                        break;
                    }
                    RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn.get(
                            RePhotoUserOwn.class,
                            rePhotoCollectionPhoto.getUpoId());

                    if (rePhotoUserOwn != null) {
                        PhotoDTO photoDTO = new PhotoDTO(
                                rePhotoUserOwn.getPhotoId(),
                                rePhotoUserOwn.getPhotoPath());
                        photoDTOs.add(photoDTO);
                        count++;
                    }

                }
                photoCollectionDTO.setPhotoDTOList(photoDTOs);
                photoCollectionDTOs.add(photoCollectionDTO);
            }
        }

        return photoCollectionDTOs;
    }
}
