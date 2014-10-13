
package com.shanlan.user.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.dayatang.querychannel.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.user.application.UserIntroductionApplication;
import com.shanlan.user.application.dto.UserIntroductionDTO;

@Controller
@RequestMapping("/UserIntroduction")
public class UserIntroductionController {
		
	@Inject
	private UserIntroductionApplication userIntroductionApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(UserIntroductionDTO userIntroductionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userIntroductionApplication.saveUserIntroduction(userIntroductionDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(UserIntroductionDTO userIntroductionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userIntroductionApplication.updateUserIntroduction(userIntroductionDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(UserIntroductionDTO userIntroductionDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<UserIntroductionDTO> all = userIntroductionApplication.pageQueryUserIntroduction(userIntroductionDTO, page, pagesize);
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
        userIntroductionApplication.removeUserIntroductions(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", userIntroductionApplication.getUserIntroduction(id));
		return result;
	}
	
		
}
