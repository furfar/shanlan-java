package com.shanlan.opf.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.common.util.StringUtil;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.infra.helper.InvokeHelper;
import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory
            .getLogger(UploadController.class);

    @Inject
    private UserDetailApplication userDetailApplication;
//    @Inject
//    private PhotoApplication photoApplication;

    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(HttpServletRequest request) {
        try {

            String cookie = request.getHeader("Cookie");
            logger.info(cookie);
            String sessionId = StringUtil.getNodeJsSession(cookie);
            UserDetailDTO userDetailDTO = userDetailApplication.isLogin(sessionId);

            String storePath = "";
            ServletFileUpload servletFileUpload = ImageUploadUtil
                    .getServletImageUpload(1, 1);

            List<FileItem> fileItemList = servletFileUpload
                    .parseRequest(request);// 在这一步的处理过程中，如果图片大小大于前面设置的值1M，会先存储到临时文件夹中，但处理完后会删除
            for (FileItem fileItem : fileItemList) {
                String originalFileName = fileItem.getName();
                if (StringUtils.isBlank(originalFileName)) {//如果文件名为空，说明没有上传文件，则跳过。
                    continue;
                }
                String contentType = fileItem.getContentType();
                byte[] bytes = fileItem.get();
                storePath += userDetailApplication.uploadAvatar(originalFileName, bytes, contentType, userDetailDTO);
            }
            logger.info(storePath);
            return JSONObject.toJSONString(new SuccessResponseDTO(storePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JSONObject.toJSONString(InvokeHelper.handleException(e));
        }
    }
}
