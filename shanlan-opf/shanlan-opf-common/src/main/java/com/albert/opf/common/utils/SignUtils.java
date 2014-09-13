/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.model.domain.request.Request;

/**
 * @ClassName:SignUtils
 * @Description: 签名工具类
 * @Author Albert
 * @Date:2013-1-21 上午9:53:51
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class SignUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SignUtils.class);

	/**
	 * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
	 * uppercase(hex(sha1(secretkey1value1key2value2...secret))
	 * 
	 * @param paramNames
	 *            需要签名的参数名
	 * @param paramValues
	 *            参数列表
	 * @param secret
	 * @return
	 */
	public static String sign(Map<String, String> paramValues, String secret) {
		return sign(paramValues, null, secret);
	}

	/**
	 * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
	 * 
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	public static String sign(Map<String, String> paramValues,
			List<String> ignoreParamNames, String secret) {

		String sign = null;

		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<String>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);

			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			byte[] sha1Digest = getSHA1Digest(sb.toString());
			sign = byte2hex(sha1Digest);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return sign;
	}

	public static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName),
					OPFConstants.UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes(OPFConstants.UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	// private static byte[] getMD5Digest(String data) throws IOException {
	// byte[] bytes = null;
	// try {
	// MessageDigest md = MessageDigest.getInstance("MD5");
	// bytes = md.digest(data.getBytes(OPFConstants.UTF8));
	// } catch (GeneralSecurityException gse) {
	// throw new IOException(gse);
	// }
	// return bytes;
	// }

	/**
	 * 二进制转十六进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}

	/**
	 * @param baseRequestHTTP
	 * @return
	 */
	public static Map<String, String> getParamMap(Request request) {
		Map<String, String> paramMap = new HashMap<String, String>();

		List<Field> fields = ReflectionUtils
				.getSelfAndDirectParentDeclaredFields(request);

		for (Field field : fields) {// 通过“反射”将参数名和参数值存入Map中

			field.setAccessible(true); // 要想访问private字段，需要先将其Accessible属性设置为true
			Object fieldValue = null;
			try {
				fieldValue = field.get(request);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			}

			paramMap.put(field.getName(), (String) fieldValue);
		}
		return paramMap;
	}

}
