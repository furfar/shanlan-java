
package com.shanlan.basic.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.dayatang.querychannel.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.basic.application.PhotoStyleApplication;
import com.shanlan.basic.application.dto.PhotoStyleDTO;

@Controller
@RequestMapping("/PhotoStyle")
public class PhotoStyleController {
		
	@Inject
	private PhotoStyleApplication photoStyleApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(PhotoStyleDTO photoStyleDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoStyleApplication.savePhotoStyle(photoStyleDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(PhotoStyleDTO photoStyleDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoStyleApplication.updatePhotoStyle(photoStyleDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(PhotoStyleDTO photoStyleDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<PhotoStyleDTO> all = photoStyleApplication.pageQueryPhotoStyle(photoStyleDTO, page, pagesize);
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
        photoStyleApplication.removePhotoStyles(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", photoStyleApplication.getPhotoStyle(id));
		return result;
	}
	
		
}
