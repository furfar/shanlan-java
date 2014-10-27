package com.shanlan.common.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by albertliu on 14-10-20.
 */
public class JPQLUtil {


    /**
     * @param clazz
     * @param columnName
     * @param columnValues
     * @return
     */
    public static String selectByColumnIn(Class clazz, String columnName, java.lang.Iterable<?> columnValues) {
        if (clazz != null && columnValues != null) {
            String entityName = clazz.getSimpleName();
            String jpql = "Select "
                    + entityName
                    + " From " + entityName + " " + entityName + " Where " + columnName + " In ('"
                    + StringUtils.join(columnValues, "','") + "')";
            return jpql;
        }
        return null;
    }


    /**
     * @param clazz
     * @param columnName
     * @param columnValue
     * @param id
     * @return
     */
    public static String updateColumnById(Class clazz, String columnName, String columnValue, Integer id) {
        if (clazz != null && StringUtils.isNotBlank(columnName)) {
            String entityName = clazz.getSimpleName();
            StringBuffer jpql = new StringBuffer();
            jpql.append("Update " + entityName + " " + entityName);
            jpql.append(" Set " + columnName + "='" + columnValue + "'");
            jpql.append(" Where id='" + id + "'");
            return jpql.toString();
        }
        return null;
    }


    /**
     * 得到形如select xx from xx where id in()的Sql语句
     *
     * @param clazz
     * @param ids
     * @return
     */
    public static String selectByIdIn(Class clazz, List<Integer> ids) {
        return selectByColumnIn(clazz, "id", ids);
    }


    /**
     * 得到形如select xx from xx where id in() and xx==的Sql语句
     *
     * @param clazz
     * @param ids
     * @return
     */
    public static String selectByIdInAndOther(Class clazz, List<Integer> ids, String columnName, String columnValue) {
        String jpql = selectByColumnIn(clazz, "id", ids);
        jpql = jpql + " And " + columnName + " = " + columnValue;
        return jpql;
    }

}
