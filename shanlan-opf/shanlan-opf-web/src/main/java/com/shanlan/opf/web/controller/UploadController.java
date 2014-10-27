package com.shanlan.opf.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.EncryptUtil;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.common.util.StringUtil;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.infra.helper.InvokeHelper;
import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory
            .getLogger(UploadController.class);

    @Inject
    private UserDetailApplication userDetailApplication;
    @Inject
    private PhotoApplication photoApplication;

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
                String contentType = fileItem.getContentType();
                byte[] bytes = fileItem.get();
                String imageMd5 = EncryptUtil.getMD5DigestInBytes(bytes);
                //通过md5值查询该图片是否已经上传过，如果已经上传过，则直接返回存储路径。
                Map<String, PhotoDTO> md5AndSelfMap = photoApplication.getMd5AndSelfMap(Collections.singletonList(imageMd5));
                PhotoDTO photoDTO = md5AndSelfMap.get(imageMd5);
                if (photoDTO != null) {
                    //如果文件名中含有占位符，则先替换它，否则将拿不到原图
                    String originalFilePath = photoDTO.getFilePath().replace(ImageUploadUtil.IMAGE_SIZE_PLACEHOLDER, "");
                    storePath += originalFilePath;
                } else {
                    String originalFileName = fileItem.getName();
                    boolean validateResult = ImageUploadUtil
                            .validateImageType(contentType);
                    if (validateResult) {
                        storePath += ImageUploadUtil.saveAvatarImage(
                                originalFileName, bytes);
                        PhotoDTO newPhotoDTO = new PhotoDTO(storePath, imageMd5);
                        newPhotoDTO = photoApplication.savePhoto(newPhotoDTO);
                        userDetailDTO.setPhotoId(newPhotoDTO.getId());
                        userDetailDTO.setPhotoPath(newPhotoDTO.getFilePath());
                        userDetailApplication.updateUser(userDetailDTO);
                    }
                }
            }
            return JSONObject.toJSONString(new SuccessResponseDTO(storePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JSONObject.toJSONString(InvokeHelper.handleException(e));
        }
    }
}
