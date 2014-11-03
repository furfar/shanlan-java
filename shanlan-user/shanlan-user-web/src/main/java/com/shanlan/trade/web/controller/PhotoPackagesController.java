
package com.shanlan.trade.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.shanlan.trade.application.PhotoPackageApplication;
import org.dayatang.querychannel.Page;
import org.openkoala.koala.auth.ss3adapter.AuthUserUtil;
import org.openkoala.koala.auth.ss3adapter.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.trade.application.dto.PhotoPackagesDTO;

@Controller
@RequestMapping("/PhotoPackages")
public class PhotoPackagesController {
		
	@Inject
	private PhotoPackageApplication photoPackagesApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(PhotoPackagesDTO photoPackagesDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoPackagesApplication.savePhotoPackages(photoPackagesDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(PhotoPackagesDTO photoPackagesDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoPackagesApplication.updatePhotoPackages(photoPackagesDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(PhotoPackagesDTO photoPackagesDTO, @RequestParam int page, @RequestParam int pagesize) {
        String userName = AuthUserUtil.getLoginUserName();
        CustomUserDetails customUserDetails = AuthUserUtil.getLoginUser();
        List<String> roles = AuthUserUtil.getRolesByCurrentUser();
		Page<PhotoPackagesDTO> all = photoPackagesApplication.pageQueryPhotoPackages(photoPackagesDTO, page, pagesize,
                userName,customUserDetails.isSuper(), roles);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] value = ids.split(",");
        Integer[] idArrs = new Integer[value.length];
        for (int i = 0; i < value.length; i ++) {
        	
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
		result.put("data", photoPackagesApplication.getPhotoPackages(id));
		return result;
	}
	
		
}
