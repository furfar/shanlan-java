package com.shanlan.user.web.controller.user;

import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.util.FileUploadUtil;
import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.dayatang.querychannel.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
