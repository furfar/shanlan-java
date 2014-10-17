
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.TradeCommentDTO;

public interface TradeCommentApplication {

	public TradeCommentDTO getTradeComment(Integer id);
	
	public TradeCommentDTO saveTradeComment(TradeCommentDTO tradeComment);
	
	public void updateTradeComment(TradeCommentDTO tradeComment);
	
	public void removeTradeComment(Integer id);
	
	public void removeTradeComments(Integer[] ids);
	
	public List<TradeCommentDTO> findAllTradeComment();
	
	public Page<TradeCommentDTO> pageQueryTradeComment(TradeCommentDTO tradeComment, int currentPage, int pageSize);
	

}

