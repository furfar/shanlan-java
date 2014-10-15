package com.shanlan.opf.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.opf.application.InvokeApplication;
import com.shanlan.opf.application.dto.BaseResponseDTO;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.RequestDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.infra.helper.InvokeHelper;

@Controller
public class OpfServlet extends HttpServlet {

	/**
     *
     */
	private static final long serialVersionUID = 5287995267669591741L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private InvokeApplication invokeApplication;

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDTO requestDTO = null;
		try {
			requestDTO = handleRequest(req, resp);
		} catch (RequestParameterException e) {
			ErrorResponseDTO errorResponseDTO = InvokeHelper.handleException(e);
			resp.getOutputStream().write(
					JsonUtil.toJson(errorResponseDTO).getBytes());
		}

		BaseResponseDTO baseResponseDTO = invokeApplication
				.invokeService(requestDTO,req.getMethod());
		handleResponse(resp, baseResponseDTO);
	}

	// protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	//
	// String request = handleRequest(req);
	// BaseResponseDTO baseResponseDTO = invokeApplication
	// .invokeService(request);
	// handleResponse(resp, baseResponseDTO);
	// }
	//
	// protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	//
	// String request = handleRequest(req);
	//
	//
	//
	//
	// BaseResponseDTO baseResponseDTO = invokeApplication
	// .invokeService(request);
	//
	// handleResponse(resp, baseResponseDTO);
	//
	// }
	//
	// protected void doPut(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	//
	// String request = handleRequest(req);
	// BaseResponseDTO baseResponseDTO = invokeApplication
	// .invokeService(request);
	//
	// handleResponse(resp, baseResponseDTO);
	//
	// }
	//
	// protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// String request = handleRequest(req);
	// BaseResponseDTO baseResponseDTO = invokeApplication
	// .invokeService(request);
	//
	// handleResponse(resp, baseResponseDTO);
	// }

	private RequestDTO handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws RequestParameterException {
		String request = req.getParameter("request");

		if (logger.isDebugEnabled()) {
			logger.debug(request);
		}

		RequestDTO requestDTO = new RequestDTO();
		try {
			requestDTO = InvokeHelper.parseRequestParameter(request);
		} catch (Exception e) {
			throw new RequestParameterException(e.getMessage());
		}

		return requestDTO;
	}

	private void handleResponse(HttpServletResponse resp,
			BaseResponseDTO baseResponseDTO) throws IOException {
		resp.addHeader(ConstantString.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		resp.addHeader(ConstantString.ACCESS_CONTROL_ALLOW_METHODS, "*");
		resp.setCharacterEncoding(ConstantString.STRING_ENCODING_UTF8);
		resp.setContentType(ConstantString.APPLICATION_JSON);

		if (baseResponseDTO != null
				&& ConstantNumber.FLAG_SUCCESS == baseResponseDTO.getCode()) {
			SuccessResponseDTO successResponseDTO = (SuccessResponseDTO) baseResponseDTO;
			resp.getOutputStream().write(
					JsonUtil.toJson(successResponseDTO).getBytes());

		} else if (baseResponseDTO != null
				&& ConstantNumber.FLAG_SUCCESS != baseResponseDTO.getCode()) {
			ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) baseResponseDTO;

			resp.getOutputStream().write(
					JsonUtil.toJson(errorResponseDTO).getBytes());
		}
	}

	public void init(ServletConfig servletConfig) throws ServletException {
		ApplicationContext ctx = getApplicationContext(servletConfig);
		this.invokeApplication = ctx.getBean(InvokeApplication.class);
		if (this.invokeApplication == null) {
			logger.error("未找到" + InvokeApplication.class.getName() + "的Bean");
		}
	}

	private ApplicationContext getApplicationContext(ServletConfig servletConfig) {
		return (ApplicationContext) servletConfig
				.getServletContext()
				.getAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

}
