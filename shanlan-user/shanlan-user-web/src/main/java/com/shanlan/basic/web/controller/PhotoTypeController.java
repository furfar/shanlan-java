
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

import com.shanlan.basic.application.PhotoTypeApplication;
import com.shanlan.basic.application.dto.PhotoTypeDTO;

@Controller
@RequestMapping("/PhotoType")
public class PhotoTypeController {
		
	@Inject
	private PhotoTypeApplication photoTypeApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(PhotoTypeDTO photoTypeDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoTypeApplication.savePhotoType(photoTypeDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(PhotoTypeDTO photoTypeDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		photoTypeApplication.updatePhotoType(photoTypeDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(PhotoTypeDTO photoTypeDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<PhotoTypeDTO> all = photoTypeApplication.pageQueryPhotoType(photoTypeDTO, page, pagesize);
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
        photoTypeApplication.removePhotoTypes(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", photoTypeApplication.getPhotoType(id));
		return result;
	}
	
		
}
