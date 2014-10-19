package com.shanlan.photo.core.domain;

import java.util.*;
import java.sql.Timestamp;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import java.io.Serializable;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "re_photo_type")
public class RePhotoType extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "re_id")
    private int reId;


    @Column(name = "type_name")
    private String typeName;


    @Column(name = "re_type")
    private String reType;


    public void setId(Integer id) {
        this.id = id;
    }


    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getReType() {
        return reType;
    }

    public void setReType(String reType) {
        this.reType = reType;
    }


    public Integer getId() {
        return id;
    }

    public boolean existed() {
        // TODO Auto-generated method stub
        return false;
    }


    public boolean notExisted() {
        // TODO Auto-generated method stub
        return false;
    }


    public boolean existed(String propertyName, Object propertyValue) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }


    public static List<RePhotoType> list(String photoType) {
        List<RePhotoType> rePhotoTypes = new ArrayList<RePhotoType>();

        if (StringUtils.isNotBlank(photoType)) {
            rePhotoTypes = findByProperty(RePhotoType.class, "typeName", photoType);
        }
        return rePhotoTypes;
    }


    public static Map<Integer, RePhotoType> getMap(String photoType) {
        Map<Integer, RePhotoType> reIdRePhotoTypeMap = new HashMap<Integer, RePhotoType>();
        List<RePhotoType> rePhotoTypes = list(photoType);
        for (RePhotoType rePhotoType : rePhotoTypes) {
            reIdRePhotoTypeMap.put(rePhotoType.getReId(), rePhotoType);
        }
        return reIdRePhotoTypeMap;
    }


}