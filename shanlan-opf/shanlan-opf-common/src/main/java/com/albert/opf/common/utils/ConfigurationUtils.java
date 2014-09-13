/**
 * Aikaka.com.cn Inc.
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName:Configuration
 * @Description: 读取配置文件
 * @Author lei.panglei
 * @Date:2012-5-3 上午9:51:10
 *
 * @Remarks:
 * @Version:V1.0
 */
public class ConfigurationUtils {

	 private static final String CONF_CLASSPATH = "/config.properties";

//	private static final String CONF_CLASSPATH = Configuration.class
//			.getClassLoader().getResource("config.properties").getPath();

	private static Log log = LogFactory.getLog(ConfigurationUtils.class);

	private static ConfigurationUtils instance = new ConfigurationUtils();

	private volatile Properties configuration = new Properties();

	/**
	 * 构造方法。
	 */
	private ConfigurationUtils() {

		InputStream is = this.getClass().getResourceAsStream(CONF_CLASSPATH);

		if (is != null) {
			try {
				this.configuration.clear();
				this.configuration.load(is);
				log.info("Loading configuration ... OK");
			} catch (IOException e) {
				log.info("Loading configuration ... FAILED", e);
			} finally {
				try {
					is.close();
				} catch (Throwable t) {
				}
			}
		} else {
			log.info("Loading configuration ... FAILED(Configuration file is not found)");
		}
	}

	/**
	 * 获得Configuration实例。
	 *
	 * @return Configuration实例
	 */
	public static ConfigurationUtils getInstance() {

		return instance;
	}

	/**
	 * 获得所有配置项。
	 *
	 * @return 所有配置项
	 */
	public Properties getConfiguration() {

		return (Properties) this.configuration.clone();
	}

	/**
	 * 获得配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public String getConfiguration(String key) {

		return this.getConfiguration(key, null);
	}

	/**
	 * 获得配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public String getConfiguration(String key, String defaultValue) {

		String result = null;

		String value = this.configuration.getProperty(key);
		if (value != null) {
			result = value;
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Integer getIntegerConfiguration(String key) {

		return this.getIntegerConfiguration(key, null);
	}

	/**
	 * 获得整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Integer getIntegerConfiguration(String key, Integer defaultValue) {

		Integer result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				result = new Integer(Integer.parseInt(value));
			} catch (NumberFormatException nfe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得短整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Short getShortConfiguration(String key) {

		return this.getShortConfiguration(key, null);
	}

	/**
	 * 获得短整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Short getShortConfiguration(String key, Short defaultValue) {

		Short result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				result = new Short(Short.parseShort(value));
			} catch (NumberFormatException nfe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得长整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Long getLongConfiguration(String key) {

		return this.getLongConfiguration(key, null);
	}

	/**
	 * 获得长整型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Long getLongConfiguration(String key, Long defaultValue) {

		Long result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				result = new Long(Long.parseLong(value));
			} catch (NumberFormatException nfe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得单精度浮点型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Float getFloatConfiguration(String key) {

		return this.getFloatConfiguration(key, null);
	}

	/**
	 * 获得单精度浮点型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Float getFloatConfiguration(String key, Float defaultValue) {

		Float result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				result = new Float(Float.parseFloat(value));
			} catch (NumberFormatException nfe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得双精度浮点型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Double getDoubleConfiguration(String key) {

		return this.getDoubleConfiguration(key, null);
	}

	/**
	 * 获得双精度浮点型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Double getDoubleConfiguration(String key, Double defaultValue) {

		Double result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				result = new Double(Double.parseDouble(value));
			} catch (NumberFormatException nfe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得布尔型配置项。
	 *
	 * @param key
	 *            配置关键字
	 *
	 * @return 配置项
	 */
	public Boolean getBooleanConfiguration(String key) {

		return this.getBooleanConfiguration(key, null);
	}

	/**
	 * 获得布尔型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Boolean getBooleanConfiguration(String key, Boolean defaultValue) {

		Boolean result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value)
					|| "1".equalsIgnoreCase(value)) {
				result = new Boolean(true);
			} else if ("false".equalsIgnoreCase(value)
					|| "no".equalsIgnoreCase(value)
					|| "0".equalsIgnoreCase(value)) {
				result = new Boolean(false);
			} else {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 获得日期型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param pattern
	 *            日期字符串格式
	 *
	 * @return 配置项
	 */
	public Date getDateConfiguration(String key, String pattern) {

		return this.getDateConfiguration(key, pattern, null);
	}

	/**
	 * 获得日期型配置项。
	 *
	 * @param key
	 *            配置关键字
	 * @param pattern
	 *            日期字符串格式
	 * @param defaultValue
	 *            默认值
	 *
	 * @return 配置项
	 */
	public Date getDateConfiguration(String key, String pattern,
			Date defaultValue) {

		Date result = null;

		String value = this.configuration.getProperty(key);
		if ((value != null) && !"".equals(value)) {
			try {
				if ((pattern != null) && !"".equals(pattern)) {
					result = (new SimpleDateFormat(pattern)).parse(value);
				} else {
					result = (new SimpleDateFormat()).parse(value);
				}
			} catch (ParseException pe) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

}
