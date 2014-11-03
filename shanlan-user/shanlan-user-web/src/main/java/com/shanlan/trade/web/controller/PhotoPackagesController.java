
package com.shanlan.trade.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.shanlan.trade.application.PhotoPackageApplication;
import com.shanlan.trade.application.dto.GoodsDTO;
import com.shanlan.trade.application.dto.PhotoPackageDTO;
import org.dayatang.querychannel.Page;
import org.openkoala.koala.auth.ss3adapter.AuthUserUtil;
import org.openkoala.koala.auth.ss3adapter.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/PhotoPackage")
public class PhotoPackagesController {

    @Inject
    private PhotoPackageApplication photoPackagesApplication;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(PhotoPackageDTO photoPackagesDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        photoPackagesApplication.savePhotoPackage(photoPackagesDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(PhotoPackageDTO photoPackagesDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        photoPackagesApplication.updatePhotoPackage(photoPackagesDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(GoodsDTO goodsDTO, @RequestParam int page, @RequestParam int pagesize) {
        String userName = AuthUserUtil.getLoginUserName();
        CustomUserDetails customUserDetails = AuthUserUtil.getLoginUser();
        List<String> roles = AuthUserUtil.getRolesByCurrentUser();
        Page<GoodsDTO> all = photoPackagesApplication.pageQueryPhotoPackages(goodsDTO, page, pagesize,
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
        photoPackagesApplication.removePhotoPackages(idArrs);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", photoPackagesApplication.getPhotoPackage(id));
        return result;
    }


}
