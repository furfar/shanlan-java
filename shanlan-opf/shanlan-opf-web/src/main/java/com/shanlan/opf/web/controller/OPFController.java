package com.shanlan.opf.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.constant.ConstantString;
import com.shanlan.opf.application.dto.*;
import com.shanlan.opf.core.domain.ErrorResponse;
import com.shanlan.opf.core.domain.Service;
import com.shanlan.opf.core.domain.SuccessResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.opf.application.InvokeApplication;
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

        Request requestObj = new Request();
        RequestDTO requestDTO = new RequestDTO();
        try {
            requestObj = super.parseRequestParameter(request);
            BeanUtils.copyProperties(requestDTO, requestObj);
        } catch (Exception e) {
            return handleException(e);
        }

        ModelAndView resultMav = new ModelAndView("jsonView");
        BaseResponseDTO baseResponseDTO = invokeApplication.invokeService(requestDTO);

        if (baseResponseDTO != null && ConstantString.FLAG_SUCCESS == baseResponseDTO.getFlag()) {
            successResponseDTO = (SuccessResponseDTO) baseResponseDTO;
            resultMav.addObject("code", "200");
            resultMav.addObject("data", successResponseDTO.getBusinessResult());
        } else if (baseResponseDTO != null && ConstantString.FLAG_FAILURE == baseResponseDTO.getFlag()) {
            ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) baseResponseDTO;
            resultMav.addObject("code", errorResponseDTO.getCode());
            resultMav.addObject("data", errorResponseDTO.getMessage());
        }
        return resultMav;

    }

}
