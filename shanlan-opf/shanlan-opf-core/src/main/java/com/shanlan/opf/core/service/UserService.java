package com.shanlan.opf.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.user.core.domain.UserBase;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shanlan.common.constant.ConstantRegex;
import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestAuthenticationException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.SignUtils;
import com.shanlan.opf.core.repository.UserRepository;


/**
 * @ClassName:CheckingService
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-20 3:14:41
 * @Remarks:
 * @Version:V1.1
 */
@Named
public class UserService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserService.class);
    @Inject
    private UserRepository userRepository;

    public UserBase login(String userAccount, String password)
            throws OPFBaseException {
        if (StringUtils.isNotBlank(userAccount)
                && StringUtils.isNotBlank(password)) {
            List<UserBase> users = new ArrayList<UserBase>();
            if (StringUtils.isNotBlank(userAccount)
                    && userAccount.matches(ConstantRegex.REGEX_EMAIL)) {// 如果是Eamil
                users = userRepository.getUserByEmail(userAccount);
            } else {
                users = userRepository.getUserByUserName(userAccount);
            }
            RequestAuthenticationException requestAuthenticationException = new RequestAuthenticationException(
                    "username or password error, please check it.");
            if (users != null && users.size() > 0) {
                UserBase user = users.get(0);
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
                return user;
            } else {
                throw requestAuthenticationException;
            }
        }
        return null;
    }

    public boolean register(UserBase user) throws RequestParameterException {
        if (user != null) {

            if (isUserNameExist(user.getUserName())) {
                throw new RequestParameterException("user name already exists");
            } else if (isEmailExist(user.getEmail())) {
                throw new RequestParameterException("email already exists");
            }

            String md5Password;
            try {
                md5Password = SignUtils
                        .getMD5DigestInString(user.getPassword());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
            user.setPassword(md5Password);
            return userRepository.add(user);
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

    public boolean isUserAccountExist(String userName, String email) {

        if (StringUtils.isNotBlank(userName)) {
            return isUserNameExist(userName);
        } else if (StringUtils.isNotBlank(email)) {
            return isEmailExist(email);
        }
        return false;
    }

    public boolean isUserNameExist(String userName) {
        if (StringUtils.isNotBlank(userName)) {
            List<UserBase> users = userRepository.getUserByUserName(userName);
            if (users != null && users.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailExist(String email) {
        if (StringUtils.isNotBlank(email)) {
            List<UserBase> users = userRepository.getUserByEmail(email);
            if (users != null && users.size() > 0) {
                return true;
            }
        }
        return false;
    }

}
