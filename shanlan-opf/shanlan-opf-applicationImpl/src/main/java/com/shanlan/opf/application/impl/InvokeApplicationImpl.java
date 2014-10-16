package com.shanlan.opf.application.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.photo.application.impl.PhotoApplicationImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestFormatException;
import com.shanlan.common.exception.sub.business.RequestMappingException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.common.util.ReflectionUtils;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.RequestDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.application.dto.UserBaseDTO;
import com.shanlan.opf.core.domain.Request;
import com.shanlan.opf.core.domain.Service;
import com.shanlan.opf.infra.helper.InvokeHelper;
import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.photo.application.dto.PhotoCollectionDTO;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.service.PhotoService;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.domain.UserIntroduction;

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
    private PhotoApplication photoApplication;


    public InvokeApplicationImpl() {
        if (photoApplication == null) {
            photoApplication = new PhotoApplicationImpl();
        }

    }

    @Override
    public SuccessResponseDTO invokeRemoteService(String serviceURI,
                                                  String param) {

        return InvokeHelper.getResponse(serviceURI, param);
    }

    @Override
    public SuccessResponseDTO invokeLocalService(String service, String param)
            throws Exception {

        Map<String, String> paramMap = JsonUtil.foJson(param,
                new TypeReference<Map<String, String>>() {
                }
        );
        String businessResult = "";
        if (service.equals("User.login")) {
            UserBase existUser = UserBase.login(paramMap.get("userName"),
                    paramMap.get("password"));
            businessResult = JsonUtil.toJson(existUser);
        } else if (service.equals("User.register")) {
            UserBase user = new UserBase(paramMap.get("userName"),
                    paramMap.get("password"), paramMap.get("nickName"),
                    paramMap.get("email"), Integer.parseInt(paramMap
                    .get("isValid"))
            );
            boolean result = UserBase.register(user);
            businessResult = JsonUtil.toJson(result);
        } else if (service.equals("User.getBaseInfoById")) {
            Integer id = Integer.parseInt(paramMap.get("id"));
            UserBase userBase = UserBase.get(UserBase.class, id);
            UserBaseDTO userBaseDTO = new UserBaseDTO();
            BeanUtils.copyProperties(userBaseDTO, userBase);
            businessResult = JsonUtil.toJson(userBaseDTO);
        } else if (service.equals("User.getBaseInfoByUserName")) {
            UserBase userBase = UserBase.findByUserName(paramMap
                    .get("userName"));
            UserBaseDTO userBaseDTO = new UserBaseDTO();
            BeanUtils.copyProperties(userBaseDTO, userBase);
            businessResult = JsonUtil.toJson(userBaseDTO);
        } else if (service.equals("Photo.getPhotoCollections")) {
            String userName = paramMap.get("userName");
            List<PhotoCollectionDTO> photoCollectionDTOs = photoApplication
                    .getPhotoCollections(userName);
            businessResult = JsonUtil.toJson(photoCollectionDTOs);
        } else if (service.equals("Photo.getPhotos")) {
            Integer photoCollectionId = Integer.parseInt(paramMap
                    .get("photoCollectionId"));
            List<Photo> photos = PhotoService.getPhotos(photoCollectionId);
            businessResult = JsonUtil.toJson(photos);
        } else if (service.equals("User.getIntroductions")) {
            String userName = paramMap.get("userName");
            List<UserIntroduction> userIntroductions = UserIntroduction
                    .findByUserName(userName);
            businessResult = JsonUtil.toJson(userIntroductions);
        }
        return new SuccessResponseDTO(businessResult);

    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BaseResponseDTO invokeService(RequestDTO requestDTO, String method) {

        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();

        // 第2步：判断调用类型，并检查访问者（包括身份验证和权限检查）
        // boolean userCheckResult = false;
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

            service = Service.getServiceByServiceNameAndVersion(
                    requestDTO.getService(), requestDTO.getV());

            if (!method.equals(service.getMethod())) {
                throw new RequestParameterException("The HTTP Method '"
                        + method + "' does not match the Method '"
                        + service.getMethod() + "' of '"
                        + service.getServiceName() + "' ");
            }

        } catch (OPFBaseException e) {
            return InvokeHelper.handleException(e);
        }

        // 第5步：服务转发

        if (ConstantNumber.IS_LOCAL_SERVICE_FALSE == service.getIsLocal()) {// 如果不是本地服务
            successResponseDTO = invokeRemoteService(service.getUrl(),
                    requestDTO.getParam());

        } else {
            try {
                successResponseDTO = invokeLocalService(
                        requestDTO.getService(), requestDTO.getParam());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return InvokeHelper.handleException(e);
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
