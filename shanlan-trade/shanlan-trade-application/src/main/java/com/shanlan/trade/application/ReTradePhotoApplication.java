
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.ReTradePhotoDTO;

public interface ReTradePhotoApplication {

	public ReTradePhotoDTO getReTradePhoto(Integer id);
	
	public ReTradePhotoDTO saveReTradePhoto(ReTradePhotoDTO reTradePhoto);
	
	public void updateReTradePhoto(ReTradePhotoDTO reTradePhoto);
	
	public void removeReTradePhoto(Integer id);
	
	public void removeReTradePhotos(Integer[] ids);
	
	public List<ReTradePhotoDTO> findAllReTradePhoto();
	
	public Page<ReTradePhotoDTO> pageQueryReTradePhoto(ReTradePhotoDTO reTradePhoto, int currentPage, int pageSize);
	

}

