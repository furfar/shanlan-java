package com.shanlan.user.web.controller.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.dayatang.querychannel.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.user.application.dto.ServiceDTO;
import com.shanlan.user.application.service.ServiceApplication;

@Controller
@RequestMapping("/Service")
public class ServiceController {

	@Inject
	private ServiceApplication serviceApplication;

	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(ServiceDTO serviceDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		serviceApplication.saveService(serviceDTO);
		result.put("result", "success");
		return result;
	}

	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(ServiceDTO serviceDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		serviceApplication.updateService(serviceDTO);
		result.put("result", "success");
		return result;
	}

	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(ServiceDTO serviceDTO, @RequestParam int page,
			@RequestParam int pagesize) {
		Page<ServiceDTO> all = serviceApplication.pageQueryService(serviceDTO,
				page, pagesize);
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
		serviceApplication.removeServices(idArrs);
		result.put("result", "success");
		return result;
	}

	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", serviceApplication.getService(id));
		return result;
	}

}
