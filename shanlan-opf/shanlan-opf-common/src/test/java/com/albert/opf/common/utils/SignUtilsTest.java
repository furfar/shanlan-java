package com.albert.opf.common.utils;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class SignUtilsTest {

	@Test
	public void testGetMD5DigestInString() throws IOException {
		String md5 = SignUtils.getMD5DigestInString("888888");
		Assert.assertEquals("21218cca77804d2ba1922c33e0151105", md5);
	}

}
