package com.shanlan.opf.application.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.exception.sub.business.*;
import com.shanlan.common.util.ReflectionUtils;
import com.shanlan.opf.application.dto.*;
import com.shanlan.opf.core.domain.*;
import com.shanlan.user.core.domain.UserBase;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.common.util.JsonUtil;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.core.service.UserService;
import com.shanlan.opf.infra.InvokeHelper;

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
            UserBase existUser = UserBase.login(paramMap.get("userName"),
                    paramMap.get("password"));
            return new SuccessResponseDTO(JsonUtil.toJson(existUser));
        } else if (service.equals("User.register")) {
            UserBase user = new UserBase(paramMap.get("userName"),
                    paramMap.get("password"), paramMap.get("nickName"),
                    paramMap.get("email"), Integer.parseInt(paramMap
                    .get("isValid"))
            );
            boolean result = UserBase.register(user);

            return new SuccessResponseDTO(JsonUtil.toJson(result));
        }
        return new SuccessResponseDTO(JsonUtil.toJson(false));

    }




    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ServiceDTO getServiceByServiceNameAndVersion(String serviceName,
                                                        String serviceVersion) throws OPFBaseException {

        Service service = Service.getServiceByServiceNameAndVersion(
                serviceName, serviceVersion);
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

    public BaseResponseDTO invokeService(RequestDTO requestObj) {

        SuccessResponseDTO successResponseDTO=new SuccessResponseDTO();

        // 第2步：判断调用类型，并检查访问者（包括身份验证和权限检查）
        boolean userCheckResult = false;
        // if (super.isHTTPSAccess(requestObj)) {// 如果是通过HTTPS访问
        //
        // try {
        // userCheckResult = serviceFacade.userService.checkUser(
        // requestObj.getKey(), requestObj.getSecret(),
        // requestObj.getService(), requestObj.getV());
        // } catch (RequestCheckingException e) {
        // return super.handleException(e);
        // }
        // } else {
        //
        // try {
        // userCheckResult = serviceFacade.userService
        // .checkUser(requestObj);
        // } catch (RequestCheckingException e) {
        // return super.handleException(e);
        // }
        //
        // }

        // 第3步：访问次数和频率检查(通过InvokeTimesAndFrequencyInterceptor实现)

        // 第4步：服务映射
        Service service = null;
        try {

            service = Service
                    .getServiceByServiceNameAndVersion(requestObj.getService(),
                            requestObj.getV());

        } catch (OPFBaseException e) {
            return handleException(e);
        }

        // 第5步：服务转发

        if (ConstantNumber.IS_LOCAL_SERVICE_FALSE == service.getIsLocal()) {//如果不是本地服务
            successResponseDTO = invokeRemoteService(
                    service.getUrl(), requestObj.getParam());

        } else {
            try {
                successResponseDTO = invokeLocalService(
                        requestObj.getService(), requestObj.getParam());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return handleException(e);
            }

        }
        return successResponseDTO;
    }




    /**
     * 解析并检查传入的请求参数
     *
     * @param request
     * @return
     * @throws RequestParameterException
     */
    protected Request parseRequestParameter(String request)
            throws RequestParameterException {

        Request requestObj = new Request();

        try {

            requestObj = JSONObject.parseObject(request, Request.class);

        } catch (JSONException e) {
            throw new RequestFormatException(e.getMessage());
        }

        List<Field> fields = ReflectionUtils
                .getSelfAndDirectParentDeclaredFields(requestObj);

        for (Field field : fields) {// 通过“反射”检查所有的字段是否成功完成映射

            field.setAccessible(true); // 要想访问private字段，需要先将其Accessible属性设置为true
            Object fieldValue = null;
            try {
                fieldValue = field.get(requestObj);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
            if (fieldValue == null) { // 如果字段的值为null，说明JSON到Java对象的映射过程出现异常
                String fieldName = field.getName();

                throw new RequestMappingException("json key '" + fieldName
                        + "' does not exist, plesse check it.");
            }

        }

        return requestObj;

    }

    protected ErrorResponseDTO handleException(Exception e) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO();

        if (e instanceof RequestFormatException) {
            errorResponse.setCode("101");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestMappingException) {
            errorResponse.setCode("201");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestAuthenticationException) {
            errorResponse.setCode("301");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestAuthorizationException) {
            errorResponse.setCode("401");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestParameterException) {
            errorResponse.setCode("501");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof ServiceDisableException) {
            errorResponse.setCode("601");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestCheckingException) {
            errorResponse.setCode("601");
            errorResponse.setMessage(e.getMessage());
        }else{
            errorResponse.setCode("999");
            errorResponse.setMessage(e.getMessage());
        }

        return errorResponse;

    }

    /**
     * 判断是否是HTTPS访问
     *
     * @param request
     * @return
     */
    protected boolean isHTTPSAccess(Request request) {

        if (request != null
                && (!StringUtils.isEmpty(request.getSecret()) && StringUtils
                .isEmpty(request.getSign()))) {// 如果secret不为空,sign为空则是HTTPS访问
            return true;
        }

        return false;
    }

}
