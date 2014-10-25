package com.shanlan.common.util;

import org.junit.Assert;
import org.junit.Test;

public class FileUploadTest {

	@Test
	public void testGetPostfix() {
		Assert.assertEquals("jpg", FileUploadUtil.getPostfix("sfasf.jpg"));
	}

}
