
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

import com.shanlan.basic.application.DistrictApplication;
import com.shanlan.basic.application.dto.DistrictDTO;

@Controller
@RequestMapping("/District")
public class DistrictController {
		
	@Inject
	private DistrictApplication districtApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(DistrictDTO districtDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		districtApplication.saveDistrict(districtDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(DistrictDTO districtDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		districtApplication.updateDistrict(districtDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(DistrictDTO districtDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<DistrictDTO> all = districtApplication.pageQueryDistrict(districtDTO, page, pagesize);
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
        districtApplication.removeDistricts(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", districtApplication.getDistrict(id));
		return result;
	}
	
		
}
