
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.GoodsDTO;

public interface GoodsApplication {

	public GoodsDTO getGoods(Integer id);
	
	public GoodsDTO saveGoods(GoodsDTO goods);
	
	public void updateGoods(GoodsDTO goods);
	
	public void removeGoods(Integer id);
	
	public void removeGoodss(Integer[] ids);
	
	public List<GoodsDTO> findAllGoods();
	
	public Page<GoodsDTO> pageQueryGoods(GoodsDTO goods, int currentPage, int pageSize);
	

}

