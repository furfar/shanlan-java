package com.shanlan.user.application.dto;

import java.io.Serializable;


public class PhotographerDTO extends UserDetailDTO implements Serializable {

    private Integer id;

    private Integer evaluationRank;

    private String other;

    private Integer serviceStatus;

    private String serviceStatusAsString;

    private Integer evaluationTimes;

    private String verifyType;


    public void setEvaluationRank(Integer evaluationRank) {
        this.evaluationRank = evaluationRank;
    }

    public Integer getEvaluationRank() {
        return this.evaluationRank;
    }


    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return this.other;
    }


    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceStatusAsString() {
        return this.serviceStatusAsString;
    }

    public void setServiceStatusAsString(String serviceStatus) {
        this.serviceStatusAsString = serviceStatus;
    }


    public void setEvaluationTimes(Integer evaluationTimes) {
        this.evaluationTimes = evaluationTimes;
    }

    public Integer getEvaluationTimes() {
        return this.evaluationTimes;
    }


    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public String getVerifyType() {
        return this.verifyType;
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
        PhotographerDTO other = (PhotographerDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}