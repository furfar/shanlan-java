
package com.shanlan.opf.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.shanlan.opf.application.ServiceApplication;
import com.shanlan.opf.application.ServiceDetailApplication;
import com.shanlan.opf.application.dto.*;
import com.shanlan.opf.core.domain.Service;
import com.shanlan.opf.core.domain.ServiceDetail;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.querychannel.Page;

@Controller
@RequestMapping("/ServiceDetail")
public class ServiceDetailController {

    @Inject
    private ServiceDetailApplication serviceDetailApplication;
    @Inject
    private ServiceApplication serviceApplication;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(ServiceDetailDTO serviceDetailDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        serviceDetailApplication.saveServiceDetail(serviceDetailDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(ServiceDetailDTO serviceDetailDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        serviceDetailApplication.updateServiceDetail(serviceDetailDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(ServiceDetailDTO serviceDetailDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<ServiceDetailDTO> all = serviceDetailApplication.pageQueryServiceDetail(serviceDetailDTO, page, pagesize);
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
        serviceDetailApplication.removeServiceDetails(idArrs);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", serviceDetailApplication.getServiceDetail(id));
        return result;
    }


}
