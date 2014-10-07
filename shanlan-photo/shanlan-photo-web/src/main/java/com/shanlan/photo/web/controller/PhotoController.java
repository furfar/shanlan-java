
package com.shanlan.photo.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.querychannel.Page;
import com.shanlan.shanlanphoto.application.photo.PhotoApplication;
import com.shanlan.shanlanphoto.application.dto.*;

@Controller
@RequestMapping("/Photo")
public class PhotoController {
		
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
        for (int i = 0; i < value.length; i ++) {
        	
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
