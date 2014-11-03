package com.shanlan.trade.application.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonDateSerializer;

import java.io.Serializable;


public class PhotoPackagesDTO extends GoodsDTO implements Serializable {

    private Integer id;


    private Integer goodsId;


    private String other;


    private String status;


    private Integer photoCount;


    private Integer alterCount;


    private String type;


    private String invalidDate;


    private String invalidDateEnd;


    private String updatedAt;


    private String updatedAtEnd;

    private String price;


    private Integer days;


    private String address;


    private Integer placeId;


    private String description;


    private String createdAt;


    private String createdAtEnd;

    private String name;


    private String validDate;


    private String validDateEnd;

    private String photographer;


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }


    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return this.other;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
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


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }


    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

   
    public String getInvalidDate() {
        return this.invalidDate;
    }

    public void setInvalidDateEnd(String invalidDateEnd) {
        this.invalidDateEnd = invalidDateEnd;
    }

   
    public String getInvalidDateEnd() {
        return this.invalidDateEnd;
    }


    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

   
    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAtEnd(String updatedAtEnd) {
        this.updatedAtEnd = updatedAtEnd;
    }

   
    public String getUpdatedAtEnd() {
        return this.updatedAtEnd;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }


    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getDays() {
        return this.days;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }


    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getPlaceId() {
        return this.placeId;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }


    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

   
    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAtEnd(String createdAtEnd) {
        this.createdAtEnd = createdAtEnd;
    }

   
    public String getCreatedAtEnd() {
        return this.createdAtEnd;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

   
    public String getValidDate() {
        return this.validDate;
    }

    public void setValidDateEnd(String validDateEnd) {
        this.validDateEnd = validDateEnd;
    }

   
    public String getValidDateEnd() {
        return this.validDateEnd;
    }


    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographer() {
        return this.photographer;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhotoPackagesDTO other = (PhotoPackagesDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}