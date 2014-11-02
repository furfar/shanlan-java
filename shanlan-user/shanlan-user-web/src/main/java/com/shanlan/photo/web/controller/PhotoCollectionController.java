package com.shanlan.photo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.shanlan.common.util.DateUtil;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.application.PhotoApplication;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.dayatang.querychannel.Page;
import org.openkoala.koala.auth.ss3adapter.AuthUserUtil;
import org.openkoala.koala.auth.ss3adapter.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.photo.application.PhotoCollectionApplication;
import com.shanlan.photo.application.dto.PhotoCollectionDTO;

@Controller
@RequestMapping("/PhotoCollection")
public class PhotoCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(PhotoCollectionController.class);
    @Inject
    private PhotoCollectionApplication photoCollectionApplication;

    @Inject
    private PhotoApplication photoApplication;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(PhotoCollectionDTO photoCollectionDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        String userName = AuthUserUtil.getLoginUserName();
        photoCollectionDTO.setCreator(userName);
        photoCollectionDTO.setCreatedAt(DateUtil.getNow(DateUtil.format_yyyyMMdd_HHmmss));
        photoCollectionApplication.savePhotoCollection(photoCollectionDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(PhotoCollectionDTO photoCollectionDTO, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        photoCollectionApplication.updatePhotoCollection(photoCollectionDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(PhotoCollectionDTO photoCollectionDTO,
                         @RequestParam int page, @RequestParam int pagesize) {
        String userName = AuthUserUtil.getLoginUserName();
        CustomUserDetails customUserDetails = AuthUserUtil.getLoginUser();
        List<String> roles = AuthUserUtil.getRolesByCurrentUser();
        Page<PhotoCollectionDTO> all = photoCollectionApplication
                .pageQueryPhotoCollection(photoCollectionDTO, page, pagesize,
                        userName, customUserDetails.isSuper(), roles);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam String ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] value = ids.split(",");
        Integer[] idArrs = new Integer[value.length];
        for (int i = 0; i < value.length; i++) {

            idArrs[i] = Integer.parseInt(value[i]);

        }
        photoCollectionApplication.removePhotoCollections(idArrs);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", photoCollectionApplication.getPhotoCollection(id));
        return result;
    }


    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public Map<String, Object> uploadPhoto(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String userName = AuthUserUtil.getLoginUserName();
        Integer photoCollectionId = Integer.parseInt(request.getParameter("id"));
        try {
            ServletFileUpload servletFileUpload = ImageUploadUtil
                    .getServletImageUpload(1, 5);

            List<FileItem> fileItemList = servletFileUpload
                    .parseRequest(request);// 在这一步的处理过程中，如果图片大小大于前面设置的值1M，会先存储到临时文件夹中，但处理完后会删除

            for (FileItem fileItem : fileItemList) {
                String originalFileName = fileItem.getName();
                long fileSize=fileItem.getSize();
                if (StringUtils.isBlank(originalFileName)) {//如果文件名为空，说明没有上传文件，则跳过。
                    continue;
                }
                String contentType = fileItem.getContentType();
                byte[] bytes = fileItem.get();

                boolean validateResult = ImageUploadUtil
                        .validateImageType(contentType);

                if (!validateResult) {
                    result.put("false", "暂时不支持" + originalFileName + "这种类型的文件,请重新上传");
                    return result;
                }
                photoApplication.uploadPhoto(originalFileName, bytes, contentType, userName, photoCollectionId,fileSize);
            }
            //照片上传成功后，更新照片集中照片数量
            photoCollectionApplication.updatePhotoCount(photoCollectionId);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.put("false", e.getMessage());
            return result;
        }
        result.put("result", "success");
        return result;
    }


}
