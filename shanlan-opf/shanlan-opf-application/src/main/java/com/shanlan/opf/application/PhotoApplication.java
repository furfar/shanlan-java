package com.shanlan.opf.application;

import java.util.List;

import com.shanlan.opf.application.dto.PhotoCollectionDTO;
import com.shanlan.opf.application.dto.PhotoDTO;

public interface PhotoApplication {

	List<PhotoCollectionDTO> getPhotoCollections(String userName);

	List<PhotoDTO> getPhotos(Integer photoCollectionId) throws Exception;

}
