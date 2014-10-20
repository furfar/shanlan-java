package com.shanlan.photo.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shanlan.common.util.JPQLUtil;
import com.shanlan.common.util.SQLUtil;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "re_photo_user_own")
public class RePhotoUserOwn extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "photo_id")
    private int photoId;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility")
    private Boolean visibility;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Serializable getId() {
        // TODO Auto-generated method stub
        return null;
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

    /**
     * 根据id列表，列出所有照片信息
     * @param ids
     * @return
     */
    public static List<RePhotoUserOwn> list(List<Integer> ids) {

        List<RePhotoUserOwn> rePhotoUserOwns = new ArrayList<RePhotoUserOwn>();
        String jpql = JPQLUtil.selectByIdIn(RePhotoUserOwn.class, ids);
        if (StringUtils.isNotBlank(jpql)) {
            rePhotoUserOwns = getRepository().createJpqlQuery(jpql).list();
        }
        return rePhotoUserOwns;
    }


    /**
     * 根据id列表，列出可见性为‘公开’的照片所有信息
     * @param ids
     * @return
     */
    public static List<RePhotoUserOwn> listPublic(List<Integer> ids) {

        List<RePhotoUserOwn> rePhotoUserOwns = new ArrayList<RePhotoUserOwn>();
        String jpql = JPQLUtil.selectByIdInAndOther(RePhotoUserOwn.class,ids,"visibility",Visibility.PUBLIC.ordinal()+"");
        if (StringUtils.isNotBlank(jpql)) {
            rePhotoUserOwns = getRepository().createJpqlQuery(jpql).list();
        }
        return rePhotoUserOwns;
    }


    public static List<Integer> listPhotoIds(List<RePhotoUserOwn> rePhotoUserOwns){
        List<Integer> photoIds=new ArrayList<Integer>();
        if (rePhotoUserOwns!=null && rePhotoUserOwns.size()>0){
            for(RePhotoUserOwn rePhotoUserOwn:rePhotoUserOwns){
                photoIds.add(rePhotoUserOwn.getPhotoId());
            }
        }
        return photoIds;
    }


    public enum Visibility{
        PRIVATE,FRIEND,JOIN,PUBLIC_BUT,PUBLIC
    }

}