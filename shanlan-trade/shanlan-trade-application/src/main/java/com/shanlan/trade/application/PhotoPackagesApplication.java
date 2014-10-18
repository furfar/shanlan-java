
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.PhotoPackagesDTO;

public interface PhotoPackagesApplication {

	public PhotoPackagesDTO getPhotoPackages(Integer id);
	
	public PhotoPackagesDTO savePhotoPackages(PhotoPackagesDTO photoPackages);
	
	public void updatePhotoPackages(PhotoPackagesDTO photoPackages);
	
	public void removePhotoPackages(Integer id);
	
	public void removePhotoPackagess(Integer[] ids);
	
	public List<PhotoPackagesDTO> findAllPhotoPackages();
	
	public Page<PhotoPackagesDTO> pageQueryPhotoPackages(PhotoPackagesDTO photoPackages, int currentPage, int pageSize);
	

}
