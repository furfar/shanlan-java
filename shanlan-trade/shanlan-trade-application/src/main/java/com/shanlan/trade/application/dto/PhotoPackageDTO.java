package com.shanlan.trade.application.dto;

import java.io.Serializable;

public class PhotoPackageDTO extends GoodsDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

//	private Integer id;

    private Integer goodsId;

//	private String other;


    private Integer photoCount;

    private Integer alterCount;


    private Integer days;


    private String photographer;


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }


    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getPhotoCount() {
        return this.photoCount;
    }

    public void setAlterCount(Integer alterCount) {
        this.alterCount = alterCount;
    }

    public Integer getAlterCount() {
        return this.alterCount;
    }


    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getDays() {
        return this.days;
    }


    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographer() {
        return this.photographer;
    }


}