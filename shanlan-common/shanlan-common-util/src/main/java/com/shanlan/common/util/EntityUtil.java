package com.shanlan.common.util;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by albertliu on 14-10-20.
 */
public class EntityUtil {

    public static String getAllColumnNameOfTable(Class clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            String tableName = table.name();
            Field[] fields = clazz.getDeclaredFields();
            StringBuffer allColumnName = new StringBuffer();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.name();
                    allColumnName.append(tableName + "." + columnName + ",");
                }
            }
            if (allColumnName.length() > 0) {
                return allColumnName.toString().substring(0,
                        allColumnName.length() - 1);
            }
        }

        return null;
    }

    /**
     * 获取Entity对应表的表名
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            return table.name();
        }
        return null;

    }

    /**
     * 获取Entity对应表的表名和别名,别名跟表名一样。
     *
     * @param clazz
     * @return 返回格式为：tableName As tableName，比如trade_order As trade_order
     */
    public static String getTableNameAndAlias(Class clazz) {
        String tableName = getTableName(clazz);
        return tableName + " As " + tableName;

    }


    public static <T extends KoalaLegacyEntity> List<Integer> getIds(List<T> entityList) {
        List<Integer> ids = new ArrayList<Integer>();
        if (entityList != null && entityList.size() > 0) {
            for (T entity : entityList) {
                ids.add((Integer) entity.getId());
            }
        }
        return ids;
    }


    public static <T extends KoalaLegacyEntity> Map<Integer,T> getIdAndEntityMap(List<T> entityList) {
        Map<Integer,T> idAndEntityMap = new HashMap<Integer, T>();
        if (entityList != null && entityList.size() > 0) {
            for (T entity : entityList) {
                idAndEntityMap.put((Integer)entity.getId(),entity);
            }
        }
        return idAndEntityMap;
    }

}
