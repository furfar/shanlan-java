
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.FrontTradeCommentDTO;
import com.shanlan.trade.application.dto.TradeCommentDTO;

public interface TradeCommentApplication {

	public TradeCommentDTO getTradeComment(Integer id);
	
	public TradeCommentDTO saveTradeComment(TradeCommentDTO tradeComment);
	
	public void updateTradeComment(TradeCommentDTO tradeComment);
	
	public void removeTradeComment(Integer id);
	
	public void removeTradeComments(Integer[] ids);
	
	public List<TradeCommentDTO> findAllTradeComment();
	
	public Page<TradeCommentDTO> pageQueryTradeComment(TradeCommentDTO tradeComment, int currentPage, int pageSize);
	
	/**根据卖家用户名分页获取其交易评价信息
	 * @param sellerUserName
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<FrontTradeCommentDTO> pageTradeComment(String sellerUserName , int currentPage, int pageSize);

}

