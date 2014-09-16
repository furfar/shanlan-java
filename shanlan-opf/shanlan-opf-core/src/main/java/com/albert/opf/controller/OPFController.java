package com.albert.opf.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.albert.opf.common.exception.OPFBaseException;
import com.albert.opf.common.exception.RequestParameterException;
import com.albert.opf.common.model.domain.request.Request;
import com.albert.opf.common.model.domain.response.SuccessResponse;
import com.albert.opf.common.utils.ConfigurationUtils;
import com.albert.opf.service.ServiceFacade;

@Controller
public class OPFController extends AbstractBaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(OPFController.class);

    boolean isRemoteCall = ConfigurationUtils.getInstance()
            .getBooleanConfiguration("isRemoteCall");

    @Autowired
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ModelAndView service(String request) {

        SuccessResponse successResponse = new SuccessResponse();

//        String message = I18NManager.getMessage("hello.world", "zh_CN");
//        System.out.println(message);

        if (logger.isDebugEnabled()) {
            logger.debug("client request is:" + request);
//            System.out.println(I18NManager.getMessage("hello.world", "en_US"));
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
//        if (super.isHTTPSAccess(requestObj)) {// 如果是通过HTTPS访问
//
//            try {
//                userCheckResult = serviceFacade.userService.checkUser(
//                        requestObj.getKey(), requestObj.getSecret(),
//                        requestObj.getService(), requestObj.getV());
//            } catch (RequestCheckingException e) {
//                return super.handleException(e);
//            }
//        } else {
//
//            try {
//                userCheckResult = serviceFacade.userService
//                        .checkUser(requestObj);
//            } catch (RequestCheckingException e) {
//                return super.handleException(e);
//            }
//
//        }

        // 第3步：访问次数和频率检查(通过InvokeTimesAndFrequencyInterceptor实现)


        // 第4步：服务映射
//        Service service = null;
//        try {
//
//            service = serviceFacade.userService
//                    .getServiceByServiceNameAndVersion(requestObj.getService(),
//                            requestObj.getV());
//
//        } catch (OPFBaseException e) {
//            return super.handleException(e);
//        }

        // 第5步：服务转发

//        if (service.getIsLocal()) {//如果是本地服务
//            successResponse = serviceFacade.invokeService.invokeRemoteService(
//                    service.getServiceURL(), requestObj.getParam());
//        } else {
            try {
				successResponse = serviceFacade.invokeService.invokeLocalService(requestObj.getService(),requestObj.getParam());
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				if (e instanceof OPFBaseException)  {
	                return super.handleException(e);
				}
			}

//        }

        ModelAndView resultMav = new ModelAndView("jsonView");
        resultMav.addObject("code","200");
        resultMav.addObject("data",successResponse.getBusinessResult());


        return resultMav;

    }

}
