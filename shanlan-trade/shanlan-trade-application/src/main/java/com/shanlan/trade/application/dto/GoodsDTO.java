package com.shanlan.trade.application.dto;

import java.io.Serializable;


public class GoodsDTO implements Serializable {

    private Integer id;


    private String updatedAt;

    private String updatedAtEnd;

    private String price;


    private String other;


    private String creator;

    private String address;


    private String status;


    private String createdAt;


    private String createdAtEnd;

    private String description;


    private String validDate;


    private String validDateEnd;

    private String name;


    private String type;


    private String invalidDate;


    private String invalidDateEnd;


    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAtEnd(String updatedAtEnd) {
        this.updatedAtEnd = updatedAtEnd;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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


    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return this.other;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
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


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
        GoodsDTO other = (GoodsDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}