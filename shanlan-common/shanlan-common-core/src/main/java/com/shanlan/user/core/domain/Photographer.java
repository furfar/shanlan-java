package com.shanlan.user.core.domain;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "photographer")
public class Photographer extends KoalaLegacyEntity {

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

    @Column(name = "evaluation_rank")
    private int evaluationRank;

    @Column(name = "evaluation_times")
    private int evaluationTimes;

    @Column(name = "service_status")
    private Integer serviceStatus;

    @Column(name = "other")
    private String other;

    @Column(name = "verify_type")
    private String verifyType;

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEvaluationRank() {
        return evaluationRank;
    }

    public void setEvaluationRank(int evaluationRank) {
        this.evaluationRank = evaluationRank;
    }

    public int getEvaluationTimes() {
        return evaluationTimes;
    }

    public void setEvaluationTimes(int evaluationTimes) {
        this.evaluationTimes = evaluationTimes;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
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


    public static Photographer get(String userName) {
        List<Photographer> photographers = findByProperty(Photographer.class, "userName", userName);
        if (photographers != null && photographers.size() == 1) {
            return photographers.get(0);
        }
        return null;
    }

}