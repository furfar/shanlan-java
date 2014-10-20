package com.shanlan.trade.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Table(name = "re_trade_photo")
public class ReTradePhoto extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "tpc_id")
    private int tpcId;

    @Column(name = "upo_id")
    private int upoId;

    public void setId(int id) {
        this.id = id;
    }

    public int getTpcId() {
        return tpcId;
    }

    public void setTpcId(int tpcId) {
        this.tpcId = tpcId;
    }

    public int getUpoId() {
        return upoId;
    }

    public void setUpoId(int upoId) {
        this.upoId = upoId;
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

    public static List<ReTradePhoto> listByTpcIds(List<Integer> tpcIds) {
        List<ReTradePhoto> reTradePhotos = new ArrayList<ReTradePhoto>();
        String jpql = JPQLUtil.selectByColumnIn(ReTradePhoto.class, "tpcId", tpcIds);
        if (StringUtils.isNotBlank(jpql)) {
            reTradePhotos = getRepository().createJpqlQuery(jpql).list();
        }

        return reTradePhotos;
    }


    public static Map<Integer, List<ReTradePhoto>> getTcpIdAndSelfListMapByTpcIds(List<Integer> tpcIds) {
        Map<Integer, List<ReTradePhoto>> tcpIdAndSelfListMap = new HashMap<Integer, List<ReTradePhoto>>();

        List<ReTradePhoto> reTradePhotos = listByTpcIds(tpcIds);

        tcpIdAndSelfListMap = getTcpIdAndSelfListMap(reTradePhotos);

        return tcpIdAndSelfListMap;
    }


    public static Map<Integer, List<ReTradePhoto>> getTcpIdAndSelfListMap(List<ReTradePhoto> reTradePhotos) {
        Map<Integer, List<ReTradePhoto>> tcpIdAndSelfListMap = new HashMap<Integer, List<ReTradePhoto>>();

        if (reTradePhotos != null && reTradePhotos.size() > 0) {
            for (ReTradePhoto reTradePhoto : reTradePhotos) {
                List<ReTradePhoto> reTradePhotoList = tcpIdAndSelfListMap.get(reTradePhoto.getTpcId());
                if (reTradePhotoList == null) {
                    reTradePhotoList = new ArrayList<ReTradePhoto>();
                    tcpIdAndSelfListMap.put(reTradePhoto.getTpcId(), reTradePhotoList);
                }
                reTradePhotoList.add(reTradePhoto);
            }
        }

        return tcpIdAndSelfListMap;
    }

}