package com.shanlan.user.core.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shanlan.common.exception.business.ParameterInvalidException;
import com.shanlan.common.util.JPQLUtil;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "user")
public class UserDetail extends KoalaLegacyEntity {

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

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "photo_id")
    private Integer photoId;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "qq")
    private String qq;

    @Column(name = "webchart")
    private String webchart;

    @Column(name = "alipay")
    private String alipay;

    @Column(name = "trade_times")
    private Integer tradeTimes;

    @Column(name = "activeness")
    private Integer activeness;

    @Column(name = "photo_count")
    private Integer photoCount;

    @Column(name = "type")
    private String type;

    @Column(name = "other")
    private String other;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWebchart() {
        return webchart;
    }

    public void setWebchart(String webchart) {
        this.webchart = webchart;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public Integer getTradeTimes() {
        return tradeTimes;
    }

    public void setTradeTimes(Integer tradeTimes) {
        this.tradeTimes = tradeTimes;
    }

    public Integer getActiveness() {
        return activeness;
    }

    public void setActiveness(Integer activeness) {
        this.activeness = activeness;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getId() {
        return id;
    }

    public UserDetail(){}

    public UserDetail(String userName, String nickName, String email, String type) {
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.type = type;
    }

    public enum Type{
        COMMON,PHOTOGRAPHER,MODEL
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

    public static Map<String, UserDetail> getUserNameAndSelfMap(List<String> userNameList) {
        Map<String, UserDetail> userNameUserDetailMap = new HashMap<String, UserDetail>();

        if (userNameList != null && userNameList.size() > 0) {
            String jpql = JPQLUtil.selectByColumnIn(UserDetail.class, "userName", userNameList);
            if (StringUtils.isNotBlank(jpql)) {
                List<UserDetail> userDetails = getRepository().createJpqlQuery(jpql).list();
                for (UserDetail userDetail : userDetails) {
                    userNameUserDetailMap.put(userDetail.getUserName(), userDetail);
                }
            }
        }

        return userNameUserDetailMap;
    }

    public static List<UserDetail> listByUserNames(List<String> userNameList){
        List<UserDetail> userDetails=new ArrayList<UserDetail>();

        if (userNameList != null && userNameList.size() > 0) {
            String jpql = JPQLUtil.selectByColumnIn(UserDetail.class, "userName", userNameList);
            if (StringUtils.isNotBlank(jpql)) {
               userDetails = getRepository().createJpqlQuery(jpql).list();
            }
        }
        return userDetails;
    }


    public static UserDetail get(String userName){
        List<UserDetail> userDetails=listByUserNames(Collections.singletonList(userName));
        if(userDetails.size()==1){
            return userDetails.get(0);
        }else{
            throw new ParameterInvalidException("用户" +userName+"不存在,请核对用户名");
        }
    }

}