/**
 *
 * Copyright(C) 2012-2016 All Rights 
 * Reserved.
 */
package com.shanlan.user.core.domain;

import java.io.IOException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlan.common.constant.ConstantRegex;
import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestAuthenticationException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.SignUtils;

/**
 * @ClassName:UserDetail
 * @Description: 用户基本信息
 * @Author Albert
 * @Date:2013-4-7 下午12:10:04
 * @Remarks:Ø
 * @Version:V1.1
 */
@Entity
@Table(name = "KS_IDENTITY")
public class UserBase extends KoalaLegacyEntity {

	private static final long serialVersionUID = 7697595059618556524L;
	private static final Logger logger = LoggerFactory.getLogger(UserBase.class);
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USER_ACCOUNT")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String nickName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ISVALID")
    private Integer isValid;

    @Column(name = "IDENTITY_TYPE")
    private String identityType;


    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "ABOLISH_DATE")
    private String ABOLISH_DATE;

    /**
     *
     */
    public UserBase() {
    }

    /**
     * 获取业务主键。业务主键是判断相同类型的两个实体在业务上的等价性的依据。如果相同类型的两个
     * 实体的业务主键相同，则认为两个实体是相同的，代表同一个实体。
     * <p>业务主键由实体的一个或多个属性组成。
     *
     * @return 组成业务主键的属性的数组。
     */
    @Override
    public String[] businessKeys() {
        return new String[0];
    }

    public UserBase(String userAccount, String userPassword,
                    String email) {
        super();
        this.userName = userAccount;
        this.password = userPassword;
        this.email = email;
    }


    public UserBase(String userAccount, String userPassword,
                    String email, Integer isValid) {
        super();
        this.userName = userAccount;
        this.password = userPassword;
        this.email = email;
        this.isValid = isValid;
    }


    public UserBase(String userAccount, String userPassword, String nickName,
                    String email, Integer isValid) {
        super();
        this.userName = userAccount;
        this.password = userPassword;
        this.nickName = nickName;
        this.email = email;
        this.isValid = isValid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userAccount) {
        this.userName = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getABOLISH_DATE() {
        return ABOLISH_DATE;
    }

    public void setABOLISH_DATE(String ABOLISH_DATE) {
        this.ABOLISH_DATE = ABOLISH_DATE;
    }

    public static UserBase getByUserName(String userName) {

        if (StringUtils.isNotBlank(userName)) {
            List<UserBase> userBaseList = findByProperty(UserBase.class, "userName", userName);
            if (userBaseList!=null && userBaseList.size()>0) {
                return userBaseList.get(0);
            }
        }
        return null;
    }

    public static UserBase findByEmail(String email) {

        if (StringUtils.isNotBlank(email)) {
            List<UserBase> userBaseList = findByProperty(UserBase.class, "email", email);
            if (userBaseList!=null && userBaseList.size()>0) {
                return userBaseList.get(0);
            }
        }
        return null;
    }


    public static boolean login(String userAccount, String password)
            throws OPFBaseException {
        if (StringUtils.isNotBlank(userAccount)
                && StringUtils.isNotBlank(password)) {
            UserBase user = new UserBase();
            if (StringUtils.isNotBlank(userAccount)
                    && userAccount.matches(ConstantRegex.REGEX_EMAIL)) {// 如果是Eamil
                user = findByEmail(userAccount);
            } else {
                user = getByUserName(userAccount);
            }
            RequestAuthenticationException requestAuthenticationException = new RequestAuthenticationException(
                    "username or password error, please check it.");
            if (user!=null){
                try {
                    String md5Password = SignUtils
                            .getMD5DigestInString(password);
                    String userPassword = user.getPassword();
                    if (!userPassword.equals(md5Password)) {// 如果密码不匹配
                        throw requestAuthenticationException;
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }else{
                throw  requestAuthenticationException;
            }

            return true;

        }
        return false;
    }


    public static boolean register(UserBase userBase) throws RequestParameterException {
        if (userBase != null) {

            if (isUserNameExist(userBase.getUserName())) {
                throw new RequestParameterException("user name already exists");
            } else if (isEmailExist(userBase.getEmail())) {
                throw new RequestParameterException("email already exists");
            }

            String md5Password;
            try {
                md5Password = SignUtils
                        .getMD5DigestInString(userBase.getPassword());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
            userBase.setPassword(md5Password);
            userBase.save();
            return true;
        }
        return false;
    }


    /**
     * 判断用户账号（用户名或者Email）是否已经存在
     *
     * @param userName
     * @param email
     * @return
     */

    public static boolean isUserAccountExist(String userName, String email) {
        if (StringUtils.isNotBlank(userName)) {
            return isUserNameExist(userName);
        } else if (StringUtils.isNotBlank(email)) {
            return isEmailExist(email);
        }
        return false;
    }

    public static boolean isUserNameExist(String userName) {
        if (StringUtils.isNotBlank(userName)) {
            UserBase users = getByUserName(userName);
            if (users != null ) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailExist(String email) {
        if (StringUtils.isNotBlank(email)) {
            UserBase users = findByEmail(email);
            if (users != null ) {
                return true;
            }
        }
        return false;
    }

    public enum Type{
        User,Role
    }

}
