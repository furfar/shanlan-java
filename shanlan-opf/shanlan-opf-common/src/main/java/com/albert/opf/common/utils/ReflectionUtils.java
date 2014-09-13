/**
 *  
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.albert.opf.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:ReflectionUtils
 * @Description: 包含一些常用反射功能的工具类
 * @Author Albert
 * @Date:2013-1-19 下午8:03:27
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class ReflectionUtils {

	/**
	 * 获得对象自身及其直接父类已声明的字段
	 * 
	 * @param object				传入的对象
	 * @return	List<Field> 	字段列表
	 */
	public static List<Field> getSelfAndDirectParentDeclaredFields(Object object) {

		List<Field> declaredFields = new ArrayList<Field>();

		Field[] selfFields = object.getClass().getDeclaredFields();

		if (selfFields != null) {
			declaredFields.addAll(Arrays.asList(selfFields));

			Field[] directParentFields = object.getClass().getSuperclass()
					.getDeclaredFields();

			if (directParentFields != null) {
				declaredFields.addAll(Arrays.asList(directParentFields));
			}

		}

		return declaredFields;

	}
}
