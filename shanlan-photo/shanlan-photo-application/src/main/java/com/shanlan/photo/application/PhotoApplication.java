package com.shanlan.photo.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.photo.application.dto.PhotoCollectionDTO;
import com.shanlan.photo.application.dto.PhotoDTO;

public interface PhotoApplication {

	public PhotoDTO getPhoto(Integer id);

	public PhotoDTO savePhoto(PhotoDTO photo);

	public void updatePhoto(PhotoDTO photo);

	public void removePhoto(Integer id);

	public void removePhotos(Integer[] ids);

	public List<PhotoDTO> findAllPhoto();

	public Page<PhotoDTO> pageQueryPhoto(PhotoDTO photo, int currentPage,
			int pageSize);

	List<PhotoCollectionDTO> getPhotoCollections(String userName)
			throws Exception;

}
