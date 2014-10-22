package com.shanlan.user.core.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.DateUtil;
import com.shanlan.common.util.SignUtils;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.domain.UserDetail;

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
				md5Password = SignUtils.getMD5DigestInString(userBase
						.getPassword());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return false;
			}
			userBase.setPassword(md5Password);
			userBase.setIsValid(1);
			userBase.setCreateDate(DateUtil.getNow(DateUtil.format1));
			userBase.setIdentityType(UserBase.Type.User.name());
            userBase.setABOLISH_DATE(ConstantString.ABOLISH_DATE);

			userBase.save();

			UserDetail userDetail = new UserDetail(userBase.getUserName(),
					userBase.getUserName(), userBase.getEmail(),
					UserDetail.Type.COMMON.name());

			userDetail.save();

			return true;
		}
		return false;
	}
}
