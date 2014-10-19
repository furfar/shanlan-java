package com.shanlan.photo.core.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import java.io.Serializable;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "re_photo_style")
public class RePhotoStyle extends KoalaLegacyEntity {

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


    @Column(name = "style_name")
    private String styleName;


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


    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
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

}