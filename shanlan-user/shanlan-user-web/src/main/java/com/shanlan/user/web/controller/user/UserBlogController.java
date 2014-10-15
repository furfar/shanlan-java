
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

import com.shanlan.user.application.UserBlogApplication;
import com.shanlan.user.application.dto.UserBlogDTO;

@Controller
@RequestMapping("/UserBlog")
public class UserBlogController {
		
	@Inject
	private UserBlogApplication userBlogApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(UserBlogDTO userBlogDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userBlogApplication.saveUserBlog(userBlogDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(UserBlogDTO userBlogDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userBlogApplication.updateUserBlog(userBlogDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(UserBlogDTO userBlogDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<UserBlogDTO> all = userBlogApplication.pageQueryUserBlog(userBlogDTO, page, pagesize);
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
        userBlogApplication.removeUserBlogs(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", userBlogApplication.getUserBlog(id));
		return result;
	}
	
		
}
