package com.shanlan.user.core.service;

import java.io.IOException;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.DateUtil;
import com.shanlan.common.util.SignUtils;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.domain.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.inject.Named;

@Named
public class UserService {
    private static final Logger logger = LoggerFactory
            .getLogger(UserBase.class);

    public static boolean register(UserBase userBase)
            throws RequestParameterException {

        if (userBase != null) {

            if (UserBase.isUserNameExist(userBase.getUserName())) {
                throw new RequestParameterException("用户名已经存在");
            } else if (UserBase.isEmailExist(userBase.getEmail())) {
                throw new RequestParameterException("邮箱已经存在");
            }

            String md5Password;
            try {
                md5Password = EncryptUtil.getMD5DigestInString(userBase
                        .getPassword());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
            userBase.setPassword(md5Password);
            userBase.setDefaultUserValue();

            userBase.save();

            UserDetail userDetail = new UserDetail(userBase.getUserName(),
                    userBase.getUserName(), userBase.getEmail(),
                    UserDetail.Type.COMMON.name(), UserDetail.Gender.SECRECY.ordinal());

            userDetail.save();

            return true;
        }
        return false;
    }


    public static UserDetail login(String userAccount, String password)
            throws OPFBaseException {

        UserDetail userDetail = new UserDetail();

        UserBase userBase = UserBase.login(userAccount, password);
        if (userBase != null) {
            userDetail = UserDetail.get(userBase.getUserName());
            return userDetail;
        }
        return null;
    }
}
