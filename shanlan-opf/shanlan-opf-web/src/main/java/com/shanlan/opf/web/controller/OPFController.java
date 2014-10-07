package com.shanlan.opf.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.opf.application.dto.ServiceDTO;
import com.shanlan.opf.core.domain.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.core.domain.Request;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OPFController extends AbstractBaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(OPFController.class);

    @Inject
    private InvokeApplication invokeApplication;

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ModelAndView service(String request) {

        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();

        if (logger.isDebugEnabled()) {
            logger.debug("client request is:" + request);
        }

        // 第1步：解析并检查传入的请求参数
        Request requestObj = new Request();
        try {
            requestObj = super.parseRequestParameter(request);
        } catch (RequestParameterException e) {
            return handleException(e);
        }

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
        ServiceDTO serviceDTO = null;
        try {

            serviceDTO = invokeApplication
                    .getServiceByServiceNameAndVersion(requestObj.getService(),
                            requestObj.getV());

        } catch (OPFBaseException e) {
            return super.handleException(e);
        }

        // 第5步：服务转发

        if (ConstantNumber.IS_LOCAL_SERVICE_FALSE==serviceDTO.getIsLocal()) {//如果不是本地服务
            successResponseDTO = invokeApplication.invokeRemoteService(
                    serviceDTO.getUrl(), requestObj.getParam());
        } else {
            try {
                successResponseDTO = invokeApplication.invokeLocalService(
                        requestObj.getService(), requestObj.getParam());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (e instanceof OPFBaseException) {
                    return super.handleException(e);
                }
            }

        }

        ModelAndView resultMav = new ModelAndView("jsonView");
        resultMav.addObject("code", "200");
        resultMav.addObject("data", successResponseDTO.getBusinessResult());

        return resultMav;

    }

}
