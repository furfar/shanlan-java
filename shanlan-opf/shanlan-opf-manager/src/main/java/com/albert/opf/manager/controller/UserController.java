package com.albert.opf.manager.controller;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.albert.opf.common.constant.OPFConstants;
import com.albert.opf.common.exception.RequestAuthenticationException;
import com.albert.opf.common.exception.RequestAuthorizationException;
import com.albert.opf.common.exception.RequestCheckingException;
import com.albert.opf.common.exception.RequestFormatException;
import com.albert.opf.common.exception.RequestMappingException;
import com.albert.opf.common.model.domain.response.ErrorResponse;
import com.albert.opf.common.utils.JsonUtil;
import com.albert.opf.manager.service.UserService;

@Controller
public class UserController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user.get", method = RequestMethod.POST)
	public ModelAndView getUserByUserName(String param) {

		UserDetails user = null;

		if (logger.isDebugEnabled()) {
			logger.debug("client request is:" + param);
		}

		if (param != null) {
			user = userService.getUserByUsername(param);
		}

		ModelAndView resultMav = null;
		if (user != null) {
			resultMav = new ModelAndView("jsonView",
					OPFConstants.BUSINESS_RESULT, JsonUtil.toJson(user));
			return resultMav;
		} else {
			return handleException(new RequestCheckingException(
					"Identity information does not match"));
		}

	}

	@RequestMapping(value = "/user.get2", method = RequestMethod.POST)
	public ModelAndView getUserByUserNameAndPassword(String param) {

		User userInput = JsonUtil.foJson(param,
				new TypeReference<User>() {
				});

		UserDetails userServer = null;

		if (userInput != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("client request is:" + param);
			}

			userServer = userService.getUserByUsername(userInput.getUsername());
		}

		ModelAndView resultMav = null;

		if (userServer != null
				&& userInput.getPassword().equals(userServer.getPassword())) {

			resultMav = new ModelAndView("jsonView",
					OPFConstants.BUSINESS_RESULT, JsonUtil.toJson(userServer));

			return resultMav;
		} else {
			return handleException(new RequestCheckingException(
					"Identity information does not match"));
		}
	}

	private ModelAndView handleException(Exception e) {

		ModelAndView resultMav = new ModelAndView("jsonView");

		if (e instanceof RequestFormatException) {
			ErrorResponse errorResponse = new ErrorResponse(1234,
					e.getMessage());

			resultMav.addObject(OPFConstants.RESPONSE, errorResponse);
		}

		if (e instanceof RequestMappingException) {

			ErrorResponse errorResponse = new ErrorResponse(123, e.getMessage());

			resultMav.addObject(OPFConstants.RESPONSE, errorResponse);

		}

		if (e instanceof RequestAuthenticationException) {

			ErrorResponse errorResponse = new ErrorResponse(123,
					"username or password error, please check it. ");

			resultMav.addObject(OPFConstants.RESPONSE, errorResponse);
		}

		if (e instanceof RequestAuthorizationException) {

			ErrorResponse errorResponse = new ErrorResponse(123, e.getMessage());

			resultMav.addObject(OPFConstants.RESPONSE, errorResponse);
		}

		return resultMav;

	}

}
