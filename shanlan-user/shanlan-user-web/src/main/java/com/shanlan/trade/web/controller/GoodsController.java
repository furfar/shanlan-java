
package com.shanlan.trade.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.dayatang.querychannel.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.trade.application.GoodsApplication;
import com.shanlan.trade.application.dto.GoodsDTO;

@Controller
@RequestMapping("/Goods")
public class GoodsController {
		
	@Inject
	private GoodsApplication goodsApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(GoodsDTO goodsDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		goodsApplication.saveGoods(goodsDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(GoodsDTO goodsDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		goodsApplication.updateGoods(goodsDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(GoodsDTO goodsDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<GoodsDTO> all = goodsApplication.pageQueryGoods(goodsDTO, page, pagesize);
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
        goodsApplication.removeGoodss(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", goodsApplication.getGoods(id));
		return result;
	}
	
		
}
