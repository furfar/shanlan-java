package com.shanlan.trade.core.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;

import com.shanlan.common.util.EntityUtil;
import com.shanlan.trade.core.domain.TradeOrder;
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

		if (StringUtils.isNotBlank(sellerUserName) && currentPage >= 1
				&& pageSize > 0) {

			int offset = (currentPage - 1) * pageSize;

			String tradeOrderName = EntityUtil.getTableName(TradeOrder.class);
			String tradeOrderItemName = EntityUtil
					.getTableName(TradeOrderItem.class);

			StringBuffer sql = new StringBuffer();
			sql.append(" Select "
					+ EntityUtil.getAllColumnNameOfTable(TradeOrderItem.class));
			sql.append(" From "
					+ EntityUtil.getTableNameAndAlias(TradeOrder.class) + ","
					+ EntityUtil.getTableNameAndAlias(TradeOrderItem.class));
			sql.append(" Where " + tradeOrderName + ".id=" + tradeOrderItemName
					+ ".order_id and " + tradeOrderName + ".seller=?");
			sql.append(" Limit ?,?;");

			List<Object[]> objectsList = getQueryChannelService()
					.createSqlQuery(sql.toString())
					.setParameters(sellerUserName, offset, pageSize).list();

			for (Object[] objects : objectsList) {
				int i = 0;
				tradeOrderItems.add(new TradeOrderItem((Integer) objects[i++],
						(Integer) objects[i++], (Integer) objects[i++],(String)objects[i++],
						(Integer) objects[i++], (Float) objects[i++]));
			}

		}

		return tradeOrderItems;
	}
}
