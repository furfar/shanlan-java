
package com.shanlan.basic.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.basic.application.dto.PhotoStyleDTO;

public interface PhotoStyleApplication {

	public PhotoStyleDTO getPhotoStyle(Integer id);
	
	public PhotoStyleDTO savePhotoStyle(PhotoStyleDTO photoStyle);
	
	public void updatePhotoStyle(PhotoStyleDTO photoStyle);
	
	public void removePhotoStyle(Integer id);
	
	public void removePhotoStyles(Integer[] ids);
	
	public List<PhotoStyleDTO> findAllPhotoStyle();
	
	public Page<PhotoStyleDTO> pageQueryPhotoStyle(PhotoStyleDTO photoStyle, int currentPage, int pageSize);
	

}

