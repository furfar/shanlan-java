package com.shanlan.photo.application.dto;

import java.io.Serializable;

public class PhotoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5716137194179265829L;

    private Integer id;

    private String other;

    private String md5;

    private Integer likeCount;

    private String filePath;

    private String createdAt;

    private Integer size;

    public PhotoDTO() {
    }

    public PhotoDTO(int photoId, String photoPath) {
        this.id = photoId;
        this.filePath = photoPath;
        this.likeCount = 0;
    }

    public PhotoDTO(String filePath, String md5) {
        this.filePath = filePath;
        this.md5 = md5;
        this.likeCount = 0;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return this.other;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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
        PhotoDTO other = (PhotoDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}