package com.shanlan.photo.application;

import java.util.List;

import com.shanlan.opf.application.dto.PhotoCollectionDTO;
import com.shanlan.opf.application.dto.PhotoDTO;

public interface PhotoApplication {

	List<PhotoCollectionDTO> getPhotoCollections(String userName)
			throws Exception;

	List<PhotoDTO> getPhotos(Integer photoCollectionId) throws Exception;

}
