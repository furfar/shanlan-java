package com.shanlan.opf.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;

/**Ø
 * Created by albertliu on 14/10/22.
 */
@Controller
public class OpfController {

	@Inject
	private InvokeApplication invokeApplication;

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ModelAndView invokeServiceWithGetMethod(String request) {

		BaseResponseDTO baseResponseDTO = invokeApplication.invokeService(
				request, RequestMethod.GET.name());

		return handleResponse(baseResponseDTO);
	}

	@RequestMapping(value = "/services", method = RequestMethod.POST)
	public ModelAndView invokeServiceWithPostMethod(String request) {

        BaseResponseDTO baseResponseDTO = invokeApplication.invokeService(
                request, RequestMethod.POST.name());

        return handleResponse(baseResponseDTO);
	}

	@RequestMapping(value = "/services", method = RequestMethod.PUT)
	public ModelAndView invokeServiceWithPutMethod(String request) {

        BaseResponseDTO baseResponseDTO = invokeApplication.invokeService(
                request, RequestMethod.PUT.name());

        return handleResponse(baseResponseDTO);
	}

	@RequestMapping(value = "/services", method = RequestMethod.DELETE)
	public ModelAndView invokeServiceWithDeleteMethod(String request) {

		BaseResponseDTO baseResponseDTO = invokeApplication.invokeService(
				request, RequestMethod.DELETE.name());

		return handleResponse(baseResponseDTO);
	}


	private ModelAndView handleResponse(BaseResponseDTO baseResponseDTO) {

		ModelAndView resultMav = new ModelAndView("jsonView");
		if (baseResponseDTO != null
				&& ConstantNumber.FLAG_SUCCESS == baseResponseDTO.getCode()) {
			SuccessResponseDTO successResponseDTO = (SuccessResponseDTO) baseResponseDTO;
			resultMav.addObject("code", 200);
			resultMav.addObject("data", successResponseDTO.getData());

		} else if (baseResponseDTO != null
				&& ConstantNumber.FLAG_SUCCESS != baseResponseDTO.getCode()) {
			ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) baseResponseDTO;
			resultMav.addObject("code", errorResponseDTO.getCode());
			resultMav.addObject("message", errorResponseDTO.getMessage());
		}
		return resultMav;
	}
}
