package com.shanlan.opf.application.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.common.constant.ConstantString;
import com.shanlan.photo.application.PhotoCollectionApplication;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.trade.application.PhotoPackageApplication;
import com.shanlan.trade.application.TradeCommentApplication;
import com.shanlan.trade.application.dto.FrontTradeCommentDTO;
import com.shanlan.trade.application.dto.PhotoPackageDTO;
import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import com.shanlan.user.core.domain.UserDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dayatang.querychannel.Page;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.RequestDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.user.application.dto.UserBaseDTO;
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
public class InvokeApplicationImpl implements InvokeApplication {

    private static final Logger logger = Logger
            .getLogger(InvokeApplicationImpl.class);

    @Inject
    private PhotoApplication photoApplication;
    @Inject
    private PhotoCollectionApplication photoCollectionApplication;
    @Inject
    private PhotoPackageApplication photoPackagesApplication;
    @Inject
    private TradeCommentApplication tradeCommentApplication;
    @Inject
    private UserDetailApplication userDetailApplication;


    @Override
    public SuccessResponseDTO invokeRemoteService(String serviceURI,
                                                  String param) {

        return InvokeHelper.getResponse(serviceURI, param);
    }

    @Override
    public SuccessResponseDTO invokeLocalService(String service, String param, String userNameLogin, String sessionId)
            throws Exception {
        logger.info("Service:" + service + " Param:" + param);
        Map<String, String> paramMap = JSONObject.parseObject(param, Map.class);
        sessionId="123456";
        String businessResult = "";
        if (service.equals("User.login")) {
            UserDetailDTO userDetailDTO = userDetailApplication.login(paramMap.get("userAccount"),
                    paramMap.get("password"), sessionId);
            businessResult = JSONObject.toJSONString(userDetailDTO);
        } else if (service.equals("User.register")) {
            UserBaseDTO userBaseDTO = new UserBaseDTO(paramMap.get("userName"),
                    paramMap.get("password"), paramMap.get("email"));
            boolean result = userDetailApplication.register(userBaseDTO);
            businessResult = JSONObject.toJSONString(result);
        } else if (service.equals("User.getBaseInfoById")) {
            Integer id = Integer.parseInt(paramMap.get("id"));
            UserBase userBase = UserBase.get(UserBase.class, id);
            UserBaseDTO userBaseDTO = new UserBaseDTO();
            BeanUtils.copyProperties(userBaseDTO, userBase);
            businessResult = JSONObject.toJSONString(userBaseDTO);
        } else if (service.equals("User.getBaseInfoByUserName")) {
            String userName = paramMap.get("userName");
            UserDetail userDetail = UserDetail.get(userName);
            UserDetailDTO userDetailDTO = new UserDetailDTO();
            BeanUtils.copyProperties(userDetailDTO, userDetail);
            businessResult = JSONObject.toJSONString(userDetailDTO);
        } else if (service.equals("Photo.getPhotoCollections")) {
            String userName = paramMap.get("userName");
            List<PhotoCollectionDTO> photoCollectionDTOs = photoCollectionApplication
                    .listPhotoCollections(userName);
            businessResult = JSONObject.toJSONString(photoCollectionDTOs);
        } else if (service.equals("Photo.getPhotos")) {
            Integer photoCollectionId = Integer.parseInt(paramMap
                    .get("photoCollectionId"));
            List<Photo> photos = PhotoService.getPhotos(photoCollectionId);
            businessResult = JSONObject.toJSONString(photos);
        } else if (service.equals("User.getIntroductions")) {
            String userName = paramMap.get("userName");
            List<UserIntroduction> userIntroductions = UserIntroduction
                    .findByUserName(userName);
            businessResult = JSONObject.toJSONString(userIntroductions);
        } else if (service.equals("Trade.listPackages")) {
            String userName = paramMap.get("userName");
            String photoType = paramMap.get("type");
            List<PhotoPackageDTO> photoPackagesDTOs = photoPackagesApplication.listPackages(userName, photoType);
            businessResult = JSONObject.toJSONString(photoPackagesDTOs);
        } else if (service.equals("Trade.pageTradeComments")) {
            String sellerUserName = paramMap.get("userName");
            Integer currentPage = Integer.parseInt(paramMap.get("currentPage"));
            Integer pageSize = Integer.parseInt(paramMap.get("pageSize"));
            Page<FrontTradeCommentDTO> frontTradeCommentDTOPage = tradeCommentApplication.pageTradeComments(sellerUserName, currentPage, pageSize);
            businessResult = JSONObject.toJSONString(frontTradeCommentDTOPage);
        } else if (service.equals("Photo.listTradePhotos")) {
            Integer tradePhotoCollectionId = Integer.parseInt(paramMap.get("tradePhotoCollectionId"));
            List<PhotoDTO> photoDTOs = photoApplication.listTradePhotos(tradePhotoCollectionId);
            businessResult = JSONObject.toJSONString(photoDTOs);
        } else if (service.equals("User.cutAvatar")) {
            Integer x = Integer.parseInt(paramMap.get("x"));
            Integer y = Integer.parseInt(paramMap.get("y"));
            Integer w = Integer.parseInt(paramMap.get("w"));
            Integer h = Integer.parseInt(paramMap.get("h"));
            String storeFilePath = userDetailApplication.handleAvatar(x, y, w, h, userNameLogin, sessionId);
            businessResult = JSONObject.toJSONString(storeFilePath);
        }
        logger.info("Service:" + service + " Result:" + businessResult);
        return new SuccessResponseDTO(businessResult);
    }

    public BaseResponseDTO invokeService(String request, String method) {

        logger.info(request);
        RequestDTO requestDTO = new RequestDTO();
        try {
            requestDTO = InvokeHelper.parseRequestParameter(request);
        } catch (Exception e) {
            return InvokeHelper.handleException(e);
        }


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
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        String sessionId = requestDTO.getSessionId();
        try {

            service = Service.getServiceByServiceNameAndVersion(
                    requestDTO.getService(), requestDTO.getV());
            logger.info("Service:" + service.getServiceName() + " Request:" + request);
            if (!method.equals(service.getMethod())) {
                throw new RequestParameterException("The HTTP Method '"
                        + method + "' does not match the Method '"
                        + service.getMethod() + "' of '"
                        + service.getServiceName() + "' ");
            }
            if (ConstantNumber.NEED_LOGIN_SERVICE_TRUE.equals(service.getNeedLogin())) {//如果是需要用户登录的服务
                userDetailDTO = userDetailApplication.isLogin(ConstantString.REDIS_KEY_PREFIX_SESSION + sessionId);
            }

        } catch (Exception e) {
            return InvokeHelper.handleException(e);
        }

        // 第5步：服务转发


        try {
            successResponseDTO = invokeLocalService(
                    requestDTO.getService(), requestDTO.getParam(), userDetailDTO.getUserName(), sessionId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return InvokeHelper.handleException(e);
        }


        return successResponseDTO;
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
