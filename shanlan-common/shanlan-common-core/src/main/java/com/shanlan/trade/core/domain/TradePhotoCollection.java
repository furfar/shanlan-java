package com.shanlan.trade.core.domain;

import java.util.*;

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
 *
 */

/**
 * @author albertliu
 *         交易照片集
 */
@Entity
@Table(name = "trade_photo_collection")
public class TradePhotoCollection extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "owner")
    private String owner;

    /**
     * trade_order_item_id
     */
    @Column(name = "toi_id")
    private int toiId;

    @Column(name = "photo_count")
    private int photoCount;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getToiId() {
        return toiId;
    }

    public void setToiId(int toiId) {
        this.toiId = toiId;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public static TradePhotoCollection get(Integer toiId) {
        if (toiId != null && toiId > 0) {
            List<TradePhotoCollection> tradePhotoCollections = findByProperty(TradePhotoCollection.class, "toiID", toiId);
            if (tradePhotoCollections != null && tradePhotoCollections.size() == 1) {
                return tradePhotoCollections.get(0);
            }
        }
        return null;

    }

    public static List<TradePhotoCollection> listByToiIds(List<Integer> orderItemIds) {
        List<TradePhotoCollection> tradePhotoCollections = new ArrayList<TradePhotoCollection>();
        String jpql = JPQLUtil.selectByColumnIn(TradePhotoCollection.class, "toiId", orderItemIds);
        if (StringUtils.isNotBlank(jpql)) {
            tradePhotoCollections = getRepository().createJpqlQuery(jpql).list();
        }

        return tradePhotoCollections;
    }


    public static Map<Integer, Integer> getToiIdAndTpcIdMap(List<TradePhotoCollection> tradePhotoCollections) {
        Map<Integer, Integer> toiIdAndTpcIdMap = new HashMap<Integer, Integer>();
        for (TradePhotoCollection tradePhotoCollection : tradePhotoCollections) {
            toiIdAndTpcIdMap.put(tradePhotoCollection.getToiId(), tradePhotoCollection.getId());
        }

        return toiIdAndTpcIdMap;
    }


}