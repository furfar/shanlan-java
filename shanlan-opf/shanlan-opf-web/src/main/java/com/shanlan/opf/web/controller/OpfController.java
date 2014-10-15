package com.shanlan.opf.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;

@Controller
public class OpfController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(OpfController.class);

	@Inject
	private InvokeApplication invokeApplication;

	@RequestMapping(value = "/services", method = RequestMethod.POST)
	public ModelAndView service(String request) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("client request is:" + request);
//		}
//
//		BaseResponseDTO baseResponseDTO = invokeApplication
//				.invokeService(request);

		ModelAndView resultMav = new ModelAndView("jsonView");
//		if (baseResponseDTO != null
//				&& ConstantNumber.FLAG_SUCCESS == baseResponseDTO.getCode()) {
//			SuccessResponseDTO successResponseDTO = (SuccessResponseDTO) baseResponseDTO;
//			resultMav.addObject("code", "200");
//			resultMav.addObject("data", successResponseDTO.getData());
//		} else if (baseResponseDTO != null
//				&& ConstantNumber.FLAG_SUCCESS != baseResponseDTO.getCode()) {
//			ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) baseResponseDTO;
//			resultMav.addObject("code", errorResponseDTO.getCode());
//			resultMav.addObject("data", errorResponseDTO.getMessage());
//		}
		return resultMav;

	}

}
