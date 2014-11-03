package com.shanlan.trade.core.domain;

import org.apache.commons.lang.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "photo_packages")
public class PhotoPackage extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "goods_id")
    private int goodsId;

    @Column(name = "photographer")
    private String photographer;

    @Column(name = "days")
    private Integer days;

    @Column(name = "photo_count")
    private Integer photoCount;

    @Column(name = "alter_count")
    private Integer alterCount;


    @Column(name = "other")
    private String other;

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getAlterCount() {
        return alterCount;
    }

    public void setAlterCount(Integer alterCount) {
        this.alterCount = alterCount;
    }


    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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


    public static List<PhotoPackage> list(String photographerUserName) {
        List<PhotoPackage> photoPackagesList = new ArrayList<PhotoPackage>();
        if (StringUtils.isNotBlank(photographerUserName)) {
            photoPackagesList = findByProperty(PhotoPackage.class, "photographer", photographerUserName);
        }
        return photoPackagesList;
    }


    public static PhotoPackage get(Integer goodsId) {
        List<PhotoPackage> photoPackages = findByProperty(PhotoPackage.class, "goodsId", goodsId);
        if (photoPackages.size() == 1) {
            return photoPackages.get(0);
        }
        return null;
    }

}