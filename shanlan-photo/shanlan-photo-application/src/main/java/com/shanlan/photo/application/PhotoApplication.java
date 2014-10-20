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

    /**
     * 根据交易集ID获取交易集的所有照片集
     * @param tradePhotoCollectionId
     * @return
     */
    public List<PhotoDTO> listTradePhotos(int tradePhotoCollectionId) throws Exception;
}
