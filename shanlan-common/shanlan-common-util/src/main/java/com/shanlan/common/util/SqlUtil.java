package com.shanlan.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by albertliu on 14-10-20.
 */
public class SQLUtil {

    /**
     * 得到开如select xx from xx where id in()的Sql语句
     * @param clazz
     * @param ids
     * @return
     */
    public static String selectByIdIn(Class clazz, List<Integer> ids) {
        if (clazz != null && ids != null && ids.size() > 0) {
            String sql = "Select "
                    + EntityUtil.getAllColumnNameOfTable(clazz)
                    + " From " + EntityUtil.getTableNameAndAlias(clazz) + " Where id In ('"
                    + StringUtils.join(ids, "','") + "')";
            return sql;
        }
        return null;
    }


//    public static String selectByColumnIn(Class clazz, String columnName, List<Integer> columnValues) {
//        if (clazz != null && columnValues != null && columnValues.size() > 0) {
//            String sql = "Select "
//                    + EntityUtil.getAllColumnNameOfTable(clazz)
//                    + " From " + EntityUtil.getTableNameAndAlias(clazz) + " Where " + columnName + " In ('"
//                    + StringUtils.join(columnValues, "','") + "')";
//            return sql;
//        }
//        return null;
//    }

    /**
     *
     * @param clazz
     * @param columnName
     * @param columnValues
     * @return
     */
    public static String selectByColumnIn(Class clazz, String columnName, java.lang.Iterable<?> columnValues) {
        if (clazz != null && columnValues != null) {
            String sql = "Select "
                    + EntityUtil.getAllColumnNameOfTable(clazz)
                    + " From " + EntityUtil.getTableNameAndAlias(clazz) + " Where " + columnName + " In ('"
                    + StringUtils.join(columnValues, "','") + "')";
            return sql;
        }
        return null;
    }


}
