
package com.shanlan.basic.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.basic.application.dto.PhotoTypeDTO;

public interface PhotoTypeApplication {

	public PhotoTypeDTO getPhotoType(Integer id);
	
	public PhotoTypeDTO savePhotoType(PhotoTypeDTO photoType);
	
	public void updatePhotoType(PhotoTypeDTO photoType);
	
	public void removePhotoType(Integer id);
	
	public void removePhotoTypes(Integer[] ids);
	
	public List<PhotoTypeDTO> findAllPhotoType();
	
	public Page<PhotoTypeDTO> pageQueryPhotoType(PhotoTypeDTO photoType, int currentPage, int pageSize);
	

}

