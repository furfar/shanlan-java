package com.shanlan.photo.application.dto;

import java.io.Serializable;
import java.util.List;


public class PhotoCollectionDTO implements Serializable {

    private Integer id;


    private String updatedAt;


    private String other;


    private String createdAt;


    private String name;

    private String description;

    private Integer photoCount;


    private String creator;

    private List<PhotoDTO> photoDTOList;


    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return this.other;
    }


    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getPhotoCount() {
        return this.photoCount;
    }

    public List<PhotoDTO> getPhotoDTOList() {
        return photoDTOList;
    }

    public void setPhotoDTOList(List<PhotoDTO> photoDTOList) {
        this.photoDTOList = photoDTOList;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
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
        PhotoCollectionDTO other = (PhotoCollectionDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}