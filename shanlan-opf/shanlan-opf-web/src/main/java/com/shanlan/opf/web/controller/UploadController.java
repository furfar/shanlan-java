package com.shanlan.opf.web.controller;

import java.util.List;

import com.shanlan.common.exception.sub.business.OPFBaseException;
import com.shanlan.common.exception.sub.business.RequestAuthenticationException;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.common.util.StringUtil;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.infra.helper.InvokeHelper;
import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import com.shanlan.user.core.domain.UserDetail;
import com.shanlan.user.core.service.UserService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.common.util.FileUploadUtil;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory
            .getLogger(UploadController.class);

    @Inject
    private UserDetailApplication userDetailApplication;

    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(HttpServletRequest request) {
        try {
            UserDetailDTO userDetailDTO = isLogin(request);

            String storePath = "";
            ServletFileUpload servletFileUpload = FileUploadUtil
                    .getServletFileUpload(1, 2);

            List<FileItem> fileItemList = servletFileUpload
                    .parseRequest(request);// 在这一步的处理过程中，如果图片大小大于前面设置的值1M，会先存储到临时文件夹中，但处理完后会删除
            for (FileItem fileItem : fileItemList) {
                String contentType = fileItem.getContentType();
                byte[] bytes = fileItem.get();
                String originalFileName = fileItem.getName();
                boolean validateResult = FileUploadUtil
                        .validateImageType(contentType);
                if (validateResult) {
                    storePath += FileUploadUtil.saveAvatarImage(
                            originalFileName, bytes);
                }
            }
            return JsonUtil.toJson(new SuccessResponseDTO(storePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonUtil.toJson(InvokeHelper.handleException(e));
        }
    }

    // @RequestMapping("/uploadAvatar")
    // @ResponseBody
    // public String uploadAvatar(HttpServletRequest request) {
    //
    // // String cookie = request.getHeader("Cookie");
    // // logger.info(cookie);
    // // String redisKey = StringUtil.getNodeJsCookie(cookie);
    // // UserDetailDTO userDetailDTO = userDetailApplication.isLogin(redisKey);
    //
    // MultipartHttpServletRequest
    // multipartHttpServletRequest=(MultipartHttpServletRequest)request;
    // HttpSession httpSession = request.getSession();
    // System.out.println(httpSession.getId());
    //
    // String storePath = "";
    // ServletFileUpload servletFileUpload =
    // FileUploadUtil.getServletFileUpload(1, 10);
    // try {
    // List<FileItem> fileItemList =
    // servletFileUpload.parseRequest(request);//在这一步的处理过程中，如果图片大小大于前面设置的值1M，会先存储到临时文件夹中，但处理完后会删除
    // for (FileItem fileItem : fileItemList) {
    // storePath += FileUploadUtil.saveAvatarImage(fileItem);
    // }
    // } catch (Exception e) {
    // logger.error(e.getMessage(), e);
    // return JsonUtil.toJson(new ErrorResponseDTO(102, "图片上传失败"));
    // }
    // return JsonUtil.toJson(new SuccessResponseDTO(storePath));
    // }

    // @RequestMapping("/uploadAvatar")
    // @ResponseBody
    // public String uploadAvatar(String userName, MultipartHttpServletRequest
    // multipartHttpServletRequest) {
    // String uploadedFilePath = "";
    // try {
    // if (userName == null) {
    // userName = "mgcheng";
    // }
    // Map<String, MultipartFile> fileNameAndFileMap =
    // multipartHttpServletRequest.getFileMap();
    // for (MultipartFile multipartFile : fileNameAndFileMap.values()) {
    // boolean validateResult =
    // FileUploadUtil.validateImageType(multipartFile.getContentType());
    // if (validateResult) {
    // uploadedFilePath += FileUploadUtil.saveAvatarImage(userName,
    // multipartFile.getBytes());
    // }
    // }
    // } catch (Exception e) {
    // logger.error("upload header picture error : ", e);
    // }
    // return uploadedFilePath;
    // }


    private UserDetailDTO isLogin(HttpServletRequest request) throws RequestAuthenticationException {
        String cookie = request.getHeader("Cookie");
        logger.info(cookie);
        String redisKey = StringUtil.getNodeJsCookie(cookie);
        UserDetailDTO userDetailDTO = userDetailApplication.isLogin(redisKey);
        return userDetailDTO;
    }

}
