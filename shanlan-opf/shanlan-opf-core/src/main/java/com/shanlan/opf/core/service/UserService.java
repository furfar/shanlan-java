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


}
