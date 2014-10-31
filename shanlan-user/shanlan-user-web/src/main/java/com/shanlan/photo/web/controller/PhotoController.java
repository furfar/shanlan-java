
package com.shanlan.photo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.common.util.StringUtil;
import com.shanlan.opf.core.domain.SuccessResponse;
import com.shanlan.user.application.dto.UserDetailDTO;
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

import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.photo.application.dto.PhotoDTO;

@Controller
@RequestMapping("/Photo")
public class PhotoController {
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);


    @Inject
    private PhotoApplication photoApplication;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(PhotoDTO photoDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        photoApplication.savePhoto(photoDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(PhotoDTO photoDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        photoApplication.updatePhoto(photoDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(PhotoDTO photoDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<PhotoDTO> all = photoApplication.pageQueryPhoto(photoDTO, page, pagesize);
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
        photoApplication.removePhotos(idArrs);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", photoApplication.getPhoto(id));
        return result;
    }


}
