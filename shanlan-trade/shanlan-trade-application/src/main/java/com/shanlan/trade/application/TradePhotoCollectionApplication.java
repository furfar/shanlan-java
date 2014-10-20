
package com.shanlan.trade.application;

import java.util.List;

import com.shanlan.photo.application.dto.PhotoCollectionDTO;
import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.TradePhotoCollectionDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TradePhotoCollectionApplication {

	public TradePhotoCollectionDTO getTradePhotoCollection(Integer id);
	
	public TradePhotoCollectionDTO saveTradePhotoCollection(TradePhotoCollectionDTO tradePhotoCollection);
	
	public void updateTradePhotoCollection(TradePhotoCollectionDTO tradePhotoCollection);
	
	public void removeTradePhotoCollection(Integer id);
	
	public void removeTradePhotoCollections(Integer[] ids);
	
	public List<TradePhotoCollectionDTO> findAllTradePhotoCollection();
	
	public Page<TradePhotoCollectionDTO> pageQueryTradePhotoCollection(TradePhotoCollectionDTO tradePhotoCollection, int currentPage, int pageSize);

}

