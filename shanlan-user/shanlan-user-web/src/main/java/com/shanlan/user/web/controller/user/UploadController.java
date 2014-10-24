package com.shanlan.user.web.controller.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shanlan.common.util.FileUploadUtil;

@Controller
@RequestMapping("/user")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);


    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(String userName, MultipartHttpServletRequest multipartHttpServletRequest) {
        String uploadedFilePath = "";
        try {
            if (userName == null) {
                userName = "mgcheng";
            }
            Map<String, MultipartFile> fileNameAndFileMap = multipartHttpServletRequest.getFileMap();
            for (MultipartFile multipartFile : fileNameAndFileMap.values()) {
                boolean validateResult = FileUploadUtil.validateImageType(multipartFile.getContentType());
                if (validateResult) {
                    uploadedFilePath += FileUploadUtil.saveAvatarImage(userName, multipartFile.getBytes());
                }
            }
        } catch (Exception e) {
            logger.error("upload header picture error : ", e);
        }
        return uploadedFilePath;
    }


}
