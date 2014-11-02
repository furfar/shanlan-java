package com.shanlan.trade.core.domain;

import org.apache.commons.lang.StringUtils;

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
public class PhotoPackages extends Goods {

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


    @Basic

    @Column(name = "photographer")
    private String photographer;

    @Column(name = "days")
    private Integer days;

    @Column(name = "photo_count")
    private Integer photoCount;

    @Column(name = "alter_count")
    private Integer alterCount;

    @Column(name = "place_id")
    private int placeId;

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

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

//	public Integer getId() {
//		return id;
//	}

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


    public static List<PhotoPackages> list(String photographerUserName) {
        List<PhotoPackages> photoPackagesList = new ArrayList<PhotoPackages>();
        if (StringUtils.isNotBlank(photographerUserName)) {
            photoPackagesList = findByProperty(PhotoPackages.class, "photographer", photographerUserName);
        }
        return photoPackagesList;
    }


}