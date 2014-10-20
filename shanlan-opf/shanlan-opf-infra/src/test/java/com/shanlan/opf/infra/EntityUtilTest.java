package com.shanlan.opf.infra;

import org.junit.Ignore;
import org.junit.Test;

import com.shanlan.common.util.EntityUtil;
import com.shanlan.trade.core.domain.Goods;

//@Ignore
public class EntityUtilTest {

	@Test
	public void testGetAllRowNameOfTable() {
		System.out.println(EntityUtil.getAllColumnNameOfTable(Goods.class));
	}

}
