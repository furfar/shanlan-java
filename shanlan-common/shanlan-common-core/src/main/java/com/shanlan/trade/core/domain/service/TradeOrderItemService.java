package com.shanlan.trade.core.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;

import com.shanlan.trade.core.domain.TradeOrderItem;

public class TradeOrderItemService {

	private static QueryChannelService getQueryChannelService() {
		QueryChannelService queryChannel = null;
		if (queryChannel == null) {
			queryChannel = InstanceFactory.getInstance(
					QueryChannelService.class, "queryChannel");
		}
		return queryChannel;
	}

	/**
	 * 根据卖家用户名分页查询其卖的商品
	 * 
	 * @param sellerUserName
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static List<TradeOrderItem> pageOrderItems(String sellerUserName,
			int currentPage, int pageSize) {
		List<TradeOrderItem> tradeOrderItems = new ArrayList<TradeOrderItem>();

		if (StringUtils.isNotBlank(sellerUserName) && currentPage >= 0
				&& pageSize > 0) {
//			getQueryChannelService().createJpqlQuery("");
			
			
		}

		return tradeOrderItems;
	}
}
