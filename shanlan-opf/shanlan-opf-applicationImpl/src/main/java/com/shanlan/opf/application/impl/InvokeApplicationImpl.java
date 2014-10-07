package com.shanlan.opf.application.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.opf.application.dto.ServiceDTO;
import com.shanlan.opf.core.domain.Service;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.core.service.UserService;
import com.shanlan.opf.infra.InvokeHelper;
import com.shanlan.user.core.domain.User;

/**
 * @ClassName:InvokeApplicationImpl
 * @Description: 服务调用实现类
 * @Author Albert
 * @Date:2013-1-21 上午8:39:11
 * @Remarks:
 * @Version:V1.1
 */

@Named
@Transactional
public class InvokeApplicationImpl implements InvokeApplication {

    private static final Logger logger = Logger
            .getLogger(InvokeApplicationImpl.class);

    @Inject
    private UserService userService;

    @Override
    public SuccessResponseDTO invokeRemoteService(String serviceURI,
                                                  String param) {

        return InvokeHelper.getResponse(serviceURI, param);
    }

    @Override
    public SuccessResponseDTO invokeLocalService(String service, String param)
            throws OPFBaseException {

        Map<String, String> paramMap = JsonUtil.foJson(param,
                new TypeReference<Map<String, String>>() {
                }
        );

        if (service.equals("User.login")) {
            User existUser = userService.login(paramMap.get("userName"),
                    paramMap.get("password"));
            return new SuccessResponseDTO(JsonUtil.toJson(existUser));
        } else if (service.equals("User.register")) {
            User user = new User(paramMap.get("userName"),
                    paramMap.get("password"), paramMap.get("nickName"),
                    paramMap.get("email"), paramMap.get("city"),
                    Integer.parseInt(paramMap.get("isValid")));
            boolean result = userService.register(user);

            return new SuccessResponseDTO(JsonUtil.toJson(result));
        }
        return new SuccessResponseDTO(JsonUtil.toJson(false));

    }

    @Override
    public ServiceDTO getServiceByServiceNameAndVersion(String serviceName, String serviceVersion) throws OPFBaseException {

        Service service = Service.getServiceByServiceNameAndVersion(serviceName, serviceVersion);
        if (service != null) {
            ServiceDTO serviceDTO = new ServiceDTO();
            try {
                BeanUtils.copyProperties(serviceDTO, service);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
                throw new RequestParameterException(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage(), e);
                throw new RequestParameterException(e.getMessage());
            }
            return serviceDTO;
        } else {
            return null;
        }
    }

}
